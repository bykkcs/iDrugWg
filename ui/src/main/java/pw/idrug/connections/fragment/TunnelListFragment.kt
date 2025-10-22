/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections.fragment

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.graphics.Color
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.databinding.ObservableList
import androidx.lifecycle.lifecycleScope
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.qrcode.QRCodeReader
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import pw.idrug.connections.Application
import pw.idrug.connections.R
import pw.idrug.connections.activity.TunnelCreatorActivity
import pw.idrug.connections.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler
import pw.idrug.connections.databinding.TunnelListFragmentBinding
import pw.idrug.connections.databinding.TunnelListItemBinding
import pw.idrug.connections.databinding.ObservableKeyedArrayList
import pw.idrug.connections.model.ObservableTunnel
import pw.idrug.connections.viewmodel.ConfigProxy
import pw.idrug.connections.fragment.AppListDialogFragment
import pw.idrug.connections.util.ErrorMessages
import pw.idrug.connections.util.QrCodeFromFileScanner
import pw.idrug.connections.util.TunnelImporter
import pw.idrug.connections.widget.MultiselectableRelativeLayout
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.math.roundToInt

/**
 * Fragment containing a list of known iDrugConnections tunnels. It allows creating and deleting tunnels.
 */
class TunnelListFragment : BaseFragment() {
    private val actionModeListener = ActionModeListener()
    private var actionMode: ActionMode? = null
    private var backPressedCallback: OnBackPressedCallback? = null
    private var binding: TunnelListFragmentBinding? = null
    private var observedTunnels: ObservableKeyedArrayList<String, ObservableTunnel>? = null
    private val tunnelsCallback = object : ObservableList.OnListChangedCallback<ObservableList<ObservableTunnel>>() {
        override fun onChanged(sender: ObservableList<ObservableTunnel>) = refreshTunnelPings(sender)
        override fun onItemRangeInserted(sender: ObservableList<ObservableTunnel>, positionStart: Int, itemCount: Int) = onChanged(sender)
        override fun onItemRangeRemoved(sender: ObservableList<ObservableTunnel>, positionStart: Int, itemCount: Int) = onChanged(sender)
        override fun onItemRangeMoved(sender: ObservableList<ObservableTunnel>, fromPosition: Int, toPosition: Int, itemCount: Int) = onChanged(sender)
        override fun onItemRangeChanged(sender: ObservableList<ObservableTunnel>, positionStart: Int, itemCount: Int) = onChanged(sender)
    }
    private val serverPings = mutableMapOf<String, PingResult>()
    private var pingJob: Job? = null
    private val pingEndpoints = mapOf(
        "germany" to TcpEndpoint("194.113.233.251", 51821),
        "madrid" to TcpEndpoint("159.255.34.41", 51821),
        "bulgaria" to TcpEndpoint("185.232.170.117", 51821)
    )
    private val tunnelFileImportResultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { data ->
        if (data == null) return@registerForActivityResult
        val activity = activity ?: return@registerForActivityResult
        val contentResolver = activity.contentResolver ?: return@registerForActivityResult
        activity.lifecycleScope.launch {
            if (QrCodeFromFileScanner.validContentType(contentResolver, data)) {
                try {
                    val qrCodeFromFileScanner = QrCodeFromFileScanner(contentResolver, QRCodeReader())
                    val result = qrCodeFromFileScanner.scan(data)
                    TunnelImporter.importTunnel(parentFragmentManager, result.text) { showSnackbar(it) }
                } catch (e: Exception) {
                    val error = ErrorMessages[e]
                    val message = Application.get().resources.getString(R.string.import_error, error)
                    Log.e(TAG, message, e)
                    showSnackbar(message)
                }
            } else {
                TunnelImporter.importTunnel(contentResolver, data) { showSnackbar(it) }
            }
        }
    }

