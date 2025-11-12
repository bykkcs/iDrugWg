/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections.viewmodel

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.core.os.ParcelCompat
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import org.amnezia.awg.config.BadConfigException
import org.amnezia.awg.config.Config
import org.amnezia.awg.config.Interface
import org.amnezia.awg.config.Peer

class ConfigProxy : Parcelable {
    val `interface`: InterfaceProxy
    val peers: ObservableList<PeerProxy> = ObservableArrayList()

    private constructor(parcel: Parcel) {
        `interface` = ParcelCompat.readParcelable(parcel, InterfaceProxy::class.java.classLoader, InterfaceProxy::class.java) ?: InterfaceProxy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ParcelCompat.readParcelableList(parcel, peers, PeerProxy::class.java.classLoader, PeerProxy::class.java)
        } else {
            parcel.readTypedList(peers, PeerProxy.CREATOR)
        }
        peers.forEach { it.bind(this) }
    }

    constructor(other: Config) {
        `interface` = InterfaceProxy(other.getInterface())
        other.peers.forEach {
            val proxy = PeerProxy(it)
            peers.add(proxy)
            proxy.bind(this)
        }
    }

    constructor() {
        `interface` = InterfaceProxy()
    }

    fun addPeer(): PeerProxy {
        val proxy = PeerProxy()
        peers.add(proxy)
        proxy.bind(this)
        return proxy
    }

    override fun describeContents() = 0

    data class BuiltConfigs(
        val awg: Config,
        val amConfig: Config,
        val amQuick: String
    )

    @Throws(BadConfigException::class)
    fun buildConfigs(): BuiltConfigs {
        val resolvedPeers: MutableCollection<Peer> = ArrayList()
        peers.forEach { resolvedPeers.add(it.resolve()) }
        val awgInterface: Interface = `interface`.resolve()
        val amInterface: Interface = `interface`.toAmInterface()
        val awgConfig = Config.Builder()
            .setInterface(awgInterface)
            .addPeers(resolvedPeers)
            .build()
        val amQuick = buildString {
            append("[Interface]\n")
            append(amInterface.toAwgQuickString(true))
        }
        val amConfig = Config.Builder()
            .setInterface(amInterface)
            .addPeers(resolvedPeers)
            .setAmQuick(amQuick)
            .build()
        return BuiltConfigs(awgConfig, amConfig, amQuick)
    }

    @Throws(BadConfigException::class)
    fun resolve(): Config = buildConfigs().awg

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(`interface`, flags)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeParcelableList(peers, flags)
        } else {
            dest.writeTypedList(peers)
        }
    }

    private class ConfigProxyCreator : Parcelable.Creator<ConfigProxy> {
        override fun createFromParcel(parcel: Parcel): ConfigProxy {
            return ConfigProxy(parcel)
        }

        override fun newArray(size: Int): Array<ConfigProxy?> {
            return arrayOfNulls(size)
        }
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ConfigProxy> = ConfigProxyCreator()
    }
}
