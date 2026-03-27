package pw.idrug.connections.fragment;

/**
 * Fragment containing a list of known iDrugConnections tunnels. It allows creating and deleting tunnels.
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 @2\u00020\u0001:\u0002?@B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010\u001b2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010$\u001a\u00020\u0019H\u0016J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\u001dH\u0016J\u001c\u0010\'\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u000f2\b\u0010)\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0012\u0010/\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u00100\u001a\u00020\u00192\u0006\u00101\u001a\u000202H\u0002J\u001e\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020\u000f2\n\u00106\u001a\u0006\u0012\u0002\b\u000307H\u0002J\u0010\u00108\u001a\u00020\u00192\u0006\u00105\u001a\u00020\u000fH\u0002J\u001c\u00109\u001a\u00020\u00192\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u0002J\u0018\u0010:\u001a\u00020\u00192\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002R\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u000e0\u000e0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00170\u00170\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006A"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment;", "Lpw/idrug/connections/fragment/BaseFragment;", "<init>", "()V", "actionModeListener", "Lpw/idrug/connections/fragment/TunnelListFragment$ActionModeListener;", "actionMode", "Landroidx/appcompat/view/ActionMode;", "backPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "binding", "Lpw/idrug/connections/databinding/TunnelListFragmentBinding;", "observedTunnels", "Lpw/idrug/connections/databinding/ObservableKeyedArrayList;", "", "Lpw/idrug/connections/model/ObservableTunnel;", "tunnelsCallback", "Landroidx/databinding/ObservableList$OnListChangedCallback;", "Landroidx/databinding/ObservableList$OnListChangedCallback;", "tunnelFileImportResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "qrImportResultLauncher", "Lcom/journeyapps/barcodescanner/ScanOptions;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onSaveInstanceState", "outState", "onSelectedTunnelChanged", "oldTunnel", "newTunnel", "onTunnelDeletionFinished", "count", "", "throwable", "", "onViewStateRestored", "showSnackbar", "message", "", "viewForTunnel", "Lpw/idrug/connections/widget/MultiselectableRelativeLayout;", "tunnel", "tunnels", "", "showAppSelectionDialog", "setTunnelsSource", "applySelectionVisualState", "card", "Lcom/google/android/material/card/MaterialCardView;", "selected", "", "ActionModeListener", "Companion", "ui_release"})
public final class TunnelListFragment extends pw.idrug.connections.fragment.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    private final pw.idrug.connections.fragment.TunnelListFragment.ActionModeListener actionModeListener = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.appcompat.view.ActionMode actionMode;
    @org.jetbrains.annotations.Nullable()
    private androidx.activity.OnBackPressedCallback backPressedCallback;
    @org.jetbrains.annotations.Nullable()
    private pw.idrug.connections.databinding.TunnelListFragmentBinding binding;
    @org.jetbrains.annotations.Nullable()
    private pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String, pw.idrug.connections.model.ObservableTunnel> observedTunnels;
    @org.jetbrains.annotations.NotNull()
    private final androidx.databinding.ObservableList.OnListChangedCallback<androidx.databinding.ObservableList<pw.idrug.connections.model.ObservableTunnel>> tunnelsCallback = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> tunnelFileImportResultLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<com.journeyapps.barcodescanner.ScanOptions> qrImportResultLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHECKED_ITEMS = "CHECKED_ITEMS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_OPEN_TUNNEL_FOR_APPS = "open_tunnel_for_apps";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "iDrugConnections/TunnelListFragment";
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.fragment.TunnelListFragment.Companion Companion = null;
    
    public TunnelListFragment() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable()
    pw.idrug.connections.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable()
    pw.idrug.connections.model.ObservableTunnel newTunnel) {
    }
    
    private final void onTunnelDeletionFinished(int count, java.lang.Throwable throwable) {
    }
    
    @java.lang.Override()
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showSnackbar(java.lang.CharSequence message) {
    }
    
    private final pw.idrug.connections.widget.MultiselectableRelativeLayout viewForTunnel(pw.idrug.connections.model.ObservableTunnel tunnel, java.util.List<?> tunnels) {
        return null;
    }
    
    private final void showAppSelectionDialog(pw.idrug.connections.model.ObservableTunnel tunnel) {
    }
    
    private final void setTunnelsSource(pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String, pw.idrug.connections.model.ObservableTunnel> tunnels) {
    }
    
    private final void applySelectionVisualState(com.google.android.material.card.MaterialCardView card, boolean selected) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u000bj\b\u0012\u0004\u0012\u00020\u0006`\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u000eJ\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0006J\u0012\u0010\u001d\u001a\u00020\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u001a\u0010\u001e\u001a\u00020\u00172\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u000eH\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment$ActionModeListener;", "Landroidx/appcompat/view/ActionMode$Callback;", "<init>", "(Lpw/idrug/connections/fragment/TunnelListFragment;)V", "checkedItems", "", "", "getCheckedItems", "()Ljava/util/Collection;", "resources", "Landroid/content/res/Resources;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onActionItemClicked", "", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onDestroyActionMode", "", "onPrepareActionMode", "setItemChecked", "position", "checked", "toggleItemChecked", "updateTitle", "animateFab", "view", "Landroid/view/View;", "show", "ui_release"})
    final class ActionModeListener implements androidx.appcompat.view.ActionMode.Callback {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Collection<java.lang.Integer> checkedItems = null;
        @org.jetbrains.annotations.Nullable()
        private android.content.res.Resources resources;
        
        public ActionModeListener() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Collection<java.lang.Integer> getCheckedItems() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<java.lang.Integer> getCheckedItems() {
            return null;
        }
        
        @java.lang.Override()
        public boolean onActionItemClicked(@org.jetbrains.annotations.NotNull()
        androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull()
        android.view.MenuItem item) {
            return false;
        }
        
        @java.lang.Override()
        public boolean onCreateActionMode(@org.jetbrains.annotations.NotNull()
        androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull()
        android.view.Menu menu) {
            return false;
        }
        
        @java.lang.Override()
        public void onDestroyActionMode(@org.jetbrains.annotations.NotNull()
        androidx.appcompat.view.ActionMode mode) {
        }
        
        @java.lang.Override()
        public boolean onPrepareActionMode(@org.jetbrains.annotations.NotNull()
        androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull()
        android.view.Menu menu) {
            return false;
        }
        
        public final void setItemChecked(int position, boolean checked) {
        }
        
        public final void toggleItemChecked(int position) {
        }
        
        private final void updateTitle(androidx.appcompat.view.ActionMode mode) {
        }
        
        private final void animateFab(android.view.View view, boolean show) {
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment$Companion;", "", "<init>", "()V", "CHECKED_ITEMS", "", "ARG_OPEN_TUNNEL_FOR_APPS", "TAG", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}