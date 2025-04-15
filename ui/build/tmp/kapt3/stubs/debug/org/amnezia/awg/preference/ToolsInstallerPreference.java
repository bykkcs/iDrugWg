package org.amnezia.awg.preference;

/**
 * Preference implementing a button that asynchronously runs `ToolsInstaller` and displays the
 * result as the preference summary.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0014J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/amnezia/awg/preference/ToolsInstallerPreference;", "Landroidx/preference/Preference;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "state", "Lorg/amnezia/awg/preference/ToolsInstallerPreference$State;", "getSummary", "", "getTitle", "onAttached", "", "onClick", "setState", "State", "ui_debug"})
public final class ToolsInstallerPreference extends androidx.preference.Preference {
    @org.jetbrains.annotations.NotNull
    private org.amnezia.awg.preference.ToolsInstallerPreference.State state = org.amnezia.awg.preference.ToolsInstallerPreference.State.INITIAL;
    
    public ToolsInstallerPreference(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null, null, 0, 0);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getSummary() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String getTitle() {
        return null;
    }
    
    @java.lang.Override
    public void onAttached() {
    }
    
    @java.lang.Override
    protected void onClick() {
    }
    
    private final void setState(org.amnezia.awg.preference.ToolsInstallerPreference.State state) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/amnezia/awg/preference/ToolsInstallerPreference$State;", "", "messageResourceId", "", "shouldEnableView", "", "(Ljava/lang/String;IIZ)V", "getMessageResourceId", "()I", "getShouldEnableView", "()Z", "INITIAL", "ALREADY", "FAILURE", "WORKING", "INITIAL_SYSTEM", "SUCCESS_SYSTEM", "INITIAL_MAGISK", "SUCCESS_MAGISK", "ui_debug"})
    static enum State {
        /*public static final*/ INITIAL /* = new INITIAL(0, false) */,
        /*public static final*/ ALREADY /* = new ALREADY(0, false) */,
        /*public static final*/ FAILURE /* = new FAILURE(0, false) */,
        /*public static final*/ WORKING /* = new WORKING(0, false) */,
        /*public static final*/ INITIAL_SYSTEM /* = new INITIAL_SYSTEM(0, false) */,
        /*public static final*/ SUCCESS_SYSTEM /* = new SUCCESS_SYSTEM(0, false) */,
        /*public static final*/ INITIAL_MAGISK /* = new INITIAL_MAGISK(0, false) */,
        /*public static final*/ SUCCESS_MAGISK /* = new SUCCESS_MAGISK(0, false) */;
        private final int messageResourceId = 0;
        private final boolean shouldEnableView = false;
        
        State(int messageResourceId, boolean shouldEnableView) {
        }
        
        public final int getMessageResourceId() {
            return 0;
        }
        
        public final boolean getShouldEnableView() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<org.amnezia.awg.preference.ToolsInstallerPreference.State> getEntries() {
            return null;
        }
    }
}