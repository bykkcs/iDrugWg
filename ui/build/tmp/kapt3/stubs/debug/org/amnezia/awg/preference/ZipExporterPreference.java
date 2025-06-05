package org.amnezia.awg.preference;

/**
 * Preference implementing a button that asynchronously exports config zips.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/amnezia/awg/preference/ZipExporterPreference;", "Landroidx/preference/Preference;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "downloadsFileSaver", "Lorg/amnezia/awg/util/DownloadsFileSaver;", "exportedFilePath", "", "exportZip", "", "getSummary", "getTitle", "onClick", "Companion", "ui_debug"})
public final class ZipExporterPreference extends androidx.preference.Preference {
    @org.jetbrains.annotations.Nullable
    private java.lang.String exportedFilePath;
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.util.DownloadsFileSaver downloadsFileSaver = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/ZipExporterPreference";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.preference.ZipExporterPreference.Companion Companion = null;
    
    public ZipExporterPreference(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null, null, 0, 0);
    }
    
    private final void exportZip() {
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
    protected void onClick() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/amnezia/awg/preference/ZipExporterPreference$Companion;", "", "()V", "TAG", "", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}