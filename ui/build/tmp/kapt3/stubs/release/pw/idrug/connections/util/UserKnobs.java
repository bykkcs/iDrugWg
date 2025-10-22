package pw.idrug.connections.util;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010%\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\"H\u0086@\u00a2\u0006\u0002\u0010&J\u001c\u0010+\u001a\u00020\f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\"0(H\u0086@\u00a2\u0006\u0002\u0010,J\u0016\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u00105\u001a\u00020\f2\u0006\u00101\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\nR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\nR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\nR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b \u0010\nR\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\b8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\nR\u001a\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0(0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0(0\b8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\nR\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b/\u0010\nR\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\b8F\u00a2\u0006\u0006\u001a\u0004\b4\u0010\n\u00a8\u00066"}, d2 = {"Lpw/idrug/connections/util/UserKnobs;", "", "<init>", "()V", "ENABLE_KERNEL_MODULE", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "enableKernelModule", "Lkotlinx/coroutines/flow/Flow;", "getEnableKernelModule", "()Lkotlinx/coroutines/flow/Flow;", "setEnableKernelModule", "", "enable", "(Ljava/lang/Boolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MULTIPLE_TUNNELS", "multipleTunnels", "getMultipleTunnels", "DARK_THEME", "darkTheme", "getDarkTheme", "setDarkTheme", "on", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ALLOW_REMOTE_CONTROL_INTENTS", "allowRemoteControlIntents", "getAllowRemoteControlIntents", "ENABLE_NOTIFICATION", "enableNotification", "getEnableNotification", "RESTORE_ON_BOOT", "restoreOnBoot", "getRestoreOnBoot", "LAST_USED_TUNNEL", "", "lastUsedTunnel", "getLastUsedTunnel", "setLastUsedTunnel", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "RUNNING_TUNNELS", "", "runningTunnels", "getRunningTunnels", "setRunningTunnels", "(Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "UPDATES_AUTO_CHECK_ENABLED", "updatesAutoCheckEnabled", "getUpdatesAutoCheckEnabled", "setUpdatesAutoCheckEnabled", "enabled", "ACCOUNT_AUTO_IMPORT", "accountAutoImport", "getAccountAutoImport", "setAccountAutoImport", "ui_release"})
public final class UserKnobs {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> ENABLE_KERNEL_MODULE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> MULTIPLE_TUNNELS = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> DARK_THEME = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> ALLOW_REMOTE_CONTROL_INTENTS = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> ENABLE_NOTIFICATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> RESTORE_ON_BOOT = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> LAST_USED_TUNNEL = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> RUNNING_TUNNELS = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> UPDATES_AUTO_CHECK_ENABLED = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> ACCOUNT_AUTO_IMPORT = null;
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.util.UserKnobs INSTANCE = null;
    
    private UserKnobs() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getEnableKernelModule() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setEnableKernelModule(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean enable, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getMultipleTunnels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getDarkTheme() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setDarkTheme(boolean on, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getAllowRemoteControlIntents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getEnableNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getRestoreOnBoot() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getLastUsedTunnel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setLastUsedTunnel(@org.jetbrains.annotations.Nullable()
    java.lang.String lastUsedTunnel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.Set<java.lang.String>> getRunningTunnels() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setRunningTunnels(@org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> runningTunnels, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getUpdatesAutoCheckEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setUpdatesAutoCheckEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getAccountAutoImport() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setAccountAutoImport(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}