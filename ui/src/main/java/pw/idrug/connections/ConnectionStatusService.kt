package pw.idrug.connections

import android.Manifest
import android.app.ForegroundServiceStartNotAllowedException
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pw.idrug.connections.activity.MainActivity
import pw.idrug.connections.backend.Tunnel
import pw.idrug.connections.model.ObservableTunnel
import pw.idrug.connections.model.TunnelManager
import pw.idrug.connections.util.applicationScope
import kotlin.math.abs

class ConnectionStatusService : Service() {

    private var isUpdateActive = true
    private val lastTrafficSamples = mutableMapOf<String, TrafficSample>()

    private data class TrafficSample(
        val totalRx: Long,
        val totalTx: Long,
        val timestampMs: Long
    )

    private enum class Stage {
        CONNECTING,
        CONNECTED_SINGLE,
        CONNECTED_MULTIPLE,
        DISCONNECTING,
        DISCONNECTED
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannelCompat.Builder(
            CONNECTION_STATUS_NOTIFICATION_CHANNEL_ID,
            NotificationManager.IMPORTANCE_LOW
        )
            .setName(getString(R.string.notification_channel_name))
            .setShowBadge(false)
            .build()
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }

    override fun onDestroy() {
        showDisconnectingNotification()
        isUpdateActive = false
        lastTrafficSamples.clear()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground()
        applicationScope.launch {
            while (isUpdateActive) {
                updateConnectionStatus()
                delay(STATUS_REFRESH_INTERVAL_MS)
            }
        }
        return START_STICKY
    }

