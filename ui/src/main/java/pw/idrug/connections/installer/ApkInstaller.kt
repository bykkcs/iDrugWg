package pw.idrug.connections.installer

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.content.FileProvider
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.R
import pw.idrug.connections.data.UpdateMeta
import pw.idrug.connections.domain.UpdateManager
import java.io.File

class ApkInstaller(
    private val context: Context,
    private val updateManager: UpdateManager,
    private val authority: String = "${BuildConfig.APPLICATION_ID}.provider"
) {
    fun download(meta: UpdateMeta): Result<Unit> = runCatching {
        if (meta.apkUrl.isBlank()) {
            throw IllegalArgumentException("Empty download url")
        }
        val updatesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
            "updates"
        )
        if (!updatesDir.exists()) {
            updatesDir.mkdirs()
        }
        val destinationFile = getDestinationFile(meta.versionCode)
        if (destinationFile.exists()) {
            destinationFile.delete()
        }
        val request = DownloadManager.Request(Uri.parse(meta.apkUrl))
            .setTitle(context.getString(R.string.update_download_title))
            .setDescription(context.getString(R.string.update_download_description))
            .setMimeType(APK_MIME_TYPE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(Uri.fromFile(destinationFile))
            .setAllowedOverRoaming(true)
            .setAllowedOverMetered(true)

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = manager.enqueue(request)
        updateManager.setPendingDownloadId(downloadId)
    }

    fun install(meta: UpdateMeta): Result<Unit> = runCatching {
        val file = getDestinationFile(meta.versionCode)
        if (!file.exists()) {
            throw IllegalStateException(context.getString(R.string.update_apk_missing))
        }
        val apkUri = FileProvider.getUriForFile(context, authority, file)
        install(apkUri)
    }

    fun install(apkUri: Uri): Result<Unit> = runCatching {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            !context.packageManager.canRequestPackageInstalls()
        ) {
            val intent = Intent(
                Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                Uri.parse("package:${context.packageName}")
            ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            context.startActivity(intent)
            return@runCatching
        }
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(apkUri, APK_MIME_TYPE)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        updateManager.markPostInstallSnackbar()
        context.startActivity(intent)
    }

    fun getDestinationFile(versionCode: Int): File {
        val updatesDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
            "updates"
        )
        if (!updatesDir.exists()) {
            updatesDir.mkdirs()
        }
        return File(updatesDir, "iDrugConnections-v$versionCode.apk")
    }

    companion object {
        private const val APK_MIME_TYPE = "application/vnd.android.package-archive"
    }
}
