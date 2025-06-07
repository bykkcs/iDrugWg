package org.amnezia.awg.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.amnezia.awg.R
import org.json.JSONObject
import java.io.IOException

class AccountFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private var pollingTimer: CountDownTimer? = null
    private val handler = Handler(Looper.getMainLooper())
    private var pendingToken: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pollingTimer?.cancel()
    }

    override fun onResume() {
        super.onResume()
        handleDeepLink(requireActivity().intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)

        view.findViewById<Button>(R.id.btn_login_telegram).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://idrug.pw/login?redirect=idrug://auth"))
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.btn_show_qr_login).setOnClickListener {
            showQrLogin()
        }
        view.findViewById<View>(R.id.fab_scan_qr).setOnClickListener {
            startQrScanner()
        }
        showCorrectScreen(view)
    }

    /* ==============================
       UI helpers
       ============================== */
    private fun showCorrectScreen(view: View) {
        if (isLoggedIn()) {
            view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.GONE
            view.findViewById<Button>(R.id.btn_show_qr_login).visibility = View.GONE
            view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
            view.findViewById<TextView>(R.id.status_text).text = "Вы вошли"
        } else {
            view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.VISIBLE
            view.findViewById<Button>(R.id.btn_show_qr_login).visibility = View.VISIBLE
            view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
            view.findViewById<TextView>(R.id.status_text).text = ""
        }
    }

    private fun isLoggedIn(): Boolean = !prefs.getString("token", null).isNullOrEmpty()

    private fun openProfile() {
        parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        showCorrectScreen(requireView())
    }

    /* ==============================
       QR login flow
       ============================== */
    private fun showQrLogin() {
        val view = view ?: return
        view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
        view.findViewById<TextView>(R.id.status_text).text = "Получаем QR..."
        generateQrLoginToken { token, deeplink ->
            if (token == null) {
                Toast.makeText(requireContext(), "Ошибка генерации QR", Toast.LENGTH_SHORT).show()
                view.findViewById<TextView>(R.id.status_text).text = ""
                return@generateQrLoginToken
            }
            pendingToken = token
            val content = deeplink ?: token
            showQrCode(content, view.findViewById(R.id.qr_code_image))
            view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.status_text).text = "Ожидаем подтверждение..."
            startPolling(token)
        }
    }

    private fun showQrCode(data: String, img: ImageView) {
        try {
            val size = 512
            val bits = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size)
            val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) for (y in 0 until size) {
                bmp.setPixel(x, y, if (bits.get(x, y)) Color.BLACK else Color.WHITE)
            }
            img.setImageBitmap(bmp)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Ошибка QR", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateQrLoginToken(cb: (token: String?, deeplink: String?) -> Unit) {
        val client = OkHttpClient()
        val req = Request.Builder()
            .url("https://idrug.pw/api/qr/login_token")
            .post("".toRequestBody("application/json".toMediaType()))
            .build()
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi { cb(null, null) }
            }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) { safeUi { cb(null, null) }; return }
                val json = JSONObject(response.body?.string() ?: "{}")
                val token = json.optString("qr_token", null)
                val link = json.optString("deeplink", null)
                safeUi { cb(token, link) }
            }
        })
    }

    private fun startPolling(token: String) {
        pollingTimer?.cancel()
        pollingTimer = object : CountDownTimer(5 * 60_000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
                pollQrStatus(token) { status, jwt, username, photo ->
                    if (status == "confirmed" && jwt != null) {
                        pollingTimer?.cancel()
                        prefs.edit().putString("token", jwt)
                            .putString("username", username)
                            .putString("photo_url", photo)
                            .apply()
                        Toast.makeText(requireContext(), "Вход выполнен", Toast.LENGTH_SHORT).show()
                        openProfile()
                    } else if (status == "expired" || status == "used") {
                        pollingTimer?.cancel()
                        view?.findViewById<TextView>(R.id.status_text)?.text = "QR-код истёк"
                    }
                }
            }
            override fun onFinish() {
                view?.findViewById<TextView>(R.id.status_text)?.text = "QR-код истёк"
            }
        }
        pollingTimer?.start()
    }

    private fun pollQrStatus(token: String, cb: (status: String, jwt: String?, username: String?, photo: String?) -> Unit) {
        val client = OkHttpClient()
        val req = Request.Builder().url("https://idrug.pw/api/qr/login_status/$token").get().build()
        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi { cb("error", null, null, null) }
            }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) { safeUi { cb("error", null, null, null) }; return }
                val json = JSONObject(response.body?.string() ?: "{}")
                val status = json.optString("status")
                val jwt = json.optString("token", null)
                val username = json.optString("username", null)
                val photo = json.optString("photo_url", null)
                safeUi { cb(status, jwt, username, photo) }
            }
        })
    }

    /* ==============================
       QR confirm on scanning device
       ============================== */
    private val qrScanLauncher = registerForActivityResult(ScanContract()) { result ->
        val scanned = result.contents
        if (!scanned.isNullOrEmpty()) {
            val uri = Uri.parse(scanned)
            val token = if (uri.scheme == "idrug" && uri.host == "qrlogin") uri.getQueryParameter("token") else scanned
            if (!token.isNullOrEmpty()) confirmQrLoginToken(token)
        }
    }

    private fun startQrScanner() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Сканируйте QR-код")
            setBeepEnabled(true)
            setBarcodeImageEnabled(false)
        }
        qrScanLauncher.launch(options)
    }

    private fun confirmQrLoginToken(token: String) {
        val jwt = prefs.getString("token", null) ?: return
        val body = """{"token":"$token"}""".toRequestBody("application/json".toMediaType())
        val req = Request.Builder()
            .url("https://idrug.pw/api/qr/login_confirm")
            .addHeader("Authorization", "Bearer $jwt")
            .post(body)
            .build()
        OkHttpClient().newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi { Toast.makeText(requireContext(), "Ошибка передачи", Toast.LENGTH_SHORT).show() }
            }
            override fun onResponse(call: Call, response: Response) {
                safeUi {
                    if (response.isSuccessful) Toast.makeText(requireContext(), "Сессия передана", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(requireContext(), "Ошибка подтверждения", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /* ==============================
       Deep link handling
       ============================== */
    private fun handleDeepLink(intent: Intent?) {
        val data = intent?.data ?: return
        when {
            data.scheme == "idrug" && data.host == "qrlogin" -> {
                val token = data.getQueryParameter("token")
                if (!token.isNullOrEmpty() && isLoggedIn()) {
                    confirmQrLoginToken(token)
                } else if (!token.isNullOrEmpty()) {
                    pendingToken = token
                }
                requireActivity().intent.data = null
            }
            (data.scheme == "idrug" && data.host == "auth") ||
                    (data.scheme == "https" && data.host == "idrug.pw" && data.path?.startsWith("/auth") == true) -> {
                val jwt = data.getQueryParameter("jwt")
                val user = data.getQueryParameter("username")
                val photo = data.getQueryParameter("photo_url")
                if (!jwt.isNullOrEmpty() && !user.isNullOrEmpty()) {
                    prefs.edit().putString("token", jwt).putString("username", user).putString("photo_url", photo).apply()
                    Toast.makeText(requireContext(), "Вход выполнен", Toast.LENGTH_SHORT).show()
                    pendingToken?.let { confirmQrLoginToken(it); pendingToken = null }
                    openProfile()
                }
                requireActivity().intent.data = null
            }
        }
    }

    private fun safeUi(block: () -> Unit) {
        if (isAdded) handler.post { if (isAdded) block() }
    }
}

