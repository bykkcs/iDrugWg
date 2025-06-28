package pw.idrug.connections

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

object KillSwitchNotification {
    private const val CHANNEL_ID = "killswitch"

    fun create(context: Context): Notification {
        val nm = context.getSystemService(NotificationManager::class.java)
        if (nm.getNotificationChannel(CHANNEL_ID) == null) {
            val ch = NotificationChannel(
                CHANNEL_ID,
                "Kill-Switch",
                NotificationManager.IMPORTANCE_HIGH
            )
            nm.createNotificationChannel(ch)
        }
        val reconnectIntent = PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, ReconnectReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.getString(R.string.killswitch_active))
            .setSmallIcon(R.drawable.ic_notification)
            .addAction(
                R.drawable.ic_notification,
                context.getString(R.string.reconnect),
                reconnectIntent
            )
            .build()
    }
}
