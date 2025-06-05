package org.amnezia.awg.widget;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B3\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0014J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bJ\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/amnezia/awg/widget/MultiselectableRelativeLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "multiselected", "", "onCreateDrawableState", "", "extraSpace", "setMultiSelected", "", "on", "setSingleSelected", "Companion", "ui_debug"})
public final class MultiselectableRelativeLayout extends android.widget.RelativeLayout {
    private boolean multiselected = false;
    @org.jetbrains.annotations.NotNull
    private static final int[] STATE_MULTISELECTED = null;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.widget.MultiselectableRelativeLayout.Companion Companion = null;
    
    @kotlin.jvm.JvmOverloads
    public MultiselectableRelativeLayout(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    protected int[] onCreateDrawableState(int extraSpace) {
        return null;
    }
    
    public final void setMultiSelected(boolean on) {
    }
    
    public final void setSingleSelected(boolean on) {
    }
    
    @kotlin.jvm.JvmOverloads
    public MultiselectableRelativeLayout() {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public MultiselectableRelativeLayout(@org.jetbrains.annotations.Nullable
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public MultiselectableRelativeLayout(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads
    public MultiselectableRelativeLayout(@org.jetbrains.annotations.Nullable
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/amnezia/awg/widget/MultiselectableRelativeLayout$Companion;", "", "()V", "STATE_MULTISELECTED", "", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}