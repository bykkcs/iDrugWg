package org.amnezia.awg.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007B\u0007\b\u0016\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0012\u001a\u00020\u000fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0006J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0014H\u0016R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lorg/amnezia/awg/viewmodel/ConfigProxy;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "other", "Lorg/amnezia/awg/config/Config;", "(Lorg/amnezia/awg/config/Config;)V", "()V", "interface", "Lorg/amnezia/awg/viewmodel/InterfaceProxy;", "getInterface", "()Lorg/amnezia/awg/viewmodel/InterfaceProxy;", "peers", "Landroidx/databinding/ObservableList;", "Lorg/amnezia/awg/viewmodel/PeerProxy;", "getPeers", "()Landroidx/databinding/ObservableList;", "addPeer", "describeContents", "", "resolve", "writeToParcel", "", "dest", "flags", "Companion", "ConfigProxyCreator", "ui_debug"})
public final class ConfigProxy implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull
    private final androidx.databinding.ObservableList<org.amnezia.awg.viewmodel.PeerProxy> peers = null;
    @kotlin.jvm.JvmField
    @org.jetbrains.annotations.NotNull
    public static final android.os.Parcelable.Creator<org.amnezia.awg.viewmodel.ConfigProxy> CREATOR = null;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.viewmodel.ConfigProxy.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull
    public final org.amnezia.awg.viewmodel.InterfaceProxy getInterface() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.databinding.ObservableList<org.amnezia.awg.viewmodel.PeerProxy> getPeers() {
        return null;
    }
    
    private ConfigProxy(android.os.Parcel parcel) {
        super();
    }
    
    public ConfigProxy(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.config.Config other) {
        super();
    }
    
    public ConfigProxy() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final org.amnezia.awg.viewmodel.PeerProxy addPeer() {
        return null;
    }
    
    @java.lang.Override
    public int describeContents() {
        return 0;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.amnezia.awg.config.BadConfigException.class})
    @org.jetbrains.annotations.NotNull
    public final org.amnezia.awg.config.Config resolve() throws org.amnezia.awg.config.BadConfigException {
        return null;
    }
    
    @java.lang.Override
    public void writeToParcel(@org.jetbrains.annotations.NotNull
    android.os.Parcel dest, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/amnezia/awg/viewmodel/ConfigProxy$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lorg/amnezia/awg/viewmodel/ConfigProxy;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/amnezia/awg/viewmodel/ConfigProxy$ConfigProxyCreator;", "Landroid/os/Parcelable$Creator;", "Lorg/amnezia/awg/viewmodel/ConfigProxy;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lorg/amnezia/awg/viewmodel/ConfigProxy;", "ui_debug"})
    static final class ConfigProxyCreator implements android.os.Parcelable.Creator<org.amnezia.awg.viewmodel.ConfigProxy> {
        
        public ConfigProxyCreator() {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public org.amnezia.awg.viewmodel.ConfigProxy createFromParcel(@org.jetbrains.annotations.NotNull
        android.os.Parcel parcel) {
            return null;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public org.amnezia.awg.viewmodel.ConfigProxy[] newArray(int size) {
            return null;
        }
    }
}