package pw.idrug.connections.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import com.google.android.material.color.MaterialColors
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.LinkedHashSet
import java.util.concurrent.TimeUnit
import kotlin.math.min
import kotlin.math.roundToInt
import pw.idrug.connections.R
import pw.idrug.connections.TunnelSyncManager
import pw.idrug.connections.dialog.SubscriptionDialogFragment
import pw.idrug.connections.dialog.LinkCodeDialogFragment
import pw.idrug.connections.dialog.LoadingDialogFragment
import pw.idrug.connections.Application
import pw.idrug.connections.config.Config
import pw.idrug.connections.util.UserKnobs

class AccountFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private val handler = Handler(Looper.getMainLooper())
    private var destroyed = false

    private var selectedServerId: String? = null
    private var serverList: List<Pair<String, String>> = listOf()
    private var qrPollingTimer: Timer? = null
    @Volatile private var isLogoutRunning = false
    private var pingJob: Job? = null
    private val serverPings = mutableMapOf<String, PingResult>()
    private var cachedProfile: ProfileSnapshot? = null
    private var cachedProfileTimestamp: Long = 0L
    private var profileCall: Call? = null
    private var profileTimeoutRunnable: Runnable? = null

    private val serverOrder = listOf("germany", "multihop", "bulgaria", "madrid")

    // Subscription model. Only the active field matters.
    private data class Subscription(
        val location: String,
        val name: String,
        val expires: String?,
        val forever: Boolean,
        val active: Boolean
    )
    private var subscriptions: List<Subscription> = emptyList()

    private enum class PingState { LOADING, SUCCESS, ERROR }

    private data class PingResult(
        val state: PingState,
        val latencyMs: Int? = null
    )

    private data class TcpEndpoint(val host: String, val port: Int)

    private data class ProfileSnapshot(
        val username: String,
        val photoUrl: String?,
        val subscriptions: List<Subscription>
    )

    private val pingEndpoints = mapOf(
        "germany" to TcpEndpoint("194.113.233.251", 51821),
        "madrid" to TcpEndpoint("159.255.34.41", 51821),
        "bulgaria" to TcpEndpoint("185.232.170.117", 51821)
    )

    private fun resetPingState() {
        pingJob?.cancel()
        pingJob = null
        serverPings.clear()
    }

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

    private fun getServerFlag(location: String?): String {
        return when (location) {
            "germany" -> "\uD83C\uDDE9\uD83C\uDDEA" // 🇩🇪
            "multihop" -> "\uD83C\uDF10" // 🌐
            "bulgaria" -> "\uD83C\uDDE7\uD83C\uDDEC" // 🇧🇬
            "madrid" -> "\uD83C\uDDEA\uD83C\uDDF8" // 🇪🇸
            else -> ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyed = true
        qrPollingTimer?.cancel()
        resetPingState()
        cancelProfileCall()
        (parentFragmentManager.findFragmentByTag("code_input") as? DialogFragment)?.dismissAllowingStateLoss()
        (parentFragmentManager.findFragmentByTag("link_code") as? DialogFragment)?.dismissAllowingStateLoss()
        LoadingDialogFragment.dismiss(parentFragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        destroyed = false
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onResume() {
        super.onResume()
        handleDeepLink(requireActivity().intent)
        if (isLoggedIn()) {
            loadServersAndProfileUI(requireView())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        setupListeners(view)
        setupServerDropdown(view)
        showCorrectScreen(view)
    }

    private fun setupListeners(view: View) {
        view.findViewById<Button>(R.id.btn_login_telegram).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://idrug.pw/login?redirect=idrug://auth"))
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            // НЕ блокируем main — уводим логаут в корутину
            if (isLogoutRunning) return@setOnClickListener
            viewLifecycleOwner.lifecycleScope.launch {
                setLoading(true)
                try {
                    performLogoutSafely(view)
                } finally {
                    setLoading(false)
                }
            }
        }
        view.findViewById<Button>(R.id.btn_link_device).setOnClickListener {
            LinkCodeDialogFragment().show(parentFragmentManager, "link_code")
        }
        view.findViewById<Button>(R.id.btn_download).setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                handleDownloadConfig()
            }
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
            loadServersAndProfileUI(view)
        } else {
            showLoginScreen(view)
        }
    }

    private fun loadServersAndProfileUI(view: View) {
        val snapshot = cachedProfile
        val now = SystemClock.elapsedRealtime()
        val useCache = snapshot != null && now - cachedProfileTimestamp <= PROFILE_CACHE_VALIDITY_MS
        if (useCache) {
            val cached = snapshot!!
            showAccountScreen(view, cached.username, cached.photoUrl, cached.subscriptions, cacheResult = false, refreshPings = false)
            loadProfileAndSetupUI(view, showLoading = false)
        } else {
            setLoading(true)
            applyLoadingState(view)
            loadProfileAndSetupUI(view, showLoading = true)
        }
    }

    private fun safeUi(block: () -> Unit) {
        if (destroyed) return
        handler.post {
            if (!destroyed) block()
        }
    }

    private fun setLoading(isLoading: Boolean) {
        safeUi {
            if (!isAdded) return@safeUi
            if (isLoading) {
                LoadingDialogFragment.show(parentFragmentManager)
            } else {
                LoadingDialogFragment.dismiss(parentFragmentManager)
            }
            view?.let { root ->
                root.findViewById<Button>(R.id.btn_login_telegram)?.isEnabled = !isLoading
                root.findViewById<Button>(R.id.btn_logout)?.isEnabled = !isLoading
                root.findViewById<Button>(R.id.btn_link_device)?.isEnabled = !isLoading
                root.findViewById<Button>(R.id.btn_renew)?.isEnabled = !isLoading
                root.findViewById<Button>(R.id.btn_referral)?.isEnabled = !isLoading
                root.findViewById<Button>(R.id.btn_download)?.isEnabled =
                    !isLoading && !selectedServerId.isNullOrEmpty()
            }
        }
    }

    private fun showLoginScreen(view: View) {
        qrPollingTimer?.cancel()
        selectedServerId = null
        subscriptions = emptyList()
        resetPingState()
        cachedProfile = null
        cachedProfileTimestamp = 0L
        cancelProfileCall()

        view.findViewById<View>(R.id.card_login)?.visibility = View.VISIBLE
        view.findViewById<View>(R.id.card_profile)?.visibility = View.GONE
        view.findViewById<View>(R.id.card_connection)?.visibility = View.GONE

        view.findViewById<Button>(R.id.btn_login_telegram)?.apply {
            visibility = View.VISIBLE
            isEnabled = true
        }
        view.findViewById<ImageView>(R.id.qr_code_image)?.visibility = View.GONE
        view.findViewById<TextView>(R.id.text_current_user)?.setText(R.string.login_via_telegram_title)
        updateSubscriptionSummary(view, emptyList())
        view.findViewById<ImageView>(R.id.avatar_image)?.setImageResource(R.drawable.ic_avatar_placeholder)
        setStatusMessage(view, null, false)
        // Ping indicator lives inside dropdown entries, nothing extra under the field
    }

    private fun showAccountScreen(
        view: View,
        username: String,
        photoUrl: String?,
        subs: List<Subscription>,
        cacheResult: Boolean,
        refreshPings: Boolean
    ) {
        if (cacheResult) {
            val cachedSubs = subs.map { it.copy() }
            cachedProfile = ProfileSnapshot(username, photoUrl, cachedSubs)
            cachedProfileTimestamp = SystemClock.elapsedRealtime()
        }

        subscriptions = subs
        updateServerListFromSubscriptions()

        view.findViewById<View>(R.id.card_login)?.visibility = View.GONE
        view.findViewById<View>(R.id.card_profile)?.visibility = View.VISIBLE
        view.findViewById<View>(R.id.card_connection)?.visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.qr_code_image)?.visibility = View.GONE

        val dropdownVisible = serverList.isNotEmpty()
        view.findViewById<TextInputLayout>(R.id.server_dropdown_container)?.visibility =
            if (dropdownVisible) View.VISIBLE else View.GONE
        view.findViewById<Button>(R.id.btn_download)?.visibility =
            if (dropdownVisible) View.VISIBLE else View.GONE

        view.findViewById<Button>(R.id.btn_download)?.isEnabled = serverList.isNotEmpty()
        view.findViewById<Button>(R.id.btn_link_device)?.apply {
            visibility = View.VISIBLE
            isEnabled = true
        }
        view.findViewById<Button>(R.id.btn_referral)?.apply {
            visibility = View.VISIBLE
            isEnabled = true
        }
        view.findViewById<Button>(R.id.btn_logout)?.apply {
            visibility = View.VISIBLE
            isEnabled = true
        }
        view.findViewById<Button>(R.id.btn_renew)?.isEnabled = true
        view.findViewById<Button>(R.id.btn_renew)?.visibility = View.VISIBLE
        view.findViewById<MaterialAutoCompleteTextView>(R.id.dropdown_server)?.isEnabled = serverList.isNotEmpty()

        val titleView = view.findViewById<TextView>(R.id.text_current_user)
        titleView?.text = if (username.isBlank()) {
            getString(R.string.login_via_telegram_title)
        } else {
            getString(R.string.your_username, username)
        }

        updateSubscriptionSummary(view, subs)
        val statusMessage = buildStatusMessage(subs)
        setStatusMessage(view, statusMessage, !statusMessage.isNullOrBlank())

        val avatarView = view.findViewById<ImageView>(R.id.avatar_image)
        if (avatarView != null) {
            val request = if (!photoUrl.isNullOrBlank()) {
                Picasso.get().load(photoUrl)
            } else {
                Picasso.get().load(R.drawable.ic_avatar_placeholder)
            }
            request
                .placeholder(R.drawable.ic_avatar_placeholder)
                .error(R.drawable.ic_avatar_placeholder)
                .transform(CircleTransformation())
                .into(avatarView)
        }

        if (refreshPings) {
            refreshServerPings(view)
        } else {
            setupServerDropdown(view)   // <-- listeners
            updateDropdownItems(view)
        }
        updateDownloadButtonState(view)
    }

    private fun applyLoadingState(view: View) {
        val username = prefs.getString("username", "") ?: ""
        val photoUrl = prefs.getString("photo_url", null)
        resetPingState()

        view.findViewById<View>(R.id.card_login)?.visibility = View.GONE
        view.findViewById<View>(R.id.card_profile)?.visibility = View.VISIBLE
        view.findViewById<View>(R.id.card_connection)?.visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.qr_code_image)?.visibility = View.GONE

        view.findViewById<Button>(R.id.btn_link_device)?.apply {
            visibility = View.VISIBLE
            isEnabled = false
        }
        view.findViewById<Button>(R.id.btn_renew)?.apply {
            visibility = View.VISIBLE
            isEnabled = false
        }
        view.findViewById<Button>(R.id.btn_referral)?.apply {
            visibility = View.VISIBLE
            isEnabled = false
        }
        view.findViewById<Button>(R.id.btn_logout)?.apply {
            visibility = View.VISIBLE
            isEnabled = false
        }
        view.findViewById<Button>(R.id.btn_download)?.apply {
            visibility = View.VISIBLE
            isEnabled = false
        }

        view.findViewById<TextInputLayout>(R.id.server_dropdown_container)?.visibility = View.VISIBLE
        view.findViewById<MaterialAutoCompleteTextView>(R.id.dropdown_server)?.apply {
            isEnabled = false
            setAdapter(null)
            setText("", false)
        }

        val titleView = view.findViewById<TextView>(R.id.text_current_user)
        titleView?.text = if (username.isBlank()) {
            getString(R.string.login_via_telegram_title)
        } else {
            getString(R.string.your_username, username)
        }

        view.findViewById<TextView>(R.id.text_expiration)?.text = ""
        setStatusMessage(view, null, false)

        val avatarView = view.findViewById<ImageView>(R.id.avatar_image)
        if (avatarView != null) {
            val request = if (!photoUrl.isNullOrBlank()) {
                Picasso.get().load(photoUrl)
            } else {
                Picasso.get().load(R.drawable.ic_avatar_placeholder)
            }
            request
                .placeholder(R.drawable.ic_avatar_placeholder)
                .error(R.drawable.ic_avatar_placeholder)
                .transform(CircleTransformation())
                .into(avatarView)
        }
    }

    private fun updateSubscriptionSummary(view: View, subs: List<Subscription>) {
        val summaryView = view.findViewById<TextView>(R.id.text_expiration) ?: return
        val orderedSubs = mutableListOf<Subscription>()
        val seenIds = mutableSetOf<String>()
        serverOrder.forEach { id ->
            val sub = subs.firstOrNull { it.location == id }
            if (sub != null) {
                orderedSubs += sub
                sub.location?.let { seenIds += it }
            }
        }
        subs.forEach { sub ->
            val location = sub.location
            if (location.isNullOrBlank()) {
                if (sub !in orderedSubs) orderedSubs += sub
            } else if (seenIds.add(location)) {
                orderedSubs += sub
            }
        }

        if (orderedSubs.isEmpty()) {
            summaryView.text = ""
            summaryView.visibility = View.INVISIBLE
            summaryView.setTextColor(
                MaterialColors.getColor(
                    summaryView,
                    com.google.android.material.R.attr.colorOnSurfaceVariant,
                    summaryView.currentTextColor
                )
            )
            return
        }

        summaryView.visibility = View.VISIBLE
        summaryView.text = orderedSubs.joinToString("\n") { subscriptionLine(it) }
        summaryView.setTextColor(
            MaterialColors.getColor(
                summaryView,
                com.google.android.material.R.attr.colorOnSurface,
                summaryView.currentTextColor
            )
        )
    }

    private fun subscriptionLine(sub: Subscription): String =
        when {
            sub.forever -> getString(R.string.subscription_forever, sub.name)
            !sub.active -> getString(R.string.subscription_inactive, sub.name)
            !sub.expires.isNullOrBlank() -> {
                val formatted = formatExpirationDate(sub.expires)
                val daysLeft = computeDaysLeft(sub.expires)
                if (daysLeft != null) {
                    getString(R.string.subscription_active_days, sub.name, formatted, daysLeft)
                } else {
                    getString(R.string.expires_on, formatted)
                }
            }
            else -> getString(R.string.subscription_inactive, sub.name)
        }

    private fun buildStatusMessage(subs: List<Subscription>): String {
        if (subs.isEmpty()) return ""
        return if (subs.any { it.active || it.forever }) {
            ""
        } else {
            getString(R.string.subscription_inactive_msg)
        }
    }

    private fun updateServerListFromSubscriptions() {
        val subscriptionIds = LinkedHashSet<String>()
        subscriptions.forEach { sub ->
            if (!sub.location.isNullOrBlank()) subscriptionIds.add(sub.location)
        }

        val orderedIds = mutableListOf<String>()
        serverOrder.forEach { id ->
            if (subscriptionIds.remove(id)) orderedIds.add(id)
        }
        orderedIds.addAll(subscriptionIds)

        val previousSelection = selectedServerId
        serverList = orderedIds.map { it to getServerName(it) }
        if (previousSelection !in orderedIds) {
            selectedServerId = null
        }
    }

    private fun refreshServerPings(root: View) {
        val ids = serverList.map { it.first }
        if (ids.isEmpty()) {
            resetPingState()
            updateDropdownItems(root)
            return
        }
        pingJob?.cancel()
        val idSet = ids.toSet()
        serverPings.keys.retainAll(idSet)
        ids.forEach { id ->
            serverPings[id] = PingResult(PingState.LOADING)
        }
        updateDropdownItems(root)

        pingJob = viewLifecycleOwner.lifecycleScope.launch {
            val token = prefs.getString("token", null)
            val client = OkHttpClient()
            for (id in ids) {
                val result = requestPing(client, token, id)
                serverPings[id] = result
                safeUi {
                    this@AccountFragment.view?.let { updateDropdownItems(it) }
                }
            }
        }
    }

    private suspend fun requestPing(client: OkHttpClient, token: String?, serverId: String): PingResult =
        withContext(Dispatchers.IO) {
            val endpoint = pingEndpoints[serverId]
            if (endpoint != null) {
                measureTcpPing(endpoint)
            } else {
                fetchPingViaApi(client, token, serverId)
            }
        }

    private fun measureTcpPing(endpoint: TcpEndpoint): PingResult {
        val samples = mutableListOf<Int>()
        repeat(3) {
            val latency = try {
                Socket().use { socket ->
                    val address = InetSocketAddress(endpoint.host, endpoint.port)
                    socket.soTimeout = PING_SOCKET_TIMEOUT_MS
                    val start = SystemClock.elapsedRealtimeNanos()
                    socket.connect(address, PING_CONNECT_TIMEOUT_MS)
                    val output = socket.getOutputStream()
                    output.write(byteArrayOf(0))
                    output.flush()
                    val end = SystemClock.elapsedRealtimeNanos()
                    val latencyMs = ((end - start) / 1_000_000.0).roundToInt().coerceAtLeast(0)
                    Log.d("Ping", "Accurate ping ${endpoint.host}:${endpoint.port} = ${latencyMs} ms")
                    latencyMs
                }
            } catch (_: Exception) {
                null
            }
            if (latency != null && latency > 0) {
                samples += latency
            }
        }
        return if (samples.isNotEmpty()) {
            PingResult(PingState.SUCCESS, samples.min())
        } else {
            PingResult(PingState.ERROR)
        }
    }

    private fun fetchPingViaApi(client: OkHttpClient, token: String?, serverId: String): PingResult {
        val requestBuilder = Request.Builder()
            .url("https://idrug.pw/api/ping?location=$serverId")
        if (!token.isNullOrBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        val request = requestBuilder.build()
        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    PingResult(PingState.ERROR)
                } else {
                    val latency = parsePingMs(response.body?.string())
                    if (latency != null) {
                        PingResult(PingState.SUCCESS, latency)
                    } else {
                        PingResult(PingState.ERROR)
                    }
                }
            }
        } catch (e: IOException) {
            Log.w("AccountFragment", "Failed to fetch ping for $serverId via API", e)
            PingResult(PingState.ERROR)
        }
    }

