package pw.idrug.connections.catalog

import android.content.Context
import android.util.Log
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import pw.idrug.connections.Application

/**
 * Loads subscription catalog metadata from the backend and exposes a cached view
 * so the UI can render tariffs, locations and price information without hardcoding.
 */
object CatalogRepository {
    private const val TAG = "CatalogRepository"
    private const val PREF_NAME = "idrug_catalog_cache"
    private const val KEY_VERSION = "version"
    private const val KEY_PAYLOAD = "payload"
    private const val CATALOG_URL = "https://idrug.pw/api/catalog"

    private val mutex = Mutex()
    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Volatile
    private var cachedData: CatalogData? = null

    /**
     * Prefetch the catalog in background. Safe to call multiple times.
     */
    fun prefetch(context: Context) {
        Application.getCoroutineScope().launch(Dispatchers.IO) {
            runCatching { getCatalog(context) }.onFailure {
                Log.v(TAG, "Prefetch failed: ${it.message}")
            }
        }
    }

    /**
     * Obtain catalog data. Reads from memory/persistent cache first and refreshes from the network when needed.
     */
    suspend fun getCatalog(context: Context, forceRefresh: Boolean = false): CatalogData? {
        val existing = cachedData
        if (!forceRefresh && existing != null) {
            return existing
        }
        return mutex.withLock {
            val current = cachedData
            if (!forceRefresh && current != null) {
                return@withLock current
            }
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            var catalog = current
            if (catalog == null) {
                val payload = prefs.getString(KEY_PAYLOAD, null)
                if (!payload.isNullOrBlank()) {
                    parseCatalog(payload)?.let {
                        catalog = it
                        cachedData = it
                    }
                }
            }
            if (!forceRefresh && catalog != null) {
                return@withLock catalog
            }
            val networkCatalog = fetchCatalogFromNetwork(context) ?: catalog
            if (networkCatalog != null) {
                cachedData = networkCatalog
                prefs.edit()
                    .putString(KEY_VERSION, networkCatalog.version)
                    .putString(KEY_PAYLOAD, networkCatalog.rawJson)
                    .apply()
            }
            networkCatalog
        }
    }

    /**
     * Returns the cached catalog if available, eagerly loading it from disk when needed.
     * Never triggers network requests.
     */
    fun getCachedCatalog(context: Context): CatalogData? {
        val current = cachedData
        if (current != null) {
            return current
        }
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val payload = prefs.getString(KEY_PAYLOAD, null) ?: return null
        return parseCatalog(payload)?.also { cachedData = it }
    }

    fun getLocationDisplayName(context: Context, id: String?, locale: Locale): String? {
        if (id.isNullOrBlank()) return null
        val catalog = getCachedCatalog(context) ?: return null
        val meta = catalog.locations.firstOrNull { it.id == id } ?: return null
        return meta.getDisplayName(locale)
    }

    fun getLocationEmoji(context: Context, id: String?): String? {
        if (id.isNullOrBlank()) return null
        val catalog = getCachedCatalog(context) ?: return null
        return catalog.locations.firstOrNull { it.id == id }?.emoji
    }

    fun getOrderedLocationIds(context: Context): List<String> {
        val catalog = getCachedCatalog(context) ?: return emptyList()
        return catalog.locations
            .sortedWith(compareBy({ it.order ?: Int.MAX_VALUE }, { it.id }))
            .map { it.id }
    }

