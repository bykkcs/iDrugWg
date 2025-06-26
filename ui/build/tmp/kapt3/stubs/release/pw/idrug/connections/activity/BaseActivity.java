package pw.idrug.connections.activity;

/**
 * Base class for activities that need to remember the currently-selected tunnel.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 \u001b2\u00020\u0001:\u0004\u001b\u001c\u001d\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0014H\u0014J\u001c\u0010\u0017\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H$J\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lpw/idrug/connections/activity/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "created", "", "value", "Lpw/idrug/connections/model/ObservableTunnel;", "selectedTunnel", "getSelectedTunnel", "()Lpw/idrug/connections/model/ObservableTunnel;", "setSelectedTunnel", "(Lpw/idrug/connections/model/ObservableTunnel;)V", "selectionChangeRegistry", "Lpw/idrug/connections/activity/BaseActivity$SelectionChangeRegistry;", "addOnSelectedTunnelChangedListener", "", "listener", "Lpw/idrug/connections/activity/BaseActivity$OnSelectedTunnelChangedListener;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onSelectedTunnelChanged", "oldTunnel", "newTunnel", "removeOnSelectedTunnelChangedListener", "Companion", "OnSelectedTunnelChangedListener", "SelectionChangeNotifier", "SelectionChangeRegistry", "ui_release"})
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull
    private final pw.idrug.connections.activity.BaseActivity.SelectionChangeRegistry selectionChangeRegistry = null;
    private boolean created = false;
    @org.jetbrains.annotations.Nullable
    private pw.idrug.connections.model.ObservableTunnel selectedTunnel;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_SELECTED_TUNNEL = "selected_tunnel";
    @org.jetbrains.annotations.NotNull
    public static final pw.idrug.connections.activity.BaseActivity.Companion Companion = null;
    
    public BaseActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final pw.idrug.connections.model.ObservableTunnel getSelectedTunnel() {
        return null;
    }
    
    public final void setSelectedTunnel(@org.jetbrains.annotations.Nullable
    pw.idrug.connections.model.ObservableTunnel value) {
    }
    
    public final void addOnSelectedTunnelChangedListener(@org.jetbrains.annotations.NotNull
    pw.idrug.connections.activity.BaseActivity.OnSelectedTunnelChangedListener listener) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull
    android.os.Bundle outState) {
    }
    
    protected abstract boolean onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable
    pw.idrug.connections.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable
    pw.idrug.connections.model.ObservableTunnel newTunnel);
    
    public final void removeOnSelectedTunnelChangedListener(@org.jetbrains.annotations.NotNull
    pw.idrug.connections.activity.BaseActivity.OnSelectedTunnelChangedListener listener) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lpw/idrug/connections/activity/BaseActivity$Companion;", "", "()V", "KEY_SELECTED_TUNNEL", "", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lpw/idrug/connections/activity/BaseActivity$OnSelectedTunnelChangedListener;", "", "onSelectedTunnelChanged", "", "oldTunnel", "Lpw/idrug/connections/model/ObservableTunnel;", "newTunnel", "ui_release"})
    public static abstract interface OnSelectedTunnelChangedListener {
        
        public abstract void onSelectedTunnelChanged(@org.jetbrains.annotations.Nullable
        pw.idrug.connections.model.ObservableTunnel oldTunnel, @org.jetbrains.annotations.Nullable
        pw.idrug.connections.model.ObservableTunnel newTunnel);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016\u00a8\u0006\f"}, d2 = {"Lpw/idrug/connections/activity/BaseActivity$SelectionChangeNotifier;", "Landroidx/databinding/CallbackRegistry$NotifierCallback;", "Lpw/idrug/connections/activity/BaseActivity$OnSelectedTunnelChangedListener;", "Lpw/idrug/connections/model/ObservableTunnel;", "()V", "onNotifyCallback", "", "listener", "oldTunnel", "ignored", "", "newTunnel", "ui_release"})
    static final class SelectionChangeNotifier extends androidx.databinding.CallbackRegistry.NotifierCallback<pw.idrug.connections.activity.BaseActivity.OnSelectedTunnelChangedListener, pw.idrug.connections.model.ObservableTunnel, pw.idrug.connections.model.ObservableTunnel> {
        
        public SelectionChangeNotifier() {
            super();
        }
        
        @java.lang.Override
        public void onNotifyCallback(@org.jetbrains.annotations.NotNull
        pw.idrug.connections.activity.BaseActivity.OnSelectedTunnelChangedListener listener, @org.jetbrains.annotations.Nullable
        pw.idrug.connections.model.ObservableTunnel oldTunnel, int ignored, @org.jetbrains.annotations.Nullable
        pw.idrug.connections.model.ObservableTunnel newTunnel) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lpw/idrug/connections/activity/BaseActivity$SelectionChangeRegistry;", "Landroidx/databinding/CallbackRegistry;", "Lpw/idrug/connections/activity/BaseActivity$OnSelectedTunnelChangedListener;", "Lpw/idrug/connections/model/ObservableTunnel;", "()V", "ui_release"})
    static final class SelectionChangeRegistry extends androidx.databinding.CallbackRegistry<pw.idrug.connections.activity.BaseActivity.OnSelectedTunnelChangedListener, pw.idrug.connections.model.ObservableTunnel, pw.idrug.connections.model.ObservableTunnel> {
        
        public SelectionChangeRegistry() {
            super(null);
        }
    }
}