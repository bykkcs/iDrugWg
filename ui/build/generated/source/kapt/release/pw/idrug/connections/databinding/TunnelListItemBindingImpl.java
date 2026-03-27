package pw.idrug.connections.databinding;
import pw.idrug.connections.R;
import pw.idrug.connections.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TunnelListItemBindingImpl extends TunnelListItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tunnel_container, 4);
    }
    // views
    // variables
    // values
    // listeners
    private OnBeforeCheckedChangeListenerImpl mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener;
    // Inverse Binding Event Handlers

    public TunnelListItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private TunnelListItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (com.google.android.material.card.MaterialCardView) bindings[0]
            , (pw.idrug.connections.widget.MultiselectableRelativeLayout) bindings[4]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (pw.idrug.connections.widget.ToggleSwitch) bindings[3]
            );
        this.tunnelCard.setTag(null);
        this.tunnelName.setTag(null);
        this.tunnelQuicBadge.setTag(null);
        this.tunnelSwitch.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((pw.idrug.connections.model.ObservableTunnel) variable);
        }
        else if (BR.fragment == variableId) {
            setFragment((pw.idrug.connections.fragment.TunnelListFragment) variable);
        }
        else if (BR.collection == variableId) {
            setCollection((pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ObservableTunnel>) variable);
        }
        else if (BR.key == variableId) {
            setKey((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable pw.idrug.connections.model.ObservableTunnel Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public void setFragment(@Nullable pw.idrug.connections.fragment.TunnelListFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setCollection(@Nullable pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ObservableTunnel> Collection) {
        this.mCollection = Collection;
    }
    public void setKey(@Nullable java.lang.String Key) {
        this.mKey = Key;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.key);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((pw.idrug.connections.model.ObservableTunnel) object, fieldId);
            case 1 :
                return onChangeCollection((pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ObservableTunnel>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(pw.idrug.connections.model.ObservableTunnel Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.quicReadyBadge) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.state) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCollection(pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ObservableTunnel> Collection, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        pw.idrug.connections.model.ObservableTunnel item = mItem;
        pw.idrug.connections.fragment.TunnelListFragment fragment = mFragment;
        pw.idrug.connections.widget.ToggleSwitch.OnBeforeCheckedChangeListener fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = null;
        boolean itemQuicReadyBadge = false;
        org.amnezia.awg.backend.Tunnel.State itemState = null;
        boolean itemStateStateUP = false;
        java.lang.String key = mKey;
        int itemQuicReadyBadgeViewVISIBLEViewGONE = 0;

        if ((dirtyFlags & 0x71L) != 0) {


            if ((dirtyFlags & 0x51L) != 0) {

                    if (item != null) {
                        // read item.quicReadyBadge
                        itemQuicReadyBadge = item.getQuicReadyBadge();
                    }
                if((dirtyFlags & 0x51L) != 0) {
                    if(itemQuicReadyBadge) {
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                    }
                }


                    // read item.quicReadyBadge ? View.VISIBLE : View.GONE
                    itemQuicReadyBadgeViewVISIBLEViewGONE = ((itemQuicReadyBadge) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x61L) != 0) {

                    if (item != null) {
                        // read item.state
                        itemState = item.getState();
                    }


                    // read item.state == State.UP
                    itemStateStateUP = (itemState) == (org.amnezia.awg.backend.Tunnel.State.UP);
            }
        }
        if ((dirtyFlags & 0x44L) != 0) {



                if (fragment != null) {
                    // read fragment::setTunnelState
                    fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = (((mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener == null) ? (mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = new OnBeforeCheckedChangeListenerImpl()) : mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener).setValue(fragment));
                }
        }
        if ((dirtyFlags & 0x48L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x48L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tunnelName, key);
        }
        if ((dirtyFlags & 0x51L) != 0) {
            // api target 1

            this.tunnelQuicBadge.setVisibility(itemQuicReadyBadgeViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x61L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setChecked(this.tunnelSwitch, itemStateStateUP);
        }
        if ((dirtyFlags & 0x44L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setOnBeforeCheckedChanged(this.tunnelSwitch, fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener);
        }
    }
    // Listener Stub Implementations
    public static class OnBeforeCheckedChangeListenerImpl implements pw.idrug.connections.widget.ToggleSwitch.OnBeforeCheckedChangeListener{
        private pw.idrug.connections.fragment.TunnelListFragment value;
        public OnBeforeCheckedChangeListenerImpl setValue(pw.idrug.connections.fragment.TunnelListFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onBeforeCheckedChanged(pw.idrug.connections.widget.ToggleSwitch arg0, boolean arg1) {
            this.value.setTunnelState(arg0, arg1); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): collection
        flag 2 (0x3L): fragment
        flag 3 (0x4L): key
        flag 4 (0x5L): item.quicReadyBadge
        flag 5 (0x6L): item.state
        flag 6 (0x7L): null
        flag 7 (0x8L): item.quicReadyBadge ? View.VISIBLE : View.GONE
        flag 8 (0x9L): item.quicReadyBadge ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}