package pw.idrug.connections.fragment;

/**
 * Fragment for editing an iDrugConnections configuration.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 <2\u00020\u00012\u00020\u0002:\u0001<B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001a\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010#\u001a\u00020\rH\u0016J\b\u0010$\u001a\u00020\rH\u0002J\u000e\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u001eJ\u0016\u0010\'\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u0005J\u0010\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\r2\b\u0010&\u001a\u0004\u0018\u00010\u001eJ\u0010\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\u0017H\u0016J\u001c\u0010/\u001a\u00020\r2\b\u00100\u001a\u0004\u0018\u00010\u000b2\b\u00101\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u00102\u001a\u00020\r2\b\u00101\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J(\u00103\u001a\u00020\r2\u0006\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0082@\u00a2\u0006\u0002\u00106J\u001a\u00107\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u00108\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020;H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lpw/idrug/connections/fragment/TunnelEditorFragment;", "Lpw/idrug/connections/fragment/BaseFragment;", "Landroidx/core/view/MenuProvider;", "()V", "autoShowAppSelector", "", "binding", "Lpw/idrug/connections/databinding/TunnelEditorFragmentBinding;", "haveShownKeys", "showingAuthenticator", "tunnel", "Lpw/idrug/connections/model/ObservableTunnel;", "onConfigLoaded", "", "config", "Lpw/idrug/connections/config/Config;", "onConfigSaved", "savedTunnel", "Lpw/idrug/connections/backend/Tunnel;", "throwable", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onFinished", "onKeyClick", "view", "onKeyFocusChange", "isFocused", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onRequestSetExcludedIncludedApplications", "onSaveInstanceState", "outState", "onSelectedTunnelChanged", "oldTunnel", "newTunnel", "onTunnelCreated", "onTunnelRenamed", "renamedTunnel", "newConfig", "(Lpw/idrug/connections/model/ObservableTunnel;Lpw/idrug/connections/config/Config;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onViewCreated", "onViewStateRestored", "showPrivateKey", "edit", "Landroid/widget/EditText;", "Companion", "ui_release"})
public final class TunnelEditorFragment extends pw.idrug.connections.fragment.BaseFragment implements androidx.core.view.MenuProvider {
    private boolean haveShownKeys = false;
    @org.jetbrains.annotations.Nullable
    private pw.idrug.connections.databinding.TunnelEditorFragmentBinding binding;
    @org.jetbrains.annotations.Nullable
    private pw.idrug.connections.model.ObservableTunnel tunnel;
    private boolean autoShowAppSelector = false;
    private boolean showingAuthenticator = false;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LOCAL_CONFIG = "local_config";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ORIGINAL_NAME = "original_name";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ARG_AUTO_SHOW_APP_SELECTOR = "auto_show_app_selector";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "iDrugConnections/TunnelEditorFragment";
    @org.jetbrains.annotations.NotNull
    public static final pw.idrug.connections.fragment.TunnelEditorFragment.Companion Companion = null;
    
    public TunnelEditorFragment() {
        super();
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onConfigLoaded(pw.idrug.connections.config.Config config) {
    }
    
    private final void onConfigSaved(pw.idrug.connections.backend.Tunnel savedTunnel, java.lang.Throwable throwable) {
    }
    
    @java.lang.Override
    public void onCreateMenu(@org.jetbrains.annotations.NotNull
    android.view.Menu menu, @org.jetbrains.annotations.NotNull
    android.view.MenuInflater menuInflater) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    private final void onFinished() {
    }
    
    @java.lang.Override
    public boolean onMenuItemSelected(@org.jetbrains.annotations.NotNull
    android.view.MenuItem menuItem) {
        return false;
    }
    
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    public final void onRequestSetExcludedIncludedApplications(@org.jetbrains.annotations.Nullable
    android.view.View view) {
    }
    
    @java.lang.Override
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull
    android.os.Bundle outState) {
    }
    
    @java.lang.Override
    public void onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable
    pw.idrug.connections.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable
    pw.idrug.connections.model.ObservableTunnel newTunnel) {
    }
    
    private final void onTunnelCreated(pw.idrug.connections.model.ObservableTunnel newTunnel, java.lang.Throwable throwable) {
    }
    
    private final java.lang.Object onTunnelRenamed(pw.idrug.connections.model.ObservableTunnel renamedTunnel, pw.idrug.connections.config.Config newConfig, java.lang.Throwable throwable, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void onKeyClick(@org.jetbrains.annotations.NotNull
    android.view.View view) {
    }
    
    public final void onKeyFocusChange(@org.jetbrains.annotations.NotNull
    android.view.View view, boolean isFocused) {
    }
    
    private final void showPrivateKey(android.widget.EditText edit) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lpw/idrug/connections/fragment/TunnelEditorFragment$Companion;", "", "()V", "ARG_AUTO_SHOW_APP_SELECTOR", "", "KEY_LOCAL_CONFIG", "KEY_ORIGINAL_NAME", "TAG", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}