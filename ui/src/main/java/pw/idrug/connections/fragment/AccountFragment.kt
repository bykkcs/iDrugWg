package pw.idrug.connections.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.*
import pw.idrug.connections.R
import pw.idrug.connections.Application
import pw.idrug.connections.config.Config
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType

class AccountFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private var destroyed = false

    private var selectedServerId: String? = null
    private var selectedServerName: String? = null
    private var serverList: List<Pair<String, String>> = listOf()
    private var qrPollingTimer: Timer? = null

    private data class Subscription(val id: String, val name: String, val expires: String?, val active: Boolean)
    private var subscriptions: List<Subscription> = emptyList()

    private val qrScanLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK && result.data != null) {
            val scanned = result.data?.getStringExtra("SCAN_RESULT")
            if (!scanned.isNullOrEmpty()) {
                confirmQrLoginToken(scanned) { success, message ->
                    safeUi {
                        Toast.makeText(
                            requireContext(),
                            if (success) "Вход подтверждён через QR" else "Ошибка подтверждения: $message",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (success) loadProfileAndSetupUI(requireView())
                    }
                }
            } else {
                Toast.makeText(requireContext(), "QR не распознан", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyed = true
        qrPollingTimer?.cancel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        destroyed = false
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onResume() {
        super.onResume()
        handleDeepLink(requireActivity().intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        setupListeners(view)
        showCorrectScreen(view)
    }

    private fun setupListeners(view: View) {
        view.findViewById<Button>(R.id.btn_login_telegram).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://idrug.pw/login?redirect=idrug://auth"))
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            setLoading(true)
            afterLogout(view)
            setLoading(false)
        }
        view.findViewById<Button>(R.id.btn_download).setOnClickListener {
            handleDownloadConfig(view)
        }
        view.findViewById<Button>(R.id.btn_renew).setOnClickListener {
            setLoading(true)
            val token = prefs.getString("token", null)
            if (token == null) {
                Toast.makeText(requireContext(), "Сначала войдите через Telegram", Toast.LENGTH_SHORT).show()
                setLoading(false)
                return@setOnClickListener
            }
            renewSubscription { success, resp ->
                safeUi {
                    setLoading(false)
                    Toast.makeText(requireContext(), if (success) "Подписка обновлена" else "Ошибка: $resp", Toast.LENGTH_SHORT).show()
                    if (success) loadProfileAndSetupUI(requireView())
                }
            }
        }
    }

    private fun isLoggedIn(): Boolean {
        return !prefs.getString("token", null).isNullOrEmpty()
    }

    private fun showCorrectScreen(view: View) {
        if (isLoggedIn()) {
            showAccountScreen(
                view,
                prefs.getString("username", "") ?: "",
                prefs.getString("photo_url", null),
                emptyList()
            )
            loadServersAndProfileUI(view)
        } else {
            showLoginScreen(view)
        }
    }

    private fun loadServersAndProfileUI(view: View) {
        setLoading(true)
        // Получаем список серверов
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/servers"
        client.newCall(Request.Builder().url(url).build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                serverList = listOf(
                    "germany" to "Германия",
                    "multihop" to "Мультхоп Германия",
                    "bulgaria" to "Болгария",
                    "madrid" to "Мадрид"
                )
                safeUi {
                    setupServerSpinner(view)
                    loadProfileAndSetupUI(view)
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val arr = JSONArray(response.body?.string() ?: "[]")
                    serverList = List(arr.length()) {
                        val obj = arr.getJSONObject(it)
                        obj.getString("id") to obj.getString("name")
                    }
                } else {
                    serverList = listOf(
                        "germany" to "Германия",
                        "multihop" to "Мультхоп Германия",
                        "bulgaria" to "Болгария",
                        "madrid" to "Мадрид"
                    )
                }
                safeUi {
                    setupServerSpinner(view)
                    loadProfileAndSetupUI(view)
                }
            }
        })
    }

    private fun setupServerSpinner(view: View) {
        val spinner = view.findViewById<Spinner>(R.id.spinner_server)
        val btnDownload = view.findViewById<Button>(R.id.btn_download)
        val serverNames = serverList.map { it.second }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, serverNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        selectedServerId = null
        selectedServerName = null
        btnDownload.isEnabled = false
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
                selectedServerId = serverList[position].first
                selectedServerName = serverList[position].second
                updateDownloadButtonState(view)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedServerId = null
                selectedServerName = null
                updateDownloadButtonState(view)
            }
        }
        spinner.visibility = View.VISIBLE
        view.findViewById<TextView>(R.id.text_server_choice).visibility = View.VISIBLE
        btnDownload.visibility = View.VISIBLE
    }

    private fun loadProfileAndSetupUI(view: View) {
        val token = prefs.getString("token", null)
        if (token == null) {
            safeUi {
                showLoginScreen(view)
                setLoading(false)
            }
            return
        }
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi {
                    setLoading(false)
                    Toast.makeText(requireContext(), "Ошибка сети: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val resp = response.body?.string() ?: ""
                safeUi {
                    setLoading(false)
                    if (response.code == 401) {
                        showLoginScreen(view)
                    } else if (response.isSuccessful) {
                        try {
                            val obj = JSONObject(resp)
                            val subsArr = obj.optJSONArray("subscriptions") ?: JSONArray()
                            val subsList = List(subsArr.length()) { i ->
                                val o = subsArr.getJSONObject(i)
                                Subscription(
                                    o.optString("id"),
                                    o.optString("name"),
                                    o.optString("expires", null),
                                    o.optBoolean("active", false)
                                )
                            }
                            subscriptions = subsList
                            val username = obj.optString("client_name", prefs.getString("username", "") ?: "")
                            val photoUrl = obj.optString("photo_url", null)
                            prefs.edit().putString("username", username).apply()
                            if (!photoUrl.isNullOrEmpty()) prefs.edit().putString("photo_url", photoUrl).apply()
                            showAccountScreen(view, username, photoUrl, subsList)
                            // Важно сразу синхронизировать туннели после получения профиля
                            syncTunnelsWithProfile()
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), "Ошибка обработки профиля", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Ошибка получения профиля: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoginScreen(view: View) {
        view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_download).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_renew).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout).visibility = View.GONE
        view.findViewById<Spinner>(R.id.spinner_server).visibility = View.GONE
        view.findViewById<TextView>(R.id.text_server_choice).visibility = View.GONE
        view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
        view.findViewById<TextView>(R.id.text_current_user).text = "Вход через Telegram или QR"
        view.findViewById<TextView>(R.id.status_text).text = ""
        view.findViewById<TextView>(R.id.text_expiration).text = ""
        view.findViewById<ImageView>(R.id.avatar_image).setImageResource(R.drawable.ic_avatar_placeholder)
    }

    private fun showAccountScreen(view: View, username: String, photoUrl: String?, subs: List<Subscription>) {
        view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout).visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
        view.findViewById<Spinner>(R.id.spinner_server).visibility = View.VISIBLE
        view.findViewById<TextView>(R.id.text_server_choice).visibility = View.VISIBLE
        val avatarImage = view.findViewById<ImageView>(R.id.avatar_image)
        if (!photoUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .error(R.drawable.ic_avatar_placeholder)
                .transform(CircleTransform())
                .into(avatarImage)
        } else {
            avatarImage.setImageResource(R.drawable.ic_avatar_placeholder)
        }
        view.findViewById<Button>(R.id.btn_download).visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_renew).visibility = View.VISIBLE
        view.findViewById<TextView>(R.id.text_current_user).text = "Ваш логин: $username"
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val lines = subs.joinToString("\n") { s ->
            val fixed = s.expires?.replace("T", " ")?.substring(0, 19) ?: ""
            val days = if (!fixed.isEmpty()) {
                try {
                    val exp = sdf.parse(fixed)
                    val now = Date()
                    ((exp.time - now.time) / (1000 * 60 * 60 * 24)).toInt().toString()
                } catch (_: Exception) { null }
            } else null
            val daysStr = days?.let { " ($it дн.)" } ?: ""
            "${s.name}: ${if (s.active) "активна" else "не активна"}${if (fixed.isNotEmpty()) " до $fixed" else ""}$daysStr"
        }
        view.findViewById<TextView>(R.id.status_text).text = lines
        view.findViewById<TextView>(R.id.text_expiration).text = ""
        updateDownloadButtonState(view)
    }

    private fun updateDownloadButtonState(view: View) {
        val btnDownload = view.findViewById<Button>(R.id.btn_download)
        val id = selectedServerId
        btnDownload.isEnabled = id != null && subscriptions.any { it.id == id && it.active }
    }

    private fun setLoading(loading: Boolean) {
        view?.findViewById<View>(R.id.loading_overlay)?.visibility = if (loading) View.VISIBLE else View.GONE
    }

