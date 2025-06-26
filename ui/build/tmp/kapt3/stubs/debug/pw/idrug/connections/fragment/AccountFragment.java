package pw.idrug.connections.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002QRB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J,\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\u001a\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00180\u001eH\u0002J<\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u001a\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00180\u001eH\u0002J\u001e\u0010\"\u001a\u00020\u00182\u0014\u0010#\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00180$H\u0002J4\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u00102\"\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00180\'H\u0002J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u0010H\u0002J\u0012\u0010*\u001a\u00020\u00182\b\u0010+\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010-\u001a\u00020\u0004H\u0002J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010/\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J$\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020\u0018H\u0016J\b\u00108\u001a\u00020\u0018H\u0016J\u001a\u00109\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u00105\u001a\u0004\u0018\u000106H\u0016J<\u0010:\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102*\u0010;\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00180<H\u0002J$\u0010=\u001a\u00020\u00182\u001a\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00180\u001eH\u0002J\u0016\u0010>\u001a\u00020\u00182\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00180@H\u0002J\u0010\u0010A\u001a\u00020\u00182\u0006\u0010B\u001a\u00020\u0004H\u0002J\u0010\u0010C\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010D\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J0\u0010E\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010F\u001a\u00020\u00102\b\u0010G\u001a\u0004\u0018\u00010\u00102\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0002J\u0010\u0010I\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010J\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010K\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010L\u001a\u00020MH\u0002J\u0010\u0010N\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0010H\u0002J\b\u0010O\u001a\u00020\u0018H\u0002J\u0010\u0010P\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00140\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006S"}, d2 = {"Lpw/idrug/connections/fragment/AccountFragment;", "Landroidx/fragment/app/Fragment;", "()V", "destroyed", "", "handler", "Landroid/os/Handler;", "prefs", "Landroid/content/SharedPreferences;", "qrPollingTimer", "Ljava/util/Timer;", "qrScanLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "selectedServerId", "", "selectedServerName", "serverList", "", "Lkotlin/Pair;", "subscriptions", "Lpw/idrug/connections/fragment/AccountFragment$Subscription;", "afterLogout", "", "view", "Landroid/view/View;", "confirmQrLoginToken", "token", "callback", "Lkotlin/Function2;", "downloadConfig", "serverId", "tunnelName", "generateQrLoginToken", "onComplete", "Lkotlin/Function1;", "getProfileFromJwt", "jwt", "Lkotlin/Function3;", "getServerName", "location", "handleDeepLink", "intent", "handleDownloadConfig", "isLoggedIn", "loadProfileAndSetupUI", "loadServersAndProfileUI", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "pollQrLoginStatus", "onResult", "Lkotlin/Function4;", "renewSubscription", "safeUi", "block", "Lkotlin/Function0;", "setLoading", "loading", "setupListeners", "setupServerSpinner", "showAccountScreen", "username", "photoUrl", "subs", "showCorrectScreen", "showLoginScreen", "showQrCode", "imageView", "Landroid/widget/ImageView;", "startPollingQrStatus", "syncTunnelsWithProfile", "updateDownloadButtonState", "CircleTransform", "Subscription", "ui_debug"})
public final class AccountFragment extends androidx.fragment.app.Fragment {
    private android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.NotNull
    private final android.os.Handler handler = null;
    private boolean destroyed = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.String selectedServerId;
    @org.jetbrains.annotations.Nullable
    private java.lang.String selectedServerName;
    @org.jetbrains.annotations.NotNull
    private java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> serverList;
    @org.jetbrains.annotations.Nullable
    private java.util.Timer qrPollingTimer;
    @org.jetbrains.annotations.NotNull
    private java.util.List<pw.idrug.connections.fragment.AccountFragment.Subscription> subscriptions;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> qrScanLauncher = null;
    
    public AccountFragment() {
        super();
    }
    
    private final java.lang.String getServerName(java.lang.String location) {
        return null;
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
    
    private final void showCorrectScreen(android.view.View view) {
    }
    
    private final void loadServersAndProfileUI(android.view.View view) {
    }
    
    private final void setupServerSpinner(android.view.View view) {
    }
    
    private final void loadProfileAndSetupUI(android.view.View view) {
    }
    
    private final void showLoginScreen(android.view.View view) {
    }
    
    private final void showAccountScreen(android.view.View view, java.lang.String username, java.lang.String photoUrl, java.util.List<pw.idrug.connections.fragment.AccountFragment.Subscription> subs) {
    }
    
    private final void updateDownloadButtonState(android.view.View view) {
    }
    
    private final void setLoading(boolean loading) {
    }
    
    private final void afterLogout(android.view.View view) {
    }
    
    private final void handleDownloadConfig(android.view.View view) {
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
    
    private final void downloadConfig(java.lang.String token, java.lang.String serverId, java.lang.String tunnelName, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void renewSubscription(kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void handleDeepLink(android.content.Intent intent) {
    }
    
    private final void syncTunnelsWithProfile() {
    }
    
    private final void safeUi(kotlin.jvm.functions.Function0<kotlin.Unit> block) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a8\u0006\b"}, d2 = {"Lpw/idrug/connections/fragment/AccountFragment$CircleTransform;", "Lcom/squareup/picasso/Transformation;", "()V", "key", "", "transform", "Landroid/graphics/Bitmap;", "source", "ui_debug"})
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\u0006\u0010\u001b\u001a\u00020\u0007J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lpw/idrug/connections/fragment/AccountFragment$Subscription;", "", "location", "", "name", "expires", "forever", "", "active", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getActive", "()Z", "getExpires", "()Ljava/lang/String;", "getForever", "getLocation", "getName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "isActive", "toString", "ui_debug"})
    static final class Subscription {
        @org.jetbrains.annotations.NotNull
        private final java.lang.String location = null;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String name = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String expires = null;
        private final boolean forever = false;
        private final boolean active = false;
        
        public Subscription(@org.jetbrains.annotations.NotNull
        java.lang.String location, @org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.Nullable
        java.lang.String expires, boolean forever, boolean active) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getLocation() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getExpires() {
            return null;
        }
        
        public final boolean getForever() {
            return false;
        }
        
        public final boolean getActive() {
            return false;
        }
        
        public final boolean isActive() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component3() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final pw.idrug.connections.fragment.AccountFragment.Subscription copy(@org.jetbrains.annotations.NotNull
        java.lang.String location, @org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.Nullable
        java.lang.String expires, boolean forever, boolean active) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
}