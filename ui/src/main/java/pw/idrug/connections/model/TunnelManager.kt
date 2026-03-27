/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.Application.Companion.get
import pw.idrug.connections.Application.Companion.getBackend
import pw.idrug.connections.Application.Companion.getTunnelManager
import pw.idrug.connections.BR
import pw.idrug.connections.R
import org.amnezia.awg.backend.Statistics
import org.amnezia.awg.backend.Tunnel
import pw.idrug.connections.configStore.ConfigStore
import pw.idrug.connections.databinding.ObservableSortedKeyedArrayList
import pw.idrug.connections.util.ErrorMessages
import pw.idrug.connections.util.AwgRuntimeConfigVerifier
import pw.idrug.connections.util.UserKnobs
import pw.idrug.connections.util.AwgConfigParser
import pw.idrug.connections.util.applicationScope
import pw.idrug.connections.viewmodel.ConfigProxy
import org.amnezia.awg.config.Config
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Maintains and mediates changes to the set of available iDrugConnections tunnels,
 */
class TunnelManager(private val configStore: ConfigStore) : BaseObservable() {
    private val tunnels = CompletableDeferred<ObservableSortedKeyedArrayList<String, ObservableTunnel>>()
    private val context: Context = get()
    private val tunnelMap: ObservableSortedKeyedArrayList<String, ObservableTunnel> = ObservableSortedKeyedArrayList(TunnelComparator)
    private var haveLoaded = false

    private fun addToList(name: String, config: Config?, amConfig: Config?, amQuick: String?, state: Tunnel.State): ObservableTunnel {
        val tunnel = ObservableTunnel(this, name, config, amConfig, amQuick, state)
        tunnelMap.add(tunnel)
        return tunnel
    }

    private fun parseAmConfig(name: String, amQuick: String?, fallback: Config?): Config? {
        if (amQuick.isNullOrBlank())
            return fallback
        return try {
            val parsed = AwgConfigParser.parse(amQuick)
            val builder = Config.Builder()
                .setInterface(parsed.getInterface())
            val peers = fallback?.peers ?: emptyList()
            if (peers.isNotEmpty()) builder.addPeers(peers)
            builder.build()
        } catch (e: Throwable) {
            Log.e(TAG, "Failed to parse Amnezia quick config for tunnel $name", e)
            fallback
        }
    }

    suspend fun getTunnels(): ObservableSortedKeyedArrayList<String, ObservableTunnel> = tunnels.await()

    suspend fun create(name: String, configs: ConfigProxy.BuiltConfigs): ObservableTunnel = withContext(Dispatchers.Main.immediate) {
        if (Tunnel.isNameInvalid(name))
            throw IllegalArgumentException(context.getString(R.string.tunnel_error_invalid_name))
        if (tunnelMap.containsKey(name))
            throw IllegalArgumentException(context.getString(R.string.tunnel_error_already_exists, name))
        val awgConfig = withContext(Dispatchers.IO) {
            val saved = configStore.create(name, configs.awg)
            configStore.saveAmQuick(name, configs.amQuick)
            saved
        }
        addToList(name, awgConfig, configs.amConfig, configs.amQuick, Tunnel.State.DOWN)
    }

    suspend fun delete(tunnel: ObservableTunnel) = withContext(Dispatchers.Main.immediate) {
        val originalState = tunnel.state
        val wasLastUsed = tunnel == lastUsedTunnel
        // Make sure nothing touches the tunnel.
        if (wasLastUsed)
            lastUsedTunnel = null
        tunnelMap.remove(tunnel)
        try {
            if (originalState == Tunnel.State.UP)
                withContext(Dispatchers.IO) { getBackend().setState(tunnel, Tunnel.State.DOWN, null) }
            try {
                withContext(Dispatchers.IO) {
                    configStore.delete(tunnel.name)
                    configStore.deleteAmQuick(tunnel.name)
                }
            } catch (e: Throwable) {
                if (originalState == Tunnel.State.UP) {
                    val revertConfig = tunnel.amConfig ?: tunnel.config
                    withContext(Dispatchers.IO) { getBackend().setState(tunnel, Tunnel.State.UP, revertConfig) }
                }
                throw e
            }
        } catch (e: Throwable) {
            // Failure, put the tunnel back.
            tunnelMap.add(tunnel)
            if (wasLastUsed)
                lastUsedTunnel = tunnel
            throw e
        }
    }

    @get:Bindable
    var lastUsedTunnel: ObservableTunnel? = null
        private set(value) {
            if (value == field) return
            field = value
            notifyPropertyChanged(BR.lastUsedTunnel)
            applicationScope.launch { UserKnobs.setLastUsedTunnel(value?.name) }
        }