    private suspend fun fetchCatalogFromNetwork(context: Context): CatalogData? = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url(CATALOG_URL)
            .header("User-Agent", Application.USER_AGENT)
            .build()
        try {
            httpClient.newCall(request).execute().use { resp ->
                if (!resp.isSuccessful) {
                    Log.w(TAG, "Catalog request failed: HTTP ${resp.code}")
                    return@withContext null
                }
                val body = resp.body?.string()
                if (body.isNullOrBlank()) {
                    Log.w(TAG, "Empty catalog response")
                    return@withContext null
                }
                parseCatalog(body)
            }
        } catch (e: IOException) {
            Log.w(TAG, "Catalog fetch error", e)
            null
        }
    }

    private fun parseCatalog(json: String): CatalogData? {
        return try {
            val root = JSONObject(json)
            val tariffsJson = root.optJSONArray("tariffs") ?: JSONArray()
            val tariffs = buildList {
                for (i in 0 until tariffsJson.length()) {
                    val obj = tariffsJson.optJSONObject(i) ?: continue
                    parseTariff(obj)?.let { add(it) }
                }
            }
            val locationsJson = root.optJSONArray("locations") ?: JSONArray()
            val locations = buildList {
                for (i in 0 until locationsJson.length()) {
                    val obj = locationsJson.optJSONObject(i) ?: continue
                    parseLocation(obj)?.let { add(it) }
                }
            }
            CatalogData(
                version = root.optString("version", null),
                currency = root.optString("currency", null),
                tariffs = tariffs,
                locations = locations,
                rawJson = json
            )
        } catch (e: Exception) {
            Log.w(TAG, "Failed to parse catalog", e)
            null
        }
    }

    private fun parseTariff(obj: JSONObject): CatalogTariff? {
        val id = obj.optString("id").takeIf { it.isNotBlank() } ?: return null
        val active = when (val raw = obj.opt("active")) {
            is Boolean -> raw
            is String -> raw != "false" && raw != "0"
            else -> true
        }
        val comingSoon = obj.optBoolean("coming_soon", false) ||
            (obj.optString("active", "").equals("soon", ignoreCase = true))
        val visible = obj.optBoolean("visible", true)
        val durations = parseDurations(obj.optJSONArray("durations"))
        val availableLocations = obj.optJSONArray("available_locations")
            ?.let { array ->
                buildList {
                    for (i in 0 until array.length()) {
                        array.optString(i)?.takeIf { it.isNotBlank() }?.let { add(it) }
                    }
                }
            }.orEmpty()
        return CatalogTariff(
            id = id,
            active = active,
            comingSoon = comingSoon,
            visible = visible,
            icon = obj.optString("icon", null),
            name = parseText(obj.opt("name")),
            description = parseText(obj.opt("description")),
            price = parseText(obj.opt("price")),
            durationText = parseText(obj.opt("duration_text")),
            promo = parseText(obj.opt("promo")),
            purchaseCode = obj.optString("purchase_code", null)?.takeIf { it.isNotBlank() },
            availableLocations = availableLocations,
            details = parseTariffDetails(obj.optJSONObject("details")),
            durations = durations,
            purchaseDisabled = obj.optBoolean("purchase_disabled", false)
        )
    }

    private fun parseDurations(array: JSONArray?): List<CatalogDuration> {
        if (array == null) return emptyList()
        val result = mutableListOf<CatalogDuration>()
        for (i in 0 until array.length()) {
            val obj = array.optJSONObject(i) ?: continue
            val code = obj.optString("code")
                .ifBlank { obj.optString("id") }
                .ifBlank { null }
            val purchaseCode = obj.optString("purchase_code").ifBlank { code }
            val rawDays = listOf("days", "duration_days", "duration").firstNotNullOfOrNull { key ->
                if (obj.has(key)) obj.optInt(key) else null
            }?.takeIf { it > 0 }
            val months = listOf("months", "duration_months").firstNotNullOfOrNull { key ->
                if (obj.has(key)) obj.optInt(key) else null
            }?.takeIf { it > 0 }
            val price = listOf("price_int", "price", "amount", "amount_int").firstNotNullOfOrNull { key ->
                if (obj.has(key)) obj.optInt(key) else null
            }?.takeIf { it >= 0 }
            val locations = obj.optJSONArray("available_locations")?.let { arr ->
                buildList {
                    for (idx in 0 until arr.length()) {
                        arr.optString(idx)?.takeIf { it.isNotBlank() }?.let { add(it) }
                    }
                }
            }.orEmpty()
            result += CatalogDuration(
                code = code,
                purchaseCode = purchaseCode,
                days = rawDays,
                months = months,
                price = price,
                label = parseText(obj.opt("label")),
                priceText = parseText(obj.opt("price_text") ?: obj.opt("price_label")),
                recommended = obj.optBoolean("recommended", false),
                availableLocations = locations
            )
        }
        return result
    }

    private fun parseTariffDetails(obj: JSONObject?): CatalogTariffDetails? {
        if (obj == null) return null
        return CatalogTariffDetails(
            title = parseText(obj.opt("title")),
            locationsTitle = parseText(obj.opt("locations_title")),
            description = parseText(obj.opt("description")),
            upcoming = parseText(obj.opt("upcoming"))
        )
    }

    private fun parseLocation(obj: JSONObject): CatalogLocation? {
        val id = obj.optString("id").takeIf { it.isNotBlank() } ?: return null
        val order = if (obj.has("order")) obj.optInt("order") else null
        return CatalogLocation(
            id = id,
            order = order,
            visible = obj.optBoolean("visible", true),
            showStatus = obj.optBoolean("show_status", true),
            emoji = obj.optString("emoji", null)?.takeIf { it.isNotBlank() },
            name = parseText(obj.opt("name"))
        )
    }

    private fun parseText(raw: Any?): CatalogText? = when (raw) {
        null -> null
        is JSONObject -> {
            val map = mutableMapOf<String, String>()
            val keys = raw.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val value = raw.optString(key, null)
                if (!value.isNullOrEmpty()) {
                    map[key.lowercase(Locale.ROOT)] = value
                }
            }
            CatalogText(map = map, fallback = null)
        }
        is String -> CatalogText(map = emptyMap(), fallback = raw)
        else -> CatalogText(map = emptyMap(), fallback = raw.toString())
    }
}

