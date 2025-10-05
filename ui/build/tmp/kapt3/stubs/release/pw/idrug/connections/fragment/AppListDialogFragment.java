package pw.idrug.connections.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J)\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H\u0002\u00a2\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lpw/idrug/connections/fragment/AppListDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "appData", "Lpw/idrug/connections/databinding/ObservableKeyedArrayList;", "", "Lpw/idrug/connections/model/ApplicationData;", "currentlySelectedApps", "", "initiallyExcluded", "", "selectButton", "Landroid/widget/Button;", "tabs", "Lcom/google/android/material/tabs/TabLayout;", "getPackagesHoldingPermissions", "Landroid/content/pm/PackageInfo;", "pm", "Landroid/content/pm/PackageManager;", "permissions", "", "(Landroid/content/pm/PackageManager;[Ljava/lang/String;)Ljava/util/List;", "loadData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "sendResult", "setButtonText", "Companion", "ui_release"})
public final class AppListDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull
    private final pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String, pw.idrug.connections.model.ApplicationData> appData = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<java.lang.String> currentlySelectedApps;
    private boolean initiallyExcluded = false;
    @org.jetbrains.annotations.Nullable
    private android.widget.Button selectButton;
    @org.jetbrains.annotations.Nullable
    private com.google.android.material.tabs.TabLayout tabs;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_SELECTED_APPS = "selected_apps";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_IS_EXCLUDED = "is_excluded";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REQUEST_SELECTION = "request_selection";
    @org.jetbrains.annotations.NotNull
    public static final pw.idrug.connections.fragment.AppListDialogFragment.Companion Companion = null;
    
    public AppListDialogFragment() {
        super();
    }
    
    private final void loadData() {
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.util.List<android.content.pm.PackageInfo> getPackagesHoldingPermissions(android.content.pm.PackageManager pm, java.lang.String[] permissions) {
        return null;
    }
    
    private final void setButtonText() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void sendResult() {
    }
    
    @java.lang.Override
    public void onDismiss(@org.jetbrains.annotations.NotNull
    android.content.DialogInterface dialog) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\u0007\u001a\u00020\b2\u001e\u0010\t\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\nj\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u0001`\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lpw/idrug/connections/fragment/AppListDialogFragment$Companion;", "", "()V", "KEY_IS_EXCLUDED", "", "KEY_SELECTED_APPS", "REQUEST_SELECTION", "newInstance", "Lpw/idrug/connections/fragment/AppListDialogFragment;", "selectedApps", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isExcluded", "", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final pw.idrug.connections.fragment.AppListDialogFragment newInstance(@org.jetbrains.annotations.Nullable
        java.util.ArrayList<java.lang.String> selectedApps, boolean isExcluded) {
            return null;
        }
    }
}