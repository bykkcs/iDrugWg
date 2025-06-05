package org.amnezia.awg.fragment;

/**
 * Fragment that shows details about a specific tunnel.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000bH\u0016J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000bH\u0016J\u001c\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010!\u001a\u00020\u000bH\u0016J\u001a\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010$\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u000e\u0010%\u001a\u00020\u000bH\u0082@\u00a2\u0006\u0002\u0010&R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelDetailFragment;", "Lorg/amnezia/awg/fragment/BaseFragment;", "Landroidx/core/view/MenuProvider;", "()V", "binding", "Lorg/amnezia/awg/databinding/TunnelDetailFragmentBinding;", "lastState", "Lorg/amnezia/awg/backend/Tunnel$State;", "timerActive", "", "onCreateMenu", "", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onResume", "onSelectedTunnelChanged", "oldTunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "newTunnel", "onStop", "onViewCreated", "view", "onViewStateRestored", "updateStats", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui_debug"})
public final class TunnelDetailFragment extends org.amnezia.awg.fragment.BaseFragment implements androidx.core.view.MenuProvider {
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.databinding.TunnelDetailFragmentBinding binding;
    @org.jetbrains.annotations.NotNull
    private org.amnezia.awg.backend.Tunnel.State lastState = org.amnezia.awg.backend.Tunnel.State.TOGGLE;
    private boolean timerActive = true;
    
    public TunnelDetailFragment() {
        super();
    }
    
    @java.lang.Override
    public boolean onMenuItemSelected(@org.jetbrains.annotations.NotNull
    android.view.MenuItem menuItem) {
        return false;
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
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable
    org.amnezia.awg.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable
    org.amnezia.awg.model.ObservableTunnel newTunnel) {
    }
    
    @java.lang.Override
    public void onStop() {
    }
    
    @java.lang.Override
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.lang.Object updateStats(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}