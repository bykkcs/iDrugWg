package pw.idrug.connections.speedtest

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

internal object SpeedTestHttpClient {
    private val apiKeyProviderRef = AtomicReference<() -> String?>({ null })

    var baseUrl: String = "https://idrug.pw"
        private set

    private val acceptEncodingInterceptor = Interceptor { chain ->
        val request = chain.request()
        val builder = request.newBuilder()
            .header("Accept-Encoding", "identity")
        apiKeyProviderRef.get()?.invoke()?.let { key ->
            if (key.isNotBlank()) {
                builder.header("X-API-Key", key)
            }
        }
        chain.proceed(builder.build())
    }

    private val clientRef = AtomicReference(buildClient(enableLogging = false))

    val client: OkHttpClient
        get() = clientRef.get()

    fun configure(
        baseUrl: String = this.baseUrl,
        apiKeyProvider: (() -> String?)? = null,
        enableLogging: Boolean = false
    ) {
        this.baseUrl = baseUrl.trimEnd('/')
        apiKeyProviderRef.set(apiKeyProvider ?: { null })
        clientRef.set(buildClient(enableLogging))
    }

    fun newRequestBuilder(path: String): Request.Builder {
        val sanitized = path.removePrefix("/")
        val url = "$baseUrl/$sanitized"
        return Request.Builder().url(url)
    }

    private fun buildClient(enableLogging: Boolean): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .callTimeout(0, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(acceptEncodingInterceptor)
        if (enableLogging) {
            builder.addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            )
        }
        return builder.build()
    }
}
