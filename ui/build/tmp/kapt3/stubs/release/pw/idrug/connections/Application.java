package pw.idrug.connections;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u000e\u0010\u001b\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u0018H\u0082@\u00a2\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0018H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lpw/idrug/connections/Application;", "Landroid/app/Application;", "<init>", "()V", "futureBackend", "Lkotlinx/coroutines/CompletableDeferred;", "Lpw/idrug/connections/backend/Backend;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "backend", "isConnectionStatusServiceStarted", "", "isServiceMonitoringActive", "rootShell", "Lpw/idrug/connections/util/RootShell;", "preferencesDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "initialPreferencesSnapshot", "toolsInstaller", "Lpw/idrug/connections/util/ToolsInstaller;", "tunnelManager", "Lpw/idrug/connections/model/TunnelManager;", "attachBaseContext", "", "context", "Landroid/content/Context;", "determineBackend", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processNotification", "onCreate", "onTerminate", "Companion", "ui_release"})
public final class Application extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CompletableDeferred<pw.idrug.connections.backend.Backend> futureBackend = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @org.jetbrains.annotations.Nullable()
    private pw.idrug.connections.backend.Backend backend;
    private boolean isConnectionStatusServiceStarted = false;
    private boolean isServiceMonitoringActive = true;
    private pw.idrug.connections.util.RootShell rootShell;
    private androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> preferencesDataStore;
    private androidx.datastore.preferences.core.Preferences initialPreferencesSnapshot;
    private pw.idrug.connections.util.ToolsInstaller toolsInstaller;
    private pw.idrug.connections.model.TunnelManager tunnelManager;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_AGENT = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "iDrugConnections/Application";
    private static java.lang.ref.WeakReference<pw.idrug.connections.Application> weakSelf;
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.Application.Companion Companion = null;
    
    public Application() {
        super();
    }
    
    @java.lang.Override()
    protected void attachBaseContext(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final java.lang.Object determineBackend(kotlin.coroutines.Continuation<? super pw.idrug.connections.backend.Backend> $completion) {
        return null;
    }
    
    private final java.lang.Object processNotification(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onTerminate() {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lpw/idrug/connections/Application$Companion;", "", "<init>", "()V", "USER_AGENT", "", "getUSER_AGENT", "()Ljava/lang/String;", "TAG", "weakSelf", "Ljava/lang/ref/WeakReference;", "Lpw/idrug/connections/Application;", "get", "getBackend", "Lpw/idrug/connections/backend/Backend;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRootShell", "Lpw/idrug/connections/util/RootShell;", "getPreferencesDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "getInitialPreferencesSnapshot", "getToolsInstaller", "Lpw/idrug/connections/util/ToolsInstaller;", "getTunnelManager", "Lpw/idrug/connections/model/TunnelManager;", "getCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUSER_AGENT() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.Application get() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Object getBackend(@org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super pw.idrug.connections.backend.Backend> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.util.RootShell getRootShell() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getPreferencesDataStore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences getInitialPreferencesSnapshot() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.util.ToolsInstaller getToolsInstaller() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.model.TunnelManager getTunnelManager() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.coroutines.CoroutineScope getCoroutineScope() {
            return null;
        }
    }
}