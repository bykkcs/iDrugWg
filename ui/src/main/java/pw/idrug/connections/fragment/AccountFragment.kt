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
import pw.idrug.connections.dialog.CodeInputDialogFragment
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

class AccountFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private var destroyed = false

    private var selectedServerId: String? = null
    private var selectedServerName: String? = null
    private var serverList: List<Pair<String, String>> = listOf()
    private var qrPollingTimer: Timer? = null

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
    private fun getServerName(location: String): String {
        return when (location) {
            "germany" -> getString(R.string.server_germany)
            "multihop" -> getString(R.string.server_multihop_germany)
            "bulgaria" -> getString(R.string.server_bulgaria)
            "madrid" -> getString(R.string.server_madrid)
            else -> location
        }
    }

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
                            if (success) "QR login confirmed" else "Confirmation error: $message",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (success) loadProfileAndSetupUI(requireView())
                    }
                }
            } else {
                Toast.makeText(requireContext(), "QR code not recognized", Toast.LENGTH_SHORT).show()
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
        view.findViewById<Button>(R.id.btn_link_device).setOnClickListener {
            if (isLoggedIn()) {
                pw.idrug.connections.dialog.LinkCodeDialogFragment()
                    .show(parentFragmentManager, "link_code")
            } else {
                CodeInputDialogFragment { code ->
                    handleLinkCodeLogin(code)
                }.show(parentFragmentManager, "code_input")
            }
        }
        view.findViewById<Button>(R.id.btn_renew).setOnClickListener {
            setLoading(true)
            val token = prefs.getString("token", null)
            if (token == null) {
                Toast.makeText(requireContext(), "Please log in via Telegram first", Toast.LENGTH_SHORT).show()
                setLoading(false)
                return@setOnClickListener
            }
            renewSubscription { success, resp ->
                safeUi {
                    setLoading(false)
                    Toast.makeText(requireContext(), if (success) "Subscription renewed" else "Error: $resp", Toast.LENGTH_SHORT).show()
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
        val client = OkHttpClient()
        val url = "https://idrug.pw/api/servers"
        client.newCall(Request.Builder().url(url).build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                serverList = listOf(
                    "germany" to getServerName("germany"),
                    "multihop" to getServerName("multihop"),
                    "bulgaria" to getServerName("bulgaria"),
                    "madrid" to getServerName("madrid")
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
                    Toast.makeText(requireContext(), "Network error: ${e.message}", Toast.LENGTH_SHORT).show()
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
                            val subsList = mutableListOf<Subscription>()
                            for (i in 0 until subsArr.length()) {
                                val o = subsArr.getJSONObject(i)
                                val id = o.optString("location", o.optString("id", ""))
                                val name = getServerName(id)
                                val expires = o.optString("expires", null)
                                val forever = o.optBoolean("forever", false)
                                val active = o.optBoolean("active", false)
                                subsList.add(Subscription(id, name, expires, forever, active))
                            }
                            subscriptions = subsList
                            val username = obj.optString("username", prefs.getString("username", "") ?: "")
                            val photoUrl = obj.optString("photo_url", null)
                            prefs.edit().putString("username", username).apply()
                            if (!photoUrl.isNullOrEmpty()) prefs.edit().putString("photo_url", photoUrl).apply()
                            showAccountScreen(view, username, photoUrl, subsList)
                            syncTunnelsWithProfile()
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), "Profile processing error: ${e.message}", Toast.LENGTH_SHORT).show()
                            subscriptions = emptyList()
                            showAccountScreen(view, prefs.getString("username", "") ?: "", prefs.getString("photo_url", null), emptyList())
                        }
                    } else {
                        Toast.makeText(requireContext(), "Failed to retrieve profile: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoginScreen(view: View) {
        view.findViewById<Button>(R.id.btn_login_telegram).visibility = View.VISIBLE
        val linkButton = view.findViewById<Button>(R.id.btn_link_device)
        linkButton.visibility = View.VISIBLE
        linkButton.text = getString(R.string.login_with_code)
        view.findViewById<Button>(R.id.btn_download).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_renew).visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout).visibility = View.GONE
        view.findViewById<Spinner>(R.id.spinner_server).visibility = View.GONE
        view.findViewById<TextView>(R.id.text_server_choice).visibility = View.GONE
        view.findViewById<ImageView>(R.id.qr_code_image).visibility = View.GONE
        view.findViewById<TextView>(R.id.text_current_user).text = getString(R.string.login_via_telegram_or_qr)
        view.findViewById<TextView>(R.id.status_text).text = ""
        view.findViewById<TextView>(R.id.text_expiration).text = ""
        view.findViewById<ImageView>(R.id.avatar_image).setImageResource(R.drawable.ic_avatar_placeholder)
    }

    private fun showAccountScreen(view: View, username: String, photoUrl: String?, subs: List<Subscription>) {
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
                    ((exp.time - now.time) / (1000 * 60 * 60 * 24)).toInt()
                } catch (_: Exception) { null }
            } else null

            val str: String = when {
                s.forever -> getString(R.string.subscription_forever, name)
                s.active && expFixed.isNotEmpty() && days != null ->
                    getString(R.string.subscription_active_days, name, expFixed.substring(0, 10).replace("-", "."), days)
                !s.active -> getString(R.string.subscription_inactive, name)
                else -> name
            }

            if (days != null && days <= 7 && s.active && !s.forever) {
                val spannable = SpannableString(str)
                spannable.setSpan(StyleSpan(Typeface.BOLD), 0, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannable.setSpan(ForegroundColorSpan(Color.parseColor("#D32F2F")), 0, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
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
        activity?.findViewById<View>(R.id.interaction_blocker)?.visibility = if (loading) View.VISIBLE else View.GONE
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
            } catch (e: Exception) {}
            safeUi {
                showLoginScreen(view)
                Toast.makeText(requireContext(), "You have logged out", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleDownloadConfig(view: View) {
        if (selectedServerId == null) {
            Toast.makeText(requireContext(), "Select a server", Toast.LENGTH_SHORT).show()
            return
        }
        val serverId = selectedServerId ?: return
        if (!subscriptions.any { it.location == serverId && it.active }) {
            Toast.makeText(requireContext(), "Subscription inactive", Toast.LENGTH_SHORT).show()
            return
        }
        val tunnelName = "idrug_$serverId"
        setLoading(true)
        val token = prefs.getString("token", null)
        if (token == null) {
            Toast.makeText(requireContext(), "Log in via Telegram", Toast.LENGTH_SHORT).show()
            setLoading(false)
            return
        }
        MainScope().launch {
            val tunnelManager = Application.getTunnelManager()
            val tunnels = tunnelManager.getTunnels()
            val tunnel = tunnels.firstOrNull { it.name == tunnelName }
            if (tunnel != null) {
                safeUi {
                    Toast.makeText(requireContext(), "Config already added", Toast.LENGTH_SHORT).show()
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
                                Toast.makeText(requireContext(), "Tunnel added", Toast.LENGTH_SHORT).show()
                                loadProfileAndSetupUI(requireView())
                            } catch (e: Exception) {
                                Toast.makeText(requireContext(), "Tunnel creation error: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), "Error: $configOrError", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

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
            Toast.makeText(requireContext(), "QR code generation error", Toast.LENGTH_SHORT).show()
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
                            Toast.makeText(requireContext(), "QR login confirmed!", Toast.LENGTH_SHORT).show()
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
                    val username = json.optString("username", null)
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
            callback(false, "You are not authenticated")
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
        val message = "Hello! I want to purchase or renew VPN. My login: " +
            (prefs.getString("username", "") ?: "unknown")

        val url = "https://t.me/idrug_vpn?start=" + Uri.encode(message)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Unable to open Telegram", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    showCorrectScreen(requireView())
                } else {
                    Toast.makeText(requireContext(), message ?: getString(R.string.login_via_telegram_or_qr), Toast.LENGTH_LONG).show()
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
                Toast.makeText(requireContext(), "Telegram login successful!", Toast.LENGTH_SHORT).show()
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
                            activeServers.add(sObj.optString("location"))
                        }
                    }
                    MainScope().launch {
                        val tunnelManager = Application.getTunnelManager()
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
