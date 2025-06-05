package org.amnezia.awg.fragment;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H\u0002J4\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u001a\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\n0\u0015H\u0002J4\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u001a\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\n0\u0015H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\nH\u0016J4\u0010!\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u001a\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\n0\u0015H\u0002J\u0010\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u0019H\u0002J<\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010\'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lorg/amnezia/awg/fragment/AccountFragment;", "Landroidx/fragment/app/Fragment;", "()V", "destroyed", "", "handler", "Landroid/os/Handler;", "prefs", "Landroid/content/SharedPreferences;", "checkAccountStatus", "", "username", "", "deleteTunnelAndFile", "tunnelName", "onFinish", "Lkotlin/Function0;", "downloadConfig", "clientName", "url", "callback", "Lkotlin/Function2;", "loginOrRegister", "password", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "register", "setupUi", "view", "updateUiMain", "mode", "loading", "info", "exp", "showDownload", "showRenew", "ui_release"})
public final class AccountFragment extends androidx.fragment.app.Fragment {
    private android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.NotNull
    private final android.os.Handler handler = null;
    private boolean destroyed = false;
    
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
    
    private final void setupUi(android.view.View view) {
    }
    
    private final void loginOrRegister(java.lang.String username, java.lang.String password, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void register(java.lang.String username, java.lang.String password, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void checkAccountStatus(java.lang.String username) {
    }
    
    private final void updateUiMain(java.lang.String mode, boolean loading, java.lang.String info, java.lang.String exp, boolean showDownload, boolean showRenew) {
    }
    
    private final void downloadConfig(java.lang.String clientName, java.lang.String url, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> callback) {
    }
    
    private final void deleteTunnelAndFile(java.lang.String tunnelName, kotlin.jvm.functions.Function0<kotlin.Unit> onFinish) {
    }
}