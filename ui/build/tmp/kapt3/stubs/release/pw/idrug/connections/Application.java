package pw.idrug.connections;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u000e\u0010\u0017\u001a\u00020\u0004H\u0082@\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lpw/idrug/connections/Application;", "Landroid/app/Application;", "()V", "backend", "Lpw/idrug/connections/backend/Backend;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "futureBackend", "Lkotlinx/coroutines/CompletableDeferred;", "initialPreferencesSnapshot", "Landroidx/datastore/preferences/core/Preferences;", "preferencesDataStore", "Landroidx/datastore/core/DataStore;", "rootShell", "Lpw/idrug/connections/util/RootShell;", "toolsInstaller", "Lpw/idrug/connections/util/ToolsInstaller;", "tunnelManager", "Lpw/idrug/connections/model/TunnelManager;", "attachBaseContext", "", "context", "Landroid/content/Context;", "determineBackend", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCreate", "onTerminate", "Companion", "ui_release"})
public final class Application extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CompletableDeferred<pw.idrug.connections.backend.Backend> futureBackend = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @org.jetbrains.annotations.Nullable()
    private pw.idrug.connections.backend.Backend backend;
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
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onTerminate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lpw/idrug/connections/Application$Companion;", "", "()V", "TAG", "", "USER_AGENT", "getUSER_AGENT", "()Ljava/lang/String;", "weakSelf", "Ljava/lang/ref/WeakReference;", "Lpw/idrug/connections/Application;", "get", "getBackend", "Lpw/idrug/connections/backend/Backend;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getInitialPreferencesSnapshot", "Landroidx/datastore/preferences/core/Preferences;", "getPreferencesDataStore", "Landroidx/datastore/core/DataStore;", "getRootShell", "Lpw/idrug/connections/util/RootShell;", "getToolsInstaller", "Lpw/idrug/connections/util/ToolsInstaller;", "getTunnelManager", "Lpw/idrug/connections/model/TunnelManager;", "ui_release"})
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