package org.amnezia.awg.activity;

/**
 * Standalone activity for creating tunnels.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0014\u00a8\u0006\f"}, d2 = {"Lorg/amnezia/awg/activity/TunnelCreatorActivity;", "Lorg/amnezia/awg/activity/BaseActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSelectedTunnelChanged", "", "oldTunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "newTunnel", "ui_debug"})
public final class TunnelCreatorActivity extends org.amnezia.awg.activity.BaseActivity {
    
    public TunnelCreatorActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected boolean onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable
    org.amnezia.awg.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable
    org.amnezia.awg.model.ObservableTunnel newTunnel) {
        return false;
    }
}