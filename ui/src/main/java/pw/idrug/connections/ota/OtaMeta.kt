package pw.idrug.connections.ota

data class OtaMeta(
    val versionCode: Int,
    val versionName: String?,
    val apkUrl: String,
    val changelog: String?
)
