package pw.idrug.connections.domain

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import pw.idrug.connections.data.UpdateMeta

class UpdatePreferences(
    private val sharedPreferences: SharedPreferences,
    moshi: Moshi
) {
    private val metaAdapter = moshi.adapter(UpdateMeta::class.java)

    fun getIgnoredVersionCode(): Int = sharedPreferences.getInt(KEY_IGNORED_VERSION_CODE, 0)

    fun setIgnoredVersionCode(versionCode: Int) {
        sharedPreferences.edit().putInt(KEY_IGNORED_VERSION_CODE, versionCode).apply()
    }

    fun clearIgnoredVersion(versionCode: Int) {
        if (getIgnoredVersionCode() == versionCode) {
            sharedPreferences.edit().putInt(KEY_IGNORED_VERSION_CODE, 0).apply()
        }
    }

    fun getLastCheckedAt(): Long = sharedPreferences.getLong(KEY_LAST_CHECKED_AT, 0L)

    fun setLastCheckedAt(timestamp: Long) {
        sharedPreferences.edit().putLong(KEY_LAST_CHECKED_AT, timestamp).apply()
    }

    fun getPendingMeta(): UpdateMeta? {
        val stored = sharedPreferences.getString(KEY_PENDING_META, null) ?: return null
        return runCatching { metaAdapter.fromJson(stored) }.getOrNull()
    }

    fun setPendingMeta(meta: UpdateMeta?) {
        if (meta == null) {
            sharedPreferences.edit().remove(KEY_PENDING_META).apply()
        } else {
            sharedPreferences.edit().putString(KEY_PENDING_META, metaAdapter.toJson(meta)).apply()
        }
    }

    fun setPendingDownloadId(id: Long) {
        sharedPreferences.edit().putLong(KEY_PENDING_DOWNLOAD_ID, id).apply()
    }

    fun getPendingDownloadId(): Long = sharedPreferences.getLong(KEY_PENDING_DOWNLOAD_ID, -1L)

    fun clearPendingDownloadId() {
        sharedPreferences.edit().remove(KEY_PENDING_DOWNLOAD_ID).apply()
    }

    fun markPostInstallSnackbarPending() {
        sharedPreferences.edit().putBoolean(KEY_SHOW_POST_INSTALL_SNACKBAR, true).apply()
    }

    fun consumePostInstallSnackbarFlag(): Boolean {
        val pending = sharedPreferences.getBoolean(KEY_SHOW_POST_INSTALL_SNACKBAR, false)
        if (pending) {
            sharedPreferences.edit().putBoolean(KEY_SHOW_POST_INSTALL_SNACKBAR, false).apply()
        }
        return pending
    }

    companion object {
        private const val KEY_IGNORED_VERSION_CODE = "ignoredVersionCode"
        private const val KEY_LAST_CHECKED_AT = "lastCheckedAt"
        private const val KEY_PENDING_META = "pendingMeta"
        private const val KEY_PENDING_DOWNLOAD_ID = "pendingDownloadId"
        private const val KEY_SHOW_POST_INSTALL_SNACKBAR = "showPostInstallSnackbar"
    }
}
