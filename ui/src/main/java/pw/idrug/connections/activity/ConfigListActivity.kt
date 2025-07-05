package pw.idrug.connections.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.button.MaterialButton
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
import pw.idrug.connections.model.ObservableTunnel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_tv_config_list)
        val list = findViewById<RecyclerView>(R.id.config_list)
        emptyView = findViewById(R.id.empty_view)
        adapter = TunnelAdapter()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
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
            emptyView.visibility = if (data.isEmpty()) View.VISIBLE else View.GONE
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
                .inflate(R.layout.item_tv_config, parent, false)
            return TunnelViewHolder(view)
        }
        override fun onBindViewHolder(holder: TunnelViewHolder, position: Int) {
            holder.bind(items[position])
        }
        override fun getItemCount() = items.size
    }

    private inner class TunnelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.config_name)
        private val status: TextView = view.findViewById(R.id.config_status)
        private val expiration: TextView = view.findViewById(R.id.config_expiration)
        private val button: MaterialButton = view.findViewById(R.id.btn_connect)
        private lateinit var tunnel: ObservableTunnel
        fun bind(t: ObservableTunnel) {
            tunnel = t
            name.text = t.name.removePrefix("idrug_")
            status.text = if (t.state == Tunnel.State.UP) getString(R.string.active) else getString(R.string.inactive)
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
        }
    }
}