// Добавь внутрь класса AccountFragment (например, рядом с другими private suspend функциями)
private suspend fun downloadConfig(token: String, serverId: String): String? =
    withContext(Dispatchers.IO) {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://idrug.pw/api/profile/download?server=$serverId")
                .addHeader("Authorization", "Bearer $token")
                .build()

            client.newCall(request).execute().use { resp ->
                if (resp.isSuccessful) resp.body?.string() else null
            }
        } catch (e: IOException) {
            Log.w("AccountFragment", "Failed to download config for $serverId", e)
            null
        }
    }


    private fun parsePingMs(body: String?): Int? {
        if (body.isNullOrBlank()) return null
        val text = body.trim()
        if (text.startsWith("{") && text.endsWith("}")) {
            try {
                val json = JSONObject(text)
                val keys = listOf("rtt", "latency", "latency_ms", "ping", "ping_ms", "ms")
                for (key in keys) {
                    if (!json.has(key)) continue
                    val value = json.get(key)
                    val number = when (value) {
                        is Number -> value.toDouble()
                        is String -> value.toDoubleOrNull()
                        else -> null
                    }
                    if (number != null) {
                        return number.roundToInt()
                    }
                }
            } catch (_: Exception) {
                // Fallback to non-JSON parsing below
            }
        }
        val match = Regex("([0-9]+(?:\\.[0-9]+)?)").find(text)
        val value = match?.groupValues?.getOrNull(1)?.toDoubleOrNull()
        return value?.roundToInt()
    }

    private fun buildPingStatus(id: String, subscription: Subscription?): Pair<String, Int> {
        if (subscription != null && !subscription.active && !subscription.forever) {
            val inactiveText = getString(R.string.inactive)
            val inactiveColor = Color.parseColor("#D32F2F")
            return inactiveText to inactiveColor
        }

        val result = serverPings[id]
        if (result == null) {
            return getString(R.string.ping_loading) to Color.parseColor("#616161")
        }
        return when (result.state) {
            PingState.SUCCESS -> {
                val latency = result.latencyMs ?: return getString(R.string.ping_unavailable) to Color.parseColor("#D32F2F")
                val text = getString(R.string.ping_value_ms, latency)
                val color = when {
                    latency <= 80 -> Color.parseColor("#388E3C")
                    latency <= 160 -> Color.parseColor("#F9A825")
                    else -> Color.parseColor("#D32F2F")
                }
                text to color
            }
            PingState.LOADING -> {
                getString(R.string.ping_loading) to Color.parseColor("#616161")
            }
            PingState.ERROR -> {
                getString(R.string.ping_unavailable) to Color.parseColor("#D32F2F")
            }
        }
    }

    private fun formatExpirationDate(raw: String?): String {
        if (raw.isNullOrBlank()) return ""
        return try {
            val datePart = raw.substring(0, 10)
            datePart.replace("-", ".")
        } catch (_: Exception) {
            raw
        }
    }

    private fun computeDaysLeft(raw: String?): Int? {
        if (raw.isNullOrBlank()) return null
        return try {
            val date = raw.substring(0, 10)
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val expires = formatter.parse(date) ?: return null
            val today = formatter.parse(formatter.format(Date())) ?: return null
            val diff = expires.time - today.time
            (diff / (24 * 60 * 60 * 1000)).toInt().coerceAtLeast(0)
        } catch (_: Exception) {
            null
        }
    }

    private fun updateDropdownItems(view: View) {
        if (!isAdded) return
        val dropdown = view.findViewById<MaterialAutoCompleteTextView>(R.id.dropdown_server) ?: return
        if (serverList.isEmpty()) {
            dropdown.setAdapter(null)
            dropdown.setText("", false)
            dropdown.isEnabled = false
            return
        }
        dropdown.isEnabled = true
        val displayItems = serverList.map { formatServerDisplayName(it.first, it.second) }
        val adapter = object : ArrayAdapter<CharSequence>(
            requireContext(),
            R.layout.item_server_dropdown,
            displayItems
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent) as TextView
                view.text = getItem(position)
                return view
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent) as TextView
                view.text = getItem(position)
                return view
            }
        }
        dropdown.setAdapter(adapter)

        val targetId = selectedServerId?.takeIf { id -> serverList.any { it.first == id } }
            ?: serverList.first().first.also { selectedServerId = it }
        val idx = serverList.indexOfFirst { it.first == targetId }
        if (idx >= 0 && idx < displayItems.size) {
            dropdown.setText(displayItems[idx], false)
        } else {
            dropdown.setText("", false)
        }
    }

    private fun formatServerDisplayName(id: String, name: String): CharSequence {
        val flag = getServerFlag(id)
        val prefix = if (flag.isNotBlank()) "$flag $name" else name
        val subscription = subscriptions.firstOrNull { it.location == id }
        val (statusText, color) = buildPingStatus(id, subscription)
        val display = "$prefix — $statusText"
        return SpannableString(display).apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, prefix.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(color), prefix.length + 3, display.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun updateDownloadButtonState(view: View) {
        val button = view.findViewById<Button>(R.id.btn_download) ?: return
        val serverId = selectedServerId
        if (serverId.isNullOrBlank()) {
            button.isEnabled = false
            return
        }
        val sub = subscriptions.firstOrNull { it.location == serverId }
        button.isEnabled = sub?.active == true || sub?.forever == true
    }

    private fun setStatusMessage(view: View, message: String?, isError: Boolean) {
        val statusView = view.findViewById<TextView>(R.id.status_text) ?: return
        if (message.isNullOrBlank()) {
            statusView.text = ""
            statusView.visibility = View.GONE
            return
        }
        statusView.visibility = View.VISIBLE
        statusView.text = message
        val attr = if (isError) com.google.android.material.R.attr.colorError else com.google.android.material.R.attr.colorOnSurfaceVariant
        val color = MaterialColors.getColor(statusView, attr)
        statusView.setTextColor(color)
    }

private suspend fun handleDownloadConfig() {
    if (view == null) return
    val serverId = selectedServerId
    if (serverId.isNullOrBlank()) {
        Toast.makeText(requireContext(), R.string.select_server, Toast.LENGTH_SHORT).show()
        return
    }
    val token = prefs.getString("token", null)
    if (token.isNullOrBlank()) {
        Toast.makeText(requireContext(), R.string.login_telegram_first, Toast.LENGTH_SHORT).show()
        return
    }

    Log.d("AccountFragment", "Downloading config for serverId=$serverId")

    try {
        setLoading(true)

        // 1) качаем конфиг
        val configText = downloadConfig(token, serverId)
        if (configText.isNullOrBlank()) {
            safeUi { Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show() }
            return
        }

        val tunnelName = "idrug_$serverId"
        val tunnelManager = Application.getTunnelManager()
        val configFile = File(requireContext().filesDir, "$tunnelName.conf")

        // 2) если такой туннель уже есть — УДАЛЯЕМ СИНХРОННО
        withContext(Dispatchers.Main) {
            tunnelManager.getTunnels()
                .firstOrNull { it.name == tunnelName }
                ?.let { existing ->
                    runCatching { tunnelManager.delete(existing) }
                        .onFailure { e -> Log.w("AccountFragment", "Failed to delete existing tunnel $tunnelName", e) }
                }
        }

        // 3) подчищаем старый файл (если вдруг остался)
        withContext(Dispatchers.IO) {
            if (configFile.exists() && !configFile.delete()) {
                Log.w("AccountFragment", "Failed to delete old config file ${configFile.name}")
            }
        }

        // 4) перечитываем список — убеждаемся, что туннель реально исчез
        val tunnelsAfterDelete = withContext(Dispatchers.Main) { tunnelManager.getTunnels() }
        if (tunnelsAfterDelete.any { it.name == tunnelName }) {
            safeUi { Toast.makeText(requireContext(), R.string.config_already_added, Toast.LENGTH_SHORT).show() }
            return
        }

        // 5) создаём из временного файла
        val temp = File(requireContext().filesDir, "tmp_$tunnelName.conf")
        withContext(Dispatchers.IO) { temp.writeText(configText) }

        try {
            val parsed = withContext(Dispatchers.IO) {
                temp.inputStream().bufferedReader().use { Config.parse(it) }
            }
            // Создание ТОЛЬКО после успешного парса
            withContext(Dispatchers.Main) {
                tunnelManager.create(tunnelName, parsed)
            }
            safeUi { Toast.makeText(requireContext(), R.string.tunnel_added, Toast.LENGTH_SHORT).show() }
        } catch (e: Exception) {
            safeUi {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.tunnel_creation_error, e.message ?: ""),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } finally {
            withContext(Dispatchers.IO) {
                if (temp.exists()) temp.delete()
            }
        }

        // 6) синхронизация как и раньше
        syncTunnelsWithProfile()
    } finally {
        setLoading(false)
    }
}


    private fun syncTunnelsWithProfile() {
        val token = prefs.getString("token", null) ?: return
        val activeServers = subscriptions.filter { it.active || it.forever }.map { it.location }
        if (activeServers.isEmpty()) return
        viewLifecycleOwner.lifecycleScope.launch {
            if (!UserKnobs.accountAutoImport.first()) return@launch
            TunnelSyncManager.scope.launch {
                try {
                    synchronizeTunnels(token, activeServers)
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    Log.w("AccountFragment", "Failed to sync tunnels", e)
                }
            }
        }
    }

    private suspend fun synchronizeTunnels(token: String, activeServers: List<String>) {
        val tunnelManager = Application.getTunnelManager()
        val tunnels = tunnelManager.getTunnels()
        val activeNames = activeServers.map { "idrug_$it" }.toSet()
        val toRemove = tunnels.filter { it.name.startsWith("idrug_") && it.name !in activeNames }
        for (tunnel in toRemove) {
            try {
                tunnelManager.delete(tunnel)
            } catch (e: Exception) {
                Log.w("AccountFragment", "Failed to remove tunnel ${tunnel.name}", e)
            }
        }
        for (server in activeServers) {
            val name = "idrug_$server"
            if (tunnels.any { it.name == name }) continue
            val config = downloadConfig(token, server)
            if (config.isNullOrBlank()) continue
            val temp = File(requireContext().filesDir, "tmp_$name.conf")
            withContext(Dispatchers.IO) { temp.writeText(config) }
            try {
                val parsed = withContext(Dispatchers.IO) {
                    temp.inputStream().bufferedReader().use { Config.parse(it) }
                }
                tunnelManager.create(name, parsed)
            } catch (e: Exception) {
                Log.w("AccountFragment", "Unable to create tunnel $name", e)
            } finally {
                withContext(Dispatchers.IO) {
                    if (temp.exists()) temp.delete()
                }
            }
        }
    }

    private suspend fun removeSubscriptionTunnelsAndConfigs() {
        val tunnelManager = Application.getTunnelManager()
        val idrugTunnels = withContext(Dispatchers.Main) {
            tunnelManager.getTunnels().filter { it.name.startsWith("idrug_") }.toList()
        }
        withContext(Dispatchers.Main) {
            idrugTunnels.forEach { tunnel ->
                runCatching { tunnelManager.delete(tunnel) }
                    .onFailure { Log.w("AccountFragment", "Failed to delete tunnel ${'$'}{tunnel.name}", it) }
            }
        }
        withContext(Dispatchers.IO) {
            val dir = context?.filesDir ?: return@withContext
            dir.listFiles()?.forEach { file ->
                if (file.isFile && file.name.startsWith("idrug_") && file.name.endsWith(".conf")) {
                    if (!file.delete()) {
                        Log.w("AccountFragment", "Failed to delete config file ${'$'}{file.name}")
                    }
                }
            }
        }
    }

    private suspend fun performLogoutSafely(view: View) {
        if (isLogoutRunning) return
        isLogoutRunning = true
        val telegramId = prefs.getString("telegram_id", null)
        try {
            val token = prefs.getString("token", null)
            if (!token.isNullOrBlank()) {
                withContext(Dispatchers.IO) {
                    runCatching {
                        val body = "{}".toRequestBody("application/json".toMediaType())
                        val request = Request.Builder()
                            .url("https://idrug.pw/api/logout")
                            .addHeader("Authorization", "Bearer $token")
                            .post(body)
                            .build()
                        OkHttpClient().newCall(request).execute().close()
                    }
                }
            }

            removeSubscriptionTunnelsAndConfigs()
            TunnelSyncManager.cancelAll()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.w("AccountFragment", "Logout cleanup failed", e)
        } finally {
            prefs.edit().clear().apply()
            if (!telegramId.isNullOrBlank()) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("user_$telegramId")
            }
            safeUi {
                showLoginScreen(view)
                Toast.makeText(requireContext(), R.string.logged_out, Toast.LENGTH_SHORT).show()
            }
            isLogoutRunning = false
        }
    }

    private fun handleDeepLink(intent: Intent?) {
        val data = intent?.data ?: return
        if (data.scheme != "idrug") return
        when (data.host) {
            "auth" -> handleAuthDeepLink(data)
        }
        intent.data = null
    }

    private fun handleAuthDeepLink(uri: Uri) {
        val token = uri.getQueryParameter("token") ?: uri.getQueryParameter("jwt") ?: return
        prefs.edit().putString("token", token).apply()
        uri.getQueryParameter("username")?.let { prefs.edit().putString("username", it).apply() }
        uri.getQueryParameter("photo_url")?.let { prefs.edit().putString("photo_url", it).apply() }
        uri.getQueryParameter("telegram_id")?.let {
            prefs.edit().putString("telegram_id", it).apply()
            FirebaseMessaging.getInstance().subscribeToTopic("user_$it")
        }
        if (isAdded) {
            view?.let { loadProfileAndSetupUI(it) }
            Toast.makeText(requireContext(), R.string.login_successful, Toast.LENGTH_SHORT).show()
        }
    }
