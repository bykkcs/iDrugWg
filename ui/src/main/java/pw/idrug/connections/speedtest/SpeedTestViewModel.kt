package pw.idrug.connections.speedtest

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.Locale
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpeedTestViewModel(
    private val service: SpeedTestService = SpeedTestService()
) : ViewModel() {

    private val _state = MutableStateFlow(SpeedTestUiState())
    val state: StateFlow<SpeedTestUiState> = _state.asStateFlow()

    private var currentJob: Job? = null

    fun setDownloadSize(bytes: Long) {
        _state.update {
            if (it.isRunning) it else it.copy(downloadBytes = bytes)
        }
    }

    fun setUploadSize(bytes: Long) {
        _state.update {
            if (it.isRunning) it else it.copy(uploadBytes = bytes)
        }
    }

    fun startTests() {
        if (_state.value.isRunning) return
        val downloadBytes = _state.value.downloadBytes
        val uploadBytes = _state.value.uploadBytes
        currentJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isRunning = true,
                    status = SpeedTestStatus.PINGING,
                    errorMessage = null,
                    cancelled = false,
                    pingResult = null,
                    downloadResult = null,
                    uploadResult = null,
                    startedAt = SystemClock.elapsedRealtime()
                )
            }
            try {
                val ping = service.ping()
                _state.update {
                    it.copy(
                        pingResult = ping,
                        status = SpeedTestStatus.PING_DONE
                    )
                }

                _state.update { it.copy(status = SpeedTestStatus.DOWNLOAD_RUNNING) }
                val download = service.download(downloadBytes)
                _state.update {
                    it.copy(
                        downloadResult = download,
                        status = SpeedTestStatus.DOWNLOAD_DONE
                    )
                }

                _state.update { it.copy(status = SpeedTestStatus.UPLOAD_RUNNING) }
                val upload = service.upload(uploadBytes)
                _state.update {
                    it.copy(
                        uploadResult = upload,
                        status = SpeedTestStatus.FINISHED,
                        isRunning = false,
                        lastCompletedAt = SystemClock.elapsedRealtime()
                    )
                }
            } catch (cancel: CancellationException) {
                _state.update {
                    it.copy(
                        isRunning = false,
                        status = SpeedTestStatus.CANCELLED,
                        cancelled = true
                    )
                }
                throw cancel
            } catch (ex: Exception) {
                _state.update {
                    it.copy(
                        isRunning = false,
                        status = SpeedTestStatus.ERROR,
                        errorMessage = ex.message ?: ex.toString()
                    )
                }
            } finally {
                currentJob = null
            }
        }
    }

    fun cancelTests() {
        currentJob?.cancel()
        currentJob = null
    }

    companion object {
        private const val ONE_MB_BYTES = 1L * 1024L * 1024L

        val DOWNLOAD_OPTIONS_BYTES = listOf(5L, 10L, 25L, 50L).map { it * ONE_MB_BYTES }
        val UPLOAD_OPTIONS_BYTES = listOf(5L, 10L, 20L, 32L).map { it * ONE_MB_BYTES }

        const val DEFAULT_DOWNLOAD_BYTES = 10L * ONE_MB_BYTES
        const val DEFAULT_UPLOAD_BYTES = 10L * ONE_MB_BYTES
    }
}

data class SpeedTestUiState(
    val isRunning: Boolean = false,
    val status: SpeedTestStatus = SpeedTestStatus.IDLE,
    val errorMessage: String? = null,
    val cancelled: Boolean = false,
    val pingResult: SpeedTestPingResult? = null,
    val downloadResult: SpeedTestDownloadResult? = null,
    val uploadResult: SpeedTestUploadResult? = null,
    val downloadBytes: Long = SpeedTestViewModel.DEFAULT_DOWNLOAD_BYTES,
    val uploadBytes: Long = SpeedTestViewModel.DEFAULT_UPLOAD_BYTES,
    val startedAt: Long? = null,
    val lastCompletedAt: Long? = null
) {
    val downloadThroughputLabel: String
        get() = downloadResult?.throughputMbps?.let { formatMbps(it) } ?: "--"

    val uploadThroughputLabel: String
        get() = uploadResult?.throughputMbps?.let { formatMbps(it) } ?: "--"

    val pingRttLabel: String
        get() = pingResult?.let { "${it.rttMillis} ms" } ?: "--"

    val lastUpdatedSeconds: Long?
        get() = lastCompletedAt?.let {
            ((SystemClock.elapsedRealtime() - it) / 1000).coerceAtLeast(0)
        }

    private fun formatMbps(mbps: Double): String {
        return String.format(Locale.US, "%.2f Mbps", mbps)
    }
}

enum class SpeedTestStatus {
    IDLE,
    PINGING,
    PING_DONE,
    DOWNLOAD_RUNNING,
    DOWNLOAD_DONE,
    UPLOAD_RUNNING,
    FINISHED,
    CANCELLED,
    ERROR
}
