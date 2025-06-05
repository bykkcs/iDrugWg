package org.amnezia.awg;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u000e\u0010\u0016\u001a\u00020\u0004H\u0082@\u00a2\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/amnezia/awg/Application;", "Landroid/app/Application;", "()V", "backend", "Lorg/amnezia/awg/backend/Backend;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "futureBackend", "Lkotlinx/coroutines/CompletableDeferred;", "preferencesDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "rootShell", "Lorg/amnezia/awg/util/RootShell;", "toolsInstaller", "Lorg/amnezia/awg/util/ToolsInstaller;", "tunnelManager", "Lorg/amnezia/awg/model/TunnelManager;", "attachBaseContext", "", "context", "Landroid/content/Context;", "determineBackend", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCreate", "onTerminate", "Companion", "ui_debug"})
public final class Application extends android.app.Application {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CompletableDeferred<org.amnezia.awg.backend.Backend> futureBackend = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.backend.Backend backend;
    private org.amnezia.awg.util.RootShell rootShell;
    private androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> preferencesDataStore;
    private org.amnezia.awg.util.ToolsInstaller toolsInstaller;
    private org.amnezia.awg.model.TunnelManager tunnelManager;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String USER_AGENT = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/Application";
    private static java.lang.ref.WeakReference<org.amnezia.awg.Application> weakSelf;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.Application.Companion Companion = null;
    
    public Application() {
        super();
    }
    
    @java.lang.Override
    protected void attachBaseContext(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    private final java.lang.Object determineBackend(kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Backend> $completion) {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public void onTerminate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/amnezia/awg/Application$Companion;", "", "()V", "TAG", "", "USER_AGENT", "getUSER_AGENT", "()Ljava/lang/String;", "weakSelf", "Ljava/lang/ref/WeakReference;", "Lorg/amnezia/awg/Application;", "get", "getBackend", "Lorg/amnezia/awg/backend/Backend;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getPreferencesDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "getRootShell", "Lorg/amnezia/awg/util/RootShell;", "getToolsInstaller", "Lorg/amnezia/awg/util/ToolsInstaller;", "getTunnelManager", "Lorg/amnezia/awg/model/TunnelManager;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getUSER_AGENT() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.amnezia.awg.Application get() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object getBackend(@org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super org.amnezia.awg.backend.Backend> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.amnezia.awg.util.RootShell getRootShell() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getPreferencesDataStore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.amnezia.awg.util.ToolsInstaller getToolsInstaller() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final org.amnezia.awg.model.TunnelManager getTunnelManager() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.coroutines.CoroutineScope getCoroutineScope() {
            return null;
        }
    }
}