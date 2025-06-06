package org.amnezia.awg.activity

import android.content.Context
import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.amnezia.awg.fragment.AppListDialogFragment
import org.amnezia.awg.viewmodel.ConfigProxy
import okhttp3.*
import org.amnezia.awg.Application
import org.amnezia.awg.R
import org.amnezia.awg.backend.Tunnel
import org.amnezia.awg.config.Config
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.util.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType

class TvMainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private val ACCOUNT_TUNNELS_KEY = "account_tunnels"
    private var serverList: List<Pair<String, String>> = listOf()
    private var selectedServerId: String? = null
    private var tunnelNames: List<String> = listOf()
    private var selectedTunnelName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_activity)

        prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)

        if (prefs.getString("token", null) == null) {
            startActivity(Intent(this, TvLoginActivity::class.java))
            finish()
            return
        }

        findViewById<Button>(R.id.btn_add_tunnel).setOnClickListener { addSelectedConfig() }
        findViewById<Button>(R.id.btn_toggle).setOnClickListener { toggleTunnel() }
        findViewById<Button>(R.id.btn_apps).setOnClickListener { chooseApps() }
        findViewById<Button>(R.id.btn_logout).setOnClickListener { logout() }

        findViewById<Spinner>(R.id.spinner_server).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedServerId = serverList[position].first
            }
            override fun onNothingSelected(parent: AdapterView<*>) { selectedServerId = null }
        }

        findViewById<Spinner>(R.id.spinner_tunnel).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedTunnelName = tunnelNames.getOrNull(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) { selectedTunnelName = null }
        }

        updateUi()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun updateUi() {
        val token = prefs.getString("token", null)
        val spinnerServer = findViewById<Spinner>(R.id.spinner_server)
        val spinnerTunnel = findViewById<Spinner>(R.id.spinner_tunnel)
        val addBtn = findViewById<Button>(R.id.btn_add_tunnel)
        val toggleBtn = findViewById<Button>(R.id.btn_toggle)
        val appsBtn = findViewById<Button>(R.id.btn_apps)
        val logoutBtn = findViewById<Button>(R.id.btn_logout)
        val status = findViewById<TextView>(R.id.status_text)
        if (token == null) return

        logoutBtn.visibility = View.VISIBLE
        loadServers {
            spinnerServer.visibility = View.VISIBLE
            loadTunnels {
                val hasTunnels = tunnelNames.isNotEmpty()
                spinnerTunnel.visibility = if (hasTunnels) View.VISIBLE else View.GONE
                toggleBtn.visibility = if (hasTunnels) View.VISIBLE else View.GONE
                appsBtn.visibility = if (hasTunnels) View.VISIBLE else View.GONE
                addBtn.visibility = View.VISIBLE
                status.text = if (hasTunnels) getString(R.string.vpn_ready) else getString(R.string.choose_location)
            }
        }
    }


    private fun loadServers(done: () -> Unit) {
        if (serverList.isNotEmpty()) { setupSpinner(); done(); return }
        val client = OkHttpClient()
        client.newCall(Request.Builder().url("https://idrug.pw/api/servers").build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                serverList = listOf("germany" to "Германия")
                safeUi { setupSpinner(); done() }
            }
            override fun onResponse(call: Call, response: Response) {
                serverList = if (response.isSuccessful) {
                    val arr = JSONArray(response.body?.string() ?: "[]")
                    List(arr.length()) { val obj = arr.getJSONObject(it); obj.getString("id") to obj.getString("name") }
                } else listOf("germany" to "Германия")
                safeUi { setupSpinner(); done() }
            }
        })
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, serverList.map { it.second })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = findViewById<Spinner>(R.id.spinner_server)
        spinner.adapter = adapter
        if (serverList.isNotEmpty()) selectedServerId = serverList[0].first
    }

    private fun loadTunnels(done: () -> Unit) {
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnels = tm.getTunnels()
            tunnelNames = tunnels.map { it.name }.filter { it.startsWith("idrug_") }
            val adapter = ArrayAdapter(this@TvMainActivity, android.R.layout.simple_spinner_item, tunnelNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            val spinner = findViewById<Spinner>(R.id.spinner_tunnel)
            spinner.adapter = adapter
            selectedTunnelName = tunnelNames.firstOrNull()
            done()
        }
    }

    private fun addSelectedConfig() {
        val serverId = selectedServerId ?: return
        val token = prefs.getString("token", null) ?: return
        val tunnelName = "idrug_$serverId"
        if (tunnelExists(tunnelName)) {
            Toast.makeText(this, R.string.config_already_added, Toast.LENGTH_SHORT).show()
            return
        }
        downloadConfig(token, serverId) { success, config ->
            if (success) {
                val file = File(filesDir, "wg_$tunnelName.conf")
                file.writeText(config ?: "")
                MainScope().launch {
                    try {
                        val cfg = Config.parse(file.bufferedReader())
                        Application.getTunnelManager().create(tunnelName, cfg)
                        file.delete()
                        val set = prefs.getStringSet(ACCOUNT_TUNNELS_KEY, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
                        set.add(tunnelName)
                        prefs.edit().putStringSet(ACCOUNT_TUNNELS_KEY, set).apply()
                        safeUi {
                            loadTunnels { updateUi() }
                        }
                    } catch (e: Exception) {
                        safeUi { Toast.makeText(this@TvMainActivity, e.message, Toast.LENGTH_LONG).show() }
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun downloadConfig(token: String, serverId: String, cb: (Boolean, String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile/download?server=$serverId"
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer $token").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { cb(false, null) }
            override fun onResponse(call: Call, response: Response) {
                cb(response.isSuccessful, response.body?.string())
            }
        })
    }

    private fun chooseApps() {
        val name = selectedTunnelName ?: return
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnel = tm.getTunnels().firstOrNull { it.name == name } ?: return@launch
            val config = tunnel.config
            var isExcluded = true
            var selected = ArrayList(config.`interface`.excludedApplications)
            if (selected.isEmpty()) {
                selected = ArrayList(config.`interface`.includedApplications)
                if (selected.isNotEmpty()) isExcluded = false
            }
            val fragment = AppListDialogFragment.newInstance(selected, isExcluded)
            supportFragmentManager.setFragmentResultListener(AppListDialogFragment.REQUEST_SELECTION, this@TvMainActivity) { _, bundle ->
                val apps = bundle.getStringArray(AppListDialogFragment.KEY_SELECTED_APPS) ?: return@setFragmentResultListener
                val excluded = bundle.getBoolean(AppListDialogFragment.KEY_IS_EXCLUDED)
                val proxy = ConfigProxy(config)
                proxy.`interface`.excludedApplications.clear()
                proxy.`interface`.includedApplications.clear()
                if (excluded) proxy.`interface`.excludedApplications.addAll(apps) else proxy.`interface`.includedApplications.addAll(apps)
                MainScope().launch {
                    try {
                        val newConfig = proxy.resolve()
                        tunnel.setConfigAsync(newConfig)
                    } catch (e: Throwable) {
                        safeUi { Toast.makeText(this@TvMainActivity, e.message, Toast.LENGTH_LONG).show() }
                    }
                }
            }
            fragment.show(supportFragmentManager, null)
        }
    }

    private fun toggleTunnel() {
        val name = selectedTunnelName ?: return
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnel = tm.getTunnels().firstOrNull { it.name == name } ?: return@launch
            try {
                tunnel.setStateAsync(Tunnel.State.TOGGLE)
            } catch (e: Throwable) {
                safeUi { Toast.makeText(this@TvMainActivity, e.message, Toast.LENGTH_LONG).show() }
            }
        }
    }

    private fun logout() {
        val accountTunnels = prefs.getStringSet(ACCOUNT_TUNNELS_KEY, emptySet()) ?: emptySet()
        prefs.edit().clear().apply()
        MainScope().launch {
            val tm = Application.getTunnelManager()
            val tunnels = tm.getTunnels()
            tunnels.filter { it.name in accountTunnels }.forEach { tm.delete(it) }
            safeUi {
                loadTunnels { updateUi() }
                startActivity(Intent(this@TvMainActivity, TvLoginActivity::class.java))
                finish()
            }
        }
    }

    private fun tunnelExists(name: String): Boolean {
        val tm = Application.getTunnelManager()
        return runBlocking { tm.getTunnels().any { it.name == name } }
    }

    private fun safeUi(block: () -> Unit) { handler.post { block() } }
}
