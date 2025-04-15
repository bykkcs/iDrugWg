package org.amnezia.awg.fragment;

/**
 * Fragment for editing an AmneziaWG configuration.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u00020\u00012\u00020\u0002:\u0001:B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\fH\u0016J\b\u0010\"\u001a\u00020\fH\u0002J\u000e\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u001aJ\u0016\u0010%\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0007J\u0010\u0010\'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010\u001aJ\u0010\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020 H\u0016J\u001c\u0010-\u001a\u00020\f2\b\u0010.\u001a\u0004\u0018\u00010\n2\b\u0010/\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u00100\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u00010\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J(\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0082@\u00a2\u0006\u0002\u00104J\u001a\u00105\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u00106\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u00107\u001a\u00020\f2\u0006\u00108\u001a\u000209H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelEditorFragment;", "Lorg/amnezia/awg/fragment/BaseFragment;", "Landroidx/core/view/MenuProvider;", "()V", "binding", "Lorg/amnezia/awg/databinding/TunnelEditorFragmentBinding;", "haveShownKeys", "", "showingAuthenticator", "tunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "onConfigLoaded", "", "config", "Lorg/amnezia/awg/config/Config;", "onConfigSaved", "savedTunnel", "Lorg/amnezia/awg/backend/Tunnel;", "throwable", "", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFinished", "onKeyClick", "view", "onKeyFocusChange", "isFocused", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onRequestSetExcludedIncludedApplications", "onSaveInstanceState", "outState", "onSelectedTunnelChanged", "oldTunnel", "newTunnel", "onTunnelCreated", "onTunnelRenamed", "renamedTunnel", "newConfig", "(Lorg/amnezia/awg/model/ObservableTunnel;Lorg/amnezia/awg/config/Config;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onViewCreated", "onViewStateRestored", "showPrivateKey", "edit", "Landroid/widget/EditText;", "Companion", "ui_debug"})
public final class TunnelEditorFragment extends org.amnezia.awg.fragment.BaseFragment implements androidx.core.view.MenuProvider {
    private boolean haveShownKeys = false;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.databinding.TunnelEditorFragmentBinding binding;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.model.ObservableTunnel tunnel;
    private boolean showingAuthenticator = false;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_LOCAL_CONFIG = "local_config";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ORIGINAL_NAME = "original_name";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/TunnelEditorFragment";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.fragment.TunnelEditorFragment.Companion Companion = null;
    
    public TunnelEditorFragment() {
        super();
    }
    
    private final void onConfigLoaded(org.amnezia.awg.config.Config config) {
    }
    
    private final void onConfigSaved(org.amnezia.awg.backend.Tunnel savedTunnel, java.lang.Throwable throwable) {
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
    org.amnezia.awg.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable
    org.amnezia.awg.model.ObservableTunnel newTunnel) {
    }
    
    private final void onTunnelCreated(org.amnezia.awg.model.ObservableTunnel newTunnel, java.lang.Throwable throwable) {
    }
    
    private final java.lang.Object onTunnelRenamed(org.amnezia.awg.model.ObservableTunnel renamedTunnel, org.amnezia.awg.config.Config newConfig, java.lang.Throwable throwable, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelEditorFragment$Companion;", "", "()V", "KEY_LOCAL_CONFIG", "", "KEY_ORIGINAL_NAME", "TAG", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}