package pw.idrug.connections.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
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
    private lateinit var errorView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_server_selection)
        val list = findViewById<RecyclerView>(R.id.server_list)
        emptyView = findViewById(R.id.empty_view)
        errorView = findViewById(R.id.error_view)
        adapter = TunnelAdapter()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadTunnels()
    }

    private fun loadTunnels() {
        lifecycleScope.launch {
            try {
                val tunnels = Application.getTunnelManager().getTunnels()
                val data = tunnels.toList()
                adapter.update(data)
                emptyView.visibility = if (data.isEmpty()) View.VISIBLE else View.GONE
                errorView.visibility = View.GONE
            } catch (_: Exception) {
                emptyView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
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
                .inflate(R.layout.item_tv_server, parent, false)
            return TunnelViewHolder(view)
        }
        override fun onBindViewHolder(holder: TunnelViewHolder, position: Int) {
            holder.bind(items[position])
        }
        override fun getItemCount() = items.size
    }

    private inner class TunnelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.server_name)
        private val ping: TextView = view.findViewById(R.id.server_ping)
        private val button: MaterialButton = view.findViewById(R.id.btn_connect)
        private lateinit var tunnel: ObservableTunnel
        fun bind(t: ObservableTunnel) {
            tunnel = t
            name.text = t.name.removePrefix("idrug_")
            ping.text = getString(R.string.ping_template, 0)
            lifecycleScope.launch {
                try {
                    val stats = t.getStatisticsAsync()
                    val peer = stats.peers().firstOrNull()
                    val time = peer?.let { stats.peer(it)?.latestHandshakeEpochMillis } ?: 0L
                    val v = if (time > 0) (System.currentTimeMillis() - time) else 0L
                    ping.text = getString(R.string.ping_template, v)
                } catch (_: Throwable) {
                }
            }
            button.setOnClickListener { requestToggle(tunnel) }
        }
    }
}
