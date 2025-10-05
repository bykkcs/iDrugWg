package pw.idrug.connections.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.data.UpdateMeta
import pw.idrug.connections.data.UpdateRepository

class UpdateManager(
    private val repository: UpdateRepository,
    private val preferences: UpdatePreferences
) {
    suspend fun check(force: Boolean = false): UpdateState {
        val result = withContext(Dispatchers.IO) { repository.fetch() }
        return result.fold(
            onSuccess = { meta ->
                preferences.setLastCheckedAt(System.currentTimeMillis())
                preferences.setPendingMeta(meta)
                if (isUpdateAvailable(meta)) {
                    UpdateState.Available(meta)
                } else {
                    UpdateState.NoUpdate
                }
            },
            onFailure = { error ->
                val cachedMeta = preferences.getPendingMeta()
                if (!force && cachedMeta != null && isUpdateAvailable(cachedMeta)) {
                    UpdateState.Available(cachedMeta)
                } else {
                    UpdateState.Error(error.message ?: "Unknown error")
                }
            }
        )
    }

    fun ignore(versionCode: Int) {
        preferences.setIgnoredVersionCode(versionCode)
    }

    fun clearIgnore(versionCode: Int) {
        preferences.clearIgnoredVersion(versionCode)
    }

    fun isIgnored(versionCode: Int): Boolean = preferences.getIgnoredVersionCode() == versionCode

    fun getLastCheckedAt(): Long = preferences.getLastCheckedAt()

    fun getPendingUpdate(): UpdateMeta? = preferences.getPendingMeta()

    fun setPendingDownloadId(id: Long) = preferences.setPendingDownloadId(id)

    fun getPendingDownloadId(): Long = preferences.getPendingDownloadId()

    fun clearPendingDownloadId() = preferences.clearPendingDownloadId()

    fun markPostInstallSnackbar() = preferences.markPostInstallSnackbarPending()

    fun consumePostInstallSnackbar(): Boolean = preferences.consumePostInstallSnackbarFlag()

    private fun isUpdateAvailable(meta: UpdateMeta): Boolean {
        if (meta.versionCode <= BuildConfig.VERSION_CODE) {
            return false
        }
        return !isIgnored(meta.versionCode)
    }
}

sealed class UpdateState {
    data object NoUpdate : UpdateState()
    data class Available(val meta: UpdateMeta) : UpdateState()
    data class Error(val message: String) : UpdateState()
}
