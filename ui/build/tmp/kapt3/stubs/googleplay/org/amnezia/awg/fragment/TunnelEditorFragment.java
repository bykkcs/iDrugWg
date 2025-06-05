package org.amnezia.awg.fragment;

/**
 * Fragment for editing an AmneziaWG configuration.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001a\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\f2\b\u0010\'\u001a\u0004\u0018\u00010\u001eJ\u001a\u0010(\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010)\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelEditorFragment;", "Lorg/amnezia/awg/fragment/BaseFragment;", "Landroidx/core/view/MenuProvider;", "()V", "binding", "Lorg/amnezia/awg/databinding/TunnelEditorFragmentBinding;", "haveShownKeys", "", "openAppSelectionOnly", "tunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "loadConfigAndOpenAppSelection", "", "onConfigLoaded", "config", "Lorg/amnezia/awg/config/Config;", "onConfigSaved", "savedTunnel", "Lorg/amnezia/awg/backend/Tunnel;", "throwable", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onRequestSetExcludedIncludedApplications", "view", "onViewCreated", "showAppSelectionDialog", "ui_googleplay"})
public final class TunnelEditorFragment extends org.amnezia.awg.fragment.BaseFragment implements androidx.core.view.MenuProvider {
    private boolean haveShownKeys = false;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.databinding.TunnelEditorFragmentBinding binding;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.model.ObservableTunnel tunnel;
    private boolean openAppSelectionOnly = false;
    
    public TunnelEditorFragment() {
        super();
    }
    
    private final void onConfigLoaded(org.amnezia.awg.config.Config config) {
    }
    
    private final void onConfigSaved(org.amnezia.awg.backend.Tunnel savedTunnel, java.lang.Throwable throwable) {
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
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
    
    private final void loadConfigAndOpenAppSelection(org.amnezia.awg.model.ObservableTunnel tunnel) {
    }
    
    private final void showAppSelectionDialog(org.amnezia.awg.config.Config config, org.amnezia.awg.model.ObservableTunnel tunnel) {
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
}