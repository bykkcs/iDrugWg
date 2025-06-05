package org.amnezia.awg.fragment;

/**
 * Fragment containing a list of known AmneziaWG tunnels. It allows creating and deleting tunnels.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 22\u00020\u0001:\u000212B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0018H\u0016J\u001c\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u001a\u0010&\u001a\u00020\u001a2\u0006\u0010\'\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010(\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+H\u0002J\u001e\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\u001f2\n\u0010/\u001a\u0006\u0012\u0002\b\u000300H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00100\u00100\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelListFragment;", "Lorg/amnezia/awg/fragment/BaseFragment;", "()V", "actionMode", "Landroidx/appcompat/view/ActionMode;", "actionModeListener", "Lorg/amnezia/awg/fragment/TunnelListFragment$ActionModeListener;", "backPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "binding", "Lorg/amnezia/awg/databinding/TunnelListFragmentBinding;", "qrImportResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Lcom/journeyapps/barcodescanner/ScanOptions;", "kotlin.jvm.PlatformType", "tunnelFileImportResultLauncher", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onSaveInstanceState", "outState", "onSelectedTunnelChanged", "oldTunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "newTunnel", "onTunnelDeletionFinished", "count", "", "throwable", "", "onViewCreated", "view", "onViewStateRestored", "showSnackbar", "message", "", "viewForTunnel", "Lorg/amnezia/awg/widget/MultiselectableRelativeLayout;", "tunnel", "tunnels", "", "ActionModeListener", "Companion", "ui_debug"})
public final class TunnelListFragment extends org.amnezia.awg.fragment.BaseFragment {
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.fragment.TunnelListFragment.ActionModeListener actionModeListener = null;
    @org.jetbrains.annotations.Nullable
    private androidx.appcompat.view.ActionMode actionMode;
    @org.jetbrains.annotations.Nullable
    private androidx.activity.OnBackPressedCallback backPressedCallback;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.databinding.TunnelListFragmentBinding binding;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> tunnelFileImportResultLauncher = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<com.journeyapps.barcodescanner.ScanOptions> qrImportResultLauncher = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String CHECKED_ITEMS = "CHECKED_ITEMS";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/TunnelListFragment";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.fragment.TunnelListFragment.Companion Companion = null;
    
    public TunnelListFragment() {
        super();
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
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
    public void onDestroyView() {
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
    
    private final void onTunnelDeletionFinished(int count, java.lang.Throwable throwable) {
    }
    
    @java.lang.Override
    public void onViewStateRestored(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showSnackbar(java.lang.CharSequence message) {
    }
    
    private final org.amnezia.awg.widget.MultiselectableRelativeLayout viewForTunnel(org.amnezia.awg.model.ObservableTunnel tunnel, java.util.List<?> tunnels) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0010j\b\u0012\u0004\u0012\u00020\u0005`\u0011J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0016\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u000fJ\u000e\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0005J\u0012\u0010 \u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelListFragment$ActionModeListener;", "Landroidx/appcompat/view/ActionMode$Callback;", "(Lorg/amnezia/awg/fragment/TunnelListFragment;)V", "checkedItems", "", "", "getCheckedItems", "()Ljava/util/Collection;", "resources", "Landroid/content/res/Resources;", "animateFab", "", "view", "Landroid/view/View;", "show", "", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onDestroyActionMode", "onPrepareActionMode", "setItemChecked", "position", "checked", "toggleItemChecked", "updateTitle", "ui_debug"})
    final class ActionModeListener implements androidx.appcompat.view.ActionMode.Callback {
        @org.jetbrains.annotations.NotNull
        private final java.util.Collection<java.lang.Integer> checkedItems = null;
        @org.jetbrains.annotations.Nullable
        private android.content.res.Resources resources;
        
        public ActionModeListener() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.Collection<java.lang.Integer> getCheckedItems() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.ArrayList<java.lang.Integer> getCheckedItems() {
            return null;
        }
        
        @java.lang.Override
        public boolean onActionItemClicked(@org.jetbrains.annotations.NotNull
        androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull
        android.view.MenuItem item) {
            return false;
        }
        
        @java.lang.Override
        public boolean onCreateActionMode(@org.jetbrains.annotations.NotNull
        androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull
        android.view.Menu menu) {
            return false;
        }
        
        @java.lang.Override
        public void onDestroyActionMode(@org.jetbrains.annotations.NotNull
        androidx.appcompat.view.ActionMode mode) {
        }
        
        @java.lang.Override
        public boolean onPrepareActionMode(@org.jetbrains.annotations.NotNull
        androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/amnezia/awg/fragment/TunnelListFragment$Companion;", "", "()V", "CHECKED_ITEMS", "", "TAG", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}