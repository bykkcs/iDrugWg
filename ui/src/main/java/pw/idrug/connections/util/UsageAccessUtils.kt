package pw.idrug.connections.util

import android.app.Activity
import android.app.AppOpsManager
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Process
import android.provider.Settings

object UsageAccessUtils {
    private const val FOREGROUND_LOOKBACK_MS = 10_000L

    fun hasUsageAccess(context: Context): Boolean {
        val appOps = context.getSystemService(AppOpsManager::class.java) ?: return false
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            @Suppress("DEPRECATION")
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        } else {
            @Suppress("DEPRECATION")
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        }
        if (mode == AppOpsManager.MODE_ALLOWED) return true
        val usageStats = context.getSystemService(UsageStatsManager::class.java) ?: return false
        val endTime = System.currentTimeMillis()
        val beginTime = endTime - FOREGROUND_LOOKBACK_MS
        return try {
            val stats = usageStats.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime)
            !stats.isNullOrEmpty()
        } catch (_: SecurityException) {
            false
        }
    }

    fun openUsageAccessSettings(context: Context) {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    fun getForegroundPackage(context: Context): String? {
        val usageStats = context.getSystemService(UsageStatsManager::class.java) ?: return null
        val endTime = System.currentTimeMillis()
        val beginTime = endTime - FOREGROUND_LOOKBACK_MS
        val events = try {
            usageStats.queryEvents(beginTime, endTime)
        } catch (_: SecurityException) {
            return null
        }
        val event = UsageEvents.Event()
        var latestPackage: String? = null
        var latestTimestamp = 0L
        while (events != null && events.hasNextEvent()) {
            events.getNextEvent(event)
            val type = event.eventType
            val isForegroundEvent = when (type) {
                UsageEvents.Event.ACTIVITY_RESUMED -> true
                else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    @Suppress("DEPRECATION")
                    type == UsageEvents.Event.MOVE_TO_FOREGROUND
                } else false
            }
            if (isForegroundEvent && event.timeStamp >= latestTimestamp && !event.packageName.isNullOrEmpty()) {
                latestTimestamp = event.timeStamp
                latestPackage = event.packageName
            }
        }
        if (latestPackage != null) return latestPackage

        val stats = try {
            usageStats.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime)
        } catch (_: SecurityException) {
            null
        }
        if (!stats.isNullOrEmpty()) {
            return stats.maxByOrNull { it.lastTimeUsed }?.packageName
        }
        return null
    }
}