    suspend fun getTunnelConfig(tunnel: ObservableTunnel): Config = withContext(Dispatchers.Main.immediate) {
        val triple = withContext(Dispatchers.IO) {
            val awg = configStore.load(tunnel.name)
            val amQuick = configStore.loadAmQuick(tunnel.name)
            val amConfig = parseAmConfig(tunnel.name, amQuick, awg)
            Triple(awg, amConfig, amQuick)
        }
        tunnel.onAmQuickChanged(triple.third)
        tunnel.onAmConfigChanged(triple.second ?: triple.first)
        tunnel.onConfigChanged(triple.first)!!
    }

    suspend fun getTunnelAmConfig(tunnel: ObservableTunnel): Config = withContext(Dispatchers.Main.immediate) {
        val cached = tunnel.amConfig
        if (cached != null) return@withContext cached
        val triple = withContext(Dispatchers.IO) {
            val awg = configStore.load(tunnel.name)
            val amQuick = configStore.loadAmQuick(tunnel.name)
            val amConfig = parseAmConfig(tunnel.name, amQuick, awg) ?: awg
            Triple(amConfig, amQuick, awg)
        }
        tunnel.onAmQuickChanged(triple.second)
        tunnel.onAmConfigChanged(triple.first)
        if (tunnel.config == null) tunnel.onConfigChanged(triple.third)
        triple.first
    }

    suspend fun getAmQuick(tunnel: ObservableTunnel): String? = withContext(Dispatchers.Main.immediate) {
        val cached = tunnel.amQuick
        if (cached != null) return@withContext cached
        val quick = withContext(Dispatchers.IO) { configStore.loadAmQuick(tunnel.name) }
        tunnel.onAmQuickChanged(quick)
        quick
    }

    fun onCreate() {
        applicationScope.launch {
            try {
                val present = withContext(Dispatchers.IO) { configStore.enumerate() }
                val running = withContext(Dispatchers.IO) { getBackend().runningTunnelNames }
                onTunnelsLoaded(present, running)
            } catch (e: Throwable) {
                Log.e(TAG, Log.getStackTraceString(e))
                completeTunnelsDeferred()
            }
        }
    }

    private fun onTunnelsLoaded(present: Iterable<String>, running: Collection<String>) {
        for (name in present) {
            val amQuick = configStore.loadAmQuick(name)
            addToList(name, null, null, amQuick, if (running.contains(name)) Tunnel.State.UP else Tunnel.State.DOWN)
        }
        completeTunnelsDeferred()
        applicationScope.launch {
            try {
                val lastUsedName = UserKnobs.lastUsedTunnel.first()
                if (lastUsedName != null)
                    lastUsedTunnel = tunnelMap[lastUsedName]
            } catch (e: Throwable) {
                Log.e(TAG, "Failed to restore last used tunnel", e)
            }
            haveLoaded = true
            restoreState(true)
        }
    }

    private fun refreshTunnelStates() {
        applicationScope.launch {
            try {
                val running = withContext(Dispatchers.IO) { getBackend().runningTunnelNames }
                for (tunnel in tunnelMap)
                    tunnel.onStateChanged(if (running.contains(tunnel.name)) Tunnel.State.UP else Tunnel.State.DOWN)
            } catch (e: Throwable) {
                Log.e(TAG, Log.getStackTraceString(e))
            }
        }
    }

    suspend fun restoreState(force: Boolean) {
        if (!haveLoaded || (!force && !UserKnobs.restoreOnBoot.first()))
            return
        val previouslyRunning = try {
            UserKnobs.runningTunnels.first()
        } catch (e: Throwable) {
            Log.e(TAG, "Failed to read running tunnels", e)
            emptySet()
        }
        if (previouslyRunning.isEmpty()) return
        // Если конфиги повреждены, запускаем их выборочно и снимаем флаг автозапуска, чтобы не зациклиться при следующих стартах.
        val failed = withContext(Dispatchers.IO) {
            tunnelMap
                .filter { previouslyRunning.contains(it.name) }
                .map { tunnel ->
                    async(Dispatchers.IO + SupervisorJob()) {
                        try {
                            setTunnelState(tunnel, Tunnel.State.UP)
                            null
                        } catch (e: Throwable) {
                            Log.e(TAG, "Failed to auto-restore tunnel ${tunnel.name}", e)
                            tunnel.name
                        }
                    }
                }
                .awaitAll()
                .filterNotNull()
                .toSet()
        }
        if (failed.isNotEmpty()) {
            UserKnobs.setRunningTunnels(previouslyRunning - failed)
        }
    }

    suspend fun saveState() {
        UserKnobs.setRunningTunnels(tunnelMap.filter { it.state == Tunnel.State.UP }.map { it.name }.toSet())
    }

    suspend fun setTunnelConfig(tunnel: ObservableTunnel, configs: ConfigProxy.BuiltConfigs): Config = withContext(Dispatchers.Main.immediate) {
        val savedConfig = withContext(Dispatchers.IO) {
            val backend = getBackend()
            val resultingState = backend.setState(tunnel, tunnel.state, configs.amConfig)
            if (BuildConfig.DEBUG && resultingState == Tunnel.State.UP) {
                AwgRuntimeConfigVerifier.verifyAndLog(backend, tunnel, configs.amConfig)
            }
            configStore.saveAmQuick(tunnel.name, configs.amQuick)
            configStore.save(tunnel.name, configs.awg)
        }
        tunnel.onAmQuickChanged(configs.amQuick)
        tunnel.onAmConfigChanged(configs.amConfig)
        tunnel.onConfigChanged(savedConfig)!!
    }

