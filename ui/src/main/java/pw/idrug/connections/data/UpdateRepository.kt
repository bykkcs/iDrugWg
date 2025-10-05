package pw.idrug.connections.data

class UpdateRepository(private val api: UpdateApi) {
    suspend fun fetch(): Result<UpdateMeta> = runCatching { api.getMeta() }
}
