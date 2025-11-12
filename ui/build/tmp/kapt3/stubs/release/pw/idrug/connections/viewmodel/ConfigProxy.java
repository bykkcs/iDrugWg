package pw.idrug.connections.viewmodel;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0003\u001d\u001e\u001fB\u0011\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0004\u0010\bB\t\b\u0016\u00a2\u0006\u0004\b\u0004\u0010\tJ\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0007J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0015H\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lpw/idrug/connections/viewmodel/ConfigProxy;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "<init>", "(Landroid/os/Parcel;)V", "other", "Lorg/amnezia/awg/config/Config;", "(Lorg/amnezia/awg/config/Config;)V", "()V", "interface", "Lpw/idrug/connections/viewmodel/InterfaceProxy;", "getInterface", "()Lpw/idrug/connections/viewmodel/InterfaceProxy;", "peers", "Landroidx/databinding/ObservableList;", "Lpw/idrug/connections/viewmodel/PeerProxy;", "getPeers", "()Landroidx/databinding/ObservableList;", "addPeer", "describeContents", "", "buildConfigs", "Lpw/idrug/connections/viewmodel/ConfigProxy$BuiltConfigs;", "resolve", "writeToParcel", "", "dest", "flags", "BuiltConfigs", "ConfigProxyCreator", "Companion", "ui_release"})
public final class ConfigProxy implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final androidx.databinding.ObservableList<pw.idrug.connections.viewmodel.PeerProxy> peers = null;
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final android.os.Parcelable.Creator<pw.idrug.connections.viewmodel.ConfigProxy> CREATOR = null;
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.viewmodel.ConfigProxy.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final pw.idrug.connections.viewmodel.InterfaceProxy getInterface() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableList<pw.idrug.connections.viewmodel.PeerProxy> getPeers() {
        return null;
    }
    
    private ConfigProxy(android.os.Parcel parcel) {
        super();
    }
    
    public ConfigProxy(@org.jetbrains.annotations.NotNull()
    org.amnezia.awg.config.Config other) {
        super();
    }
    
    public ConfigProxy() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final pw.idrug.connections.viewmodel.PeerProxy addPeer() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.amnezia.awg.config.BadConfigException.class})
    @org.jetbrains.annotations.NotNull()
    public final pw.idrug.connections.viewmodel.ConfigProxy.BuiltConfigs buildConfigs() throws org.amnezia.awg.config.BadConfigException {
        return null;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.amnezia.awg.config.BadConfigException.class})
    @org.jetbrains.annotations.NotNull()
    public final org.amnezia.awg.config.Config resolve() throws org.amnezia.awg.config.BadConfigException {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel dest, int flags) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lpw/idrug/connections/viewmodel/ConfigProxy$BuiltConfigs;", "", "awg", "Lorg/amnezia/awg/config/Config;", "amConfig", "amQuick", "", "<init>", "(Lorg/amnezia/awg/config/Config;Lorg/amnezia/awg/config/Config;Ljava/lang/String;)V", "getAwg", "()Lorg/amnezia/awg/config/Config;", "getAmConfig", "getAmQuick", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "ui_release"})
    public static final class BuiltConfigs {
        @org.jetbrains.annotations.NotNull()
        private final org.amnezia.awg.config.Config awg = null;
        @org.jetbrains.annotations.NotNull()
        private final org.amnezia.awg.config.Config amConfig = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String amQuick = null;
        
        public BuiltConfigs(@org.jetbrains.annotations.NotNull()
        org.amnezia.awg.config.Config awg, @org.jetbrains.annotations.NotNull()
        org.amnezia.awg.config.Config amConfig, @org.jetbrains.annotations.NotNull()
        java.lang.String amQuick) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.amnezia.awg.config.Config getAwg() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.amnezia.awg.config.Config getAmConfig() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAmQuick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.amnezia.awg.config.Config component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.amnezia.awg.config.Config component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.viewmodel.ConfigProxy.BuiltConfigs copy(@org.jetbrains.annotations.NotNull()
        org.amnezia.awg.config.Config awg, @org.jetbrains.annotations.NotNull()
        org.amnezia.awg.config.Config amConfig, @org.jetbrains.annotations.NotNull()
        java.lang.String amQuick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lpw/idrug/connections/viewmodel/ConfigProxy$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lpw/idrug/connections/viewmodel/ConfigProxy;", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lpw/idrug/connections/viewmodel/ConfigProxy$ConfigProxyCreator;", "Landroid/os/Parcelable$Creator;", "Lpw/idrug/connections/viewmodel/ConfigProxy;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lpw/idrug/connections/viewmodel/ConfigProxy;", "ui_release"})
    static final class ConfigProxyCreator implements android.os.Parcelable.Creator<pw.idrug.connections.viewmodel.ConfigProxy> {
        
        public ConfigProxyCreator() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public pw.idrug.connections.viewmodel.ConfigProxy createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public pw.idrug.connections.viewmodel.ConfigProxy[] newArray(int size) {
            return null;
        }
    }
}