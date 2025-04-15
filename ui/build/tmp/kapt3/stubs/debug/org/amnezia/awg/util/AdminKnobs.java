package org.amnezia.awg.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/amnezia/awg/util/AdminKnobs;", "", "()V", "disableConfigExport", "", "getDisableConfigExport", "()Z", "restrictions", "Landroid/content/RestrictionsManager;", "ui_debug"})
public final class AdminKnobs {
    @org.jetbrains.annotations.Nullable
    private static final android.content.RestrictionsManager restrictions = null;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.util.AdminKnobs INSTANCE = null;
    
    private AdminKnobs() {
        super();
    }
    
    public final boolean getDisableConfigExport() {
        return false;
    }
}