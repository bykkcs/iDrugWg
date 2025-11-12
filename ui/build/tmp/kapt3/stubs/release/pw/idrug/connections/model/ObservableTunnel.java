package pw.idrug.connections.model;

/**
 * Encapsulates the volatile and nonvolatile state of an iDrugConnections tunnel.
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 B2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004:\u0001BB?\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0014\u001a\u00020\u0003H\u0017J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\rH\u0016J\u000e\u0010$\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rJ\u0016\u0010%\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010&J\u000e\u0010,\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010-J\u000e\u0010.\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010-J\u0016\u00103\u001a\u00020\t2\u0006\u00104\u001a\u000205H\u0086@\u00a2\u0006\u0002\u00106J\u0016\u00103\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u00107J\u0012\u00108\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0012\u00109\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0012\u0010:\u001a\u0004\u0018\u00010\u00032\b\u0010*\u001a\u0004\u0018\u00010\u0003J\u000e\u0010?\u001a\u00020;H\u0086@\u00a2\u0006\u0002\u0010-J\u0012\u0010@\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010;J\u000e\u0010A\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R&\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r8G@BX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\t8G@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\t@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\"\u0010*\u001a\u0004\u0018\u00010\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0013R\u0011\u0010/\u001a\u0002008G\u00a2\u0006\u0006\u001a\u0004\b1\u00102R$\u0010<\u001a\u0004\u0018\u00010;2\b\u0010\u001c\u001a\u0004\u0018\u00010;8G@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010>\u00a8\u0006C"}, d2 = {"Lpw/idrug/connections/model/ObservableTunnel;", "Landroidx/databinding/BaseObservable;", "Lpw/idrug/connections/databinding/Keyed;", "", "Lorg/amnezia/awg/backend/Tunnel;", "manager", "Lpw/idrug/connections/model/TunnelManager;", "initialName", "config", "Lorg/amnezia/awg/config/Config;", "amConfig", "initialAmQuick", "initialState", "Lorg/amnezia/awg/backend/Tunnel$State;", "<init>", "(Lpw/idrug/connections/model/TunnelManager;Ljava/lang/String;Lorg/amnezia/awg/config/Config;Lorg/amnezia/awg/config/Config;Ljava/lang/String;Lorg/amnezia/awg/backend/Tunnel$State;)V", "internalName", "key", "getKey", "()Ljava/lang/String;", "getName", "updateName", "", "newName", "setNameAsync", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onNameChanged", "value", "state", "getState", "()Lorg/amnezia/awg/backend/Tunnel$State;", "setState", "(Lorg/amnezia/awg/backend/Tunnel$State;)V", "onStateChange", "newState", "onStateChanged", "setStateAsync", "(Lorg/amnezia/awg/backend/Tunnel$State;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConfig", "()Lorg/amnezia/awg/config/Config;", "getAmConfig", "amQuick", "getAmQuick", "getConfigAsync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAmConfigAsync", "quicReadyBadge", "", "getQuicReadyBadge", "()Z", "setConfigAsync", "configs", "Lpw/idrug/connections/viewmodel/ConfigProxy$BuiltConfigs;", "(Lpw/idrug/connections/viewmodel/ConfigProxy$BuiltConfigs;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lorg/amnezia/awg/config/Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onConfigChanged", "onAmConfigChanged", "onAmQuickChanged", "Lorg/amnezia/awg/backend/Statistics;", "statistics", "getStatistics", "()Lorg/amnezia/awg/backend/Statistics;", "getStatisticsAsync", "onStatisticsChanged", "deleteAsync", "Companion", "ui_release"})
public final class ObservableTunnel extends androidx.databinding.BaseObservable implements pw.idrug.connections.databinding.Keyed<java.lang.String>, org.amnezia.awg.backend.Tunnel {
    @org.jetbrains.annotations.NotNull()
    private final pw.idrug.connections.model.TunnelManager manager = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String internalName;
    @org.jetbrains.annotations.NotNull()
    private org.amnezia.awg.backend.Tunnel.State state;
    @org.jetbrains.annotations.Nullable()
    private org.amnezia.awg.config.Config config;
    @org.jetbrains.annotations.Nullable()
    private org.amnezia.awg.config.Config amConfig;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String amQuick;
    @org.jetbrains.annotations.Nullable()
    private org.amnezia.awg.backend.Statistics statistics;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "iDrugConnections/ObservableTunnel";
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.model.ObservableTunnel.Companion Companion = null;
    
    public ObservableTunnel(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.model.TunnelManager manager, @org.jetbrains.annotations.NotNull()
    java.lang.String initialName, @org.jetbrains.annotations.Nullable()
    org.amnezia.awg.config.Config config, @org.jetbrains.annotations.Nullable()
    org.amnezia.awg.config.Config amConfig, @org.jetbrains.annotations.Nullable()
    java.lang.String initialAmQuick, @org.jetbrains.annotations.NotNull()
    org.amnezia.awg.backend.Tunnel.State initialState) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getKey() {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    private final void updateName(java.lang.String newName) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setNameAsync(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String onNameChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final org.amnezia.awg.backend.Tunnel.State getState() {
        return null;
    }
    
    private final void setState(org.amnezia.awg.backend.Tunnel.State value) {
    }
    
    @java.lang.Override()
    public void onStateChange(@org.jetbrains.annotations.NotNull()
    org.amnezia.awg.backend.Tunnel.State newState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.amnezia.awg.backend.Tunnel.State onStateChanged(@org.jetbrains.annotations.NotNull()
    org.amnezia.awg.backend.Tunnel.State state) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setStateAsync(@org.jetbrains.annotations.NotNull()
    org.amnezia.awg.backend.Tunnel.State state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Tunnel.State> $completion) {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.Nullable()
    public final org.amnezia.awg.config.Config getConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.amnezia.awg.config.Config getAmConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAmQuick() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getConfigAsync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAmConfigAsync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @androidx.databinding.Bindable()
    public final boolean getQuicReadyBadge() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setConfigAsync(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.viewmodel.ConfigProxy.BuiltConfigs configs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setConfigAsync(@org.jetbrains.annotations.NotNull()
    org.amnezia.awg.config.Config config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.amnezia.awg.config.Config onConfigChanged(@org.jetbrains.annotations.Nullable()
    org.amnezia.awg.config.Config config) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.amnezia.awg.config.Config onAmConfigChanged(@org.jetbrains.annotations.Nullable()
    org.amnezia.awg.config.Config amConfig) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String onAmQuickChanged(@org.jetbrains.annotations.Nullable()
    java.lang.String amQuick) {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.Nullable()
    public final org.amnezia.awg.backend.Statistics getStatistics() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getStatisticsAsync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Statistics> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.amnezia.awg.backend.Statistics onStatisticsChanged(@org.jetbrains.annotations.Nullable()
    org.amnezia.awg.backend.Statistics statistics) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAsync(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lpw/idrug/connections/model/ObservableTunnel$Companion;", "", "<init>", "()V", "TAG", "", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}