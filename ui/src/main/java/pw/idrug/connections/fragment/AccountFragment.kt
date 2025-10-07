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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pw.idrug.connections.TunnelSyncManager
import okhttp3.*
import com.google.firebase.messaging.FirebaseMessaging
import pw.idrug.connections.R
import pw.idrug.connections.dialog.CodeInputDialogFragment
import pw.idrug.connections.dialog.SubscriptionDialogFragment
import androidx.fragment.app.DialogFragment
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
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.ForegroundColorSpan
import android.graphics.Typeface
import android.graphics.Color
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView

class AccountFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private var destroyed = false

    private var selectedServerId: String? = null
    private var selectedServerName: String? = null
    private var serverList: List<Pair<String, String>> = listOf()
    private var qrPollingTimer: Timer? = null
    private var isLogoutRunning = false

    // Subscription model. Only the active field matters.
    private data class Subscription(
        val location: String,
        val name: String,
        val expires: String?,
        val forever: Boolean,
        val active: Boolean
    ) {
        fun isActive(): Boolean = active
    }
    private var subscriptions: List<Subscription> = emptyList()

    // --- Локализация названия сервера ---
    private fun getServerName(location: String?): String {
        if (location.isNullOrBlank()) {
            Log.w("AccountFragment", "getServerName: empty or null location")
            return "Unknown"
        }
        return when (location) {
            "germany" -> getString(R.string.server_germany)
            "multihop" -> getString(R.string.server_multihop_germany)
            "bulgaria" -> getString(R.string.server_bulgaria)
            "madrid" -> getString(R.string.server_madrid)
            else -> {
                Log.w("AccountFragment", "getServerName: unrecognized location $location")
                location
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyed = true
        qrPollingTimer?.cancel()
        (parentFragmentManager.findFragmentByTag("code_input") as? DialogFragment)?.dismissAllowingStateLoss()
        (parentFragmentManager.findFragmentByTag("link_code") as? DialogFragment)?.dismissAllowingStateLoss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        destroyed = false
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onResume() {
        super.onResume()
        handleDeepLink(requireActivity().intent)
        if (isLoggedIn()) {
            loadProfileAndSetupUI(requireView())
        }
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
            handleDownloadConfig()
        }
        view.findViewById<Button>(R.id.btn_link_device).setOnClickListener {
            pw.idrug.connections.dialog.LinkCodeDialogFragment()
                .show(parentFragmentManager, "link_code")
        }
        view.findViewById<Button>(R.id.btn_renew).setOnClickListener {
            SubscriptionDialogFragment().show(parentFragmentManager, "subscription")
        }

        view.findViewById<Button>(R.id.btn_referral).setOnClickListener {
            val tgId = prefs.getString("telegram_id", "") ?: ""
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Моя реферальная ссылка: https://idrug.pw/login?ref=$tgId")
            }
            startActivity(Intent.createChooser(shareIntent, null))
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
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/servers"
        client.newCall(Request.Builder().url(url).build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                safeUi {
                    if (!isLoggedIn()) return@safeUi
                    serverList = listOf(
                        "germany" to getServerName("germany"),
                        "multihop" to getServerName("multihop"),
                        "bulgaria" to getServerName("bulgaria"),
                        "madrid" to getServerName("madrid")
                    )
                    setupServerSpinner(view)
                    loadProfileAndSetupUI(view)
                }
            }
            override fun onResponse(call: Call, response: Response) {
                safeUi {
                    if (!isLoggedIn()) return@safeUi
                    if (response.isSuccessful) {
                        val arr = JSONArray(response.body?.string() ?: "[]")
                        serverList = List(arr.length()) {
                            val obj = arr.getJSONObject(it)
                            val id = obj.getString("id")
                            id to getServerName(id)
                        }
                    } else {
                        serverList = listOf(
                            "germany" to getServerName("germany"),
                            "multihop" to getServerName("multihop"),
                            "bulgaria" to getServerName("bulgaria"),
                            "madrid" to getServerName("madrid")
                        )
                    }
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
                    if (prefs.getString("token", null) != token) return@safeUi
                    setLoading(false)
                    Toast.makeText(requireContext(), getString(R.string.network_error_msg, e.message), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onResponse(call: Call, response: Response) {
                val resp = response.body?.string() ?: ""
                safeUi {
                    if (prefs.getString("token", null) != token) return@safeUi
                    setLoading(false)
                    if (response.code == 401) {
                        showLoginScreen(view)
                    } else if (response.isSuccessful) {
                        try {
                            val obj = JSONObject(resp)
                            val subsArr = obj.optJSONArray("subscriptions") ?: JSONArray()
                            val subsList = mutableListOf<Subscription>()
                            for (i in 0 until subsArr.length()) {
                                val o = subsArr.getJSONObject(i)
                                val id = o.optString("location", o.optString("id", ""))
                                val name = getServerName(id)
                                val expires = o.optString("expires")
                                val forever = o.optBoolean("forever", false)
                                val active = o.optBoolean("active", false)
                                subsList.add(Subscription(id, name, expires, forever, active))
                            }
                            subscriptions = subsList
                            val username = obj.optString("username", prefs.getString("username", "") ?: "")
                            val photoUrl = obj.optString("photo_url")
                            val telegramId = obj.optString("telegram_id")
                            prefs.edit().putString("username", username).apply()
                            if (!photoUrl.isNullOrEmpty()) prefs.edit().putString("photo_url", photoUrl).apply()
                            if (!telegramId.isNullOrEmpty()) {
                                prefs.edit().putString("telegram_id", telegramId).apply()
                                FirebaseMessaging.getInstance().subscribeToTopic("user_$telegramId")
                            }
                            showAccountScreen(view, username, photoUrl, subsList)
                            syncTunnelsWithProfile()
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), getString(R.string.profile_processing_error, e.message), Toast.LENGTH_SHORT).show()
                            subscriptions = emptyList()
                            showAccountScreen(view, prefs.getString("username", "") ?: "", prefs.getString("photo_url", null), emptyList())
                        }
                    } else {
                        Toast.makeText(requireContext(), getString(R.string.profile_retrieval_failed, response.code), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoginScreen(view: View) {
        view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.VISIBLE
        val linkButton = view.findViewById<Button>(R.id.btn_link_device)
        linkButton.visibility = View.GONE
        view.findViewById<Button>(R.id.btn_download).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_renew).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_referral).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout).visibility = View.GONE
        view.findViewById<Spinner>(R.id.spinner_server).visibility = View.GONE
        view.findViewById<TextView>(R.id.text_server_choice).visibility = View.GONE
        view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
        view.findViewById<TextView>(R.id.text_current_user).text = getString(R.string.login_via_telegram)
        view.findViewById<TextView>(R.id.status_text).text = ""
        view.findViewById<TextView>(R.id.text_expiration).text = ""
        view.findViewById<ImageView>(R.id.avatar_image).setImageResource(R.drawable.ic_avatar_placeholder)
    }

    private fun showAccountScreen(view: View, username: String, photoUrl: String?, subs: List<Subscription>) {
        val tgId = prefs.getString("telegram_id", null)
        if (!tgId.isNullOrEmpty()) {
            FirebaseMessaging.getInstance().subscribeToTopic("user_$tgId")
        }
        view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.GONE
        val linkButton = view.findViewById<Button>(R.id.btn_link_device)
        linkButton.visibility = View.VISIBLE
        linkButton.text = getString(R.string.link_device)
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
        view.findViewById<Button>(R.id.btn_referral).visibility = View.VISIBLE
        view.findViewById<TextView>(R.id.text_current_user).text = getString(R.string.your_username, username)

        val statusTextView = view.findViewById<TextView>(R.id.status_text)
        val lines = mutableListOf<CharSequence>()

        subs.forEach { s ->
            val name = getServerName(s.location)
            val expFixed = s.expires?.replace("T", " ")?.substring(0, 19) ?: ""
            val days: Int? = if (expFixed.isNotEmpty()) {
                try {
                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val exp = sdf.parse(expFixed)
                    val now = Date()
                    if (exp != null) {
                        ((exp.time - now.time) / (1000 * 60 * 60 * 24)).toInt()
                    } else null
                } catch (_: Exception) { null }
            } else null

            val str: String = when {
                s.forever -> getString(R.string.subscription_forever, name)
                s.active && expFixed.isNotEmpty() && days != null ->
                    getString(R.string.subscription_active_days, name, expFixed.substring(0, 10).replace("-", "."), days)
                !s.active -> getString(R.string.subscription_inactive, name)
                else -> name
            }

            val color = when {
                s.forever -> Color.parseColor("#388E3C")
                days != null && days >= 15 -> Color.parseColor("#388E3C")
                days != null && days in 8..14 -> Color.parseColor("#FBC02D")
                days != null && days in 0..7 -> Color.parseColor("#D32F2F")
                else -> null
            }
            if (color != null && s.active) {
                val spannable = SpannableString(str)
                spannable.setSpan(ForegroundColorSpan(color), 0, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                lines.add(spannable)
            } else {
                lines.add(str)
            }
        }

        statusTextView.text = android.text.TextUtils.join("\n", lines)
        view.findViewById<TextView>(R.id.text_expiration).text = ""
        updateDownloadButtonState(view)
    }

    private fun updateDownloadButtonState(view: View) {
        val btnDownload = view.findViewById<Button>(R.id.btn_download)
        val id = selectedServerId
        btnDownload.isEnabled = id != null && subscriptions.any { it.location == id && it.active }
    }

    private fun setLoading(loading: Boolean) {
        view?.findViewById<View>(R.id.loading_overlay)?.visibility = if (loading) View.VISIBLE else View.GONE
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.isEnabled = !loading
    }
    
private fun afterLogout(view: View) {
    if (isLogoutRunning) return
    isLogoutRunning = true
    setLoading(true)
    prefs.edit().clear().apply()
    qrPollingTimer?.cancel()
    qrPollingTimer = null

    // Удаляем туннели сразу, синхронно!
    try {
        runBlocking {
            val tunnelManager = Application.getTunnelManager()
            val tunnels = tunnelManager.getTunnels()
            tunnels.filter { it.name.startsWith("idrug_") }.forEach { tunnel ->
                try {
                    tunnelManager.delete(tunnel)
                    Log.i("AccountFragment", "Tunnel deleted: ${tunnel.name}")
                } catch (te: Exception) {
                    Log.e("AccountFragment", "Tunnel delete error: ${tunnel.name}", te)
                }
            }
        }
    } catch (e: Exception) {
        Log.e("AccountFragment", "Error while deleting tunnels during logout", e)
    }

    deleteDownloadedConfigs()

    // После логаута отменяем все операции синхронизации
    TunnelSyncManager.cancelAll()

    safeUi {
        if (isLoggedIn()) {
            setLoading(false)
            isLogoutRunning = false
            return@safeUi
        }
        showLoginScreen(view)
        Toast.makeText(requireContext(), getString(R.string.logged_out), Toast.LENGTH_SHORT).show()
        setLoading(false)
        isLogoutRunning = false
    }
}

    private fun deleteDownloadedConfigs() {
        val filesDir = context?.filesDir ?: return
        filesDir.listFiles()?.forEach { file ->
            if (file.isFile && file.name.startsWith("wg_idrug_") && file.name.endsWith(".conf")) {
                val deleted = file.delete()
                if (!deleted) {
                    Log.w("AccountFragment", "Failed to delete config file: ${file.absolutePath}")
                } else {
                    Log.i("AccountFragment", "Config file deleted: ${file.name}")
                }
            }
        }
    }

    private fun handleDownloadConfig() {
        if (selectedServerId == null) {
            Toast.makeText(requireContext(), getString(R.string.select_server), Toast.LENGTH_SHORT).show()
            return
        }
        val serverId = selectedServerId ?: return
        if (!subscriptions.any { it.location == serverId && it.active }) {
            Toast.makeText(requireContext(), getString(R.string.subscription_inactive_msg), Toast.LENGTH_SHORT).show()
            return
        }
        val tunnelName = "idrug_$serverId"
        setLoading(true)
        val token = prefs.getString("token", null)
        if (token == null) {
            Toast.makeText(requireContext(), getString(R.string.login_via_telegram), Toast.LENGTH_SHORT).show()
            setLoading(false)
            return
        }
        TunnelSyncManager.scope.launch {
            if (prefs.getString("token", null).isNullOrEmpty()) {
                setLoading(false)
                return@launch
            }
            val tunnelManager = Application.getTunnelManager()
            val tunnels = tunnelManager.getTunnels()
            val tunnel = tunnels.firstOrNull { it.name == tunnelName }
            if (tunnel != null) {
                safeUi {
                    Toast.makeText(requireContext(), getString(R.string.config_already_added), Toast.LENGTH_SHORT).show()
                    setLoading(false)
                }
                return@launch
            }
            downloadConfig(token, serverId) { success, configOrError ->
                safeUi {
                    if (prefs.getString("token", null).isNullOrEmpty()) {
                        setLoading(false)
                        return@safeUi
                    }
                    setLoading(false)
                    if (success) {
                        val file = File(requireContext().filesDir, "wg_$tunnelName.conf")
                        file.writeText(configOrError ?: "")
                        TunnelSyncManager.scope.launch {
                            try {
                                val config = Config.parse(file.bufferedReader())
                                tunnelManager.create(tunnelName, config)
                                file.delete()
                                Toast.makeText(requireContext(), getString(R.string.tunnel_added), Toast.LENGTH_SHORT).show()
                                loadProfileAndSetupUI(requireView())
                            } catch (e: Exception) {
                                Toast.makeText(requireContext(), getString(R.string.tunnel_creation_error, e.message), Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), getString(R.string.error_with_message, configOrError), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
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
                    val username = json.optString("username")
                    val photoUrl = json.optString("photo_url")
                    val telegramId = json.optString("telegram_id")
                    if (!telegramId.isNullOrEmpty()) {
                        prefs.edit().putString("telegram_id", telegramId).apply()
                        FirebaseMessaging.getInstance().subscribeToTopic("user_$telegramId")
                    }
                    callback(true, username, photoUrl)
                } else {
                    callback(false, null, null)
                }
            }
        })
    }

    private fun downloadConfig(token: String, serverId: String, callback: (Boolean, String?) -> Unit) {
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

    private fun renewSubscription() {
        val message = "Hello! I want to purchase or renew VPN. My login: " +
            (prefs.getString("username", "") ?: "unknown")

        val url = "https://t.me/idrug_vpn?start=" + Uri.encode(message)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), getString(R.string.unable_open_telegram), Toast.LENGTH_SHORT).show()
        }
    }


    private fun handleLinkCodeLogin(code: String) {
        linkAccountWithCode(code) { success, token, username, message ->
            safeUi {
                if (success && token != null && username != null) {
                    prefs.edit()
                        .putString("token", token)
                        .putString("username", username)
                        .apply()
                    Toast.makeText(requireContext(), getString(R.string.login_successful), Toast.LENGTH_SHORT).show()
                    showCorrectScreen(requireView())
                } else {
                    Toast.makeText(requireContext(), message ?: getString(R.string.login_via_telegram), Toast.LENGTH_LONG).show()
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
                callback(false, null, null, getString(R.string.network_error_msg, e.message))
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val obj = JSONObject(response.body?.string() ?: "{}")
                    val jwt = obj.optString("jwt")
                    val username = obj.optString("username")
                    val telegramId = obj.optString("telegram_id")
                    if (!jwt.isNullOrEmpty() && !username.isNullOrEmpty()) {
                        if (!telegramId.isNullOrEmpty()) {
                            prefs.edit().putString("telegram_id", telegramId).apply()
                            FirebaseMessaging.getInstance().subscribeToTopic("user_$telegramId")
                        }
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

    private fun handleDeepLink(intent: Intent?) {
        val data = intent?.data
        if (data != null && data.scheme == "idrug" && data.host == "auth") {
            val jwt = data.getQueryParameter("jwt")
            val username = data.getQueryParameter("username")
            val photoUrl = data.getQueryParameter("photo_url")
            val telegramId = data.getQueryParameter("telegram_id")
            if (!jwt.isNullOrEmpty() && !username.isNullOrEmpty()) {
                prefs.edit()
                    .putString("token", jwt)
                    .putString("username", username)
                    .putString("photo_url", photoUrl)
                    .apply()
                if (!telegramId.isNullOrEmpty()) {
                    prefs.edit().putString("telegram_id", telegramId).apply()
                    FirebaseMessaging.getInstance().subscribeToTopic("user_$telegramId")
                }
                Toast.makeText(requireContext(), getString(R.string.telegram_login_successful), Toast.LENGTH_SHORT).show()
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
            val bitmap = Bitmap.createBitmap(size, size, source.config)
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
                        activeServers.add(sObj.optString("location"))
                    }
                }
                TunnelSyncManager.scope.launch {
                    val tunnelManager = Application.getTunnelManager()
                    val tunnels = tunnelManager.getTunnels().toList()

                    // Только удаляем туннели, которые неактивны
                    val toRemove = tunnels.filter {
                        it.name.startsWith("idrug_") &&
                            it.name.removePrefix("idrug_") !in activeServers
                    }
                    for (tunnel in toRemove) {
                        try {
                            tunnelManager.delete(tunnel)
                            Log.i("AccountFragment", "Tunnel deleted: ${tunnel.name}")
                        } catch (te: Exception) {
                            Log.e("AccountFragment", "Tunnel delete error: ${tunnel.name}", te)
                        }
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
