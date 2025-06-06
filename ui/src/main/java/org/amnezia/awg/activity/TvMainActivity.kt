package org.amnezia.awg.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.amnezia.awg.Application
import org.amnezia.awg.R
import org.amnezia.awg.backend.Tunnel
import org.amnezia.awg.config.Config
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.util.*
import okhttp3.*

class TvMainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private val ACCOUNT_TUNNELS_KEY = "account_tunnels"
    private var serverList: List<Pair<String, String>> = listOf()
    private var selectedServerId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_activity)

        prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)

        if (prefs.getString("token", null) == null) {
            startActivity(Intent(this, TvLoginActivity::class.java))
            finish()
            return
        }

        findViewById<Button>(R.id.btn_connect).setOnClickListener { onConnectClicked() }
        loadServers { setupServerList() }
    }

    private fun loadServers(done: () -> Unit) {
        if (serverList.isNotEmpty()) { done(); return }
        val client = OkHttpClient()
        client.newCall(Request.Builder().url("https://idrug.pw/api/servers").build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                serverList = listOf("germany" to "\uD83C\uDDE9\uD83C\uDDEA Германия")
                safeUi { done() }
            }
            override fun onResponse(call: Call, response: Response) {
                serverList = if (response.isSuccessful) {
                    val arr = JSONArray(response.body?.string() ?: "[]")
                    List(arr.length()) {
                        val obj = arr.getJSONObject(it)
                        val id = obj.getString("id")
                        val name = obj.getString("name")
                        id to name
                    }
                } else listOf("germany" to "\uD83C\uDDE9\uD83C\uDDEA Германия")
                safeUi { done() }
            }
        })
    }

    private fun setupServerList() {
        val listView = findViewById<ListView>(R.id.list_servers)
        val names = serverList.map { it.second }
        val adapter = ArrayAdapter(this, R.layout.tv_server_item, R.id.server_name, names)
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.adapter = adapter
        if (serverList.isNotEmpty()) {
            selectedServerId = serverList[0].first
            listView.setItemChecked(0, true)
            updateConnectButton()
        }
        listView.setOnItemClickListener { _, _, position, _ ->
            selectedServerId = serverList[position].first
            updateConnectButton()
        }
    }

    private fun onConnectClicked() {
        val serverId = selectedServerId ?: return
        val tunnelName = "idrug_$serverId"
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnel = tm.getTunnels().firstOrNull { it.name == tunnelName }
            if (tunnel == null) {
                val token = prefs.getString("token", null) ?: return@launch
                downloadConfig(token, serverId) { success, cfgText ->
                    if (!success) {
                        safeUi { Toast.makeText(this@TvMainActivity, R.string.error_network, Toast.LENGTH_SHORT).show() }
                        return@downloadConfig
                    }
                    val file = File(filesDir, "wg_$tunnelName.conf")
                    file.writeText(cfgText ?: "")
                    MainScope().launch {
                        try {
                            val cfg = Config.parse(file.bufferedReader())
                            tm.create(tunnelName, cfg)
                            file.delete()
                            val set = prefs.getStringSet(ACCOUNT_TUNNELS_KEY, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
                            set.add(tunnelName)
                            prefs.edit().putStringSet(ACCOUNT_TUNNELS_KEY, set).apply()
                            toggleTunnel(tunnelName)
                        } catch (e: Exception) {
                            safeUi { Toast.makeText(this@TvMainActivity, e.message, Toast.LENGTH_LONG).show() }
                        }
                    }
                }
            } else {
                toggleTunnel(tunnelName)
            }
        }
    }

    private fun downloadConfig(token: String, serverId: String, cb: (Boolean, String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile/download?server=$serverId"
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $token").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { cb(false, null) }
            override fun onResponse(call: Call, response: Response) { cb(response.isSuccessful, response.body?.string()) }
        })
    }

    private fun toggleTunnel(name: String) {
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnel = tm.getTunnels().firstOrNull { it.name == name } ?: return@launch
            try {
                tunnel.setStateAsync(Tunnel.State.TOGGLE)
            } catch (e: Throwable) {
                safeUi { Toast.makeText(this@TvMainActivity, e.message, Toast.LENGTH_LONG).show() }
            }
            updateConnectButton()
        }
    }

    private fun updateConnectButton() {
        val id = selectedServerId ?: return
        val tunnelName = "idrug_$id"
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnel = tm.getTunnels().firstOrNull { it.name == tunnelName }
            val btn = findViewById<Button>(R.id.btn_connect)
            if (tunnel?.state == Tunnel.State.UP) {
                btn.text = getString(R.string.disconnect)
            } else {
                btn.text = getString(R.string.connect)
            }
        }
    }

    private fun safeUi(block: () -> Unit) { handler.post { block() } }
}
