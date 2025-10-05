package pw.idrug.connections.data

import retrofit2.http.GET

interface UpdateApi {
    @GET("ota/meta.json")
    suspend fun getMeta(): UpdateMeta
}
