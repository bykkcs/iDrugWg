package pw.idrug.connections.ota

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import java.io.File
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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

    /** Чек мета-инфы об апдейте. */
    fun checkUpdate(auto: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(loading = true, error = null)
            val result = repository.getMeta()
            result.onSuccess { meta ->
                if (meta.versionCode > BuildConfig.VERSION_CODE) {
                    _state.value = _state.value.copy(
                        loading = false,
                        updateAvailable = true,
                        versionCode = meta.versionCode,
                        versionName = meta.versionName,
                        changelog = meta.changelog?.trim().orEmpty(),
                        apkUrl = meta.apkUrl
                    )
                } else {
                    _state.value = UpdateState(loading = false) // чистый idle
                    if (!auto) _events.emit(UpdateEvent.NoUpdate)
                }
            }.onFailure { t ->
                val msg = t.localizedMessage
                    ?: getApplication<Application>().getString(R.string.update_error_generic)
                _state.value = UpdateState(loading = false, error = msg)
                if (!auto) _events.emit(UpdateEvent.Error(msg))
            }
        }
    }

    /** Позволяет пробросить мету извне (если уже загружена). */
    fun setMeta(meta: OtaMeta) {
        _state.value = _state.value.copy(
            loading = false,
            updateAvailable = true,
            versionCode = meta.versionCode,
            versionName = meta.versionName,
            changelog = meta.changelog?.trim().orEmpty(),
            apkUrl = meta.apkUrl
        )
    }

    /** Старт скачивания + трекинг прогресса. */
    fun downloadAndInstall() {
        val context = getApplication<Application>()
        val url = _state.value.apkUrl
        if (url.isNullOrBlank()) {
            viewModelScope.launch { _events.emit(UpdateEvent.Error(context.getString(R.string.update_error_generic))) }
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // если уже что-то качали — подчистим
                currentDownloadId?.let { oldId ->
                    runCatching {
                        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        dm.remove(oldId)
                    }
                }

                // включаем прогресс-UI
                _state.value = _state.value.copy(
                    downloading = true,
                    downloadProgress = null,
                    error = null
                )

                val request = DownloadManager.Request(Uri.parse(url))
                    .setTitle(context.getString(R.string.update_download_title))
                    .setDescription(context.getString(R.string.update_download_description))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true)
                    .setMimeType(APK_MIME_TYPE)

                @Suppress("DEPRECATION")
                val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                if (!directory.exists()) directory.mkdirs()

                val destination = File(directory, APK_FILE_NAME)
                if (destination.exists()) destination.delete()

                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, APK_FILE_NAME)

                val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val id = manager.enqueue(request)
                currentDownloadId = id

                _events.emit(UpdateEvent.DownloadStarted(id))

                // отдельная корутина — поллить DownloadManager и апдейтить проценты
                trackDownloadProgress(manager, id)
            } catch (ce: CancellationException) {
                // корутина отменена — просто погасим прогресс
                _state.value = _state.value.copy(downloading = false, downloadProgress = null)
                throw ce
            } catch (t: Throwable) {
                val msg = t.localizedMessage
                    ?: context.getString(R.string.update_error_generic)
                _state.value = _state.value.copy(downloading = false, downloadProgress = null, error = msg)
                _events.emit(UpdateEvent.Error(msg))
            }
        }
    }

    /** Поллинг статуса загрузки: гоняет проценты в _state до успеха/провала. */
    private fun trackDownloadProgress(manager: DownloadManager, downloadId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val query = DownloadManager.Query().setFilterById(downloadId)
            var done = false
            while (!done && isActive) {
                try {
                    manager.query(query)?.use { c ->
                        if (c.moveToFirst()) {
                            val status = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                            val downloaded = c.getLong(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                            val total = c.getLong(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))

                            when (status) {
                                DownloadManager.STATUS_PENDING,
                                DownloadManager.STATUS_PAUSED,
                                DownloadManager.STATUS_RUNNING -> {
                                    val prog: Int? =
                                        if (total > 0) ((downloaded * 100L / total).toInt().coerceIn(0, 100)) else null
                                    _state.value = _state.value.copy(
                                        downloading = true,
                                        downloadProgress = prog
                                    )
                                }

                                DownloadManager.STATUS_SUCCESSFUL -> {
                                    _state.value = _state.value.copy(
                                        downloading = false,
                                        downloadProgress = 100
                                    )
                                    _events.emit(UpdateEvent.DownloadFinished)
                                    done = true
                                }

                                DownloadManager.STATUS_FAILED -> {
                                    val reason = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_REASON))
                                    _state.value = _state.value.copy(
                                        downloading = false,
                                        downloadProgress = null,
                                        error = "Download failed ($reason)"
                                    )
                                    _events.emit(UpdateEvent.Error("Download failed ($reason)"))
                                    done = true
                                }
                            }
                        } else {
                            // курсор пуст — считаем, что загрузка оборвалась
                            _state.value = _state.value.copy(downloading = false, downloadProgress = null)
                            done = true
                        }
                    }
                    if (!done) delay(250)
                } catch (t: Throwable) {
                    _state.value = _state.value.copy(
                        downloading = false,
                        downloadProgress = null,
                        error = t.localizedMessage
                    )
                    _events.emit(UpdateEvent.Error(t.localizedMessage ?: "Download error"))
                    done = true
                }
            }
        }
    }

    /** Позвать из BroadcastReceiver/диалога после ручной установки, чтобы спрятать прогресс. */
    fun clearDownloadUi() {
        _state.value = _state.value.copy(downloading = false, downloadProgress = null)
        currentDownloadId = null
    }

    fun getCurrentDownloadId(): Long? = currentDownloadId

    fun getDownloadedApkUri(context: Context): Uri? {
        val id = currentDownloadId ?: return null
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        return runCatching { manager.getUriForDownloadedFile(id) }.getOrNull()
    }

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
