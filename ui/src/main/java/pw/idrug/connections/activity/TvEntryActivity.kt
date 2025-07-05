package pw.idrug.connections.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import pw.idrug.connections.R
import pw.idrug.connections.Application
import pw.idrug.connections.config.Config
import java.io.File

class TvEntryActivity : AppCompatActivity() {
    private lateinit var codeInput: TextInputEditText
    private lateinit var progress: CircularProgressIndicator
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check for token
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null)
        if (!token.isNullOrEmpty()) {
            startActivity(Intent(this, ConfigListActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.activity_tv_entry)
        codeInput = findViewById(R.id.edit_code)
        progress = findViewById(R.id.progress)
        errorText = findViewById(R.id.error_text)
        findViewById<MaterialButton>(R.id.btn_submit_code).setOnClickListener { submitCode() }
    }

    private fun submitCode() {
        val code = codeInput.text.toString().trim()
        if (code.length != 6) {
            Toast.makeText(this, R.string.enter_code, Toast.LENGTH_SHORT).show()
            return
        }
        progress.visibility = View.VISIBLE
        lifecycleScope.launch {
            val result = linkAccountWithCode(code)
            if (result != null) {
                val (token, username) = result
                val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
                prefs.edit().putString("token", token).putString("username", username).apply()
                autoImportConfigs()
                startActivity(Intent(this@TvEntryActivity, ConfigListActivity::class.java))
                finish()
            } else {
                showError(getString(R.string.code_invalid_or_expired))
            }
            progress.visibility = View.GONE
        }
    }

    private fun showError(msg: String) {
        errorText.visibility = View.VISIBLE
        errorText.text = msg
    }

    private suspend fun linkAccountWithCode(code: String): Pair<String, String>? {
        val client = OkHttpClient()
        val body = FormBody.Builder().add("code", code).build()
        val request = Request.Builder()
            .url("https://idrug.pw/api/linking/consume")
            .post(body)
            .build()
        val response = withContext(Dispatchers.IO) { client.newCall(request).execute() }
        if (response.isSuccessful) {
            val obj = JSONObject(response.body?.string() ?: "{}")
            val jwt = obj.optString("jwt")
            val username = obj.optString("username")
            if (jwt.isNotEmpty() && username.isNotEmpty()) {
                return Pair(jwt, username)
            }
        }
        return null
    }

    private suspend fun autoImportConfigs() {
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val token = prefs.getString("token", null) ?: return
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/profile")
            .addHeader("Authorization", "Bearer $token")
            .build()
        val response = withContext(Dispatchers.IO) { client.newCall(request).execute() }
        if (!response.isSuccessful) return
        val resp = response.body?.string() ?: return
        try {
            val obj = JSONObject(resp)
            val subs = obj.optJSONArray("subscriptions") ?: return
            val active = mutableListOf<String>()
            for (i in 0 until subs.length()) {
                val sObj = subs.getJSONObject(i)
                if (sObj.optBoolean("active", false)) {
                    active.add(sObj.optString("location"))
                }
            }
            val tm = Application.getTunnelManager()
            val tunnels = tm.getTunnels().toList()
            val existing = tunnels.map { it.name }.toSet()
            for (server in active) {
                val name = "idrug_$server"
                if (name !in existing) {
                    val config = downloadConfig(token, server)
                    if (!config.isNullOrEmpty()) {
                        val file = File(filesDir, "wg_$name.conf")
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
        } catch (_: Exception) {
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
}
