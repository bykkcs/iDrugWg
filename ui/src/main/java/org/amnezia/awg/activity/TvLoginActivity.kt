package org.amnezia.awg.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import okhttp3.*
import org.amnezia.awg.R
import org.json.JSONObject
import java.io.IOException
import java.util.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType

class TvLoginActivity : AppCompatActivity() {
    private lateinit var prefs: android.content.SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private var qrPollingTimer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_login_activity)

        prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)

        findViewById<Button>(R.id.btn_login_qr).setOnClickListener {
            startQrLogin()
        }

        if (savedInstanceState == null) {
            startQrLogin()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        qrPollingTimer?.cancel()
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
        img.visibility = android.view.View.VISIBLE
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
                            Toast.makeText(this@TvLoginActivity, R.string.login_success, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@TvLoginActivity, TvMainActivity::class.java))
                            finish()
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
                    val status = json.optString("status")
                    if (status == "confirmed") {
                        val jwt = json.optString("token")
                        if (!jwt.isNullOrEmpty()) {
                            safeUi { cb(true, jwt) }
                            return
                        }
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

    private fun safeUi(block: () -> Unit) { handler.post { block() } }
}

