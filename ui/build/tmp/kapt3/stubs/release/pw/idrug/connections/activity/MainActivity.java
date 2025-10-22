package pw.idrug.connections.activity;

/**
 * CRUD interface for iDrugConnections tunnels. This activity serves as the main entry point to the
 * iDrugConnections application, and contains several fragments for listing, viewing details of, and
 * editing the configuration and interface state of iDrugConnections tunnels.
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 72\u00020\u00012\u00020\u0002:\u00017B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0014J\u0012\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u0006\u0010\"\u001a\u00020\u0019J\b\u0010#\u001a\u00020\u0019H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\u0010\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\'H\u0016J\u0010\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020*H\u0016J\u001c\u0010+\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010-H\u0014J\u0010\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u000201H\u0002J\u000e\u00102\u001a\u00020\u0019H\u0082@\u00a2\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u000206H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\u00a8\u00068"}, d2 = {"Lpw/idrug/connections/activity/MainActivity;", "Lpw/idrug/connections/activity/BaseActivity;", "Landroidx/fragment/app/FragmentManager$OnBackStackChangedListener;", "<init>", "()V", "actionBar", "Landroidx/appcompat/app/ActionBar;", "isTwoPaneLayout", "", "backPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "notificationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "updateDialogVersionShown", "", "Ljava/lang/Integer;", "updateViewModel", "Lpw/idrug/connections/ota/UpdateViewModel;", "getUpdateViewModel", "()Lpw/idrug/connections/ota/UpdateViewModel;", "updateViewModel$delegate", "Lkotlin/Lazy;", "handleBackPressed", "", "onBackStackChanged", "onResume", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "syncSystemNavigationBarColor", "bottomNavigation", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "refreshSystemNavigationBarColor", "subscribeToGlobalNotifications", "registerForFcm", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onSelectedTunnelChanged", "oldTunnel", "Lpw/idrug/connections/model/ObservableTunnel;", "newTunnel", "safeReplaceFragment", "fragment", "Landroidx/fragment/app/Fragment;", "performAutoUpdateCheck", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "maybeShowUpdateDialog", "state", "Lpw/idrug/connections/ota/UpdateState;", "Companion", "ui_release"})
public final class MainActivity extends pw.idrug.connections.activity.BaseActivity implements androidx.fragment.app.FragmentManager.OnBackStackChangedListener {
    @org.jetbrains.annotations.Nullable()
    private androidx.appcompat.app.ActionBar actionBar;
    private boolean isTwoPaneLayout = false;
    @org.jetbrains.annotations.Nullable()
    private androidx.activity.OnBackPressedCallback backPressedCallback;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> notificationPermissionLauncher = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer updateDialogVersionShown;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy updateViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_OPEN_ACCOUNT = "open_account";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String UPDATE_DIALOG_TAG = "update_dialog";
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.activity.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    private final pw.idrug.connections.ota.UpdateViewModel getUpdateViewModel() {
        return null;
    }
    
    private final void handleBackPressed() {
    }
    
    @java.lang.Override()
    public void onBackStackChanged() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void syncSystemNavigationBarColor(com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigation) {
    }
    
    public final void refreshSystemNavigationBarColor() {
    }
    
    private final void subscribeToGlobalNotifications() {
    }
    
    private final void registerForFcm() {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected boolean onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable()
    pw.idrug.connections.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable()
    pw.idrug.connections.model.ObservableTunnel newTunnel) {
        return false;
    }
    
    private final void safeReplaceFragment(androidx.fragment.app.Fragment fragment) {
    }
    
    private final java.lang.Object performAutoUpdateCheck(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void maybeShowUpdateDialog(pw.idrug.connections.ota.UpdateState state) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lpw/idrug/connections/activity/MainActivity$Companion;", "", "<init>", "()V", "EXTRA_OPEN_ACCOUNT", "", "UPDATE_DIALOG_TAG", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}