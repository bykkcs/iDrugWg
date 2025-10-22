package pw.idrug.connections.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import pw.idrug.connections.R
import pw.idrug.connections.Application
import pw.idrug.connections.backend.GoBackend
import pw.idrug.connections.backend.Tunnel
import pw.idrug.connections.config.Config
import pw.idrug.connections.model.ObservableTunnel
import pw.idrug.connections.viewmodel.ConfigProxy
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.math.roundToInt
import kotlinx.coroutines.Job

class ConfigListActivity : AppCompatActivity() {
    private lateinit var adapter: TunnelAdapter
    private var pendingTunnel: ObservableTunnel? = null
    private lateinit var prefs: android.content.SharedPreferences

    private data class Subscription(
        val location: String,
        val expires: String?,
        val forever: Boolean,
        val active: Boolean
    )

    private var subscriptions: List<Subscription> = emptyList()
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val tunnel = pendingTunnel
            if (tunnel != null) toggleTunnel(tunnel)
            pendingTunnel = null
        }

    private lateinit var emptyView: TextView
    private lateinit var autoConnectSwitch: SwitchMaterial
    private lateinit var routeYoutubeSwitch: SwitchMaterial
    private lateinit var emptyContainer: View
    private val pingResults = mutableMapOf<String, PingResult>()
    private var pingJob: Job? = null
    private val pingEndpoints = mapOf(
        "germany" to TcpEndpoint("194.113.233.251", 51821),
        "madrid" to TcpEndpoint("159.255.34.41", 51821),
        "bulgaria" to TcpEndpoint("185.232.170.117", 51821)
    )
    private val frequentApps = listOf("com.google.android.youtube")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_tv_config_list)
        val list = findViewById<RecyclerView>(R.id.config_list)
        emptyView = findViewById(R.id.empty_view)
        emptyContainer = findViewById(R.id.empty_container)
        autoConnectSwitch = findViewById(R.id.switch_auto_connect)
        routeYoutubeSwitch = findViewById(R.id.switch_route_youtube)
        adapter = TunnelAdapter()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        val autoConnect = prefs.getBoolean(PREF_TV_AUTO_CONNECT, false)
        autoConnectSwitch.isChecked = autoConnect
        autoConnectSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(PREF_TV_AUTO_CONNECT, isChecked).apply()
        }

        val routeYoutube = prefs.getBoolean(PREF_TV_ROUTE_YOUTUBE, false)
        routeYoutubeSwitch.isChecked = routeYoutube
        routeYoutubeSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean(PREF_TV_ROUTE_YOUTUBE, isChecked).apply()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        pingJob?.cancel()
        pingJob = null
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        checkSubscriptions()
        loadTunnels()
    }

    private fun loadTunnels() {
        lifecycleScope.launch {
            val tunnels = Application.getTunnelManager().getTunnels()
            val data = tunnels.toList()
            adapter.update(data)
            val isEmpty = data.isEmpty()
            emptyContainer.visibility = if (isEmpty) View.VISIBLE else View.GONE
            emptyView.visibility = if (isEmpty) View.VISIBLE else View.GONE
            refreshPings(data)
        }
    }

    private fun checkSubscriptions() {
        val token = prefs.getString("token", null) ?: return
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) {
                try {
                    val request = Request.Builder()
                        .url("https://idrug.pw/api/profile")
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    val resp = OkHttpClient().newCall(request).execute()
                    if (!resp.isSuccessful) return@withContext emptyList<Subscription>()
                    val body = resp.body?.string() ?: return@withContext emptyList<Subscription>()
                    val arr = JSONObject(body).optJSONArray("subscriptions") ?: JSONArray()
                    val result = mutableListOf<Subscription>()
                    for (i in 0 until arr.length()) {
                        val o = arr.getJSONObject(i)
                        result.add(
                            Subscription(
                                o.optString("location", o.optString("id", "")),
                                o.optString("expires"),
                                o.optBoolean("forever", false),
                                o.optBoolean("active", false)
                            )
                        )
                    }
                    result
                } catch (_: Exception) {
                    emptyList()
                }
            }
            subscriptions = list
            adapter.notifyDataSetChanged()
            val active = list.filter { it.active }.map { it.location }
            syncTunnels(token, active)
            loadTunnels()
        }
    }

    private fun requestToggle(tunnel: ObservableTunnel) {
        lifecycleScope.launch {
            if (Application.getBackend() is GoBackend) {
                val intent = GoBackend.VpnService.prepare(this@ConfigListActivity)
                if (intent != null) {
                    pendingTunnel = tunnel
                    permissionLauncher.launch(intent)
                    return@launch
                }
            }
            val targetUp = tunnel.state != Tunnel.State.UP
            if (targetUp) {
                applyRoutingPreferences(tunnel)
            }
            toggleTunnel(tunnel)
        }
    }

    private fun toggleTunnel(tunnel: ObservableTunnel) {
        lifecycleScope.launch {
            try {
                tunnel.setStateAsync(Tunnel.State.TOGGLE)
            } catch (_: Throwable) {
            }
            adapter.notifyDataSetChanged()
        }
    }

    private suspend fun downloadConfig(token: String, serverId: String): String? {
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile/download?server=$serverId"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()
        val response = withContext(Dispatchers.IO) { client.newCall(request).execute() }
        return if (response.isSuccessful) response.body?.string() else null
    }

    private suspend fun syncTunnels(token: String, activeServers: List<String>) {
        val tm = Application.getTunnelManager()
        val tunnels = tm.getTunnels().toList()
        val existing = tunnels.map { it.name }.toSet()
        val activeNames = activeServers.map { "idrug_$it" }.toSet()
        for (t in tunnels) {
            if (t.name.startsWith("idrug_") && t.name !in activeNames) {
                tm.delete(t)
            }
        }
        for (server in activeServers) {
            val name = "idrug_$server"
            if (name !in existing) {
                val config = downloadConfig(token, server)
                if (!config.isNullOrEmpty()) {
                    val file = java.io.File(filesDir, "wg_$name.conf")
                    withContext(Dispatchers.IO) { file.writeText(config) }
                    try {
                        val parsed = Config.parse(file.bufferedReader())
                        tm.create(name, parsed)
                    } catch (_: Exception) {
                    } finally {
                        file.delete()
                    }
                }
            }
        }
    }

    private inner class TunnelAdapter : RecyclerView.Adapter<TunnelViewHolder>() {
        private var items: List<ObservableTunnel> = emptyList()
        fun update(list: List<ObservableTunnel>) {
            items = list
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TunnelViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tv_config, parent, false)
            return TunnelViewHolder(view)
        }
        override fun onBindViewHolder(holder: TunnelViewHolder, position: Int) {
            holder.bind(items[position])
        }
        override fun getItemCount() = items.size
    }

    private inner class TunnelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val card: MaterialCardView = view.findViewById(R.id.tv_config_card)
        private val name: TextView = view.findViewById(R.id.config_name)
        private val status: TextView = view.findViewById(R.id.config_status)
        private val expiration: TextView = view.findViewById(R.id.config_expiration)
        private val ping: TextView = view.findViewById(R.id.config_ping)
        private val routeHint: TextView = view.findViewById(R.id.config_route)
        private val button: MaterialButton = view.findViewById(R.id.btn_connect)
        private lateinit var tunnel: ObservableTunnel
        fun bind(t: ObservableTunnel) {
            tunnel = t
            name.text = t.name.removePrefix("idrug_")
            val isUp = t.state == Tunnel.State.UP
            val statusText = if (isUp) getString(R.string.active) else getString(R.string.inactive)
            status.text = statusText
            val statusColor = if (isUp)
                ContextCompat.getColor(itemView.context, R.color.tv_focus_stroke)
            else
                ContextCompat.getColor(itemView.context, R.color.tv_text_secondary)
            status.setTextColor(statusColor)
            val serverId = t.name.removePrefix("idrug_")
            val sub = subscriptions.firstOrNull { it.location == serverId }
            expiration.text = when {
                sub == null -> ""
                sub.forever -> getString(R.string.subscription_lifetime)
                !sub.active -> getString(R.string.inactive)
                !sub.expires.isNullOrEmpty() -> {
                    val date = sub.expires.substring(0, 10).replace("-", ".")
                    getString(R.string.expires_on, date)
                }
                else -> ""
            }
            button.text = if (t.state == Tunnel.State.UP) {
                itemView.context.getString(R.string.disconnect)
            } else {
                itemView.context.getString(R.string.connect)
            }
            button.setOnClickListener { requestToggle(tunnel) }
            button.isFocusable = false

            val routeYoutube = prefs.getBoolean(PREF_TV_ROUTE_YOUTUBE, false)
            if (routeYoutube && t.name.startsWith("idrug_")) {
                routeHint.visibility = View.VISIBLE
                routeHint.text = getString(R.string.tv_route_label_youtube)
            } else {
                routeHint.visibility = View.GONE
            }
            val serverPing = pingResults[serverId]
            if (!t.name.startsWith("idrug_")) {
                ping.visibility = View.GONE
            } else {
                val neutral = ContextCompat.getColor(itemView.context, R.color.tv_text_secondary)
                val (label, color) = when {
                    serverPing == null || serverPing.state == PingState.LOADING -> getString(R.string.ping_loading) to neutral
                    serverPing.state == PingState.SUCCESS && serverPing.latencyMs != null -> {
                        val latency = serverPing.latencyMs
                        val text = getString(R.string.ping_value_ms, latency)
                        val good = ContextCompat.getColor(itemView.context, R.color.tv_focus_stroke)
                        val mid = ContextCompat.getColor(itemView.context, R.color.tv_text_secondary)
                        val bad = ContextCompat.getColor(itemView.context, R.color.md_theme_light_error)
                        val color = when {
                            latency <= 80 -> good
                            latency <= 160 -> mid
                            else -> bad
                        }
                        text to color
                    }
                    else -> {
                        val error = ContextCompat.getColor(itemView.context, R.color.md_theme_light_error)
                        getString(R.string.ping_unavailable) to error
                    }
                }
                ping.visibility = View.VISIBLE
                ping.text = label
                ping.setTextColor(color)
            }
            card.setOnClickListener { requestToggle(tunnel) }
            card.setOnFocusChangeListener { _, hasFocus -> applyCardFocus(card, hasFocus) }
            applyCardFocus(card, card.isFocused)
        }
    }

    private fun applyCardFocus(card: MaterialCardView, focused: Boolean) {
        val surface = ContextCompat.getColor(card.context, R.color.tv_card_surface)
        val highlight = ContextCompat.getColor(card.context, R.color.tv_card_highlight)
        val strokeColor = ContextCompat.getColor(card.context, R.color.tv_focus_stroke)
        card.setCardBackgroundColor(if (focused) highlight else surface)
        card.strokeWidth = if (focused) (card.resources.displayMetrics.density * 2f).roundToInt() else 0
        card.strokeColor = strokeColor
    }

    private fun refreshPings(tunnels: List<ObservableTunnel>) {
        val ids = tunnels.mapNotNull { serverIdFor(it) }.distinct()
        if (ids.isEmpty()) {
            pingJob?.cancel()
            pingJob = null
            pingResults.clear()
            adapter.notifyDataSetChanged()
            return
        }
        pingJob?.cancel()
        pingResults.keys.retainAll(ids)
        ids.forEach { pingResults[it] = PingResult(PingState.LOADING) }
        adapter.notifyDataSetChanged()
        val token = prefs.getString("token", null)
        pingJob = lifecycleScope.launch {
            val client = OkHttpClient()
            for (id in ids) {
                val result = requestPing(client, token, id)
                pingResults[id] = result
                adapter.notifyDataSetChanged()
            }
        }.also { job -> job.invokeOnCompletion { pingJob = null } }
    }

    private suspend fun applyRoutingPreferences(tunnel: ObservableTunnel) {
        val routeYoutube = prefs.getBoolean(PREF_TV_ROUTE_YOUTUBE, false)
        val currentConfig = tunnel.getConfigAsync()
        val iface = currentConfig.`interface`
        val includes = iface.includedApplications.toList()
        val shouldApply = routeYoutube
        if (shouldApply) {
            if (includes.size == frequentApps.size && includes.containsAll(frequentApps)) return
        } else {
            if (includes.isEmpty()) return
            if (!(includes.size == frequentApps.size && includes.containsAll(frequentApps))) return
        }
        val proxy = ConfigProxy(currentConfig)
        proxy.`interface`.excludedApplications.clear()
        proxy.`interface`.includedApplications.clear()
        if (shouldApply) {
            proxy.`interface`.includedApplications.addAll(frequentApps)
        }
        val resolved = try {
            proxy.resolve()
        } catch (_: Exception) {
            return
        }
        try {
            tunnel.setConfigAsync(resolved)
        } catch (_: Throwable) {
        }
    }

    private fun serverIdFor(tunnel: ObservableTunnel): String? {
        val name = tunnel.name
        return if (name.startsWith("idrug_")) name.removePrefix("idrug_") else null
    }

    private suspend fun requestPing(client: OkHttpClient, token: String?, serverId: String): PingResult =
        withContext(Dispatchers.IO) {
            val endpoint = pingEndpoints[serverId]
            val direct = endpoint?.let { measureTcpPing(it) }
            val api = fetchPingViaApi(client, token, serverId)
            when {
                direct?.state == PingState.SUCCESS && api.state == PingState.SUCCESS -> {
                    val directLatency = direct.latencyMs
                    val apiLatency = api.latencyMs
                    when {
                        directLatency != null && apiLatency != null -> if (directLatency <= apiLatency) direct else api
                        directLatency != null -> direct
                        apiLatency != null -> api
                        else -> direct
                    }
                }
                direct?.state == PingState.SUCCESS -> direct
                api.state == PingState.SUCCESS -> api
                direct != null -> direct
                else -> api
            }
        }

    private fun measureTcpPing(endpoint: TcpEndpoint): PingResult {
        return try {
            Socket().use { socket ->
                val start = System.nanoTime()
                socket.soTimeout = PING_SOCKET_TIMEOUT_MS
                socket.connect(InetSocketAddress(endpoint.host, endpoint.port), PING_CONNECT_TIMEOUT_MS)
                val end = System.nanoTime()
                val latency = ((end - start) / 1_000_000.0).roundToInt().coerceAtLeast(0)
                PingResult(PingState.SUCCESS, latency)
            }
        } catch (_: Exception) {
            PingResult(PingState.ERROR)
        }
    }

    private fun fetchPingViaApi(client: OkHttpClient, token: String?, serverId: String): PingResult {
        val requestBuilder = Request.Builder()
            .url("https://idrug.pw/api/ping?location=$serverId")
        if (!token.isNullOrBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        return try {
            client.newCall(requestBuilder.build()).execute().use { response ->
                if (!response.isSuccessful) {
                    PingResult(PingState.ERROR)
                } else {
                    val latency = parsePingMs(response.body?.string())
                    if (latency != null) PingResult(PingState.SUCCESS, latency) else PingResult(PingState.ERROR)
                }
            }
        } catch (_: Exception) {
            PingResult(PingState.ERROR)
        }
    }

    private fun parsePingMs(body: String?): Int? {
        if (body.isNullOrBlank()) return null
        val text = body.trim()
        if (text.startsWith("{") && text.endsWith("}")) {
            try {
                val json = JSONObject(text)
                val keys = listOf("rtt", "latency", "latency_ms", "ping", "ping_ms", "ms")
                for (key in keys) {
                    if (!json.has(key)) continue
                    val value = json.get(key)
                    val number = when (value) {
                        is Number -> value.toDouble()
                        is String -> value.toDoubleOrNull()
                        else -> null
                    }
                    if (number != null) return number.roundToInt()
                }
            } catch (_: Exception) {
            }
        }
        val match = Regex("([0-9]+(?:\\.[0-9]+)?)").find(text)
        val number = match?.groupValues?.getOrNull(1)?.toDoubleOrNull()
        return number?.roundToInt()
    }

    private enum class PingState { LOADING, SUCCESS, ERROR }
    private data class PingResult(val state: PingState, val latencyMs: Int? = null)
    private data class TcpEndpoint(val host: String, val port: Int)

    companion object {
        private const val PING_CONNECT_TIMEOUT_MS = 2000
        private const val PING_SOCKET_TIMEOUT_MS = 2000
        private const val PREF_TV_AUTO_CONNECT = "tv_auto_connect"
        private const val PREF_TV_ROUTE_YOUTUBE = "tv_route_youtube"
    }
}
