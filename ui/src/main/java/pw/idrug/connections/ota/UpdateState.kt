package pw.idrug.connections.ota

data class UpdateState(
    val loading: Boolean = false,
    val error: String? = null,
    val updateAvailable: Boolean = false,
    val versionCode: Int? = null,
    val versionName: String? = null,
    val changelog: String = "",
    val apkUrl: String? = null,
    val downloading: Boolean = false,
    val downloadProgress: Int = 0
)
