package org.amnezia.awg.activity

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.amnezia.awg.Application
import org.amnezia.awg.R
import org.amnezia.awg.backend.GoBackend
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
    private var qrPollingTimer: Timer? = null
    private var serverList: List<Pair<String, String>> = listOf()
    private var selectedServerId: String? = null
    private var currentTunnelName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_activity)

        prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        currentTunnelName = prefs.getString("tunnel_name", null)

        findViewById<Button>(R.id.btn_login_qr).setOnClickListener { startQrLogin() }
        findViewById<Button>(R.id.btn_add_tunnel).setOnClickListener { addSelectedConfig() }
        findViewById<Button>(R.id.btn_toggle).setOnClickListener { toggleTunnel() }
        findViewById<Button>(R.id.btn_logout).setOnClickListener { logout() }

        findViewById<Spinner>(R.id.spinner_server).onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedServerId = serverList[position].first
            }
            override fun onNothingSelected(parent: AdapterView<*>) { selectedServerId = null }
        }

        updateUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        qrPollingTimer?.cancel()
    }

    private fun updateUi() {
        val token = prefs.getString("token", null)
        val hasTunnel = currentTunnelName?.let { tunnelExists(it) } ?: false
        val loginBtn = findViewById<Button>(R.id.btn_login_qr)
        val spinner = findViewById<Spinner>(R.id.spinner_server)
        val addBtn = findViewById<Button>(R.id.btn_add_tunnel)
        val toggleBtn = findViewById<Button>(R.id.btn_toggle)
        val logoutBtn = findViewById<Button>(R.id.btn_logout)
        val qrImage = findViewById<ImageView>(R.id.qr_code_image)
        val status = findViewById<TextView>(R.id.status_text)

        qrImage.visibility = View.GONE
        if (token == null) {
            loginBtn.visibility = View.VISIBLE
            logoutBtn.visibility = View.GONE
            spinner.visibility = View.GONE
            addBtn.visibility = View.GONE
            toggleBtn.visibility = View.GONE
            status.text = getString(R.string.login_needed)
        } else {
            loginBtn.visibility = View.GONE
            logoutBtn.visibility = View.VISIBLE
            loadServers {
                spinner.visibility = View.VISIBLE
                addBtn.visibility = if (hasTunnel) View.GONE else View.VISIBLE
                toggleBtn.visibility = if (hasTunnel) View.VISIBLE else View.GONE
                status.text = if (hasTunnel) getString(R.string.vpn_ready) else getString(R.string.choose_location)
            }
        }
    }

    private fun startQrLogin() {
        findViewById<Button>(R.id.btn_login_qr).isEnabled = false
        generateQrLoginToken { token ->
            findViewById<Button>(R.id.btn_login_qr).isEnabled = true
            if (token == null) {
                Toast.makeText(this, R.string.error_network, Toast.LENGTH_SHORT).show()
                return@generateQrLoginToken
            }
            showQrCode(token)
            startPollingQrStatus(token)
        }
    }

    private fun showQrCode(token: String) {
        val size = 512
        val bits = QRCodeWriter().encode(token, BarcodeFormat.QR_CODE, size, size)
        val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
        for (x in 0 until size) for (y in 0 until size) bmp.setPixel(x, y, if (bits.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
        val img = findViewById<ImageView>(R.id.qr_code_image)
        img.setImageBitmap(bmp)
        img.visibility = View.VISIBLE
        findViewById<TextView>(R.id.status_text).text = getString(R.string.scan_qr_from_phone)
    }

    private fun startPollingQrStatus(token: String) {
        qrPollingTimer?.cancel()
        qrPollingTimer = Timer()
        qrPollingTimer?.schedule(object : TimerTask() {
            override fun run() {
                pollQrLoginStatus(token) { confirmed, jwt ->
                    if (confirmed && jwt != null) {
                        qrPollingTimer?.cancel()
                        prefs.edit().putString("token", jwt).apply()
                        safeUi {
                            Toast.makeText(this@TvMainActivity, R.string.login_success, Toast.LENGTH_SHORT).show()
                            updateUi()
                        }
                    }
                }
            }
        }, 0, 3000)
    }

    private fun pollQrLoginStatus(token: String, cb: (Boolean, String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://idrug.pw/api/qr/login_status/$token").get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { safeUi { cb(false, null) } }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "{}")
                    if (json.optString("status") == "confirmed") {
                        val jwt = json.optString("token")
                        safeUi { cb(true, jwt) }
                        return
                    }
                }
                safeUi { cb(false, null) }
            }
        })
    }

    private fun generateQrLoginToken(cb: (String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/qr/login_token")
            .post("".toRequestBody("application/json".toMediaType()))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { safeUi { cb(null) } }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "{}")
                    safeUi { cb(json.optString("qr_token", null)) }
                } else safeUi { cb(null) }
            }
        })
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
                        prefs.edit().putString("tunnel_name", tunnelName).apply()
                        currentTunnelName = tunnelName
                        safeUi { updateUi() }
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

    private fun toggleTunnel() {
        val name = currentTunnelName ?: return
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
        prefs.edit().clear().apply()
        currentTunnelName = null
        MainScope().launch {
            val tm = Application.getTunnelManager()
            tm.getTunnels().forEach { tm.delete(it) }
            safeUi { updateUi() }
        }
    }

    private fun tunnelExists(name: String): Boolean {
        val tm = Application.getTunnelManager()
        return runBlocking { tm.getTunnels().any { it.name == name } }
    }

    private fun safeUi(block: () -> Unit) { handler.post { block() } }
}
