package org.amnezia.awg.databinding;

/**
 * Helper class for binding an ObservableList to the children of a ViewGroup.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0017B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/amnezia/awg/databinding/ItemChangeListener;", "T", "", "container", "Landroid/view/ViewGroup;", "layoutId", "", "fragment", "Landroidx/fragment/app/Fragment;", "(Landroid/view/ViewGroup;ILandroidx/fragment/app/Fragment;)V", "callback", "Lorg/amnezia/awg/databinding/ItemChangeListener$OnListChangedCallback;", "layoutInflater", "Landroid/view/LayoutInflater;", "list", "Landroidx/databinding/ObservableList;", "getView", "Landroid/view/View;", "position", "convertView", "setList", "", "newList", "OnListChangedCallback", "ui_debug"})
public final class ItemChangeListener<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull
    private final android.view.ViewGroup container = null;
    private final int layoutId = 0;
    @org.jetbrains.annotations.Nullable
    private final androidx.fragment.app.Fragment fragment = null;
    @org.jetbrains.annotations.NotNull
    private final org.amnezia.awg.databinding.ItemChangeListener.OnListChangedCallback<T> callback = null;
    @org.jetbrains.annotations.NotNull
    private final android.view.LayoutInflater layoutInflater = null;
    @org.jetbrains.annotations.Nullable
    private androidx.databinding.ObservableList<T> list;
    
    public ItemChangeListener(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup container, int layoutId, @org.jetbrains.annotations.Nullable
    androidx.fragment.app.Fragment fragment) {
        super();
    }
    
    private final android.view.View getView(int position, android.view.View convertView) {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.Nullable
    androidx.databinding.ObservableList<T> newList) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0016J&\u0010\f\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J&\u0010\u0010\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J.\u0010\u0011\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J&\u0010\u0014\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00050\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/amnezia/awg/databinding/ItemChangeListener$OnListChangedCallback;", "T", "Landroidx/databinding/ObservableList$OnListChangedCallback;", "Landroidx/databinding/ObservableList;", "listener", "Lorg/amnezia/awg/databinding/ItemChangeListener;", "(Lorg/amnezia/awg/databinding/ItemChangeListener;)V", "weakListener", "Ljava/lang/ref/WeakReference;", "onChanged", "", "sender", "onItemRangeChanged", "positionStart", "", "itemCount", "onItemRangeInserted", "onItemRangeMoved", "fromPosition", "toPosition", "onItemRangeRemoved", "ui_debug"})
    static final class OnListChangedCallback<T extends java.lang.Object> extends androidx.databinding.ObservableList.OnListChangedCallback<androidx.databinding.ObservableList<T>> {
        @org.jetbrains.annotations.NotNull
        private final java.lang.ref.WeakReference<org.amnezia.awg.databinding.ItemChangeListener<T>> weakListener = null;
        
        public OnListChangedCallback(@org.jetbrains.annotations.NotNull
        org.amnezia.awg.databinding.ItemChangeListener<T> listener) {
            super();
        }
        
        @java.lang.Override
        public void onChanged(@org.jetbrains.annotations.NotNull
        androidx.databinding.ObservableList<T> sender) {
        }
        
        @java.lang.Override
        public void onItemRangeChanged(@org.jetbrains.annotations.NotNull
        androidx.databinding.ObservableList<T> sender, int positionStart, int itemCount) {
        }
        
        @java.lang.Override
        public void onItemRangeInserted(@org.jetbrains.annotations.NotNull
        androidx.databinding.ObservableList<T> sender, int positionStart, int itemCount) {
        }
        
        @java.lang.Override
        public void onItemRangeMoved(@org.jetbrains.annotations.NotNull
        androidx.databinding.ObservableList<T> sender, int fromPosition, int toPosition, int itemCount) {
        }
        
        @java.lang.Override
        public void onItemRangeRemoved(@org.jetbrains.annotations.NotNull
        androidx.databinding.ObservableList<T> sender, int positionStart, int itemCount) {
        }
    }
}