package org.amnezia.awg.model;

/**
 * Encapsulates the volatile and nonvolatile state of an AmneziaWG tunnel.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0012\u0018\u0000 +2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004:\u0001+B)\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\b\u0010\u001d\u001a\u00020\u0003H\u0017J\u000e\u0010\u001e\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u000e\u0010 \u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u000bH\u0016J\u000e\u0010#\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010$\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015J\u0016\u0010%\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010&J\u0016\u0010\'\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010*R$\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\r\u001a\u0004\u0018\u00010\t8G@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b8G@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\r\u001a\u0004\u0018\u00010\u00158G@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006,"}, d2 = {"Lorg/amnezia/awg/model/ObservableTunnel;", "Landroidx/databinding/BaseObservable;", "Lorg/amnezia/awg/databinding/Keyed;", "", "Lorg/amnezia/awg/backend/Tunnel;", "manager", "Lorg/amnezia/awg/model/TunnelManager;", "name", "config", "Lorg/amnezia/awg/config/Config;", "state", "Lorg/amnezia/awg/backend/Tunnel$State;", "(Lorg/amnezia/awg/model/TunnelManager;Ljava/lang/String;Lorg/amnezia/awg/config/Config;Lorg/amnezia/awg/backend/Tunnel$State;)V", "<set-?>", "getConfig", "()Lorg/amnezia/awg/config/Config;", "key", "getKey", "()Ljava/lang/String;", "getState", "()Lorg/amnezia/awg/backend/Tunnel$State;", "Lorg/amnezia/awg/backend/Statistics;", "statistics", "getStatistics", "()Lorg/amnezia/awg/backend/Statistics;", "deleteAsync", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConfigAsync", "getName", "getStatisticsAsync", "onConfigChanged", "onNameChanged", "onStateChange", "newState", "onStateChanged", "onStatisticsChanged", "setConfigAsync", "(Lorg/amnezia/awg/config/Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setNameAsync", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setStateAsync", "(Lorg/amnezia/awg/backend/Tunnel$State;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ui_debug"})
public final class ObservableTunnel extends androidx.databinding.BaseObservable implements org.amnezia.awg.databinding.Keyed<java.lang.String>, org.amnezia.awg.backend.Tunnel {
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.model.TunnelManager manager = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String name;
    @org.jetbrains.annotations.NotNull
    private org.amnezia.awg.backend.Tunnel.State state;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.config.Config config;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.backend.Statistics statistics;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/ObservableTunnel";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.model.ObservableTunnel.Companion Companion = null;
    
    public ObservableTunnel(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.model.TunnelManager manager, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    org.amnezia.awg.config.Config config, @org.jetbrains.annotations.NotNull
    org.amnezia.awg.backend.Tunnel.State state) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getKey() {
        return null;
    }
    
    @androidx.databinding.Bindable
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setNameAsync(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String onNameChanged(@org.jetbrains.annotations.NotNull
    java.lang.String name) {
        return null;
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.NotNull
    public final org.amnezia.awg.backend.Tunnel.State getState() {
        return null;
    }
    
    @java.lang.Override
    public void onStateChange(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.backend.Tunnel.State newState) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final org.amnezia.awg.backend.Tunnel.State onStateChanged(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.backend.Tunnel.State state) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setStateAsync(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.backend.Tunnel.State state, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Tunnel.State> $completion) {
        return null;
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.Nullable
    public final org.amnezia.awg.config.Config getConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getConfigAsync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setConfigAsync(@org.jetbrains.annotations.NotNull
    org.amnezia.awg.config.Config config, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.config.Config> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final org.amnezia.awg.config.Config onConfigChanged(@org.jetbrains.annotations.Nullable
    org.amnezia.awg.config.Config config) {
        return null;
    }
    
    @androidx.databinding.Bindable
    @org.jetbrains.annotations.Nullable
    public final org.amnezia.awg.backend.Statistics getStatistics() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStatisticsAsync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Statistics> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final org.amnezia.awg.backend.Statistics onStatisticsChanged(@org.jetbrains.annotations.Nullable
    org.amnezia.awg.backend.Statistics statistics) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAsync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/amnezia/awg/model/ObservableTunnel$Companion;", "", "()V", "TAG", "", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}