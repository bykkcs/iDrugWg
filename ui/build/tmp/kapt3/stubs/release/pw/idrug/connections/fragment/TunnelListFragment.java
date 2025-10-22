package pw.idrug.connections.fragment;

/**
 * Fragment containing a list of known iDrugConnections tunnels. It allows creating and deleting tunnels.
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u00c6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 [2\u00020\u0001:\u0005WXYZ[B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020!H\u0016J&\u0010\'\u001a\u0004\u0018\u00010#2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010,\u001a\u00020!H\u0016J\u0010\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020%H\u0016J\u001c\u0010/\u001a\u00020!2\b\u00100\u001a\u0004\u0018\u00010\u000f2\b\u00101\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u00102\u001a\u00020!2\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106H\u0002J\u0012\u00107\u001a\u00020!2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020:H\u0002J\u001e\u0010;\u001a\u0004\u0018\u00010<2\u0006\u0010=\u001a\u00020\u000f2\n\u0010>\u001a\u0006\u0012\u0002\b\u00030?H\u0002J\u0010\u0010@\u001a\u00020!2\u0006\u0010=\u001a\u00020\u000fH\u0002J\u001c\u0010A\u001a\u00020!2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u0002J\u0016\u0010B\u001a\u00020!2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0?H\u0002J\u0012\u0010C\u001a\u0004\u0018\u00010\u000e2\u0006\u0010=\u001a\u00020\u000fH\u0002J\u0018\u0010D\u001a\u00020!2\u0006\u0010\n\u001a\u00020E2\u0006\u0010=\u001a\u00020\u000fH\u0002J\u0018\u0010F\u001a\u00020!2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JH\u0002J(\u0010K\u001a\u00020\u00152\u0006\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u000e2\u0006\u0010O\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010PJ\u0010\u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00020\u001aH\u0002J\"\u0010S\u001a\u00020\u00152\u0006\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u000e2\u0006\u0010O\u001a\u00020\u000eH\u0002J\u0019\u0010T\u001a\u0004\u0018\u0001042\b\u0010U\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0002\u0010VR\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u000e0\u000e0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u001f0\u001f0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\\"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment;", "Lpw/idrug/connections/fragment/BaseFragment;", "<init>", "()V", "actionModeListener", "Lpw/idrug/connections/fragment/TunnelListFragment$ActionModeListener;", "actionMode", "Landroidx/appcompat/view/ActionMode;", "backPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "binding", "Lpw/idrug/connections/databinding/TunnelListFragmentBinding;", "observedTunnels", "Lpw/idrug/connections/databinding/ObservableKeyedArrayList;", "", "Lpw/idrug/connections/model/ObservableTunnel;", "tunnelsCallback", "Landroidx/databinding/ObservableList$OnListChangedCallback;", "Landroidx/databinding/ObservableList$OnListChangedCallback;", "serverPings", "", "Lpw/idrug/connections/fragment/TunnelListFragment$PingResult;", "pingJob", "Lkotlinx/coroutines/Job;", "pingEndpoints", "", "Lpw/idrug/connections/fragment/TunnelListFragment$TcpEndpoint;", "tunnelFileImportResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "qrImportResultLauncher", "Lcom/journeyapps/barcodescanner/ScanOptions;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onSaveInstanceState", "outState", "onSelectedTunnelChanged", "oldTunnel", "newTunnel", "onTunnelDeletionFinished", "count", "", "throwable", "", "onViewStateRestored", "showSnackbar", "message", "", "viewForTunnel", "Lpw/idrug/connections/widget/MultiselectableRelativeLayout;", "tunnel", "tunnels", "", "showAppSelectionDialog", "setTunnelsSource", "refreshTunnelPings", "serverIdFromTunnel", "bindPing", "Lpw/idrug/connections/databinding/TunnelListItemBinding;", "applySelectionVisualState", "card", "Lcom/google/android/material/card/MaterialCardView;", "selected", "", "requestPing", "client", "Lokhttp3/OkHttpClient;", "token", "serverId", "(Lokhttp3/OkHttpClient;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "measureTcpPing", "endpoint", "fetchPingViaApi", "parsePingMs", "body", "(Ljava/lang/String;)Ljava/lang/Integer;", "PingState", "PingResult", "TcpEndpoint", "ActionModeListener", "Companion", "ui_release"})
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
    private final java.util.Map<java.lang.String, pw.idrug.connections.fragment.TunnelListFragment.PingResult> serverPings = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job pingJob;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, pw.idrug.connections.fragment.TunnelListFragment.TcpEndpoint> pingEndpoints = null;
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
    private static final int PING_CONNECT_TIMEOUT_MS = 2000;
    private static final long PING_REFRESH_INTERVAL_MS = 5000L;
    private static final int PING_SOCKET_TIMEOUT_MS = 2000;
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
    
    private final void refreshTunnelPings(java.util.List<pw.idrug.connections.model.ObservableTunnel> tunnels) {
    }
    
    private final java.lang.String serverIdFromTunnel(pw.idrug.connections.model.ObservableTunnel tunnel) {
        return null;
    }
    
    private final void bindPing(pw.idrug.connections.databinding.TunnelListItemBinding binding, pw.idrug.connections.model.ObservableTunnel tunnel) {
    }
    
    private final void applySelectionVisualState(com.google.android.material.card.MaterialCardView card, boolean selected) {
    }
    
    private final java.lang.Object requestPing(okhttp3.OkHttpClient client, java.lang.String token, java.lang.String serverId, kotlin.coroutines.Continuation<? super pw.idrug.connections.fragment.TunnelListFragment.PingResult> $completion) {
        return null;
    }
    
    private final pw.idrug.connections.fragment.TunnelListFragment.PingResult measureTcpPing(pw.idrug.connections.fragment.TunnelListFragment.TcpEndpoint endpoint) {
        return null;
    }
    
    private final pw.idrug.connections.fragment.TunnelListFragment.PingResult fetchPingViaApi(okhttp3.OkHttpClient client, java.lang.String token, java.lang.String serverId) {
        return null;
    }
    
    private final java.lang.Integer parsePingMs(java.lang.String body) {
        return null;
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
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment$Companion;", "", "<init>", "()V", "CHECKED_ITEMS", "", "ARG_OPEN_TUNNEL_FOR_APPS", "TAG", "PING_CONNECT_TIMEOUT_MS", "", "PING_REFRESH_INTERVAL_MS", "", "PING_SOCKET_TIMEOUT_MS", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment$PingResult;", "", "state", "Lpw/idrug/connections/fragment/TunnelListFragment$PingState;", "latencyMs", "", "<init>", "(Lpw/idrug/connections/fragment/TunnelListFragment$PingState;Ljava/lang/Integer;)V", "getState", "()Lpw/idrug/connections/fragment/TunnelListFragment$PingState;", "getLatencyMs", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Lpw/idrug/connections/fragment/TunnelListFragment$PingState;Ljava/lang/Integer;)Lpw/idrug/connections/fragment/TunnelListFragment$PingResult;", "equals", "", "other", "hashCode", "toString", "", "ui_release"})
    static final class PingResult {
        @org.jetbrains.annotations.NotNull()
        private final pw.idrug.connections.fragment.TunnelListFragment.PingState state = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer latencyMs = null;
        
        public PingResult(@org.jetbrains.annotations.NotNull()
        pw.idrug.connections.fragment.TunnelListFragment.PingState state, @org.jetbrains.annotations.Nullable()
        java.lang.Integer latencyMs) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.fragment.TunnelListFragment.PingState getState() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getLatencyMs() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.fragment.TunnelListFragment.PingState component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.fragment.TunnelListFragment.PingResult copy(@org.jetbrains.annotations.NotNull()
        pw.idrug.connections.fragment.TunnelListFragment.PingState state, @org.jetbrains.annotations.Nullable()
        java.lang.Integer latencyMs) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment$PingState;", "", "<init>", "(Ljava/lang/String;I)V", "LOADING", "SUCCESS", "ERROR", "ui_release"})
    static enum PingState {
        /*public static final*/ LOADING /* = new LOADING() */,
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ ERROR /* = new ERROR() */;
        
        PingState() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<pw.idrug.connections.fragment.TunnelListFragment.PingState> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lpw/idrug/connections/fragment/TunnelListFragment$TcpEndpoint;", "", "host", "", "port", "", "<init>", "(Ljava/lang/String;I)V", "getHost", "()Ljava/lang/String;", "getPort", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "ui_release"})
    static final class TcpEndpoint {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String host = null;
        private final int port = 0;
        
        public TcpEndpoint(@org.jetbrains.annotations.NotNull()
        java.lang.String host, int port) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHost() {
            return null;
        }
        
        public final int getPort() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.fragment.TunnelListFragment.TcpEndpoint copy(@org.jetbrains.annotations.NotNull()
        java.lang.String host, int port) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}