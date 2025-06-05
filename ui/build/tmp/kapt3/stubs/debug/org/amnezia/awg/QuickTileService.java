package org.amnezia.awg;

/**
 * Service that maintains the application's custom Quick Settings tile. This service is bound by the
 * system framework as necessary to update the appearance of the tile in the system UI, and to
 * forward click events to the application.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0003\u0019\u001a\u001bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/amnezia/awg/QuickTileService;", "Landroid/service/quicksettings/TileService;", "()V", "iconOff", "Landroid/graphics/drawable/Icon;", "iconOn", "onStateChangedCallback", "Lorg/amnezia/awg/QuickTileService$OnStateChangedCallback;", "onTunnelChangedCallback", "Lorg/amnezia/awg/QuickTileService$OnTunnelChangedCallback;", "tunnel", "Lorg/amnezia/awg/model/ObservableTunnel;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onClick", "", "onCreate", "onDestroy", "onStartListening", "onStopListening", "onTileAdded", "onTileRemoved", "updateTile", "Companion", "OnStateChangedCallback", "OnTunnelChangedCallback", "ui_debug"})
@androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.N)
public final class QuickTileService extends android.service.quicksettings.TileService {
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.QuickTileService.OnStateChangedCallback onStateChangedCallback = null;
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.QuickTileService.OnTunnelChangedCallback onTunnelChangedCallback = null;
    @org.jetbrains.annotations.Nullable
    private android.graphics.drawable.Icon iconOff;
    @org.jetbrains.annotations.Nullable
    private android.graphics.drawable.Icon iconOn;
    @org.jetbrains.annotations.Nullable
    private org.amnezia.awg.model.ObservableTunnel tunnel;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/QuickTileService";
    private static boolean isAdded = false;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.QuickTileService.Companion Companion = null;
    
    public QuickTileService() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public void onClick() {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @java.lang.Override
    public void onStartListening() {
    }
    
    @java.lang.Override
    public void onStopListening() {
    }
    
    @java.lang.Override
    public void onTileAdded() {
    }
    
    @java.lang.Override
    public void onTileRemoved() {
    }
    
    private final void updateTile() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lorg/amnezia/awg/QuickTileService$Companion;", "", "()V", "TAG", "", "<set-?>", "", "isAdded", "()Z", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isAdded() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\t"}, d2 = {"Lorg/amnezia/awg/QuickTileService$OnStateChangedCallback;", "Landroidx/databinding/Observable$OnPropertyChangedCallback;", "(Lorg/amnezia/awg/QuickTileService;)V", "onPropertyChanged", "", "sender", "Landroidx/databinding/Observable;", "propertyId", "", "ui_debug"})
    final class OnStateChangedCallback extends androidx.databinding.Observable.OnPropertyChangedCallback {
        
        public OnStateChangedCallback() {
            super();
        }
        
        @java.lang.Override
        public void onPropertyChanged(@org.jetbrains.annotations.NotNull
        androidx.databinding.Observable sender, int propertyId) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\t"}, d2 = {"Lorg/amnezia/awg/QuickTileService$OnTunnelChangedCallback;", "Landroidx/databinding/Observable$OnPropertyChangedCallback;", "(Lorg/amnezia/awg/QuickTileService;)V", "onPropertyChanged", "", "sender", "Landroidx/databinding/Observable;", "propertyId", "", "ui_debug"})
    final class OnTunnelChangedCallback extends androidx.databinding.Observable.OnPropertyChangedCallback {
        
        public OnTunnelChangedCallback() {
            super();
        }
        
        @java.lang.Override
        public void onPropertyChanged(@org.jetbrains.annotations.NotNull
        androidx.databinding.Observable sender, int propertyId) {
        }
    }
}