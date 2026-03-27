/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import pw.idrug.connections.BR
import org.amnezia.awg.config.Attribute
import org.amnezia.awg.config.BadConfigException
import org.amnezia.awg.config.Interface
import org.amnezia.awg.crypto.Key
import org.amnezia.awg.crypto.KeyFormatException
import org.amnezia.awg.crypto.KeyPair

class InterfaceProxy : BaseObservable, Parcelable {
    @get:Bindable
    val excludedApplications: ObservableList<String> = ObservableArrayList()

    @get:Bindable
    val includedApplications: ObservableList<String> = ObservableArrayList()

    @get:Bindable
    var addresses: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.addresses)
        }

    @get:Bindable
    var dnsServers: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.dnsServers)
        }

    @get:Bindable
    var dnsSearchDomains: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.dnsSearchDomains)
        }

    @get:Bindable
    var listenPort: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.listenPort)
        }

    @get:Bindable
    var mtu: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mtu)
        }

    @get:Bindable
    var junkPacketCount: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.junkPacketCount)
        }

    @get:Bindable
    var junkPacketMinSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.junkPacketMinSize)
        }

    @get:Bindable
    var junkPacketMaxSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.junkPacketMaxSize)
        }

    @get:Bindable
    var initPacketJunkSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.initPacketJunkSize)
        }

    @get:Bindable
    var responsePacketJunkSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.responsePacketJunkSize)
        }

    @get:Bindable
    var cookieReplyPacketJunkSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cookieReplyPacketJunkSize)
        }

    @get:Bindable
    var transportPacketJunkSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.transportPacketJunkSize)
        }

    @get:Bindable
    var initPacketMagicHeader: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.initPacketMagicHeader)
        }

    @get:Bindable
    var responsePacketMagicHeader: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.responsePacketMagicHeader)
        }

    @get:Bindable
    var underloadPacketMagicHeader: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.underloadPacketMagicHeader)
        }

    @get:Bindable
    var transportPacketMagicHeader: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.transportPacketMagicHeader)
        }

    @get:Bindable
    var specialJunkPacket1: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialJunkPacket1)
        }

    @get:Bindable
    var specialJunkPacket2: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialJunkPacket2)
        }

    @get:Bindable
    var specialJunkPacket3: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialJunkPacket3)
        }

    @get:Bindable
    var specialJunkPacket4: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialJunkPacket4)
        }

    @get:Bindable
    var specialJunkPacket5: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.specialJunkPacket5)
        }

    @get:Bindable
    var controlledJunkPacket1: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.controlledJunkPacket1)
        }

    @get:Bindable
    var controlledJunkPacket2: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.controlledJunkPacket2)
        }

    @get:Bindable
    var controlledJunkPacket3: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.controlledJunkPacket3)
        }

    @get:Bindable
    var itimeSeconds: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.itimeSeconds)
        }

    @get:Bindable
    var privateKey: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.privateKey)
            notifyPropertyChanged(BR.publicKey)
        }

    @get:Bindable
    val publicKey: String
        get() = try {
            KeyPair(Key.fromBase64(privateKey)).publicKey.toBase64()
        } catch (ignored: KeyFormatException) {
            ""
        }

    private constructor(parcel: Parcel) {
        addresses = parcel.readString() ?: ""
        dnsServers = parcel.readString() ?: ""
        dnsSearchDomains = parcel.readString() ?: ""
        parcel.readStringList(excludedApplications)
        parcel.readStringList(includedApplications)
        listenPort = parcel.readString() ?: ""
        mtu = parcel.readString() ?: ""
        junkPacketCount = parcel.readString() ?: ""
        junkPacketMinSize = parcel.readString() ?: ""
        junkPacketMaxSize = parcel.readString() ?: ""
        initPacketJunkSize = parcel.readString() ?: ""
        responsePacketJunkSize = parcel.readString() ?: ""
        cookieReplyPacketJunkSize = parcel.readString() ?: ""
        transportPacketJunkSize = parcel.readString() ?: ""
        initPacketMagicHeader = parcel.readString() ?: ""
        responsePacketMagicHeader = parcel.readString() ?: ""
        underloadPacketMagicHeader = parcel.readString() ?: ""
        transportPacketMagicHeader = parcel.readString() ?: ""
        specialJunkPacket1 = parcel.readString() ?: ""
        specialJunkPacket2 = parcel.readString() ?: ""
        specialJunkPacket3 = parcel.readString() ?: ""
        specialJunkPacket4 = parcel.readString() ?: ""
        specialJunkPacket5 = parcel.readString() ?: ""
        controlledJunkPacket1 = parcel.readString() ?: ""
        controlledJunkPacket2 = parcel.readString() ?: ""
        controlledJunkPacket3 = parcel.readString() ?: ""
        itimeSeconds = parcel.readString() ?: ""
        privateKey = parcel.readString() ?: ""
    }

    constructor(other: Interface) {
        addresses = Attribute.join(other.addresses)
        dnsSearchDomains = Attribute.join(other.dnsSearchDomains)
        val dnsServerStrings = other.dnsServers.map { it.hostAddress }.plus(other.dnsSearchDomains)
        dnsServers = Attribute.join(dnsServerStrings)
        excludedApplications.addAll(other.excludedApplications)
        includedApplications.addAll(other.includedApplications)
        listenPort = other.listenPort.map { it.toString() }.orElse("")
        mtu = other.mtu.map { it.toString() }.orElse("")
        junkPacketCount = other.junkPacketCount.map { it.toString() }.orElse("")
        junkPacketMinSize = other.junkPacketMinSize.map { it.toString() }.orElse("")
        junkPacketMaxSize = other.junkPacketMaxSize.map { it.toString() }.orElse("")
        initPacketJunkSize = other.initPacketJunkSize.map { it.toString() }.orElse("")
        responsePacketJunkSize = other.responsePacketJunkSize.map { it.toString() }.orElse("")
        cookieReplyPacketJunkSize = other.cookieReplyPacketJunkSize.map { it.toString() }.orElse("")
        transportPacketJunkSize = other.transportPacketJunkSize.map { it.toString() }.orElse("")
        initPacketMagicHeader = other.initPacketMagicHeader.map { it.toString() }.orElse("")
        responsePacketMagicHeader = other.responsePacketMagicHeader.map { it.toString() }.orElse("")
        underloadPacketMagicHeader = other.underloadPacketMagicHeader.map { it.toString() }.orElse("")
        transportPacketMagicHeader = other.transportPacketMagicHeader.map { it.toString() }.orElse("")
        specialJunkPacket1 = other.specialJunkI1.orElse("")
        specialJunkPacket2 = other.specialJunkI2.orElse("")
        specialJunkPacket3 = other.specialJunkI3.orElse("")
        specialJunkPacket4 = other.specialJunkI4.orElse("")
        specialJunkPacket5 = other.specialJunkI5.orElse("")
        controlledJunkPacket1 = ""
        controlledJunkPacket2 = ""
        controlledJunkPacket3 = ""
        itimeSeconds = ""
        val keyPair = other.keyPair
        privateKey = keyPair.privateKey.toBase64()
    }

    constructor()

    override fun describeContents() = 0

    fun generateKeyPair() {
        val keyPair = KeyPair()
        privateKey = keyPair.privateKey.toBase64()
        notifyPropertyChanged(BR.privateKey)
        notifyPropertyChanged(BR.publicKey)
    }

    @Throws(BadConfigException::class)
    fun resolve(): Interface = buildInterface()

    @Throws(BadConfigException::class)
    fun toAmInterface(): Interface = buildInterface()

    @Throws(BadConfigException::class)
    private fun buildInterface(): Interface {
        val builder = Interface.Builder()
        if (addresses.isNotEmpty()) builder.parseAddresses(addresses)
        if (dnsServers.isNotEmpty()) builder.parseDnsServers(dnsServers)
        if (excludedApplications.isNotEmpty()) builder.excludeApplications(excludedApplications)
        if (includedApplications.isNotEmpty()) builder.includeApplications(includedApplications)
        if (listenPort.isNotEmpty()) builder.parseListenPort(listenPort)
        if (mtu.isNotEmpty()) builder.parseMtu(mtu)
        if (junkPacketCount.isNotEmpty()) builder.parseJunkPacketCount(junkPacketCount)
        if (junkPacketMinSize.isNotEmpty()) builder.parseJunkPacketMinSize(junkPacketMinSize)
        if (junkPacketMaxSize.isNotEmpty()) builder.parseJunkPacketMaxSize(junkPacketMaxSize)
        if (initPacketJunkSize.isNotEmpty()) builder.parseInitPacketJunkSize(initPacketJunkSize)
        if (responsePacketJunkSize.isNotEmpty()) builder.parseResponsePacketJunkSize(responsePacketJunkSize)
        val cookieTrimmed = cookieReplyPacketJunkSize.trim()
        if (cookieTrimmed.isNotEmpty()) builder.parseCookieReplyPacketJunkSize(cookieTrimmed) else builder.setCookieReplyPacketJunkSize(0)
        val transportTrimmed = transportPacketJunkSize.trim()
        if (transportTrimmed.isNotEmpty()) builder.parseTransportPacketJunkSize(transportTrimmed) else builder.setTransportPacketJunkSize(0)
        if (initPacketMagicHeader.isNotEmpty()) builder.parseInitPacketMagicHeader(initPacketMagicHeader)
        if (responsePacketMagicHeader.isNotEmpty()) builder.parseResponsePacketMagicHeader(responsePacketMagicHeader)
        if (underloadPacketMagicHeader.isNotEmpty()) builder.parseUnderloadPacketMagicHeader(underloadPacketMagicHeader)
        if (transportPacketMagicHeader.isNotEmpty()) builder.parseTransportPacketMagicHeader(transportPacketMagicHeader)
        val i1Value = specialJunkPacket1.trim()
        if (i1Value.isNotEmpty()) builder.parseSpecialJunkI1(i1Value)
        val i2Value = specialJunkPacket2.trim()
        if (i2Value.isNotEmpty()) builder.parseSpecialJunkI2(i2Value)
        val i3Value = specialJunkPacket3.trim()
        if (i3Value.isNotEmpty()) builder.parseSpecialJunkI3(i3Value)
        val i4Value = specialJunkPacket4.trim()
        if (i4Value.isNotEmpty()) builder.parseSpecialJunkI4(i4Value)
        val i5Value = specialJunkPacket5.trim()
        if (i5Value.isNotEmpty()) builder.parseSpecialJunkI5(i5Value)
        if (privateKey.isNotEmpty()) builder.parsePrivateKey(privateKey)
        return builder.build()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(addresses)
        dest.writeString(dnsServers)
        dest.writeString(dnsSearchDomains)
        dest.writeStringList(excludedApplications)
        dest.writeStringList(includedApplications)
        dest.writeString(listenPort)
        dest.writeString(mtu)
        dest.writeString(junkPacketCount)
        dest.writeString(junkPacketMinSize)
        dest.writeString(junkPacketMaxSize)
        dest.writeString(initPacketJunkSize)
        dest.writeString(responsePacketJunkSize)
        dest.writeString(cookieReplyPacketJunkSize)
        dest.writeString(transportPacketJunkSize)
        dest.writeString(initPacketMagicHeader)
        dest.writeString(responsePacketMagicHeader)
        dest.writeString(underloadPacketMagicHeader)
        dest.writeString(transportPacketMagicHeader)
        dest.writeString(specialJunkPacket1)
        dest.writeString(specialJunkPacket2)
        dest.writeString(specialJunkPacket3)
        dest.writeString(specialJunkPacket4)
        dest.writeString(specialJunkPacket5)
        dest.writeString(controlledJunkPacket1)
        dest.writeString(controlledJunkPacket2)
        dest.writeString(controlledJunkPacket3)
        dest.writeString(itimeSeconds)
        dest.writeString(privateKey)
    }

    private class InterfaceProxyCreator : Parcelable.Creator<InterfaceProxy> {
        override fun createFromParcel(parcel: Parcel): InterfaceProxy {
            return InterfaceProxy(parcel)
        }

        override fun newArray(size: Int): Array<InterfaceProxy?> {
            return arrayOfNulls(size)
        }
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<InterfaceProxy> = InterfaceProxyCreator()
    }
}
