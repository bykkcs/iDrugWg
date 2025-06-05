package org.amnezia.awg.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001IB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J,\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u001a\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00100\u0017H\u0002J\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00152\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001bH\u0002J,\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u001a\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00100\u0017H\u0002J\u001e\u0010\u001d\u001a\u00020\u00102\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00100\u001fH\u0002J4\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00152\"\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00100\"H\u0002J\u0012\u0010#\u001a\u00020\u00102\b\u0010$\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0004H\u0002J\u0010\u0010\'\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J$\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u0010H\u0016J\b\u00100\u001a\u00020\u0010H\u0016J\u001a\u00101\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J<\u00102\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152*\u00103\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u001004H\u0002J$\u00105\u001a\u00020\u00102\u001a\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00100\u0017H\u0002J\u0016\u00106\u001a\u00020\u00102\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00100\u001bH\u0002J\u0010\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u0004H\u0002J\u0010\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0004H\u0002J\u0010\u0010<\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J4\u0010=\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010>\u001a\u00020\u00152\b\u0010?\u001a\u0004\u0018\u00010\u00152\u0006\u0010@\u001a\u00020\u00152\b\u0010A\u001a\u0004\u0018\u00010\u0015H\u0002J\u0010\u0010B\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010C\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010D\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010H\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lorg/amnezia/awg/fragment/AccountFragment;", "Landroidx/fragment/app/Fragment;", "()V", "destroyed", "", "handler", "Landroid/os/Handler;", "prefs", "Landroid/content/SharedPreferences;", "qrPollingTimer", "Ljava/util/Timer;", "qrScanLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "afterLogout", "", "view", "Landroid/view/View;", "confirmQrLoginToken", "token", "", "callback", "Lkotlin/Function2;", "deleteTunnelAndFile", "tunnelName", "onFinish", "Lkotlin/Function0;", "downloadConfig", "generateQrLoginToken", "onComplete", "Lkotlin/Function1;", "getProfileFromJwt", "jwt", "Lkotlin/Function3;", "handleDeepLink", "intent", "handleDownloadConfig", "isLoggedIn", "loadProfileAndSetupUI", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "pollQrLoginStatus", "onResult", "Lkotlin/Function4;", "renewSubscription", "safeUi", "block", "setFabScanQr", "show", "setLoading", "loading", "setupListeners", "showAccountScreen", "username", "photoUrl", "status", "expDateStr", "showCorrectScreen", "showLoginScreen", "showQrCode", "imageView", "Landroid/widget/ImageView;", "startPollingQrStatus", "startQrScanner", "CircleTransform", "ui_googleplay"})
public final class AccountFragment extends androidx.fragment.app.Fragment {
    private android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.NotNull
    private final android.os.Handler handler = null;
    private boolean destroyed = false;
    @org.jetbrains.annotations.Nullable
    private java.util.Timer qrPollingTimer;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> qrScanLauncher = null;
    
    public AccountFragment() {
        super();
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupListeners(android.view.View view) {
    }
    
    private final boolean isLoggedIn() {
        return false;
    }
    
    private final void setFabScanQr(boolean show) {
    }
    
    private final void showCorrectScreen(android.view.View view) {
    }
    
    private final void loadProfileAndSetupUI(android.view.View view) {
    }
    
    private final void showLoginScreen(android.view.View view) {
    }
    
    private final void showAccountScreen(android.view.View view, java.lang.String username, java.lang.String photoUrl, java.lang.String status, java.lang.String expDateStr) {
    }
    
    private final void setLoading(boolean loading) {
    }
    
    private final void afterLogout(android.view.View view) {
    }
    
    private final void handleDownloadConfig(android.view.View view) {
    }
    
    private final void downloadConfig(java.lang.String token, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void renewSubscription(kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void deleteTunnelAndFile(java.lang.String tunnelName, kotlin.jvm.functions.Function0<kotlin.Unit> onFinish) {
    }
    
    private final void safeUi(kotlin.jvm.functions.Function0<kotlin.Unit> block) {
    }
    
    private final void startQrScanner() {
    }
    
    private final void generateQrLoginToken(kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onComplete) {
    }
    
    private final void showQrCode(java.lang.String token, android.widget.ImageView imageView) {
    }
    
    private final void startPollingQrStatus(java.lang.String token) {
    }
    
    private final void pollQrLoginStatus(java.lang.String token, kotlin.jvm.functions.Function4<? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onResult) {
    }
    
    private final void getProfileFromJwt(java.lang.String jwt, kotlin.jvm.functions.Function3<? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void confirmQrLoginToken(java.lang.String token, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void handleDeepLink(android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a8\u0006\b"}, d2 = {"Lorg/amnezia/awg/fragment/AccountFragment$CircleTransform;", "Lcom/squareup/picasso/Transformation;", "()V", "key", "", "transform", "Landroid/graphics/Bitmap;", "source", "ui_googleplay"})
    public static final class CircleTransform implements com.squareup.picasso.Transformation {
        
        public CircleTransform() {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public android.graphics.Bitmap transform(@org.jetbrains.annotations.NotNull
        android.graphics.Bitmap source) {
            return null;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String key() {
            return null;
        }
    }
}