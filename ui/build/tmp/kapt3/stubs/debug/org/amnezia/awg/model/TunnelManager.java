package org.amnezia.awg.model;

/**
 * Maintains and mediates changes to the set of available AmneziaWG tunnels,
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u000e\u0018\u0000 82\u00020\u0001:\u000289B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J \u0010\u001b\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010 J\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\u0011H\u0086@\u00a2\u0006\u0002\u0010&J\u0006\u0010\'\u001a\u00020\u001eJ$\u0010(\u001a\u00020\u001e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00120*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120,H\u0002J\b\u0010-\u001a\u00020\u001eH\u0002J\u0016\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u00100J\u000e\u00101\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010&J\u001e\u00102\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u00103J\u001e\u00104\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u00105J\u001e\u00106\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u00107R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R*\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8G@BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\n0\u00110\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lorg/amnezia/awg/model/TunnelManager;", "Landroidx/databinding/BaseObservable;", "configStore", "Lorg/amnezia/awg/configStore/ConfigStore;", "(Lorg/amnezia/awg/configStore/ConfigStore;)V", "context", "Landroid/content/Context;", "haveLoaded", "", "value", "Lorg/amnezia/awg/model/ObservableTunnel;", "lastUsedTunnel", "getLastUsedTunnel", "()Lorg/amnezia/awg/model/ObservableTunnel;", "setLastUsedTunnel", "(Lorg/amnezia/awg/model/ObservableTunnel;)V", "tunnelMap", "Lorg/amnezia/awg/databinding/ObservableSortedKeyedArrayList;", "", "tunnels", "Lkotlinx/coroutines/CompletableDeferred;", "addToList", "name", "config", "Lorg/amnezia/awg/config/Config;", "state", "Lorg/amnezia/awg/backend/Tunnel$State;", "create", "(Ljava/lang/String;Lorg/amnezia/awg/config/Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "tunnel", "(Lorg/amnezia/awg/model/ObservableTunnel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTunnelConfig", "getTunnelState", "getTunnelStatistics", "Lorg/amnezia/awg/backend/Statistics;", "getTunnels", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCreate", "onTunnelsLoaded", "present", "", "running", "", "refreshTunnelStates", "restoreState", "force", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveState", "setTunnelConfig", "(Lorg/amnezia/awg/model/ObservableTunnel;Lorg/amnezia/awg/config/Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTunnelName", "(Lorg/amnezia/awg/model/ObservableTunnel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTunnelState", "(Lorg/amnezia/awg/model/ObservableTunnel;Lorg/amnezia/awg/backend/Tunnel$State;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "IntentReceiver", "ui_debug"})
public final class TunnelManager extends androidx.databinding.BaseObservable {
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.configStore.ConfigStore configStore = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CompletableDeferred<org.amnezia.awg.databinding.ObservableSortedKeyedArrayList<java.lang.String, org.amnezia.awg.model.ObservableTunnel>> tunnels = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.databinding.ObservableSortedKeyedArrayList<java.lang.String, org.amnezia.awg.model.ObservableTunnel> tunnelMap = null;
    private boolean haveLoaded = false;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.model.ObservableTunnel lastUsedTunnel;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/TunnelManager";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.model.TunnelManager.Companion Companion = null;
    
    public TunnelManager(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.configStore.ConfigStore configStore) {
        super();
    }
    
    private final org.amnezia.awg.model.ObservableTunnel addToList(java.lang.String name, org.amnezia.awg.config.Config config, org.amnezia.awg.backend.Tunnel.State state) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTunnels(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.databinding.ObservableSortedKeyedArrayList<java.lang.String, org.amnezia.awg.model.ObservableTunnel>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object create(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    org.amnezia.awg.config.Config config, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.model.ObservableTunnel> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.Nullable
    public final org.amnezia.awg.model.ObservableTunnel getLastUsedTunnel() {
        return null;
    }
    
    private final void setLastUsedTunnel(org.amnezia.awg.model.ObservableTunnel value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTunnelConfig(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    public final void onCreate() {
    }
    
    private final void onTunnelsLoaded(java.lang.Iterable<java.lang.String> present, java.util.Collection<java.lang.String> running) {
    }
    
    private final void refreshTunnelStates() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object restoreState(boolean force, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveState(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setTunnelConfig(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    org.amnezia.awg.config.Config config, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setTunnelName(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setTunnelState(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    org.amnezia.awg.backend.Tunnel.State state, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Tunnel.State> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTunnelState(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Tunnel.State> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTunnelStatistics(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.ObservableTunnel tunnel, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Statistics> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/amnezia/awg/model/TunnelManager$Companion;", "", "()V", "TAG", "", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a8\u0006\t"}, d2 = {"Lorg/amnezia/awg/model/TunnelManager$IntentReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "ui_debug"})
    public static final class IntentReceiver extends android.content.BroadcastReceiver {
        
        public IntentReceiver() {
            super();
        }
        
        @java.lang.Override
        public void onReceive(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.Nullable
        android.content.Intent intent) {
        }
    }
}