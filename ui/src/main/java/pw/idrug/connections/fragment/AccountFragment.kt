package pw.idrug.connections.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.LinkedHashSet
import kotlin.math.min
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
        setLoading(true)
        applyLoadingState(view)
        loadProfileAndSetupUI(view)
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

        view.findViewById<Button>(R.id.btn_login_telegram).apply {
            visibility = View.VISIBLE
            isEnabled = true
        }
        view.findViewById<Button>(R.id.btn_logout)?.visibility = View.GONE
        view.findViewById<Button>(R.id.btn_link_device)?.visibility = View.GONE
        view.findViewById<Button>(R.id.btn_download)?.apply {
            visibility = View.GONE
            isEnabled = false
        }
        view.findViewById<Button>(R.id.btn_renew)?.visibility = View.GONE
        view.findViewById<Button>(R.id.btn_referral)?.visibility = View.GONE

        view.findViewById<TextView>(R.id.text_current_user)?.setText(R.string.login_via_telegram_title)
        view.findViewById<TextView>(R.id.text_expiration)?.text = ""
        view.findViewById<TextView>(R.id.status_text)?.text = ""
        view.findViewById<ImageView>(R.id.avatar_image)?.setImageResource(R.drawable.ic_avatar_placeholder)
        view.findViewById<ImageView>(R.id.qr_code_image)?.visibility = View.GONE

        view.findViewById<TextInputLayout>(R.id.server_dropdown_container)?.visibility = View.GONE
        view.findViewById<MaterialAutoCompleteTextView>(R.id.dropdown_server)?.apply {
            isEnabled = false
            setAdapter(null)
            setText("", false)
        }
    }

    private fun showAccountScreen(view: View, username: String, photoUrl: String?, subs: List<Subscription>) {
        subscriptions = subs
        updateServerListFromSubscriptions()

        view.findViewById<Button>(R.id.btn_login_telegram)?.visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout)?.visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_link_device)?.visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_renew)?.visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_referral)?.visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.qr_code_image)?.visibility = View.GONE

        val dropdownVisible = serverList.isNotEmpty()
        view.findViewById<TextInputLayout>(R.id.server_dropdown_container)?.visibility =
            if (dropdownVisible) View.VISIBLE else View.GONE
        view.findViewById<Button>(R.id.btn_download)?.visibility =
            if (dropdownVisible) View.VISIBLE else View.GONE

        view.findViewById<Button>(R.id.btn_download)?.isEnabled = serverList.isNotEmpty()
        view.findViewById<Button>(R.id.btn_link_device)?.isEnabled = true
        view.findViewById<Button>(R.id.btn_renew)?.isEnabled = true
        view.findViewById<Button>(R.id.btn_referral)?.isEnabled = true
        view.findViewById<Button>(R.id.btn_logout)?.isEnabled = true
        view.findViewById<MaterialAutoCompleteTextView>(R.id.dropdown_server)?.isEnabled = serverList.isNotEmpty()

        val titleView = view.findViewById<TextView>(R.id.text_current_user)
        titleView?.text = if (username.isBlank()) {
            getString(R.string.login_via_telegram_title)
        } else {
            getString(R.string.your_username, username)
        }

        view.findViewById<TextView>(R.id.text_expiration)?.text = buildSubscriptionSummary(subs)
        view.findViewById<TextView>(R.id.status_text)?.text = buildStatusMessage(subs)

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

        updateDropdownItems(view)
        updateDownloadButtonState(view)
    }

    private fun applyLoadingState(view: View) {
        val username = prefs.getString("username", "") ?: ""
        val photoUrl = prefs.getString("photo_url", null)

        view.findViewById<Button>(R.id.btn_login_telegram)?.visibility = View.GONE
        view.findViewById<Button>(R.id.btn_logout)?.visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_link_device)?.visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_renew)?.visibility = View.VISIBLE
        view.findViewById<Button>(R.id.btn_referral)?.visibility = View.VISIBLE
        view.findViewById<ImageView>(R.id.qr_code_image)?.visibility = View.GONE

        view.findViewById<Button>(R.id.btn_link_device)?.isEnabled = false
        view.findViewById<Button>(R.id.btn_renew)?.isEnabled = false
        view.findViewById<Button>(R.id.btn_referral)?.isEnabled = false
        view.findViewById<Button>(R.id.btn_logout)?.isEnabled = false
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
        view.findViewById<TextView>(R.id.status_text)?.text = ""

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

    private fun buildSubscriptionSummary(subs: List<Subscription>): CharSequence {
        if (subs.isEmpty()) return ""
        val parts = subs.map { sub ->
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
        }
        return parts.joinToString(separator = "\n")
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
        val sub = subscriptions.firstOrNull { it.location == id }
        val flag = getServerFlag(id)
        val prefix = if (flag.isNotBlank()) "$flag $name" else name
        if (sub == null) return prefix
        val statusText = when {
            sub.forever -> getString(R.string.subscription_lifetime)
            !sub.active -> getString(R.string.inactive)
            !sub.expires.isNullOrBlank() -> getString(R.string.expires_on, formatExpirationDate(sub.expires))
            else -> getString(R.string.active)
        }
        val display = "$prefix — $statusText"
        return SpannableString(display).apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, prefix.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            val color = if (sub.active || sub.forever) Color.parseColor("#388E3C") else Color.parseColor("#D32F2F")
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
        try {
            setLoading(true)
            val configText = downloadConfig(token, serverId)
            if (configText.isNullOrBlank()) {
                safeUi { Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show() }
                return
            }
            val tunnelName = "idrug_$serverId"
            val tunnelManager = Application.getTunnelManager()
            val existing = tunnelManager.getTunnels().firstOrNull { it.name == tunnelName }
            if (existing != null) {
                safeUi { Toast.makeText(requireContext(), R.string.config_already_added, Toast.LENGTH_SHORT).show() }
                return
            }
            val temp = File(requireContext().filesDir, "tmp_$tunnelName.conf")
            withContext(Dispatchers.IO) { temp.writeText(configText) }
            try {
                val parsed = withContext(Dispatchers.IO) {
                    temp.inputStream().bufferedReader().use { Config.parse(it) }
                }
                tunnelManager.create(tunnelName, parsed)
                safeUi {
                    Toast.makeText(requireContext(), R.string.tunnel_added, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                safeUi {
                    Toast.makeText(requireContext(), getString(R.string.tunnel_creation_error, e.message ?: ""), Toast.LENGTH_SHORT).show()
                }
            } finally {
                withContext(Dispatchers.IO) {
                    if (temp.exists()) temp.delete()
                }
            }
            syncTunnelsWithProfile()
        } finally {
            setLoading(false)
        }
    }

    private suspend fun downloadConfig(token: String, serverId: String): String? = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://idrug.pw/api/profile/download?server=$serverId")
            .addHeader("Authorization", "Bearer $token")
            .build()
        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) response.body?.string() else null
            }
        } catch (e: IOException) {
            Log.w("AccountFragment", "Failed to download config", e)
            null
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

        selectedServerId = null
        btnDownload.isEnabled = false
        dropdown.setText("", false)

        dropdown.setOnItemClickListener { _, _, position, _ ->
            if (position in serverList.indices) {
                selectedServerId = serverList[position].first
            } else {
                selectedServerId = null
            }
            updateDownloadButtonState(view)
        }

        dropdown.setOnClickListener { dropdown.showDropDown() }
        dropdown.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) (v as? MaterialAutoCompleteTextView)?.showDropDown()
        }

        updateDropdownItems(view)
        container.visibility = if (serverList.isNotEmpty()) View.VISIBLE else View.GONE
        btnDownload.visibility = if (serverList.isNotEmpty()) View.VISIBLE else View.GONE
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
                    // Toast.makeText(requireContext(), getString(R.string.network_error_msg, e.message), Toast.LENGTH_SHORT).show()
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
}
