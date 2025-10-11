package pw.idrug.connections.ota

sealed interface UpdateEvent {
    data object NoUpdate : UpdateEvent
    data class Error(val message: String) : UpdateEvent
    data class DownloadStarted(val downloadId: Long) : UpdateEvent
    data object DownloadFinished : UpdateEvent
}
