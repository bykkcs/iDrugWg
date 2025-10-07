package pw.idrug.connections.ota

class OtaRepository(
    private val api: OtaApi
) {
    suspend fun getMeta(): Result<OtaMeta> = runCatching {
        api.getMeta()
    }
}
