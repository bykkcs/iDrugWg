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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pw.idrug.connections.activity.MainActivity
import org.amnezia.awg.backend.Tunnel
import org.amnezia.awg.config.Config
import pw.idrug.connections.model.ObservableTunnel
import pw.idrug.connections.model.TunnelManager
import pw.idrug.connections.util.UsageAccessUtils
import pw.idrug.connections.util.UserKnobs
import pw.idrug.connections.util.applicationScope
import pw.idrug.connections.viewmodel.ConfigProxy
import pw.idrug.connections.viewmodel.InterfaceProxy
import kotlin.math.abs

class ConnectionStatusService : Service() {

    private var isUpdateActive = true
    private val lastTrafficSamples = mutableMapOf<String, TrafficSample>()
    private val cachedConfigs = mutableMapOf<String, Config>()
    private val appLabelCache = mutableMapOf<String, String>()
    private val selectableAppsCache = mutableMapOf<String, Boolean>()
    private var liveUsageChipEnabled = false
    private var liveUsagePreferenceJob: Job? = null
    private var statusJob: Job? = null
    private var lastUsageStatus: UsageStatus? = null
    private val reconfigInProgress = mutableSetOf<String>()

    private data class TrafficSample(
        val totalRx: Long,
        val totalTx: Long,
        val timestampMs: Long
    )

