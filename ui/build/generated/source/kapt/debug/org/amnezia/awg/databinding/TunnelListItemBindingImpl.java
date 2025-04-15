package org.amnezia.awg.databinding;
import org.amnezia.awg.R;
import org.amnezia.awg.BR;
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
        sViewsWithIds = null;
    }
    // views
    // variables
    // values
    // listeners
    private OnBeforeCheckedChangeListenerImpl mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener;
    // Inverse Binding Event Handlers

    public TunnelListItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private TunnelListItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (org.amnezia.awg.widget.MultiselectableRelativeLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            , (org.amnezia.awg.widget.ToggleSwitch) bindings[2]
            );
        this.tunnelListItem.setTag(null);
        this.tunnelName.setTag(null);
        this.tunnelSwitch.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
            setItem((org.amnezia.awg.model.ObservableTunnel) variable);
        }
        else if (BR.fragment == variableId) {
            setFragment((org.amnezia.awg.fragment.TunnelListFragment) variable);
        }
        else if (BR.collection == variableId) {
            setCollection((org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel>) variable);
        }
        else if (BR.key == variableId) {
            setKey((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable org.amnezia.awg.model.ObservableTunnel Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    public void setFragment(@Nullable org.amnezia.awg.fragment.TunnelListFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setCollection(@Nullable org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel> Collection) {
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
                return onChangeItem((org.amnezia.awg.model.ObservableTunnel) object, fieldId);
            case 1 :
                return onChangeCollection((org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(org.amnezia.awg.model.ObservableTunnel Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.state) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeCollection(org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel> Collection, int fieldId) {
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
        org.amnezia.awg.model.ObservableTunnel item = mItem;
        org.amnezia.awg.fragment.TunnelListFragment fragment = mFragment;
        org.amnezia.awg.backend.Tunnel.State itemState = null;
        boolean itemStateStateUP = false;
        org.amnezia.awg.widget.ToggleSwitch.OnBeforeCheckedChangeListener fragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener = null;
        java.lang.String key = mKey;

        if ((dirtyFlags & 0x31L) != 0) {



                if (item != null) {
                    // read item.state
                    itemState = item.getState();
                }


                // read item.state == State.UP
                itemStateStateUP = (itemState) == (org.amnezia.awg.backend.Tunnel.State.UP);
        }
        if ((dirtyFlags & 0x24L) != 0) {



                if (fragment != null) {
                    // read fragment::setTunnelState
                    fragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener = (((mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener == null) ? (mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener = new OnBeforeCheckedChangeListenerImpl()) : mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener).setValue(fragment));
                }
        }
        if ((dirtyFlags & 0x28L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x28L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tunnelName, key);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            org.amnezia.awg.databinding.BindingAdapters.setChecked(this.tunnelSwitch, itemStateStateUP);
        }
        if ((dirtyFlags & 0x24L) != 0) {
            // api target 1

            org.amnezia.awg.databinding.BindingAdapters.setOnBeforeCheckedChanged(this.tunnelSwitch, fragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener);
        }
    }
    // Listener Stub Implementations
    public static class OnBeforeCheckedChangeListenerImpl implements org.amnezia.awg.widget.ToggleSwitch.OnBeforeCheckedChangeListener{
        private org.amnezia.awg.fragment.TunnelListFragment value;
        public OnBeforeCheckedChangeListenerImpl setValue(org.amnezia.awg.fragment.TunnelListFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onBeforeCheckedChanged(org.amnezia.awg.widget.ToggleSwitch arg0, boolean arg1) {
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
        flag 4 (0x5L): item.state
        flag 5 (0x6L): null
    flag mapping end*/
    //end
}