package pw.idrug.connections.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import pw.idrug.connections.R
import pw.idrug.connections.di.UpdateModules

class DownloadCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
        if (downloadId == -1L) return
        val updateManager = UpdateModules.provideUpdateManager(context)
        if (updateManager.getPendingDownloadId() != downloadId) {
            return
        }
        updateManager.clearPendingDownloadId()
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val cursor = downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
        cursor?.use {
            if (!it.moveToFirst()) return
            val status = it.getInt(it.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                val meta = updateManager.getPendingUpdate()
                if (meta == null) {
                    Toast.makeText(context, R.string.update_no_metadata, Toast.LENGTH_LONG).show()
                    return
                }
                val installer = UpdateModules.provideApkInstaller(context)
                val installResult = installer.install(meta)
                installResult.exceptionOrNull()?.let { error ->
                    Toast.makeText(
                        context,
                        context.getString(R.string.update_install_failed, error.localizedMessage ?: error.javaClass.simpleName),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(context, R.string.update_download_failed, Toast.LENGTH_LONG).show()
            }
        }
    }
}