private fun setupServerDropdown(view: View) {
    val container = view.findViewById<TextInputLayout>(R.id.server_dropdown_container)
        ?: error("TextInputLayout not found")
    val dropdown = view.findViewById<MaterialAutoCompleteTextView>(R.id.dropdown_server)
        ?: error("MaterialAutoCompleteTextView not found")
    val btnDownload = view.findViewById<Button>(R.id.btn_download)

    // НЕ сбрасываем выбор, если уже был
    if (selectedServerId.isNullOrBlank() || serverList.none { it.first == selectedServerId }) {
        selectedServerId = serverList.firstOrNull()?.first
    }

    val displayItems = serverList.map { formatServerDisplayName(it.first, it.second) }
    val adapter = object : ArrayAdapter<CharSequence>(
        requireContext(),
        R.layout.item_server_dropdown,
        displayItems
    ) {}
    dropdown.setAdapter(adapter)

    // отрисовка текущего текста
    val idx = serverList.indexOfFirst { it.first == selectedServerId }
    if (idx in displayItems.indices) {
        dropdown.setText(displayItems[idx], false)
    } else {
        dropdown.setText("", false)
    }

    dropdown.setOnItemClickListener { _, _, position, _ ->
        selectedServerId = serverList.getOrNull(position)?.first
        updateDownloadButtonState(view)
    }
    dropdown.setOnClickListener { dropdown.showDropDown() }
    dropdown.setOnFocusChangeListener { v, hasFocus -> if (hasFocus) (v as? MaterialAutoCompleteTextView)?.showDropDown() }

    container.visibility = if (serverList.isNotEmpty()) View.VISIBLE else View.GONE
    btnDownload.visibility = if (serverList.isNotEmpty()) View.VISIBLE else View.GONE
    btnDownload.isEnabled = !selectedServerId.isNullOrBlank()
}


    private class CircleTransformation : Transformation {
        override fun transform(source: Bitmap): Bitmap {
            val size = min(source.width, source.height)
            val x = (source.width - size) / 2
            val y = (source.height - size) / 2

            val squared = Bitmap.createBitmap(source, x, y, size, size)
            if (squared != source) source.recycle()

            val result = Bitmap.createBitmap(size, size, source.config ?: Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)
            val paint = Paint().apply {
                isAntiAlias = true
                shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            }
            val radius = size / 2f
            canvas.drawCircle(radius, radius, radius, paint)
            squared.recycle()
            return result
        }

        override fun key(): String = "circle"
    }

    private fun loadProfileAndSetupUI(view: View, showLoading: Boolean = true) {
        val token = prefs.getString("token", null)
        if (token == null) {
            safeUi {
                showLoginScreen(view)
                if (showLoading) setLoading(false)
            }
            return
        }
        val client = OkHttpClient.Builder()
            .connectTimeout(PROFILE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(PROFILE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .writeTimeout(PROFILE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .callTimeout(PROFILE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()
        val url = "https://idrug.pw/api/profile"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .build()
        cancelProfileCall()
        val call = client.newCall(request)
        profileCall = call
        val timeoutRunnable = Runnable {
            if (profileCall == call && !call.isCanceled()) {
                call.cancel()
                safeUi {
                    if (prefs.getString("token", null) != token) return@safeUi
                    if (showLoading) setLoading(false)
                    val cached = cachedProfile
                    if (cached != null) {
                        showAccountScreen(view, cached.username, cached.photoUrl, cached.subscriptions, cacheResult = false, refreshPings = false)
                    } else {
                        setStatusMessage(view, getString(R.string.profile_timeout_error), true)
                    }
                }
            }
        }
        profileTimeoutRunnable = timeoutRunnable
        handler.postDelayed(timeoutRunnable, PROFILE_TIMEOUT_MS)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (profileCall == call) cancelProfileCall()
                safeUi {
                    if (prefs.getString("token", null) != token) return@safeUi
                    if (showLoading) setLoading(false)
                    if (cachedProfile != null) {
                        showAccountScreen(view, cachedProfile!!.username, cachedProfile!!.photoUrl, cachedProfile!!.subscriptions, cacheResult = false, refreshPings = false)
                    } else {
                        if (call.isCanceled() || e.message?.equals("Canceled", ignoreCase = true) == true) {
                            return@safeUi
                        }
                        val message = getString(
                            R.string.network_error_msg,
                            e.message ?: getString(R.string.generic_error)
                        )
                        setStatusMessage(view, message, true)
                        setLoading(false)
                    }
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if (profileCall == call) cancelProfileCall()
                val resp = response.body?.string() ?: ""
                safeUi {
                    if (prefs.getString("token", null) != token) return@safeUi
                    if (showLoading) setLoading(false)
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
                            showAccountScreen(view, username, photoUrl, subsList, cacheResult = true, refreshPings = true)
                            syncTunnelsWithProfile()
                        } catch (e: Exception) {
                            Toast.makeText(requireContext(), getString(R.string.profile_processing_error, e.message), Toast.LENGTH_SHORT).show()
                            subscriptions = emptyList()
                            val cachedUsername = prefs.getString("username", "") ?: ""
                            val cachedPhoto = prefs.getString("photo_url", null)
                            showAccountScreen(view, cachedUsername, cachedPhoto, emptyList(), cacheResult = true, refreshPings = true)
                        }
                    } else {
                        setStatusMessage(
                            view,
                            getString(R.string.profile_retrieval_failed, response.code),
                            true
                        )
                        cachedProfile?.let {
                            showAccountScreen(view, it.username, it.photoUrl, it.subscriptions, cacheResult = false, refreshPings = false)
                        }
                    }
                }
            }
        })
    }

    companion object {
        private const val PING_CONNECT_TIMEOUT_MS = 2000
        private const val PING_SOCKET_TIMEOUT_MS = 2000
        private const val PROFILE_CACHE_VALIDITY_MS = 15_000L
        private const val PROFILE_TIMEOUT_MS = 5_000L
    }

    private fun cancelProfileCall() {
        profileTimeoutRunnable?.let { handler.removeCallbacks(it) }
        profileTimeoutRunnable = null
        profileCall?.cancel()
        profileCall = null
    }
}
