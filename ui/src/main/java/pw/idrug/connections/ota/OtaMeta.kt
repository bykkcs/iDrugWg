package pw.idrug.connections.ota

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtaMeta(
    val versionCode: Int,
    val apkUrl: String,
    val versionName: String? = null,
    val changelog: String? = null
)
