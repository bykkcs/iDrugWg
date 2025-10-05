package pw.idrug.connections.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import pw.idrug.connections.R
import pw.idrug.connections.di.UpdateModules
import pw.idrug.connections.notification.UpdateNotifier

class UpdateActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val action = intent.action ?: return
        val updateManager = UpdateModules.provideUpdateManager(context)
        val meta = updateManager.getPendingUpdate()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.cancel(UpdateNotifier.NOTIFICATION_ID)
        when (action) {
            ACTION_DOWNLOAD -> {
                if (meta == null) {
                    Toast.makeText(context, R.string.update_no_metadata, Toast.LENGTH_LONG).show()
                    return
                }
                val installer = UpdateModules.provideApkInstaller(context)
                val result = installer.download(meta)
                result.onFailure { error ->
                    Toast.makeText(
                        context,
                        context.getString(R.string.update_download_failed_with_reason, error.localizedMessage ?: error.javaClass.simpleName),
                        Toast.LENGTH_LONG
                    ).show()
                }
                result.onSuccess {
                    Toast.makeText(context, R.string.update_download_started, Toast.LENGTH_SHORT).show()
                }
            }
            ACTION_IGNORE -> {
                if (meta != null) {
                    updateManager.ignore(meta.versionCode)
                    Toast.makeText(context, R.string.update_ignored, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val ACTION_DOWNLOAD = "pw.idrug.connections.action.UPDATE_DOWNLOAD"
        const val ACTION_IGNORE = "pw.idrug.connections.action.UPDATE_IGNORE"
    }
}
