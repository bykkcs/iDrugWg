package pw.idrug.connections.model;

/**
 * Maintains and mediates changes to the set of available iDrugConnections tunnels,
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u001e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ;2\u00020\u0001:\u0002:;B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0086@\u00a2\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010$\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0006\u0010%\u001a\u00020\u001bJ$\u0010&\u001a\u00020\u001b2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\t0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0*H\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\u0016\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u001e\u00100\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u00101J\u001e\u00102\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u00103J\u001e\u00104\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u00105J\u0016\u00106\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u00107\u001a\u0002082\u0006\u0010\u001c\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u001dJ\b\u00109\u001a\u00020\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R*\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\n8G@BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u00a8\u0006<"}, d2 = {"Lpw/idrug/connections/model/TunnelManager;", "Landroidx/databinding/BaseObservable;", "configStore", "Lpw/idrug/connections/configStore/ConfigStore;", "<init>", "(Lpw/idrug/connections/configStore/ConfigStore;)V", "tunnels", "Lkotlinx/coroutines/CompletableDeferred;", "Lpw/idrug/connections/databinding/ObservableSortedKeyedArrayList;", "", "Lpw/idrug/connections/model/ObservableTunnel;", "context", "Landroid/content/Context;", "tunnelMap", "haveLoaded", "", "addToList", "name", "config", "Lpw/idrug/connections/config/Config;", "state", "Lpw/idrug/connections/backend/Tunnel$State;", "getTunnels", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "(Ljava/lang/String;Lpw/idrug/connections/config/Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "tunnel", "(Lpw/idrug/connections/model/ObservableTunnel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "value", "lastUsedTunnel", "getLastUsedTunnel", "()Lpw/idrug/connections/model/ObservableTunnel;", "setLastUsedTunnel", "(Lpw/idrug/connections/model/ObservableTunnel;)V", "getTunnelConfig", "onCreate", "onTunnelsLoaded", "present", "", "running", "", "refreshTunnelStates", "restoreState", "force", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveState", "setTunnelConfig", "(Lpw/idrug/connections/model/ObservableTunnel;Lpw/idrug/connections/config/Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTunnelName", "(Lpw/idrug/connections/model/ObservableTunnel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTunnelState", "(Lpw/idrug/connections/model/ObservableTunnel;Lpw/idrug/connections/backend/Tunnel$State;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTunnelState", "getTunnelStatistics", "Lpw/idrug/connections/backend/Statistics;", "completeTunnelsDeferred", "IntentReceiver", "Companion", "ui_release"})
public final class TunnelManager extends androidx.databinding.BaseObservable {
    @org.jetbrains.annotations.NotNull()
    private final pw.idrug.connections.configStore.ConfigStore configStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CompletableDeferred<pw.idrug.connections.databinding.ObservableSortedKeyedArrayList<java.lang.String, pw.idrug.connections.model.ObservableTunnel>> tunnels = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final pw.idrug.connections.databinding.ObservableSortedKeyedArrayList<java.lang.String, pw.idrug.connections.model.ObservableTunnel> tunnelMap = null;
    private boolean haveLoaded = false;
    @org.jetbrains.annotations.Nullable()
    private pw.idrug.connections.model.ObservableTunnel lastUsedTunnel;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "iDrugConnections/TunnelManager";
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.model.TunnelManager.Companion Companion = null;
    
    public TunnelManager(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.configStore.ConfigStore configStore) {
        super();
    }
    
    private final pw.idrug.connections.model.ObservableTunnel addToList(java.lang.String name, pw.idrug.connections.config.Config config, pw.idrug.connections.backend.Tunnel.State state) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTunnels(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.databinding.ObservableSortedKeyedArrayList<java.lang.String, pw.idrug.connections.model.ObservableTunnel>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object create(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    pw.idrug.connections.config.Config config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.model.ObservableTunnel> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.Nullable()
    public final pw.idrug.connections.model.ObservableTunnel getLastUsedTunnel() {
        return null;
    }
    
    private final void setLastUsedTunnel(pw.idrug.connections.model.ObservableTunnel value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTunnelConfig(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.config.Config> $completion) {
        return null;
    }
    
    public final void onCreate() {
    }
    
    private final void onTunnelsLoaded(java.lang.Iterable<java.lang.String> present, java.util.Collection<java.lang.String> running) {
    }
    
    private final void refreshTunnelStates() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object restoreState(boolean force, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveState(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setTunnelConfig(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    pw.idrug.connections.config.Config config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setTunnelName(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setTunnelState(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    pw.idrug.connections.backend.Tunnel.State state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.backend.Tunnel.State> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTunnelState(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.backend.Tunnel.State> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTunnelStatistics(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super pw.idrug.connections.backend.Statistics> $completion) {
        return null;
    }
    
    private final void completeTunnelsDeferred() {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lpw/idrug/connections/model/TunnelManager$Companion;", "", "<init>", "()V", "TAG", "", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016\u00a8\u0006\n"}, d2 = {"Lpw/idrug/connections/model/TunnelManager$IntentReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "ui_release"})
    public static final class IntentReceiver extends android.content.BroadcastReceiver {
        
        public IntentReceiver() {
            super();
        }
        
        @java.lang.Override()
        public void onReceive(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.Nullable()
        android.content.Intent intent) {
        }
    }
}