package pw.idrug.connections.ota

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.R
import pw.idrug.connections.di.UpdateModules
import pw.idrug.connections.util.UserKnobs

class UpdateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UpdateModules.repository

    private val _state = MutableStateFlow(UpdateState())
    val state: StateFlow<UpdateState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<UpdateEvent>()
    val events: SharedFlow<UpdateEvent> = _events.asSharedFlow()

    private var currentDownloadId: Long? = null
    private val _autoCheckEnabled = MutableStateFlow(true)
    val autoCheckEnabled: StateFlow<Boolean> = _autoCheckEnabled.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _autoCheckEnabled.value = UserKnobs.updatesAutoCheckEnabled.first()
            UserKnobs.updatesAutoCheckEnabled.collect { enabled ->
                _autoCheckEnabled.value = enabled
            }
        }
    }

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

    fun setAutoCheckEnabled(enabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            UserKnobs.setUpdatesAutoCheckEnabled(enabled)
        }
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
                _state.value = _state.value.copy(downloading = true, downloadProgress = null, error = null)
                _events.emit(UpdateEvent.DownloadStarted(currentDownloadId!!))
                val query = DownloadManager.Query().setFilterById(currentDownloadId!!)
                var downloadSuccessful = false
                while (true) {
                    var shouldContinue = false
                    val cursor = manager.query(query)
                    cursor?.use {
                        if (!it.moveToFirst()) {
                            return@use
                        }
                        when (it.getInt(it.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))) {
                            DownloadManager.STATUS_SUCCESSFUL -> {
                                downloadSuccessful = true
                                _state.value = _state.value.copy(downloadProgress = 100)
                            }
                            DownloadManager.STATUS_FAILED -> {
                                // handled after loop
                            }
                            DownloadManager.STATUS_RUNNING, DownloadManager.STATUS_PAUSED, DownloadManager.STATUS_PENDING -> {
                                val totalBytes = it.getLong(it.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                                val downloadedBytes = it.getLong(it.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                                val progress = if (totalBytes > 0) {
                                    ((downloadedBytes * 100) / totalBytes).toInt().coerceIn(0, 100)
                                } else {
                                    null
                                }
                                _state.value = _state.value.copy(downloadProgress = progress)
                                shouldContinue = true
                            }
                            else -> shouldContinue = true
                        }
                    }
                    if (!shouldContinue || downloadSuccessful) {
                        break
                    }
                    delay(300)
                }
                _state.value = _state.value.copy(downloading = false, downloadProgress = if (downloadSuccessful) 100 else null)
                if (downloadSuccessful) {
                    val downloadedUri = manager.getUriForDownloadedFile(currentDownloadId!!)
                    val apkFile = destination
                    val uri = downloadedUri ?: FileProvider.getUriForFile(
                        context,
                        "${BuildConfig.APPLICATION_ID}.fileprovider",
                        apkFile
                    )
                    val installIntent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, APK_MIME_TYPE)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    withContext(Dispatchers.Main) {
                        context.startActivity(installIntent)
                    }
                } else {
                    val message = context.getString(R.string.update_download_failed)
                    _state.value = _state.value.copy(error = message)
                    _events.emit(UpdateEvent.Error(message))
                }
            }.onFailure { throwable ->
                val message = throwable.localizedMessage
                    ?: context.getString(R.string.update_error_generic)
                _state.value = _state.value.copy(downloading = false, downloadProgress = null, error = message)
                _events.emit(UpdateEvent.Error(message))
            }
        }
    }

    fun getCurrentDownloadId(): Long? = currentDownloadId

    companion object {
        private const val APK_MIME_TYPE = "application/vnd.android.package-archive"
        const val APK_FILE_NAME = "iDrugConnections.apk"
    }
}
