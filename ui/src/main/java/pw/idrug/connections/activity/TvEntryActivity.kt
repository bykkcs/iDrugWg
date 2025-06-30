package pw.idrug.connections.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import pw.idrug.connections.R
import pw.idrug.connections.Application
import pw.idrug.connections.config.Config
import java.io.File

class TvEntryActivity : AppCompatActivity() {
    private lateinit var codeInput: EditText
    private lateinit var progress: ProgressBar
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_entry)
        codeInput = findViewById(R.id.edit_code)
        progress = findViewById(R.id.progress)
        errorText = findViewById(R.id.error_text)
        findViewById<Button>(R.id.btn_submit_code).setOnClickListener { submitCode() }
    }

    private fun submitCode() {
        val code = codeInput.text.toString().trim()
        if (code.length != 6) {
            Toast.makeText(this, R.string.enter_code, Toast.LENGTH_SHORT).show()
            return
        }
        progress.visibility = View.VISIBLE
        linkAccountWithCode(code) { success, token, username, message ->
            runOnUiThread {
                progress.visibility = View.GONE
                if (success && token != null && username != null) {
                    val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
                    prefs.edit().putString("token", token).putString("username", username).apply()
                    autoImportConfigs {
                        startActivity(Intent(this, ConfigListActivity::class.java))
                        finish()
                    }
                } else {
                    showError(message ?: getString(R.string.code_invalid_or_expired))
                }
            }
        }
    }

    private fun showError(msg: String) {
        errorText.visibility = View.VISIBLE
        errorText.text = msg
    }

    private fun linkAccountWithCode(code: String, callback: (Boolean, String?, String?, String?) -> Unit) {
        val client = OkHttpClient()
        val body = FormBody.Builder().add("code", code).build()
        val request = Request.Builder()
            .url("https://idrug.pw/api/linking/consume")
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, null, null, getString(R.string.network_error_msg, e.message))
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val obj = JSONObject(response.body?.string() ?: "{}")
                    val jwt = obj.optString("jwt", null)
                    val username = obj.optString("username", null)
                    if (!jwt.isNullOrEmpty() && !username.isNullOrEmpty()) {
                        callback(true, jwt, username, null)
                    } else {
                        callback(false, null, null, getString(R.string.invalid_response))
                    }
                } else {
                    callback(false, null, null, getString(R.string.code_invalid_or_expired))
                }
            }
        })
    }

    private fun autoImportConfigs(onDone: () -> Unit) {
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null) ?: return onDone()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/profile")
            .addHeader("Authorization", "Bearer $token")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { onDone() }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) { onDone(); return }
                val resp = response.body?.string() ?: return onDone()
                try {
                    val obj = JSONObject(resp)
                    val subs = obj.optJSONArray("subscriptions") ?: return onDone()
                    val active = mutableListOf<String>()
                    for (i in 0 until subs.length()) {
                        val sObj = subs.getJSONObject(i)
                        if (sObj.optBoolean("active", false)) {
                            active.add(sObj.optString("location"))
                        }
                    }
                    MainScope().launch {
                        val tm = Application.getTunnelManager()
                        val tunnels = tm.getTunnels().toList()
                        val existing = tunnels.map { it.name }.toSet()
                        for (server in active) {
                            val name = "idrug_$server"
                            if (name !in existing) {
                                downloadConfig(token, server, name) { ok, config ->
                                    if (ok && config != null) {
                                        val file = File(filesDir, "wg_$name.conf")
                                        file.writeText(config)
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
                        onDone()
                    }
                } catch (_: Exception) { onDone() }
            }
        })
    }

    private fun downloadConfig(token: String, serverId: String, tunnelName: String, callback: (Boolean, String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile/download?server=$serverId"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { callback(false, e.message) }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    callback(true, response.body?.string())
                } else {
                    callback(false, response.body?.string())
                }
            }
        })
    }
}
