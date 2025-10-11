package pw.idrug.connections.speedtest

data class SpeedTestPingResult(
    val rttMillis: Long,
    val pong: Boolean,
    val serverTimestamp: Long?
)

data class SpeedTestDownloadResult(
    val requestedBytes: Long,
    val bytesRead: Long,
    val durationMillis: Long,
    val throughputMbps: Double,
    val contentEncoding: String?
)

data class SpeedTestUploadResult(
    val requestedBytes: Long,
    val bytesSent: Long,
    val receivedBytes: Long?,
    val durationMillis: Long,
    val throughputMbps: Double
)

data class SpeedTestMeta(
    val version: String?,
    val limits: Map<String, Any?>
)

class SpeedTestException(message: String, cause: Throwable? = null) : Exception(message, cause)