    private val qrImportResultLauncher = registerForActivityResult(ScanContract()) { result ->
        val qrCode = result.contents
        val activity = activity
        if (qrCode != null && activity != null) {
            activity.lifecycleScope.launch { TunnelImporter.importTunnel(parentFragmentManager, qrCode) { showSnackbar(it) } }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            val checkedItems = savedInstanceState.getIntegerArrayList(CHECKED_ITEMS)
            if (checkedItems != null) {
                for (i in checkedItems) actionModeListener.setItemChecked(i, true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observedTunnels?.let { refreshTunnelPings(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = TunnelListFragmentBinding.inflate(inflater, container, false)
        val bottomSheet = AddTunnelsSheet()
        binding?.apply {
            createFab.setOnClickListener {
                if (childFragmentManager.findFragmentByTag("BOTTOM_SHEET") != null)
                    return@setOnClickListener
                childFragmentManager.setFragmentResultListener(AddTunnelsSheet.REQUEST_KEY_NEW_TUNNEL, viewLifecycleOwner) { _, bundle ->
                    when (bundle.getString(AddTunnelsSheet.REQUEST_METHOD)) {
                        AddTunnelsSheet.REQUEST_CREATE -> {
                            startActivity(Intent(requireActivity(), TunnelCreatorActivity::class.java))
                        }

                        AddTunnelsSheet.REQUEST_IMPORT -> {
                            tunnelFileImportResultLauncher.launch("*/*")
                        }

                        AddTunnelsSheet.REQUEST_SCAN -> {
                            qrImportResultLauncher.launch(
                                ScanOptions()
                                    .setOrientationLocked(false)
                                    .setBeepEnabled(false)
                                    .setPrompt(getString(R.string.qr_code_hint))
                            )
                        }
                    }
                }
                bottomSheet.showNow(childFragmentManager, "BOTTOM_SHEET")
            }
            executePendingBindings()
        }
        backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) { actionMode?.finish() }
        backPressedCallback?.isEnabled = false

        return binding?.root
    }

    override fun onDestroyView() {
        observedTunnels?.removeOnListChangedCallback(tunnelsCallback)
        observedTunnels = null
        pingJob?.cancel()
        pingJob = null
        serverPings.clear()
        binding = null
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(CHECKED_ITEMS, actionModeListener.getCheckedItems())
    }

    override fun onSelectedTunnelChanged(oldTunnel: ObservableTunnel?, newTunnel: ObservableTunnel?) {
        binding ?: return
        lifecycleScope.launch {
            val tunnels = Application.getTunnelManager().getTunnels()
            if (newTunnel != null) viewForTunnel(newTunnel, tunnels)?.setSingleSelected(true)
            if (oldTunnel != null) viewForTunnel(oldTunnel, tunnels)?.setSingleSelected(false)
        }
    }

    private fun onTunnelDeletionFinished(count: Int, throwable: Throwable?) {
        val message: String
        val ctx = activity ?: Application.get()
        if (throwable == null) {
            message = ctx.resources.getQuantityString(R.plurals.delete_success, count, count)
        } else {
            val error = ErrorMessages[throwable]
            message = ctx.resources.getQuantityString(R.plurals.delete_error, count, count, error)
            Log.e(TAG, message, throwable)
        }
        showSnackbar(message)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding ?: return
        binding!!.fragment = this
        lifecycleScope.launch {
            val tunnels = Application.getTunnelManager().getTunnels()
            binding!!.tunnels = tunnels
            setTunnelsSource(tunnels)
            arguments?.getString(ARG_OPEN_TUNNEL_FOR_APPS)?.let { name ->
                val tunnel = tunnels[name]
                if (tunnel != null) {
                    showAppSelectionDialog(tunnel)
                }
                arguments?.remove(ARG_OPEN_TUNNEL_FOR_APPS)
            }
        }
        binding!!.rowConfigurationHandler = object : RowConfigurationHandler<TunnelListItemBinding, ObservableTunnel> {
            override fun onConfigureRow(binding: TunnelListItemBinding, item: ObservableTunnel, position: Int) {
                binding.fragment = this@TunnelListFragment
                val card = binding.tunnelCard
                val container = binding.tunnelContainer
                val clickListener = View.OnClickListener {
                    if (actionMode == null) {
                        showAppSelectionDialog(item)
                    } else {
                        actionModeListener.toggleItemChecked(position)
                    }
                }
                card.setOnClickListener(clickListener)
                val longClickListener = View.OnLongClickListener {
                    actionModeListener.toggleItemChecked(position)
                    true
                }
                card.setOnLongClickListener(longClickListener)

                val isMulti = actionMode != null && actionModeListener.checkedItems.contains(position)
                val isSingle = actionMode == null && selectedTunnel == item
                if (actionMode != null) {
                    container.setMultiSelected(isMulti)
                } else {
                    container.setSingleSelected(isSingle)
                }
                applySelectionVisualState(card, isMulti || isSingle)
                bindPing(binding, item)
            }
        }
    }

    private fun showSnackbar(message: CharSequence) {
        val binding = binding
        if (binding != null)
            Snackbar.make(binding.mainContainer, message, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.createFab)
                .show()
        else
            Toast.makeText(activity ?: Application.get(), message, Toast.LENGTH_SHORT).show()
    }

    private fun viewForTunnel(tunnel: ObservableTunnel, tunnels: List<*>): MultiselectableRelativeLayout? {
        val holderView = binding?.tunnelList?.findViewHolderForAdapterPosition(tunnels.indexOf(tunnel))?.itemView
        return holderView?.findViewById(R.id.tunnel_container) as? MultiselectableRelativeLayout
    }

    private fun showAppSelectionDialog(tunnel: ObservableTunnel) {
        lifecycleScope.launch {
            val config = try {
                tunnel.getConfigAsync()
            } catch (e: Throwable) {
                showSnackbar(ErrorMessages[e])
                return@launch
            }
            val iface = config.`interface`
            var isExcluded = true
            var selectedApps = ArrayList(iface.excludedApplications)
            if (selectedApps.isEmpty()) {
                selectedApps = ArrayList(iface.includedApplications)
                if (selectedApps.isNotEmpty()) isExcluded = false
            }
            val fragment = AppListDialogFragment.newInstance(selectedApps, isExcluded)
            parentFragmentManager.setFragmentResultListener(
                AppListDialogFragment.REQUEST_SELECTION,
                viewLifecycleOwner
            ) { _, bundle ->
                val apps = bundle.getStringArray(AppListDialogFragment.KEY_SELECTED_APPS) ?: return@setFragmentResultListener
                val excluded = bundle.getBoolean(AppListDialogFragment.KEY_IS_EXCLUDED)
                val proxy = ConfigProxy(config)
                proxy.`interface`.excludedApplications.clear()
                proxy.`interface`.includedApplications.clear()
                if (excluded) {
                    proxy.`interface`.excludedApplications.addAll(apps)
                } else {
                    proxy.`interface`.includedApplications.addAll(apps)
                }
                val newConfig = try {
                    proxy.resolve()
                } catch (e: Throwable) {
                    showSnackbar(ErrorMessages[e])
                    return@setFragmentResultListener
                }
                lifecycleScope.launch {
                    try {
                        tunnel.setConfigAsync(newConfig)
                    } catch (e: Throwable) {
                        showSnackbar(ErrorMessages[e])
                    }
                }
            }
            fragment.show(parentFragmentManager, null)
        }
    }

    private fun setTunnelsSource(tunnels: ObservableKeyedArrayList<String, ObservableTunnel>) {
        if (observedTunnels === tunnels) {
            refreshTunnelPings(tunnels)
            return
        }
        observedTunnels?.removeOnListChangedCallback(tunnelsCallback)
        observedTunnels = tunnels
        tunnels.addOnListChangedCallback(tunnelsCallback)
        refreshTunnelPings(tunnels)
    }

    private fun refreshTunnelPings(tunnels: List<ObservableTunnel>) {
        val ids = tunnels.mapNotNull { serverIdFromTunnel(it) }.distinct()
        if (ids.isEmpty()) {
            pingJob?.cancel()
            pingJob = null
            serverPings.clear()
            binding?.tunnelList?.adapter?.notifyDataSetChanged()
            return
        }
        val ctx = context
        pingJob?.cancel()
        if (ctx == null) {
            serverPings.clear()
            binding?.tunnelList?.adapter?.notifyDataSetChanged()
            return
        }
        serverPings.keys.retainAll(ids)
        ids.forEach { id ->
            if (serverPings[id] == null) {
                serverPings[id] = PingResult(PingState.LOADING)
            }
        }
        binding?.tunnelList?.adapter?.notifyDataSetChanged()
        val idsSnapshot = ids
        pingJob = viewLifecycleOwner.lifecycleScope.launch {
            val prefs = ctx.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val client = OkHttpClient()
            while (isActive) {
                val token = prefs.getString("token", null)
                for (id in idsSnapshot) {
                    val result = requestPing(client, token, id)
                    serverPings[id] = result
                    binding?.tunnelList?.adapter?.notifyDataSetChanged()
                }
                delay(PING_REFRESH_INTERVAL_MS)
            }
        }.also { job -> job.invokeOnCompletion { pingJob = null } }
    }

    private fun serverIdFromTunnel(tunnel: ObservableTunnel): String? {
        val name = tunnel.name
        return if (name.startsWith("idrug_")) name.removePrefix("idrug_") else null
    }

    private fun bindPing(binding: TunnelListItemBinding, tunnel: ObservableTunnel) {
        val pingView = binding.tunnelPing
        val serverId = serverIdFromTunnel(tunnel)
        if (serverId == null) {
            pingView.visibility = View.GONE
            return
        }
        val neutralColor = Color.parseColor("#616161")
        val result = serverPings[serverId]
        val (label, color) = when {
            result == null || result.state == PingState.LOADING -> {
                getString(R.string.ping_loading) to neutralColor
            }
            result.state == PingState.SUCCESS && result.latencyMs != null -> {
                val latency = result.latencyMs
                val text = getString(R.string.ping_value_ms, latency)
                val goodColor = Color.parseColor("#388E3C")
                val midColor = Color.parseColor("#F9A825")
                val badColor = Color.parseColor("#D32F2F")
                val color = when {
                    latency <= 80 -> goodColor
                    latency <= 160 -> midColor
                    else -> badColor
                }
                text to color
            }
            else -> {
                getString(R.string.ping_unavailable) to Color.parseColor("#D32F2F")
            }
        }
        pingView.visibility = View.VISIBLE
        pingView.text = label
        pingView.setTextColor(color)
    }

    private fun applySelectionVisualState(card: MaterialCardView, selected: Boolean) {
        val surface = MaterialColors.getColor(card, com.google.android.material.R.attr.colorSurface)
        val highlight = MaterialColors.getColor(card, com.google.android.material.R.attr.colorSecondaryContainer, surface)
        val strokeColor = MaterialColors.getColor(card, com.google.android.material.R.attr.colorPrimary, highlight)
        card.setCardBackgroundColor(if (selected) highlight else surface)
        card.strokeWidth = if (selected) (card.resources.displayMetrics.density * 1.5f).roundToInt() else 0
        card.strokeColor = strokeColor
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
        return try {
            client.newCall(requestBuilder.build()).execute().use { response ->
                if (!response.isSuccessful) {
                    PingResult(PingState.ERROR)
                } else {
                    val latency = parsePingMs(response.body?.string())
                    if (latency != null) PingResult(PingState.SUCCESS, latency) else PingResult(PingState.ERROR)
                }
            }
        } catch (_: Exception) {
            PingResult(PingState.ERROR)
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
                // fall through to regex parsing
            }
        }
        val match = Regex("([0-9]+(?:\\.[0-9]+)?)").find(text)
        val number = match?.groupValues?.getOrNull(1)?.toDoubleOrNull()
        return number?.roundToInt()
    }

    private enum class PingState { LOADING, SUCCESS, ERROR }

    private data class PingResult(val state: PingState, val latencyMs: Int? = null)

    private data class TcpEndpoint(val host: String, val port: Int)


    private inner class ActionModeListener : ActionMode.Callback {
        val checkedItems: MutableCollection<Int> = HashSet()
        private var resources: Resources? = null

        fun getCheckedItems(): ArrayList<Int> {
            return ArrayList(checkedItems)
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_action_delete -> {
                    val activity = activity ?: return true
                    val copyCheckedItems = HashSet(checkedItems)
                    binding?.createFab?.apply {
                        visibility = View.VISIBLE
                        scaleX = 1f
                        scaleY = 1f
                    }
                    activity.lifecycleScope.launch {
                        try {
                            val tunnels = Application.getTunnelManager().getTunnels()
                            val tunnelsToDelete = ArrayList<ObservableTunnel>()
                            for (position in copyCheckedItems) tunnelsToDelete.add(tunnels[position])
                            val futures = tunnelsToDelete.map { async(SupervisorJob()) { it.deleteAsync() } }
                            onTunnelDeletionFinished(futures.awaitAll().size, null)
                        } catch (e: Throwable) {
                            onTunnelDeletionFinished(0, e)
                        }
                    }
                    checkedItems.clear()
                    mode.finish()
                    true
                }

                R.id.menu_action_select_all -> {
                    lifecycleScope.launch {
                        val tunnels = Application.getTunnelManager().getTunnels()
                        for (i in 0 until tunnels.size) {
                            setItemChecked(i, true)
                        }
                    }
                    true
                }

                else -> false
            }
        }

        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            actionMode = mode
            backPressedCallback?.isEnabled = true
            if (activity != null) {
                resources = activity!!.resources
            }
            animateFab(binding?.createFab, false)
            mode.menuInflater.inflate(R.menu.tunnel_list_action_mode, menu)
            binding?.tunnelList?.adapter?.notifyDataSetChanged()
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
            backPressedCallback?.isEnabled = false
            resources = null
            animateFab(binding?.createFab, true)
            checkedItems.clear()
            binding?.tunnelList?.adapter?.notifyDataSetChanged()
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            updateTitle(mode)
            return false
        }

        fun setItemChecked(position: Int, checked: Boolean) {
            if (checked) {
                checkedItems.add(position)
            } else {
                checkedItems.remove(position)
            }
            val adapter = if (binding == null) null else binding!!.tunnelList.adapter
            if (actionMode == null && !checkedItems.isEmpty() && activity != null) {
                (activity as AppCompatActivity).startSupportActionMode(this)
            } else if (actionMode != null && checkedItems.isEmpty()) {
                actionMode!!.finish()
            }
            adapter?.notifyItemChanged(position)
            updateTitle(actionMode)
        }

        fun toggleItemChecked(position: Int) {
            setItemChecked(position, !checkedItems.contains(position))
        }

        private fun updateTitle(mode: ActionMode?) {
            if (mode == null) {
                return
            }
            val count = checkedItems.size
            if (count == 0) {
                mode.title = ""
            } else {
                mode.title = resources!!.getQuantityString(R.plurals.delete_title, count, count)
            }
        }

        private fun animateFab(view: View?, show: Boolean) {
            view ?: return
            val animation = AnimationUtils.loadAnimation(
                context, if (show) R.anim.scale_up else R.anim.scale_down
            )
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    if (!show) view.visibility = View.GONE
                }

                override fun onAnimationStart(animation: Animation?) {
                    if (show) view.visibility = View.VISIBLE
                }
            })
            view.startAnimation(animation)
        }
    }

    companion object {
        private const val CHECKED_ITEMS = "CHECKED_ITEMS"
        const val ARG_OPEN_TUNNEL_FOR_APPS = "open_tunnel_for_apps"
        private const val TAG = "iDrugConnections/TunnelListFragment"
        private const val PING_CONNECT_TIMEOUT_MS = 2000
        private const val PING_REFRESH_INTERVAL_MS = 5_000L
        private const val PING_SOCKET_TIMEOUT_MS = 2000
    }
}