    private data class UsageStatus(
        val tunnelName: String,
        val packageName: String,
        val appLabel: String?,
        val isRouted: Boolean
    ) {
        val displayName: String?
            get() = appLabel?.takeUnless { it.isBlank() }
    }

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
        liveUsagePreferenceJob = applicationScope.launch {
            UserKnobs.liveUsageChip.collect { liveUsageChipEnabled = it }
        }
    }

    override fun onDestroy() {
        showDisconnectingNotification()
        isUpdateActive = false
        lastTrafficSamples.clear()
        cachedConfigs.clear()
        liveUsagePreferenceJob?.cancel()
        liveUsagePreferenceJob = null
        statusJob?.cancel()
        statusJob = null
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_INCLUDE_APP -> {
                val tunnelName = intent.getStringExtra(EXTRA_TUNNEL_NAME)
                val packageName = intent.getStringExtra(EXTRA_PACKAGE_NAME)
                if (tunnelName != null && packageName != null) {
                    applyUsagePreference(tunnelName, packageName, include = true)
                }
                return START_STICKY
            }

            ACTION_EXCLUDE_APP -> {
                val tunnelName = intent.getStringExtra(EXTRA_TUNNEL_NAME)
                val packageName = intent.getStringExtra(EXTRA_PACKAGE_NAME)
                if (tunnelName != null && packageName != null) {
                    applyUsagePreference(tunnelName, packageName, include = false)
                }
                return START_STICKY
            }
        }

        startForeground()
        if (statusJob?.isActive != true) {
            statusJob = applicationScope.launch {
                while (isUpdateActive) {
                    updateConnectionStatus()
                    delay(STATUS_REFRESH_INTERVAL_MS)
                }
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

    private fun createUsageActionIntent(
        action: String,
        tunnelName: String,
        packageName: String
    ): PendingIntent {
        val intent = Intent(this, ConnectionStatusService::class.java).apply {
            this.action = action
            putExtra(EXTRA_TUNNEL_NAME, tunnelName)
            putExtra(EXTRA_PACKAGE_NAME, packageName)
        }
        val requestCode = (31 * action.hashCode() + packageName.hashCode()).and(0x7FFFFFFF)
        return PendingIntent.getService(
            this,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private suspend fun updateConnectionStatus() {
        val manager = Application.getTunnelManager()
        val activeTunnels = manager.getTunnels().filter { it.state == Tunnel.State.UP }
        val activeNames = activeTunnels.map { it.name }.toMutableSet()
        synchronized(reconfigInProgress) {
            activeNames.addAll(reconfigInProgress)
        }
        lastTrafficSamples.keys.retainAll(activeNames)
        cachedConfigs.keys.retainAll(activeNames)

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

    private fun applyUsagePreference(tunnelName: String, packageName: String, include: Boolean) {
        if (!isSelectableApp(packageName)) {
            Log.d(TAG, "applyUsagePreference: package=$packageName is not selectable, skipping")
            return
        }
        applicationScope.launch {
            val manager = Application.getTunnelManager()
            val tunnel = manager.getTunnels()[tunnelName] ?: return@launch
            val currentConfig = runCatching { tunnel.getConfigAsync() }.getOrNull() ?: return@launch
            val proxy = ConfigProxy(currentConfig)
            val changed = updateInterfaceRouting(proxy.`interface`, packageName, include)
            Log.d(TAG, "applyUsagePreference: tunnel=$tunnelName pkg=$packageName include=$include changed=$changed")
            if (!changed) {
                updateLastUsageStatus(tunnelName, packageName, include)
                updateConnectionStatus()
                return@launch
            }
            val configsToApply = runCatching { proxy.buildConfigs() }.getOrNull() ?: return@launch
            synchronized(reconfigInProgress) { reconfigInProgress.add(tunnelName) }
            try {
                reconfigureTunnel(tunnel, configsToApply)
            } finally {
                synchronized(reconfigInProgress) { reconfigInProgress.remove(tunnelName) }
            }
            cachedConfigs[tunnelName] = configsToApply.awg
            updateLastUsageStatus(tunnelName, packageName, include)
            Log.d(TAG, "applyUsagePreference: reconfigured tunnel=$tunnelName pkg=$packageName routed=$include")
            updateConnectionStatus()
        }
    }

    private fun updateLastUsageStatus(tunnelName: String, packageName: String, include: Boolean) {
        lastUsageStatus = lastUsageStatus
            ?.takeIf { it.tunnelName == tunnelName && it.packageName == packageName }
            ?.copy(isRouted = include)
            ?: lastUsageStatus
    }

    private fun updateInterfaceRouting(
        iface: InterfaceProxy,
        packageName: String,
        include: Boolean
    ): Boolean {
        val includes = iface.includedApplications
        val excludes = iface.excludedApplications
        val includeMode = includes.isNotEmpty()
        val excludeMode = excludes.isNotEmpty()

        return when {
            includeMode -> {
                if (include) {
                    if (includes.contains(packageName)) false else {
                        includes.add(packageName)
                        true
                    }
                } else {
                    includes.remove(packageName)
                }
            }

            excludeMode -> {
                if (include) {
                    excludes.remove(packageName)
                } else {
                    if (excludes.contains(packageName)) false else {
                        excludes.add(packageName)
                        true
                    }
                }
            }

            include -> false

            else -> {
                if (excludes.contains(packageName)) false else {
                    excludes.add(packageName)
                    true
                }
            }
        }
    }

    private fun isSelectableApp(packageName: String): Boolean {
        selectableAppsCache[packageName]?.let { return it }
        val pm = packageManager
        val hasLauncher = pm.getLaunchIntentForPackage(packageName) != null
        val hasInternetPermission =
            pm.checkPermission(Manifest.permission.INTERNET, packageName) == PackageManager.PERMISSION_GRANTED
        return (hasLauncher && hasInternetPermission).also { selectableAppsCache[packageName] = it }
    }

    private suspend fun createSingleTunnelNotification(
        tunnel: ObservableTunnel,
        manager: TunnelManager
    ): Notification {
        val usageStatus = resolveUsageStatus(tunnel)
        val pausedAppName = usageStatus?.displayName
        val contentText = when {
            usageStatus != null && !usageStatus.isRouted && !pausedAppName.isNullOrBlank() ->
                getString(R.string.notification_text_paused, pausedAppName)
            else -> resolveSpeedText(tunnel, manager)
                ?: getString(R.string.notification_text_connected_default)
        }
        val statusChip = when {
            usageStatus == null -> null
            usageStatus.isRouted -> getString(R.string.notification_status_chip_online)
            else -> getString(R.string.notification_status_chip_offline)
        }
        return buildStatusNotification(
            stage = Stage.CONNECTED_SINGLE,
            title = getString(R.string.notification_title_connected_to, tunnel.name),
            contentText = contentText,
            includeDisconnectAction = true,
            statusChipText = statusChip,
            usageStatus = usageStatus,
            tunnelName = tunnel.name
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
        lastUsageStatus = null
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
        cachedConfigs.clear()
        lastUsageStatus = null
        return buildStatusNotification(
            stage = Stage.DISCONNECTED,
            title = getString(R.string.notification_title_disconnected),
            contentText = null,
            includeDisconnectAction = false,
            statusChipText = getString(R.string.notification_status_chip_disconnected)
        )
    }

    private suspend fun resolveUsageStatus(tunnel: ObservableTunnel): UsageStatus? {
        if (!liveUsageChipEnabled) {
            lastUsageStatus = null
            return null
        }
        if (!UsageAccessUtils.hasUsageAccess(this)) {
            lastUsageStatus = null
            return null
        }
        val config = cachedConfigs[tunnel.name] ?: run {
            runCatching { tunnel.getConfigAsync() }.getOrNull()?.also { cachedConfigs[tunnel.name] = it }
        } ?: return null
        val iface = config.getInterface()
        val includes = iface.includedApplications
        val excludes = iface.excludedApplications
        if (includes.isEmpty() && excludes.isEmpty()) {
            lastUsageStatus = null
            return null
        }
        val foregroundPackage = UsageAccessUtils.getForegroundPackage(this)
        if (foregroundPackage.isNullOrEmpty()) {
            lastUsageStatus = null
            return null
        }
        if (!isSelectableApp(foregroundPackage)) {
            lastUsageStatus = null
            return null
        }
        val appLabel = resolveAppLabel(foregroundPackage)
        if (appLabel.isNullOrBlank()) {
            lastUsageStatus = null
            return null
        }
        val isRouted = when {
            includes.isNotEmpty() -> includes.contains(foregroundPackage)
            excludes.isNotEmpty() -> !excludes.contains(foregroundPackage)
            else -> true
        }
        val usageStatus = UsageStatus(
            tunnelName = tunnel.name,
            packageName = foregroundPackage,
            appLabel = appLabel,
            isRouted = isRouted
        )
        lastUsageStatus = usageStatus
        return usageStatus
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
        timeoutAfterMs: Long? = null,
        usageStatus: UsageStatus? = null,
        tunnelName: String? = null
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

        if (usageStatus != null && tunnelName != null) {
            val actionLabel = if (usageStatus.isRouted) {
                R.string.notification_action_exclude_app
            } else {
                R.string.notification_action_include_app
            }
            val actionIntent = createUsageActionIntent(
                if (usageStatus.isRouted) ACTION_EXCLUDE_APP else ACTION_INCLUDE_APP,
                tunnelName,
                usageStatus.packageName
            )
            builder.addAction(
                NotificationCompat.Action.Builder(
                    null,
                    getString(actionLabel),
                    actionIntent
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

    private fun resolveAppLabel(packageName: String): String? {
        appLabelCache[packageName]?.let { return it }
        return runCatching {
            val pm = packageManager
            val appInfo = pm.getApplicationInfo(packageName, 0)
            pm.getApplicationLabel(appInfo).toString().also { appLabelCache[packageName] = it }
        }.getOrNull()
    }

    private suspend fun reconfigureTunnel(
        tunnel: ObservableTunnel,
        configs: ConfigProxy.BuiltConfigs
    ) {
        runCatching { tunnel.setConfigAsync(configs) }.getOrNull()
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
        private const val ACTION_INCLUDE_APP =
            "pw.idrug.connections.action.INCLUDE_APP"
        private const val ACTION_EXCLUDE_APP =
            "pw.idrug.connections.action.EXCLUDE_APP"
        private const val EXTRA_TUNNEL_NAME = "extra_tunnel_name"
        private const val EXTRA_PACKAGE_NAME = "extra_package_name"
        private const val STATUS_REFRESH_INTERVAL_MS = 1_000L
        private const val DISCONNECTING_TIMEOUT_MS = 500L
        private const val MAX_STATUS_CHIP_CHARS = 7
    }
}
