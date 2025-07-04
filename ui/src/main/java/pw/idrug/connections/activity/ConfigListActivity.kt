package pw.idrug.connections.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import pw.idrug.connections.config.Config
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import pw.idrug.connections.R
import pw.idrug.connections.Application
import pw.idrug.connections.backend.GoBackend
import pw.idrug.connections.backend.Tunnel
import pw.idrug.connections.model.ObservableTunnel

class ConfigListActivity : AppCompatActivity() {
    private lateinit var adapter: TunnelAdapter
    private var pendingTunnel: ObservableTunnel? = null
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val tunnel = pendingTunnel
            if (tunnel != null) toggleTunnel(tunnel)
            pendingTunnel = null
        }

    private lateinit var emptyView: TextView
    private lateinit var stateContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_server_selection)
        val list = findViewById<RecyclerView>(R.id.server_list)
        stateContainer = findViewById(R.id.state_container)
        emptyView = findViewById(R.id.state_message)
        adapter = TunnelAdapter()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        syncWithProfile()
    }

    private fun loadTunnels() {
        lifecycleScope.launch {
            val tunnels = Application.getTunnelManager().getTunnels()
            val data = tunnels.toList()
            adapter.update(data)
            if (data.isEmpty()) {
                emptyView.text = getString(R.string.no_servers_found)
                stateContainer.visibility = View.VISIBLE
            } else {
                stateContainer.visibility = View.GONE
            }
        }
    }

    private fun syncWithProfile() {
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null) ?: return
        lifecycleScope.launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://idrug.pw/api/profile")
                .addHeader("Authorization", "Bearer $token")
                .build()
            val response = withContext(Dispatchers.IO) { client.newCall(request).execute() }
            if (!response.isSuccessful) {
                emptyView.text = getString(R.string.error_loading_servers)
                stateContainer.visibility = View.VISIBLE
                loadTunnels()
                return@launch
            }
            val body = response.body?.string() ?: run {
                emptyView.text = getString(R.string.error_loading_servers)
                stateContainer.visibility = View.VISIBLE
                loadTunnels();
                return@launch
            }
            try {
                val obj = JSONObject(body)
                val subs = obj.optJSONArray("subscriptions") ?: return@launch
                val active = mutableSetOf<String>()
                for (i in 0 until subs.length()) {
                    val so = subs.getJSONObject(i)
                    if (so.optBoolean("active", false)) {
                        active.add(so.optString("location"))
                    }
                }
                val tm = Application.getTunnelManager()
                val tunnels = tm.getTunnels().toList()
                val existing = tunnels.map { it.name }.toSet()
                for (t in tunnels) {
                    if (t.name.startsWith("idrug_") && t.name.removePrefix("idrug_") !in active) {
                        tm.delete(t)
                    }
                }
                for (server in active) {
                    val name = "idrug_$server"
                    if (name !in existing) {
                        val cfg = downloadConfig(token, server)
                        if (!cfg.isNullOrEmpty()) {
                            val parsed = Config.parse(
                                ByteArrayInputStream(cfg.toByteArray(StandardCharsets.UTF_8))
                            )
                            try { tm.create(name, parsed) } catch (_: Exception) {}
                        }
                    }
                }
            } catch (_: Exception) {
            }
            loadTunnels()
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

    private inner class TunnelAdapter : RecyclerView.Adapter<TunnelViewHolder>() {
        private var items: List<ObservableTunnel> = emptyList()
        fun update(list: List<ObservableTunnel>) {
            items = list
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TunnelViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_server_card, parent, false)
            return TunnelViewHolder(view)
        }
        override fun onBindViewHolder(holder: TunnelViewHolder, position: Int) {
            holder.bind(items[position])
        }
        override fun getItemCount() = items.size
    }

    private inner class TunnelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.server_name)
        private val button: Button = view.findViewById(R.id.btn_connect)
        private lateinit var tunnel: ObservableTunnel
        fun bind(t: ObservableTunnel) {
            tunnel = t
            name.text = t.name.removePrefix("idrug_")
            button.isEnabled = false
            button.text = if (t.state == Tunnel.State.UP) {
                getString(R.string.disconnect)
            } else {
                getString(R.string.connect)
            }
            itemView.setOnClickListener { requestToggle(tunnel) }
        }
    }
}
