package pw.idrug.connections.widget;

/**
 * InputFilter for entering iDrugConnections private/public keys encoded with base64.
 */
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a8\u0006\u000f"}, d2 = {"Lpw/idrug/connections/widget/KeyInputFilter;", "Landroid/text/InputFilter;", "<init>", "()V", "filter", "", "source", "sStart", "", "sEnd", "dest", "Landroid/text/Spanned;", "dStart", "dEnd", "Companion", "ui_release"})
public final class KeyInputFilter implements android.text.InputFilter {
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.widget.KeyInputFilter.Companion Companion = null;
    
    public KeyInputFilter() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.CharSequence filter(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence source, int sStart, int sEnd, @org.jetbrains.annotations.NotNull()
    android.text.Spanned dest, int dStart, int dEnd) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.widget.KeyInputFilter newInstance() {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\n"}, d2 = {"Lpw/idrug/connections/widget/KeyInputFilter$Companion;", "", "<init>", "()V", "isAllowed", "", "c", "", "newInstance", "Lpw/idrug/connections/widget/KeyInputFilter;", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final boolean isAllowed(char c) {
            return false;
        }
        
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.NotNull()
        public final pw.idrug.connections.widget.KeyInputFilter newInstance() {
            return null;
        }
    }
}