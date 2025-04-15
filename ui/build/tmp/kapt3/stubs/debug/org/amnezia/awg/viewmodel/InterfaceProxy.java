package org.amnezia.awg.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 H2\u00020\u00012\u00020\u0002:\u0002HIB\u000f\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u0007\b\u0016\u00a2\u0006\u0002\u0010\tJ\b\u0010@\u001a\u00020AH\u0016J\u0006\u0010B\u001a\u00020CJ\u0006\u0010D\u001a\u00020\u0007J\u0018\u0010E\u001a\u00020C2\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020AH\u0016R&\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0019\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00158G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00158G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R&\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R&\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R&\u0010 \u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010R&\u0010#\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000e\"\u0004\b%\u0010\u0010R&\u0010&\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u000e\"\u0004\b(\u0010\u0010R&\u0010)\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u000e\"\u0004\b+\u0010\u0010R&\u0010,\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u000e\"\u0004\b.\u0010\u0010R&\u0010/\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000e\"\u0004\b1\u0010\u0010R\u0011\u00102\u001a\u00020\u000b8G\u00a2\u0006\u0006\u001a\u0004\b3\u0010\u000eR&\u00104\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u000e\"\u0004\b6\u0010\u0010R&\u00107\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u000e\"\u0004\b9\u0010\u0010R&\u0010:\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u000e\"\u0004\b<\u0010\u0010R&\u0010=\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u000e\"\u0004\b?\u0010\u0010\u00a8\u0006J"}, d2 = {"Lorg/amnezia/awg/viewmodel/InterfaceProxy;", "Landroidx/databinding/BaseObservable;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "other", "Lorg/amnezia/awg/config/Interface;", "(Lorg/amnezia/awg/config/Interface;)V", "()V", "value", "", "addresses", "getAddresses", "()Ljava/lang/String;", "setAddresses", "(Ljava/lang/String;)V", "dnsServers", "getDnsServers", "setDnsServers", "excludedApplications", "Landroidx/databinding/ObservableList;", "getExcludedApplications", "()Landroidx/databinding/ObservableList;", "includedApplications", "getIncludedApplications", "initPacketJunkSize", "getInitPacketJunkSize", "setInitPacketJunkSize", "initPacketMagicHeader", "getInitPacketMagicHeader", "setInitPacketMagicHeader", "junkPacketCount", "getJunkPacketCount", "setJunkPacketCount", "junkPacketMaxSize", "getJunkPacketMaxSize", "setJunkPacketMaxSize", "junkPacketMinSize", "getJunkPacketMinSize", "setJunkPacketMinSize", "listenPort", "getListenPort", "setListenPort", "mtu", "getMtu", "setMtu", "privateKey", "getPrivateKey", "setPrivateKey", "publicKey", "getPublicKey", "responsePacketJunkSize", "getResponsePacketJunkSize", "setResponsePacketJunkSize", "responsePacketMagicHeader", "getResponsePacketMagicHeader", "setResponsePacketMagicHeader", "transportPacketMagicHeader", "getTransportPacketMagicHeader", "setTransportPacketMagicHeader", "underloadPacketMagicHeader", "getUnderloadPacketMagicHeader", "setUnderloadPacketMagicHeader", "describeContents", "", "generateKeyPair", "", "resolve", "writeToParcel", "dest", "flags", "Companion", "InterfaceProxyCreator", "ui_debug"})
public final class InterfaceProxy extends androidx.databinding.BaseObservable implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull
    private final androidx.databinding.ObservableList<java.lang.String> excludedApplications = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.databinding.ObservableList<java.lang.String> includedApplications = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String addresses = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String dnsServers = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String listenPort = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String mtu = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String junkPacketCount = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String junkPacketMinSize = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String junkPacketMaxSize = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String initPacketJunkSize = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String responsePacketJunkSize = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String initPacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String responsePacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String underloadPacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String transportPacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String privateKey = "";
    @kotlin.jvm.JvmField
    @org.jetbrains.annotations.NotNull
    public static final android.os.Parcelable.Creator<org.amnezia.awg.viewmodel.InterfaceProxy> CREATOR = null;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.viewmodel.InterfaceProxy.Companion Companion = null;
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final androidx.databinding.ObservableList<java.lang.String> getExcludedApplications() {
        return null;
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final androidx.databinding.ObservableList<java.lang.String> getIncludedApplications() {
        return null;
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAddresses() {
        return null;
    }
    
    public final void setAddresses(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDnsServers() {
        return null;
    }
    
    public final void setDnsServers(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getListenPort() {
        return null;
    }
    
    public final void setListenPort(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getMtu() {
        return null;
    }
    
    public final void setMtu(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getJunkPacketCount() {
        return null;
    }
    
    public final void setJunkPacketCount(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getJunkPacketMinSize() {
        return null;
    }
    
    public final void setJunkPacketMinSize(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getJunkPacketMaxSize() {
        return null;
    }
    
    public final void setJunkPacketMaxSize(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getInitPacketJunkSize() {
        return null;
    }
    
    public final void setInitPacketJunkSize(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getResponsePacketJunkSize() {
        return null;
    }
    
    public final void setResponsePacketJunkSize(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getInitPacketMagicHeader() {
        return null;
    }
    
    public final void setInitPacketMagicHeader(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getResponsePacketMagicHeader() {
        return null;
    }
    
    public final void setResponsePacketMagicHeader(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnderloadPacketMagicHeader() {
        return null;
    }
    
    public final void setUnderloadPacketMagicHeader(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTransportPacketMagicHeader() {
        return null;
    }
    
    public final void setTransportPacketMagicHeader(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPrivateKey() {
        return null;
    }
    
    public final void setPrivateKey(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPublicKey() {
        return null;
    }
    
    private InterfaceProxy(android.os.Parcel parcel) {
        super();
    }
    
    public InterfaceProxy(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.config.Interface other) {
        super();
    }
    
    public InterfaceProxy() {
        super();
    }
    
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    public final void generateKeyPair() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.amnezia.awg.config.BadConfigException.class})
    @org.jetbrains.annotations.NotNull
    public final org.amnezia.awg.config.Interface resolve() throws org.amnezia.awg.config.BadConfigException {
        return null;
    }
    
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel dest, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/amnezia/awg/viewmodel/InterfaceProxy$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lorg/amnezia/awg/viewmodel/InterfaceProxy;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/amnezia/awg/viewmodel/InterfaceProxy$InterfaceProxyCreator;", "Landroid/os/Parcelable$Creator;", "Lorg/amnezia/awg/viewmodel/InterfaceProxy;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lorg/amnezia/awg/viewmodel/InterfaceProxy;", "ui_debug"})
    static final class InterfaceProxyCreator implements android.os.Parcelable.Creator<org.amnezia.awg.viewmodel.InterfaceProxy> {
        
        public InterfaceProxyCreator() {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public org.amnezia.awg.viewmodel.InterfaceProxy createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel parcel) {
            return null;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public org.amnezia.awg.viewmodel.InterfaceProxy[] newArray(int size) {
            return null;
        }
    }
}