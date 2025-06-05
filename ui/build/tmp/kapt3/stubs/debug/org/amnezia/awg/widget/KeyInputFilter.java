package org.amnezia.awg.widget;

/**
 * InputFilter for entering AmneziaWG private/public keys encoded with base64.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a8\u0006\u000e"}, d2 = {"Lorg/amnezia/awg/widget/KeyInputFilter;", "Landroid/text/InputFilter;", "()V", "filter", "", "source", "sStart", "", "sEnd", "dest", "Landroid/text/Spanned;", "dStart", "dEnd", "Companion", "ui_debug"})
public final class KeyInputFilter implements android.text.InputFilter {
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.widget.KeyInputFilter.Companion Companion = null;
    
    public KeyInputFilter() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.CharSequence filter(@org.jetbrains.annotations.NotNull
    java.lang.CharSequence source, int sStart, int sEnd, @org.jetbrains.annotations.NotNull
    android.text.Spanned dest, int dStart, int dEnd) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.widget.KeyInputFilter newInstance() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lorg/amnezia/awg/widget/KeyInputFilter$Companion;", "", "()V", "isAllowed", "", "c", "", "newInstance", "Lorg/amnezia/awg/widget/KeyInputFilter;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final boolean isAllowed(char c) {
            return false;
        }
        
        @kotlin.jvm.JvmStatic
        @org.jetbrains.annotations.NotNull
        public final org.amnezia.awg.widget.KeyInputFilter newInstance() {
            return null;
        }
    }
}