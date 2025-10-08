package pw.idrug.connections.ota

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.R
import pw.idrug.connections.di.UpdateModules

class UpdateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UpdateModules.repository

    private val _state = MutableStateFlow(UpdateState())
    val state: StateFlow<UpdateState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<UpdateEvent>()
    val events: SharedFlow<UpdateEvent> = _events.asSharedFlow()

    private var currentDownloadId: Long? = null

    fun checkUpdate(auto: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(loading = true, error = null)
            val result = repository.getMeta()
            result.onSuccess { meta ->
                if (meta.versionCode > BuildConfig.VERSION_CODE) {
                    _state.value = UpdateState(
                        loading = false,
                        updateAvailable = true,
                        versionCode = meta.versionCode,
                        versionName = meta.versionName,
                        changelog = meta.changelog?.trim().orEmpty(),
                        apkUrl = meta.apkUrl
                    )
                } else {
                    _state.value = UpdateState(loading = false)
                    if (!auto) {
                        _events.emit(UpdateEvent.NoUpdate)
                    }
                }
            }.onFailure { throwable ->
                val message = throwable.localizedMessage
                    ?: getApplication<Application>().getString(R.string.update_error_generic)
                _state.value = UpdateState(loading = false, error = message)
                if (!auto) {
                    _events.emit(UpdateEvent.Error(message))
                }
            }
        }
    }

    fun setMeta(meta: OtaMeta) {
        _state.value = UpdateState(
            loading = false,
            updateAvailable = true,
            versionCode = meta.versionCode,
            versionName = meta.versionName,
            changelog = meta.changelog?.trim().orEmpty(),
            apkUrl = meta.apkUrl
        )
    }

    fun downloadAndInstall() {
        val context = getApplication<Application>()
        val url = _state.value.apkUrl
        if (url.isNullOrBlank()) {
            viewModelScope.launch { _events.emit(UpdateEvent.Error(context.getString(R.string.update_error_generic))) }
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle(context.getString(R.string.update_download_title))
                    .setDescription(context.getString(R.string.update_download_description))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)
                    .setMimeType(APK_MIME_TYPE)
                @Suppress("DEPRECATION")
                val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                if (!directory.exists()) {
                    directory.mkdirs()
                }
                val destination = File(directory, APK_FILE_NAME)
                if (destination.exists()) {
                    destination.delete()
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, APK_FILE_NAME)
                val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                currentDownloadId = manager.enqueue(request)
                _events.emit(UpdateEvent.DownloadStarted(currentDownloadId!!))
                val query = DownloadManager.Query().setFilterById(currentDownloadId!!)
                var downloadSuccessful = false
                while (true) {
                    val cursor = manager.query(query)
                    if (cursor != null && cursor.moveToFirst()) {
                        when (cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))) {
                            DownloadManager.STATUS_SUCCESSFUL -> {
                                downloadSuccessful = true
                                cursor.close()
                                break
                            }
                            DownloadManager.STATUS_FAILED -> {
                                cursor.close()
                                break
                            }
                        }
                    }
                    cursor?.close()
                    kotlinx.coroutines.delay(1000)
                }
                if (downloadSuccessful) {
                    val apkFile = destination
                    val uri = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        androidx.core.content.FileProvider.getUriForFile(context, "${context.packageName}.provider", apkFile)
                    } else {
                        Uri.fromFile(apkFile)
                    }
                    val installIntent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, APK_MIME_TYPE)
                        addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
                        addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    context.startActivity(installIntent)
                } else {
                    _events.emit(UpdateEvent.Error(context.getString(R.string.update_error_generic)))
                }
            }.onFailure { throwable ->
                val message = throwable.localizedMessage
                    ?: context.getString(R.string.update_error_generic)
                _events.emit(UpdateEvent.Error(message))
            }
        }
    }

    fun getCurrentDownloadId(): Long? = currentDownloadId

    fun getDownloadedFile(): File {
        @Suppress("DEPRECATION")
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        return File(directory, APK_FILE_NAME)
    }

    companion object {
        private const val APK_MIME_TYPE = "application/vnd.android.package-archive"
        const val APK_FILE_NAME = "iDrugConnections.apk"
    }
}