private fun afterLogout(view: View) {
    prefs.edit().clear().apply()
    MainScope().launch {
        try {
            val tunnelManager = Application.getTunnelManager()
            val tunnels = tunnelManager.getTunnels()
            tunnels
                .filter { it.name.startsWith("idrug_") }
                .forEach { tunnel ->
                    tunnelManager.delete(tunnel)
                }
        } catch (e: Exception) {
            // Можно залогировать ошибку, если потребуется
        }
        safeUi {
            showLoginScreen(view)
            Toast.makeText(requireContext(), "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
        }
    }
}


    private fun handleDownloadConfig(view: View) {
        if (selectedServerId == null) {
            Toast.makeText(requireContext(), "Выберите сервер", Toast.LENGTH_SHORT).show()
            return
        }
        val serverId = selectedServerId ?: return
        if (!subscriptions.any { it.id == serverId && it.active }) {
            Toast.makeText(requireContext(), "Подписка не активна", Toast.LENGTH_SHORT).show()
            return
        }
        val tunnelName = "idrug_$serverId"
        setLoading(true)
        val token = prefs.getString("token", null)
        if (token == null) {
            Toast.makeText(requireContext(), "Войдите через Telegram", Toast.LENGTH_SHORT).show()
            setLoading(false)
            return
        }
        MainScope().launch {
            val tunnelManager = Application.getTunnelManager()
            val tunnels = tunnelManager.getTunnels()
            val tunnel = tunnels.firstOrNull { it.name == tunnelName }
            if (tunnel != null) {
                safeUi {
                    Toast.makeText(requireContext(), "Конфиг уже добавлен", Toast.LENGTH_SHORT).show()
                    setLoading(false)
                }
                return@launch
            }
            downloadConfig(token, serverId, tunnelName) { success, configOrError ->
                safeUi {
                    setLoading(false)
                    if (success) {
                        val file = File(requireContext().filesDir, "wg_$tunnelName.conf")
                        file.writeText(configOrError ?: "")
                        MainScope().launch {
                            try {
                                val config = Config.parse(file.bufferedReader())
                                tunnelManager.create(tunnelName, config)
                                file.delete()
                                Toast.makeText(requireContext(), "Туннель добавлен", Toast.LENGTH_SHORT).show()
                                loadProfileAndSetupUI(requireView())
                            } catch (e: Exception) {
                                Toast.makeText(requireContext(), "Ошибка создания туннеля: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), "Ошибка: $configOrError", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    // QR — генерация, polling, подтверждение (как раньше)
    private fun generateQrLoginToken(onComplete: (String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/qr/login_token")
            .post("".toRequestBody("application/json".toMediaType()))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) { safeUi { onComplete(null) } }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "{}")
                    val token = json.optString("qr_token", null)
                    safeUi { onComplete(token) }
                } else {
                    safeUi { onComplete(null) }
                }
            }
        })
    }

    private fun showQrCode(token: String, imageView: ImageView) {
        try {
            val size = 512
            val bits = QRCodeWriter().encode(token, BarcodeFormat.QR_CODE, size, size)
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bitmap.setPixel(x, y, if (bits.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
                }
            }
            imageView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Ошибка генерации QR кода", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startPollingQrStatus(token: String) {
        qrPollingTimer?.cancel()
        qrPollingTimer = Timer()
        qrPollingTimer?.schedule(object : TimerTask() {
            override fun run() {
                pollQrLoginStatus(token) { confirmed, jwt, username, photoUrl ->
                    if (confirmed && jwt != null && username != null) {
                        qrPollingTimer?.cancel()
                        prefs.edit()
                            .putString("username", username)
                            .putString("token", jwt)
                            .putString("photo_url", photoUrl)
                            .apply()
                        safeUi {
                            Toast.makeText(requireContext(), "Вход через QR подтверждён!", Toast.LENGTH_SHORT).show()
                            loadProfileAndSetupUI(requireView())
                        }
                    }
                }
            }
        }, 0, 3000)
    }

    private fun pollQrLoginStatus(token: String, onResult: (Boolean, String?, String?, String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/qr/login_status/$token")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi { onResult(false, null, null, null) }
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "{}")
                    val status = json.optString("status")
                    if (status == "confirmed") {
                        val jwt = json.optString("token")
                        getProfileFromJwt(jwt) { success, username, photoUrl ->
                            safeUi {
                                onResult(success, jwt, username, photoUrl)
                            }
                        }
                    } else {
                        safeUi { onResult(false, null, null, null) }
                    }
                } else {
                    safeUi { onResult(false, null, null, null) }
                }
            }
        })
    }

    private fun getProfileFromJwt(jwt: String, callback: (Boolean, String?, String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/profile")
            .addHeader("Authorization", "Bearer $jwt")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, null, null)
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = JSONObject(response.body?.string() ?: "{}")
                    val username = json.optString("client_name", null)
                    val photoUrl = json.optString("photo_url", null)
                    callback(true, username, photoUrl)
                } else {
                    callback(false, null, null)
                }
            }
        })
    }

    private fun confirmQrLoginToken(token: String, callback: (Boolean, String?) -> Unit) {
        val jwt = prefs.getString("token", null)
        if (jwt == null) {
            callback(false, "Вы не авторизованы")
            return
        }
        val client = OkHttpClient()
        val json = """{"token":"$token"}"""
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url("https://idrug.pw/api/qr/login_confirm")
            .addHeader("Authorization", "Bearer $jwt")
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi { callback(false, e.message) }
            }
            override fun onResponse(call: Call, response: Response) {
                safeUi { callback(response.isSuccessful, response.body?.string()) }
            }
        })
    }

    private fun startQrScanner() {
        // Используй свою ActivityResult/Intent для сканирования QR (ZXing и т.п.)
        // ...
    }

    private fun downloadConfig(token: String, serverId: String, tunnelName: String, callback: (Boolean, String?) -> Unit) {
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile/download?server=$serverId"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, e.message)
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    callback(true, response.body?.string())
                } else {
                    callback(false, response.body?.string())
                }
            }
        })
    }

    private fun renewSubscription(callback: (Boolean, String?) -> Unit) {
        val token = prefs.getString("token", null) ?: return callback(false, "Нет токена")
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/profile/renew")
            .post("".toRequestBody("application/json".toMediaType()))
            .addHeader("Authorization", "Bearer $token")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, e.message)
            }
            override fun onResponse(call: Call, response: Response) {
                callback(response.isSuccessful, response.body?.string())
            }
        })
    }

    private fun handleDeepLink(intent: Intent?) {
        val data = intent?.data
        if (data != null && data.scheme == "idrug" && data.host == "auth") {
            val jwt = data.getQueryParameter("jwt")
            val username = data.getQueryParameter("username")
            val photoUrl = data.getQueryParameter("photo_url")
            if (!jwt.isNullOrEmpty() && !username.isNullOrEmpty()) {
                prefs.edit()
                    .putString("token", jwt)
                    .putString("username", username)
                    .putString("photo_url", photoUrl)
                    .apply()
                Toast.makeText(requireContext(), "Telegram-вход выполнен!", Toast.LENGTH_SHORT).show()
                showCorrectScreen(requireView())
            }
            requireActivity().intent.data = null
        }
    }

    class CircleTransform : com.squareup.picasso.Transformation {
        override fun transform(source: Bitmap): Bitmap {
            val size = Math.min(source.width, source.height)
            val x = (source.width - size) / 2
            val y = (source.height - size) / 2
            val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
            if (squaredBitmap != source) source.recycle()
            val bitmap = Bitmap.createBitmap(size, size, source.config ?: Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = Paint()
            val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.shader = shader
            paint.isAntiAlias = true
            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)
            squaredBitmap.recycle()
            return bitmap
        }
        override fun key() = "circle"
    }

    private fun syncTunnelsWithProfile() {
        val token = prefs.getString("token", null) ?: return
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/profile"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) return
                val resp = response.body?.string() ?: return
                try {
                    val obj = JSONObject(resp)
                    val subscriptionsArr = obj.optJSONArray("subscriptions") ?: return
                    val activeServers = mutableSetOf<String>()
                    for (i in 0 until subscriptionsArr.length()) {
                        val sObj = subscriptionsArr.getJSONObject(i)
                        if (sObj.optBoolean("active", false)) {
                            activeServers.add(sObj.optString("id"))
                        }
                    }
                    MainScope().launch {
                        val tunnelManager = Application.getTunnelManager()
                        // Make a snapshot of tunnels to avoid concurrent modifications
                        val tunnels = tunnelManager.getTunnels().toList()
                        val toRemove = tunnels.filter {
                            it.name.startsWith("idrug_") &&
                                it.name.removePrefix("idrug_") !in activeServers
                        }
                        for (tunnel in toRemove) {
                            tunnelManager.delete(tunnel)
                        }
                    }
                } catch (_: Exception) {}
            }
        })
    }

    private fun safeUi(block: () -> Unit) {
        if (destroyed || !isAdded || activity == null) return
        handler.post {
            if (!destroyed && isAdded && activity != null) block()
        }
    }
}
