package org.amnezia.awg.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u0003\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J2\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\fH\u0086@\u00a2\u0006\u0002\u0010\u000eJ*\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\fJ8\u0010\u0012\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/amnezia/awg/util/TunnelImporter;", "", "()V", "TAG", "", "importTunnel", "", "contentResolver", "Landroid/content/ContentResolver;", "uri", "Landroid/net/Uri;", "messageCallback", "Lkotlin/Function1;", "", "(Landroid/content/ContentResolver;Landroid/net/Uri;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parentFragmentManager", "Landroidx/fragment/app/FragmentManager;", "configText", "onTunnelImportFinished", "tunnels", "", "Lorg/amnezia/awg/model/ObservableTunnel;", "throwables", "", "", "ui_debug"})
public final class TunnelImporter {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/TunnelImporter";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.util.TunnelImporter INSTANCE = null;
    
    private TunnelImporter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object importTunnel(@org.jetbrains.annotations.NotNull
    android.content.ContentResolver contentResolver, @org.jetbrains.annotations.NotNull
    android.net.Uri uri, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.CharSequence, kotlin.Unit> messageCallback, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void importTunnel(@org.jetbrains.annotations.NotNull
    androidx.fragment.app.FragmentManager parentFragmentManager, @org.jetbrains.annotations.NotNull
    java.lang.String configText, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.CharSequence, kotlin.Unit> messageCallback) {
    }
    
    private final void onTunnelImportFinished(java.util.List<org.amnezia.awg.model.ObservableTunnel> tunnels, java.util.Collection<? extends java.lang.Throwable> throwables, kotlin.jvm.functions.Function1<? super java.lang.CharSequence, kotlin.Unit> messageCallback) {
    }
}