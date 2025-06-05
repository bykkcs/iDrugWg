package org.amnezia.awg.fragment;

/**
 * Base class for fragments that need to know the currently-selected tunnel. Only does anything when
 * attached to a `BaseActivity`.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\u0016\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0007H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\bR\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00058D@DX\u0084\u000e\u00a2\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lorg/amnezia/awg/fragment/BaseFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/amnezia/awg/activity/BaseActivity$OnSelectedTunnelChangedListener;", "()V", "pendingTunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "pendingTunnelUp", "", "Ljava/lang/Boolean;", "permissionActivityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "tunnel", "selectedTunnel", "getSelectedTunnel", "()Lorg/amnezia/awg/model/ObservableTunnel;", "setSelectedTunnel", "(Lorg/amnezia/awg/model/ObservableTunnel;)V", "onAttach", "", "context", "Landroid/content/Context;", "onDetach", "setTunnelState", "view", "Landroid/view/View;", "checked", "setTunnelStateWithPermissionsResult", "Companion", "ui_debug"})
public abstract class BaseFragment extends androidx.fragment.app.Fragment implements org.amnezia.awg.activity.BaseActivity.OnSelectedTunnelChangedListener {
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.model.ObservableTunnel pendingTunnel;
    @org.jetbrains.annotations.Nullable
    private java.lang.Boolean pendingTunnelUp;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> permissionActivityResultLauncher = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/BaseFragment";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.fragment.BaseFragment.Companion Companion = null;
    
    public BaseFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    protected final org.amnezia.awg.model.ObservableTunnel getSelectedTunnel() {
        return null;
    }
    
    protected final void setSelectedTunnel(@org.jetbrains.annotations.Nullable
    org.amnezia.awg.model.ObservableTunnel tunnel) {
    }
    
    @java.lang.Override
    public void onAttach(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @java.lang.Override
    public void onDetach() {
    }
    
    public final void setTunnelState(@org.jetbrains.annotations.NotNull
    android.view.View view, boolean checked) {
    }
    
    private final void setTunnelStateWithPermissionsResult(org.amnezia.awg.model.ObservableTunnel tunnel, boolean checked) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/amnezia/awg/fragment/BaseFragment$Companion;", "", "()V", "TAG", "", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}