    private fun createContentIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun createActionIntent(): PendingIntent {
        val intent = Intent(this, DisconnectTunnelsReceiver::class.java).apply {
            action = ACTION_SET_ALL_TUNNELS_DOWN
            putExtra(NotificationCompat.EXTRA_NOTIFICATION_ID, FOREGROUND_NOTIFICATION_ID)
        }
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private suspend fun updateConnectionStatus() {
        val manager = Application.getTunnelManager()
        val activeTunnels = manager.getTunnels().filter { it.state == Tunnel.State.UP }
        val activeNames = activeTunnels.map { it.name }.toSet()
        lastTrafficSamples.keys.retainAll(activeNames)

        val notification = when {
            activeTunnels.size == 1 -> {
                val tunnel = activeTunnels.first()
                createSingleTunnelNotification(tunnel, manager)
            }

            activeTunnels.size > 1 -> createMultipleTunnelNotification(activeTunnels)
            else -> createDisconnectedNotification()
        }

        showNotification(notification)
    }

    private suspend fun createSingleTunnelNotification(
        tunnel: ObservableTunnel,
        manager: TunnelManager
    ): Notification {
        val contentText = resolveSpeedText(tunnel, manager)
            ?: getString(R.string.notification_text_connected_default)
        return buildStatusNotification(
            stage = Stage.CONNECTED_SINGLE,
            title = getString(R.string.notification_title_connected_to, tunnel.name),
            contentText = contentText,
            includeDisconnectAction = true,
            statusChipText = null
        )
    }

    private suspend fun resolveSpeedText(
        tunnel: ObservableTunnel,
        manager: TunnelManager
    ): CharSequence? {
        return runCatching { manager.getTunnelStatistics(tunnel) }.getOrNull()?.let { stats ->
            val totalRx = stats.totalRx()
            val totalTx = stats.totalTx()
            val now = SystemClock.elapsedRealtime()
            val tunnelName = tunnel.name
            val previous = lastTrafficSamples[tunnelName]
            lastTrafficSamples[tunnelName] = TrafficSample(
                totalRx = totalRx,
                totalTx = totalTx,
                timestampMs = now
            )
            if (previous == null) return null
            val elapsed = now - previous.timestampMs
            if (elapsed <= 0) return null
            val rxDiff = totalRx - previous.totalRx
            val txDiff = totalTx - previous.totalTx
            if (rxDiff < 0 || txDiff < 0) return null
            val rxRate = rxDiff * 1000.0 / elapsed
            val txRate = txDiff * 1000.0 / elapsed
            getString(
                R.string.notification_text_speed,
                formatRate(txRate),
                formatRate(rxRate)
            )
        }
    }

    private fun createMultipleTunnelNotification(tunnels: List<ObservableTunnel>): Notification {
        val chipText = getString(R.string.notification_status_chip_multiple, tunnels.size)
        return buildStatusNotification(
            stage = Stage.CONNECTED_MULTIPLE,
            title = getString(R.string.notification_title_connected),
            contentText = getString(R.string.notification_text_tunnels_count, tunnels.size),
            includeDisconnectAction = true,
            statusChipText = chipText
        )
    }

    private fun createConnectingNotification(): Notification {
        return buildStatusNotification(
            stage = Stage.CONNECTING,
            title = getString(R.string.notification_title_connecting),
            contentText = null,
            includeDisconnectAction = false,
            statusChipText = getString(R.string.notification_status_chip_connecting)
        )
    }

    private fun createDisconnectedNotification(): Notification {
        lastTrafficSamples.clear()
        return buildStatusNotification(
            stage = Stage.DISCONNECTED,
            title = getString(R.string.notification_title_disconnected),
            contentText = null,
            includeDisconnectAction = false,
            statusChipText = getString(R.string.notification_status_chip_disconnected)
        )
    }

    private fun showDisconnectingNotification() {
        val notification = buildStatusNotification(
            stage = Stage.DISCONNECTING,
            title = getString(R.string.notification_title_disconnecting),
            contentText = null,
            includeDisconnectAction = false,
            statusChipText = getString(R.string.notification_status_chip_disconnecting),
            timeoutAfterMs = DISCONNECTING_TIMEOUT_MS
        )
        showNotification(notification)
    }

    private fun showNotification(notification: Notification) {
        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@ConnectionStatusService,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@with
            }
            notify(FOREGROUND_NOTIFICATION_ID, notification)
        }
    }

    private fun buildStatusNotification(
        stage: Stage,
        title: CharSequence,
        contentText: CharSequence?,
        includeDisconnectAction: Boolean,
        statusChipText: CharSequence?,
        timeoutAfterMs: Long? = null
    ): Notification {
        val builder = NotificationCompat.Builder(this, CONNECTION_STATUS_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_icon_notification)
            .setContentTitle(title)
            .setContentIntent(createContentIntent())
            .setOngoing(true)
            .setLocalOnly(true)
            .setSilent(true)
            .setOnlyAlertOnce(true)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setRequestPromotedOngoing(true)
            .setShowWhen(false)

        if (!contentText.isNullOrBlank()) {
            builder.setContentText(contentText)
        }

        val shortText = statusChipText.sanitizedChip() ?: stage.defaultChipText()
        shortText?.let { builder.setShortCriticalText(it.toString()) }

        if (!contentText.isNullOrBlank()) {
            builder.setStyle(NotificationCompat.BigTextStyle().bigText(contentText))
        }

        if (includeDisconnectAction) {
            builder.addAction(
                NotificationCompat.Action.Builder(
                    null,
                    getString(R.string.notification_action_disconnect),
                    createActionIntent()
                ).build()
            )
        }

        timeoutAfterMs?.let(builder::setTimeoutAfter)

        return builder.build()
    }

    private fun CharSequence?.sanitizedChip(): CharSequence? {
        if (this.isNullOrBlank()) return null
        val condensed = this.toString()
            .replace("""\s+""".toRegex(), "")
            .take(MAX_STATUS_CHIP_CHARS)
        return condensed.ifBlank { null }
    }

    private fun Stage.defaultChipText(): CharSequence? {
        return when (this) {
            Stage.CONNECTING -> getString(R.string.notification_status_chip_connecting)
            Stage.CONNECTED_SINGLE -> getString(R.string.notification_status_chip_online)
            Stage.CONNECTED_MULTIPLE -> null // expect explicit override with count
            Stage.DISCONNECTING -> getString(R.string.notification_status_chip_disconnecting)
            Stage.DISCONNECTED -> getString(R.string.notification_status_chip_disconnected)
        }
    }


    private fun formatRate(bytesPerSecond: Double): String {
        val magnitude = abs(bytesPerSecond)
        return when {
            magnitude >= 1024.0 * 1024.0 * 1024.0 ->
                getString(R.string.transfer_rate_gib, magnitude / (1024.0 * 1024.0 * 1024.0))
            magnitude >= 1024.0 * 1024.0 ->
                getString(R.string.transfer_rate_mib, magnitude / (1024.0 * 1024.0))
            magnitude >= 1024.0 ->
                getString(R.string.transfer_rate_kib, magnitude / 1024.0)
            else ->
                getString(R.string.transfer_rate_bytes, magnitude)
        }
    }


    private fun startForeground() {
        try {
            ServiceCompat.startForeground(
                this,
                FOREGROUND_NOTIFICATION_ID,
                createConnectingNotification(),
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                } else {
                    0
                }
            )
        } catch (e: Exception) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                e is ForegroundServiceStartNotAllowedException
            ) {
                Log.e(TAG, "Failed to start foreground service", e)
            }
        }
    }

    companion object {
        private const val TAG = "iDrugConnections/ConnectionStatusService"
        private const val CONNECTION_STATUS_NOTIFICATION_CHANNEL_ID = "connection_status"
        private const val FOREGROUND_NOTIFICATION_ID = 1
        private const val ACTION_SET_ALL_TUNNELS_DOWN =
            "pw.idrug.connections.action.SET_ALL_TUNNELS_DOWN"
        private const val STATUS_REFRESH_INTERVAL_MS = 1_000L
        private const val DISCONNECTING_TIMEOUT_MS = 500L
        private const val MAX_STATUS_CHIP_CHARS = 7
    }
}
