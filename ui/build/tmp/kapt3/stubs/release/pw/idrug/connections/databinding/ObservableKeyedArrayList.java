package pw.idrug.connections.databinding;

/**
 * ArrayList that allows looking up elements by some key property. As the key property must always
 * be retrievable, this list cannot hold `null` elements. Because this class places no
 * restrictions on the order or duplication of keys, lookup by key, as well as all list modification
 * operations, require O(n) time.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0010\b\u0001\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0086\u0002\u00a2\u0006\u0002\u0010\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lpw/idrug/connections/databinding/ObservableKeyedArrayList;", "K", "E", "Lpw/idrug/connections/databinding/Keyed;", "Landroidx/databinding/ObservableArrayList;", "()V", "containsKey", "", "key", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/Object;)Lpw/idrug/connections/databinding/Keyed;", "indexOfKey", "", "(Ljava/lang/Object;)I", "ui_release"})
public class ObservableKeyedArrayList<K extends java.lang.Object, E extends pw.idrug.connections.databinding.Keyed<? extends K>> extends androidx.databinding.ObservableArrayList<E> {
    
    public ObservableKeyedArrayList() {
        super();
    }
    
    public final boolean containsKey(K key) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final E get(K key) {
        return null;
    }
    
    public int indexOfKey(K key) {
        return 0;
    }
    
    @java.lang.Override
    public final boolean contains(java.lang.Object element) {
        return false;
    }
    
    @java.lang.Override
    public boolean contains(pw.idrug.connections.databinding.Keyed<?> element) {
        return false;
    }
    
    @java.lang.Override
    public int getSize() {
        return 0;
    }
    
    @java.lang.Override
    public final int indexOf(java.lang.Object element) {
        return 0;
    }
    
    @java.lang.Override
    public int indexOf(pw.idrug.connections.databinding.Keyed<?> element) {
        return 0;
    }
    
    @java.lang.Override
    public final int lastIndexOf(java.lang.Object element) {
        return 0;
    }
    
    @java.lang.Override
    public int lastIndexOf(pw.idrug.connections.databinding.Keyed<?> element) {
        return 0;
    }
    
    public final E remove(int index) {
        return null;
    }
    
    @java.lang.Override
    public final boolean remove(java.lang.Object element) {
        return false;
    }
    
    @java.lang.Override
    public boolean remove(pw.idrug.connections.databinding.Keyed<?> element) {
        return false;
    }
    
    @java.lang.Override
    public pw.idrug.connections.databinding.Keyed<?> removeAt(int p0) {
        return null;
    }
    
    @java.lang.Override
    public final int size() {
        return 0;
    }
}