data class CatalogData(
    val version: String?,
    val currency: String?,
    val tariffs: List<CatalogTariff>,
    val locations: List<CatalogLocation>,
    val rawJson: String
)

data class CatalogTariff(
    val id: String,
    val active: Boolean,
    val comingSoon: Boolean,
    val visible: Boolean,
    val icon: String?,
    val name: CatalogText?,
    val description: CatalogText?,
    val price: CatalogText?,
    val durationText: CatalogText?,
    val promo: CatalogText?,
    val purchaseCode: String?,
    val availableLocations: List<String>,
    val details: CatalogTariffDetails?,
    val durations: List<CatalogDuration>,
    val purchaseDisabled: Boolean
)

data class CatalogTariffDetails(
    val title: CatalogText?,
    val locationsTitle: CatalogText?,
    val description: CatalogText?,
    val upcoming: CatalogText?
)

data class CatalogDuration(
    val code: String?,
    val purchaseCode: String?,
    val days: Int?,
    val months: Int?,
    val price: Int?,
    val label: CatalogText?,
    val priceText: CatalogText?,
    val recommended: Boolean,
    val availableLocations: List<String>
)

data class CatalogLocation(
    val id: String,
    val order: Int?,
    val visible: Boolean,
    val showStatus: Boolean,
    val emoji: String?,
    val name: CatalogText?
) {
    fun getDisplayName(locale: Locale): String? = name?.resolve(locale)
}

data class CatalogText(
    val map: Map<String, String>,
    val fallback: String?
) {
    fun resolve(locale: Locale): String? {
        if (map.isEmpty()) {
            return fallback
        }
        val tags = mutableListOf<String>()
        val languageTag = locale.toLanguageTag()
        if (!languageTag.isNullOrBlank()) {
            tags += languageTag.lowercase(Locale.ROOT)
        }
        val language = locale.language?.lowercase(Locale.ROOT)
        if (!language.isNullOrBlank() && language !in tags) {
            tags += language
        }
        tags += listOf("ru", "en")
        for (tag in tags) {
            map[tag]?.takeIf { it.isNotBlank() }?.let { return it }
        }
        return map.values.firstOrNull { it.isNotBlank() } ?: fallback
    }
}
