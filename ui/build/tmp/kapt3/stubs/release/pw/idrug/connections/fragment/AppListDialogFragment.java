package pw.idrug.connections.fragment;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J)\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\t2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060#H\u0002\u00a2\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u001aH\u0002J\u0012\u0010&\u001a\u00020\'2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010(\u001a\u00020\u001aH\u0002J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006-"}, d2 = {"Lpw/idrug/connections/fragment/AppListDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "appData", "Lpw/idrug/connections/databinding/ObservableKeyedArrayList;", "", "Lpw/idrug/connections/model/ApplicationData;", "currentlySelectedApps", "", "initiallyExcluded", "", "selectButton", "Landroid/widget/Button;", "tabs", "Lcom/google/android/material/tabs/TabLayout;", "popularCount", "", "headerRowConfigurationHandler", "Lpw/idrug/connections/databinding/ObservableKeyedRecyclerViewAdapter$RowConfigurationHandler;", "Lpw/idrug/connections/databinding/ObservableKeyedRecyclerViewAdapter$RowConfigurationHandler;", "rowConfigurationHandler", "Lpw/idrug/connections/databinding/AppListItemBinding;", "getRowConfigurationHandler", "()Lpw/idrug/connections/databinding/ObservableKeyedRecyclerViewAdapter$RowConfigurationHandler;", "loadData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "getPackagesHoldingPermissions", "Landroid/content/pm/PackageInfo;", "pm", "Landroid/content/pm/PackageManager;", "permissions", "", "(Landroid/content/pm/PackageManager;[Ljava/lang/String;)Ljava/util/List;", "setButtonText", "onCreateDialog", "Landroid/app/Dialog;", "sendResult", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "Companion", "ui_release"})
public final class AppListDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    private final pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String, pw.idrug.connections.model.ApplicationData> appData = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<java.lang.String> currentlySelectedApps;
    private boolean initiallyExcluded = false;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button selectButton;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.material.tabs.TabLayout tabs;
    private int popularCount = 0;
    @org.jetbrains.annotations.NotNull()
    private final pw.idrug.connections.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler<pw.idrug.connections.databinding.AppListItemBinding, pw.idrug.connections.model.ApplicationData> headerRowConfigurationHandler = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SELECTED_APPS = "selected_apps";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_IS_EXCLUDED = "is_excluded";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REQUEST_SELECTION = "request_selection";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> POPULAR_APP_ORDER = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, java.lang.Integer> POPULAR_ORDER_MAP = null;
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.fragment.AppListDialogFragment.Companion Companion = null;
    
    public AppListDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final pw.idrug.connections.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler<pw.idrug.connections.databinding.AppListItemBinding, pw.idrug.connections.model.ApplicationData> getRowConfigurationHandler() {
        return null;
    }
    
    private final void loadData() {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.util.List<android.content.pm.PackageInfo> getPackagesHoldingPermissions(android.content.pm.PackageManager pm, java.lang.String[] permissions) {
        return null;
    }
    
    private final void setButtonText() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void sendResult() {
    }
    
    @java.lang.Override()
    public void onDismiss(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J(\u0010\r\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t\u0012\u0004\u0012\u00020\f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\tH\u0002J.\u0010\u0011\u001a\u00020\u00122\u001e\u0010\u0013\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0014j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u0001`\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lpw/idrug/connections/fragment/AppListDialogFragment$Companion;", "", "<init>", "()V", "KEY_SELECTED_APPS", "", "KEY_IS_EXCLUDED", "REQUEST_SELECTION", "POPULAR_APP_ORDER", "", "POPULAR_ORDER_MAP", "", "", "prioritizePopularApps", "Lkotlin/Pair;", "Lpw/idrug/connections/model/ApplicationData;", "apps", "newInstance", "Lpw/idrug/connections/fragment/AppListDialogFragment;", "selectedApps", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isExcluded", "", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final kotlin.Pair<java.util.List<pw.idrug.connections.model.ApplicationData>, java.lang.Integer> prioritizePopularApps(java.util.List<pw.idrug.connections.model.ApplicationData> apps) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.fragment.AppListDialogFragment newInstance(@org.jetbrains.annotations.Nullable()
        java.util.ArrayList<java.lang.String> selectedApps, boolean isExcluded) {
            return null;
        }
    }
}