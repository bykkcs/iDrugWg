package pw.idrug.connections.ota

data class UpdateState(
    val loading: Boolean = false,          // загрузка меты (meta.json/changelog)
    val downloading: Boolean = false,      // идёт скачивание APK
    val downloadProgress: Int? = null,     // 0..100 или null, если total неизвестен
    val error: String? = null,

    val updateAvailable: Boolean = false,
    val versionCode: Int? = null,
    val versionName: String? = null,
    val changelog: String = "",
    val apkUrl: String? = null
)
