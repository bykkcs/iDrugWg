package pw.idrug.connections.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pw.idrug.connections.R
import pw.idrug.connections.data.UpdateMeta
import pw.idrug.connections.receiver.UpdateActionReceiver
import pw.idrug.connections.ui.settings.UpdateSettingsActivity

class UpdateNotifier(private val context: Context) {
    fun showUpdateAvailable(meta: UpdateMeta) {
        createChannelIfNeeded()
        val notificationManager = NotificationManagerCompat.from(context)

        val contentIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, UpdateSettingsActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val downloadIntent = PendingIntent.getBroadcast(
            context,
            1,
            Intent(context, UpdateActionReceiver::class.java).apply { action = UpdateActionReceiver.ACTION_DOWNLOAD },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val ignoreIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, UpdateActionReceiver::class.java).apply { action = UpdateActionReceiver.ACTION_IGNORE },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context.getString(R.string.update_notification_title))
            .setContentText(context.getString(R.string.update_notification_description, meta.versionCode))
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(
                0,
                context.getString(R.string.update_action_install),
                downloadIntent
            )
            .addAction(
                0,
                context.getString(R.string.update_action_ignore),
                ignoreIntent
            )
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createChannelIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (manager.getNotificationChannel(CHANNEL_ID) != null) return
        val channel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.update_notification_channel),
            NotificationManager.IMPORTANCE_HIGH
        )
        manager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "updates"
        const val NOTIFICATION_ID = 2001
    }
}
