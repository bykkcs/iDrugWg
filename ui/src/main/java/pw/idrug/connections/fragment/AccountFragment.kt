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

    override fun onDestroyView() {
        super.onDestroyView()
        destroyed = true
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
                btnDownload.isEnabled = true
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedServerId = null
                selectedServerName = null
                btnDownload.isEnabled = false
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
                            val status = obj.optString("status", "unknown")
                            val expDateStr = obj.optString("expiration_date", "")
                            val username = obj.optString("client_name", prefs.getString("username", "") ?: "")
                            val photoUrl = obj.optString("photo_url", null)
                            val subsArr = obj.optJSONArray("subscriptions")
                            val activeSubs = mutableSetOf<String>()
                            if (subsArr != null) {
                                for (i in 0 until subsArr.length()) {
                                    activeSubs.add(subsArr.optString(i))
                                }
                            }
                            prefs.edit().putString("username", username).apply()
                            if (!photoUrl.isNullOrEmpty()) prefs.edit().putString("photo_url", photoUrl).apply()
                            showAccountScreen(view, username, photoUrl, status, expDateStr)
                            removeInactiveTunnelsIfNeeded(status, activeSubs)
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
        view.findViewById<TextView>(R.id.text_current_user).text = "Вход через Telegram"
        view.findViewById<TextView>(R.id.status_text).text = ""
        view.findViewById<TextView>(R.id.text_expiration).text = ""
        view.findViewById<ImageView>(R.id.avatar_image).setImageResource(R.drawable.ic_avatar_placeholder)
    }

    private fun showAccountScreen(view: View, username: String, photoUrl: String?, status: String, expDateStr: String?) {
        view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout).visibility = View.VISIBLE
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
        view.findViewById<Button>(R.id.btn_download).visibility = if (status == "active") View.VISIBLE else View.GONE
        view.findViewById<Button>(R.id.btn_renew).visibility = View.VISIBLE
        view.findViewById<TextView>(R.id.text_current_user).text = "Ваш логин: $username"
        view.findViewById<TextView>(R.id.status_text).text =
            when (status) {
                "revoked" -> "Доступ к конфигу заблокирован. Оплатите подписку для восстановления."
                "expired" -> "Срок действия подписки истёк. Оплатите для получения нового конфига."
                "active" -> "Скачайте конфиг для автоматического импорта в приложение"
                else -> "Статус аккаунта: $status"
            }
        view.findViewById<TextView>(R.id.text_expiration).text = expDateStr?.let {
            if (status == "active" && it.isNotEmpty()) {
                val fixed = it.replace("T", " ").substring(0, 19)
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val expDate = sdf.parse(fixed)
                val now = Date()
                val daysLeft = ((expDate.time - now.time) / (1000 * 60 * 60 * 24)).toInt()
                "Дней до окончания: $daysLeft"
            } else ""
        } ?: ""
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

    private fun removeInactiveTunnelsIfNeeded(status: String, activeSubs: Set<String>) {
        MainScope().launch {
            try {
                val tunnelManager = Application.getTunnelManager()
                val tunnels = tunnelManager.getTunnels()
                val deleteAll = status == "revoked" || status == "expired"
                tunnels.filter { tunnel ->
                    tunnel.name.startsWith("idrug_") &&
                        (deleteAll || tunnel.name.removePrefix("idrug_") !in activeSubs)
                }.forEach { tunnelManager.delete(it) }
            } catch (_: Exception) {
            }
        }
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

    private fun safeUi(block: () -> Unit) {
        if (destroyed || !isAdded || activity == null) return
        handler.post {
            if (!destroyed && isAdded && activity != null) block()
        }
    }
}