    suspend fun setTunnelName(tunnel: ObservableTunnel, name: String): String = withContext(Dispatchers.Main.immediate) {
        if (Tunnel.isNameInvalid(name))
            throw IllegalArgumentException(context.getString(R.string.tunnel_error_invalid_name))
        if (tunnelMap.containsKey(name)) {
            throw IllegalArgumentException(context.getString(R.string.tunnel_error_already_exists, name))
        }
        val oldName = tunnel.name
        val originalState = tunnel.state
        val wasLastUsed = tunnel == lastUsedTunnel
        // Make sure nothing touches the tunnel.
        if (wasLastUsed)
            lastUsedTunnel = null
        tunnelMap.remove(tunnel)
        var throwable: Throwable? = null
        var newName: String? = null
        try {
            if (originalState == Tunnel.State.UP)
                withContext(Dispatchers.IO) { getBackend().setState(tunnel, Tunnel.State.DOWN, null) }
            withContext(Dispatchers.IO) {
                configStore.rename(oldName, name)
                configStore.renameAmQuick(oldName, name)
            }
            newName = tunnel.onNameChanged(name)
            if (originalState == Tunnel.State.UP)
                withContext(Dispatchers.IO) {
                    val revertConfig = tunnel.amConfig ?: tunnel.config
                    getBackend().setState(tunnel, Tunnel.State.UP, revertConfig)
                }
        } catch (e: Throwable) {
            throwable = e
            // On failure, we don't know what state the tunnel might be in. Fix that.
            getTunnelState(tunnel)
        }
        // Add the tunnel back to the manager, under whatever name it thinks it has.
        tunnelMap.add(tunnel)
        if (wasLastUsed)
            lastUsedTunnel = tunnel
        if (throwable != null)
            throw throwable
        newName!!
    }

    suspend fun setTunnelState(tunnel: ObservableTunnel, state: Tunnel.State): Tunnel.State = withContext(Dispatchers.Main.immediate) {
        var newState = tunnel.state
        var throwable: Throwable? = null
        try {
            newState = withContext(Dispatchers.IO) {
                val backend = getBackend()
                val amConfig = tunnel.getAmConfigAsync()
                val result = backend.setState(tunnel, state, amConfig)
                if (BuildConfig.DEBUG && result == Tunnel.State.UP) {
                    AwgRuntimeConfigVerifier.verifyAndLog(backend, tunnel, amConfig)
                }
                result
            }
            if (newState == Tunnel.State.UP)
                lastUsedTunnel = tunnel
        } catch (e: Throwable) {
            throwable = e
        }
        tunnel.onStateChanged(newState)
        saveState()
        if (throwable != null)
            throw throwable
        newState
    }

    class IntentReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            applicationScope.launch {
                val manager = getTunnelManager()
                if (intent == null) return@launch
                val action = intent.action ?: return@launch
                if ("pw.idrug.connections.action.REFRESH_TUNNEL_STATES" == action) {
                    manager.refreshTunnelStates()
                    return@launch
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || !UserKnobs.allowRemoteControlIntents.first())
                    return@launch
                val state: Tunnel.State
                state = when (action) {
                    "pw.idrug.connections.action.SET_TUNNEL_UP" -> Tunnel.State.UP
                    "pw.idrug.connections.action.SET_TUNNEL_DOWN" -> Tunnel.State.DOWN
                    else -> return@launch
                }
                val tunnelName = intent.getStringExtra("tunnel") ?: return@launch
                val tunnels = manager.getTunnels()
                val tunnel = tunnels[tunnelName] ?: return@launch
                try {
                    manager.setTunnelState(tunnel, state)
                } catch (e: Throwable) {
                    Toast.makeText(context, ErrorMessages[e], Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    suspend fun getTunnelState(tunnel: ObservableTunnel): Tunnel.State = withContext(Dispatchers.Main.immediate) {
        tunnel.onStateChanged(withContext(Dispatchers.IO) { getBackend().getState(tunnel) })
    }

    suspend fun getTunnelStatistics(tunnel: ObservableTunnel): Statistics = withContext(Dispatchers.Main.immediate) {
        tunnel.onStatisticsChanged(withContext(Dispatchers.IO) { getBackend().getStatistics(tunnel) })!!
    }

    companion object {
        private const val TAG = "iDrugConnections/TunnelManager"
    }

    private fun completeTunnelsDeferred() {
        if (!tunnels.isCompleted && !tunnels.isCancelled) {
            // Завершаем отложенную инициализацию как можно раньше, чтобы UI не зависал в ожидании.
            tunnels.complete(tunnelMap)
        }
    }
}
