package pw.idrug.connections.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LinkCodeActivity : AppCompatActivity() {
    private lateinit var editCode: EditText
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_code)

        editCode = findViewById(R.id.edit_code)
        progress = findViewById(R.id.progress)
        findViewById<Button>(R.id.btn_submit_code).setOnClickListener { submitCode() }
    }

    private fun submitCode() {
        val code = editCode.text.toString().trim()
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
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, message ?: getString(R.string.login_via_telegram_or_qr), Toast.LENGTH_LONG).show()
                }
            }
        }
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
                callback(false, null, null, e.message)
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val obj = JSONObject(response.body?.string() ?: "{}")
                    val jwt = obj.optString("jwt", null)
                    val username = obj.optString("username", null)
                    if (!jwt.isNullOrEmpty() && !username.isNullOrEmpty()) {
                        callback(true, jwt, username, null)
                    } else {
                        callback(false, null, null, "Invalid response")
                    }
                } else {
                    callback(false, null, null, "Code invalid or expired")
                }
            }
        })
    }
}
