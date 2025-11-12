/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections.model

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import pw.idrug.connections.BR
import org.amnezia.awg.backend.Statistics
import org.amnezia.awg.backend.Tunnel
import pw.idrug.connections.databinding.Keyed
import pw.idrug.connections.util.applicationScope
import pw.idrug.connections.viewmodel.ConfigProxy
import org.amnezia.awg.config.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Encapsulates the volatile and nonvolatile state of an iDrugConnections tunnel.
 */
class ObservableTunnel internal constructor(
    private val manager: TunnelManager,
    initialName: String,
    config: Config?,
    amConfig: Config?,
    initialAmQuick: String?,
    initialState: Tunnel.State
) : BaseObservable(), Keyed<String>, Tunnel {
    private var internalName: String = initialName

    override val key: String
        get() = internalName

    @Bindable
    override fun getName(): String = internalName

    private fun updateName(newName: String) {
        if (internalName != newName) {
            internalName = newName
            notifyPropertyChanged(BR.name)
        }
    }

    suspend fun setNameAsync(name: String): String = withContext(Dispatchers.Main.immediate) {
        if (name != internalName) {
            manager.setTunnelName(this@ObservableTunnel, name)
        } else {
            internalName
        }
    }

    fun onNameChanged(name: String): String {
        updateName(name)
        return name
    }


    @get:Bindable
    var state: Tunnel.State = initialState
        private set(value) {
            field = value
            notifyPropertyChanged(BR.state)
        }

    override fun onStateChange(newState: Tunnel.State) {
        onStateChanged(newState)
    }

    fun onStateChanged(state: Tunnel.State): Tunnel.State {
        if (state != Tunnel.State.UP) onStatisticsChanged(null)
        this.state = state
        notifyPropertyChanged(BR.state)
        return state
    }

    suspend fun setStateAsync(state: Tunnel.State): Tunnel.State = withContext(Dispatchers.Main.immediate) {
        if (state != this@ObservableTunnel.state)
            manager.setTunnelState(this@ObservableTunnel, state)
        else
            this@ObservableTunnel.state
    }


    @get:Bindable
    var config = config
        get() {
            if (field == null)
            // Opportunistically fetch this if we don't have a cached one, and rely on data bindings to update it eventually
                applicationScope.launch {
                    try {
                        manager.getTunnelConfig(this@ObservableTunnel)
                    } catch (e: Throwable) {
                        Log.e(TAG, Log.getStackTraceString(e))
                    }
                }
            return field
        }
        private set

    var amConfig: Config? = amConfig
        private set

    var amQuick: String? = initialAmQuick
        private set

    suspend fun getConfigAsync(): Config = withContext(Dispatchers.Main.immediate) {
        config ?: manager.getTunnelConfig(this@ObservableTunnel)
    }

    suspend fun getAmConfigAsync(): Config = withContext(Dispatchers.Main.immediate) {
        amConfig ?: manager.getTunnelAmConfig(this@ObservableTunnel)
    }

    @get:Bindable
    val quicReadyBadge: Boolean
        get() = config?.let {
            runCatching { it.getInterface().i1.orElse("").isNotEmpty() }.getOrDefault(false)
        } ?: false

    suspend fun setConfigAsync(configs: ConfigProxy.BuiltConfigs): Config = withContext(Dispatchers.Main.immediate) {
        val currentConfig = this@ObservableTunnel.config
        val currentQuick = this@ObservableTunnel.amQuick
        if (currentConfig != null && currentConfig == configs.awg && currentQuick == configs.amQuick) {
            return@withContext currentConfig
        }
        manager.setTunnelConfig(this@ObservableTunnel, configs)
    }

    suspend fun setConfigAsync(config: Config): Config =
        setConfigAsync(ConfigProxy(config).buildConfigs())

    fun onConfigChanged(config: Config?): Config? {
        this.config = config
        notifyPropertyChanged(BR.config)
        notifyPropertyChanged(BR.quicReadyBadge)
        return config
    }

    fun onAmConfigChanged(amConfig: Config?): Config? {
        this.amConfig = amConfig
        return amConfig
    }

    fun onAmQuickChanged(amQuick: String?): String? {
        this.amQuick = amQuick
        return amQuick
    }


    @get:Bindable
    var statistics: Statistics? = null
        get() {
            if (field == null || field?.isStale != false)
            // Opportunistically fetch this if we don't have a cached one, and rely on data bindings to update it eventually
                applicationScope.launch {
                    try {
                        manager.getTunnelStatistics(this@ObservableTunnel)
                    } catch (e: Throwable) {
                        Log.e(TAG, Log.getStackTraceString(e))
                    }
                }
            return field
        }
        private set

    suspend fun getStatisticsAsync(): Statistics = withContext(Dispatchers.Main.immediate) {
        statistics.let {
            if (it == null || it.isStale)
                manager.getTunnelStatistics(this@ObservableTunnel)
            else
                it
        }
    }

    fun onStatisticsChanged(statistics: Statistics?): Statistics? {
        this.statistics = statistics
        notifyPropertyChanged(BR.statistics)
        return statistics
    }


    suspend fun deleteAsync() = manager.delete(this)


    companion object {
        private const val TAG = "iDrugConnections/ObservableTunnel"
    }
}
