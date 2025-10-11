package pw.idrug.connections.speedtest

import android.os.SystemClock
import java.io.IOException
import java.security.SecureRandom
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.BufferedSink
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SpeedTestService(
    private val client: OkHttpClient = SpeedTestHttpClient.client,
    private val rateLimiter: SpeedTestRateLimiter = SpeedTestRateLimiter(DEFAULT_RATE_LIMIT_INTERVAL_MS)
) {

    suspend fun ping(): SpeedTestPingResult {
        val request = SpeedTestHttpClient
            .newRequestBuilder("speed/ping")
            .get()
            .build()
        val start = SystemClock.elapsedRealtime()
        return executeCall(KEY_PING, request, rateLimit = false) { _, response ->
            val elapsed = max(0L, SystemClock.elapsedRealtime() - start)
            val json = response.useBodyAsJson()
            val pong = json.optBoolean("pong", false)
            val timestamp = json.optLong("ts", 0L).takeIf { it != 0L }
            SpeedTestPingResult(
                rttMillis = elapsed,
                pong = pong,
                serverTimestamp = timestamp
            )
        }
    }

    suspend fun download(sizeBytes: Long): SpeedTestDownloadResult {
        require(sizeBytes > 0) { "Download size must be positive" }
        val request = SpeedTestHttpClient
            .newRequestBuilder("speed/download?size=$sizeBytes")
            .get()
            .build()
        return executeCall(KEY_DOWNLOAD, request, rateLimit = true) { _, response ->
            parseDownload(sizeBytes, response)
        }
    }

    suspend fun upload(sizeBytes: Long): SpeedTestUploadResult {
        val boundedSize = sizeBytes.coerceIn(1L, MAX_UPLOAD_BYTES)
        val request = SpeedTestHttpClient
            .newRequestBuilder("speed/upload?discard=true")
            .post(RandomPayloadRequestBody(boundedSize))
            .build()
        val start = SystemClock.elapsedRealtime()
        return executeCall(KEY_UPLOAD, request, rateLimit = true) { _, response ->
            val elapsed = max(1L, SystemClock.elapsedRealtime() - start)
            parseUpload(boundedSize, elapsed, response)
        }
    }

    suspend fun meta(): SpeedTestMeta {
        val request = SpeedTestHttpClient
            .newRequestBuilder("speed/meta")
            .get()
            .build()
        return executeCall(KEY_META, request, rateLimit = false) { _, response ->
            val json = response.useBodyAsJson()
            val version = json.optString("version", null)
            val limits = mutableMapOf<String, Any?>()
            json.optJSONObject("limits")?.let { limitsJson ->
                limitsJson.keys().forEach { key ->
                    limits[key] = limitsJson.get(key)
                }
            }
            SpeedTestMeta(version = version, limits = limits)
        }
    }

    suspend fun healthz(): Boolean {
        val request = SpeedTestHttpClient
            .newRequestBuilder("healthz")
            .get()
            .build()
        return executeCall(KEY_HEALTH, request, rateLimit = false) { _, response ->
            response.close()
            true
        }
    }

    private suspend fun <T> executeCall(
        rateLimiterKey: String,
        request: Request,
        rateLimit: Boolean,
        handler: (Call, Response) -> T
    ): T {
        rateLimiter.awaitPermit(rateLimiterKey, rateLimit)
        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine { cont ->
                val call = client.newCall(request)
                cont.invokeOnCancellation { call.cancel() }
                try {
                    val response = call.execute()
                    response.use {
                        if (!it.isSuccessful) {
                            when (it.code) {
                                413 -> throw SpeedTestException("Server rejected upload (413 – payload too large). Try lowering upload size.")
                                429 -> throw SpeedTestException("Server rate limit reached (429). Please wait and retry.")
                                else -> throw SpeedTestException("HTTP ${it.code} for ${request.url}")
                            }
                        }
                        val result = handler(call, it)
                        cont.resume(result)
                    }
                } catch (io: IOException) {
                    if (call.isCanceled()) {
                        cont.resumeWithException(CancellationException("Speed test cancelled", io))
                    } else {
                        cont.resumeWithException(SpeedTestException("Network error for ${request.url}", io))
                    }
                } catch (other: Exception) {
                    cont.resumeWithException(other)
                }
            }
        }
    }

    private fun parseDownload(sizeBytes: Long, response: Response): SpeedTestDownloadResult {
        val contentEncoding = response.header("Content-Encoding")
        if (contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)) {
            throw SpeedTestException("Unexpected Content-Encoding: $contentEncoding")
        }
        val start = SystemClock.elapsedRealtime()
        var total = 0L
        val buffer = ByteArray(DOWNLOAD_BUFFER_BYTES)
        try {
            response.body?.byteStream()?.use { stream ->
                while (true) {
                    val read = stream.read(buffer)
                    if (read == -1) break
                    total += read
                }
            } ?: throw SpeedTestException("Missing response body for ${response.request.url}")
        } catch (io: IOException) {
            throw SpeedTestException("Failed during download stream", io)
        }
        val elapsed = max(1L, SystemClock.elapsedRealtime() - start)
        val throughput = computeThroughputMbps(total, elapsed)
        return SpeedTestDownloadResult(
            requestedBytes = sizeBytes,
            bytesRead = total,
            durationMillis = elapsed,
            throughputMbps = throughput,
            contentEncoding = contentEncoding
        )
    }

    private fun parseUpload(requestedSize: Long, elapsedMillis: Long, response: Response): SpeedTestUploadResult {
        val json = response.useBodyAsJson()
        val receivedBytes = json.optLong("received_bytes", -1L).takeIf { it >= 0 }
        val throughput = computeThroughputMbps(requestedSize, elapsedMillis)
        return SpeedTestUploadResult(
            requestedBytes = requestedSize,
            bytesSent = requestedSize,
            receivedBytes = receivedBytes,
            durationMillis = elapsedMillis,
            throughputMbps = throughput
        )
    }

    private fun computeThroughputMbps(bytes: Long, durationMillis: Long): Double {
        if (durationMillis <= 0) return 0.0
        val bits = bytes * 8.0
        val seconds = durationMillis / 1000.0
        if (seconds == 0.0) return 0.0
        val mbps = bits / seconds / 1_000_000.0
        return round(mbps * 100) / 100.0
    }

    private fun Response.useBodyAsJson(): JSONObject {
        val bodyString = body?.string() ?: throw SpeedTestException("Empty body for ${request.url}")
        return try {
            JSONObject(bodyString)
        } catch (ex: Exception) {
            throw SpeedTestException("Invalid JSON from ${request.url}", ex)
        }
    }

    private class RandomPayloadRequestBody(
        private val totalBytes: Long,
        private val chunkBytes: Int = DEFAULT_UPLOAD_CHUNK
    ) : RequestBody() {

        private val buffer: ByteArray = ByteArray(chunkBytes).also { SecureRandom().nextBytes(it) }
        private val contentLengthValue = totalBytes.coerceIn(1L, MAX_UPLOAD_BYTES)

        override fun contentType() = "application/octet-stream".toMediaType()

        override fun contentLength(): Long = contentLengthValue

        override fun writeTo(sink: BufferedSink) {
            var remaining = contentLengthValue
            while (remaining > 0) {
                val toWrite = min(remaining, buffer.size.toLong()).toInt()
                sink.write(buffer, 0, toWrite)
                remaining -= toWrite
            }
            sink.flush()
        }
    }

    companion object {
        private const val DEFAULT_RATE_LIMIT_INTERVAL_MS = 10_000L
        private const val DOWNLOAD_BUFFER_BYTES = 64 * 1024
        private const val DEFAULT_UPLOAD_CHUNK = 1 * 1024 * 1024
        private const val MAX_UPLOAD_BYTES = 32L * 1024 * 1024
        private const val KEY_PING = "ping"
        private const val KEY_DOWNLOAD = "download"
        private const val KEY_UPLOAD = "upload"
        private const val KEY_META = "meta"
        private const val KEY_HEALTH = "health"
    }
}
