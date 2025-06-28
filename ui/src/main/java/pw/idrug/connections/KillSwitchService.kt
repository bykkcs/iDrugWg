package pw.idrug.connections

import android.app.Service
import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import pw.idrug.connections.util.KillSwitchPrefs

class KillSwitchService : VpnService() {
    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, KillSwitchNotification.create(this))
        val builder = Builder().setSession("KillSwitch")
        val whitelist = KillSwitchPrefs.getWhitelist() + packageName
        packageManager.getInstalledApplications(0)
            .map { it.packageName }
            .filter { it !in whitelist }
            .forEach {
                try { builder.addDisallowedApplication(it) } catch (_: Exception) {}
            }
        builder.addAddress("10.0.0.2", 32)
        vpnInterface?.close()
        vpnInterface = builder.establish()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        vpnInterface?.close()
        vpnInterface = null
        super.onDestroy()
    }
}
