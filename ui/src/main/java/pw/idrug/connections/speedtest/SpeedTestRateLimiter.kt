package pw.idrug.connections.speedtest

import android.os.SystemClock
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SpeedTestRateLimiter(private val minIntervalMillis: Long) {
    private val mutex = Mutex()
    private val lastTimestamps = mutableMapOf<String, Long>()

    suspend fun awaitPermit(key: String, rateLimit: Boolean) {
        if (!rateLimit) return
        mutex.withLock {
            val now = SystemClock.elapsedRealtime()
            val last = lastTimestamps[key] ?: 0L
            val wait = last + minIntervalMillis - now
            if (wait > 0) {
                delay(wait)
            }
            lastTimestamps[key] = SystemClock.elapsedRealtime()
        }
    }
}
