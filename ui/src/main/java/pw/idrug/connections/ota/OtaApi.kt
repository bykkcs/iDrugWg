package pw.idrug.connections.ota

import retrofit2.http.GET

interface OtaApi {
    @GET("meta.json")
    suspend fun getMeta(): OtaMeta
}
