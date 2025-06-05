package org.amnezia.awg.databinding;
import org.amnezia.awg.R;
import org.amnezia.awg.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TunnelListFragmentBindingImpl extends TunnelListFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.logo_placeholder, 3);
        sViewsWithIds.put(R.id.create_fab, 4);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView2;
    // variables
    // values
    private org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel> mOldTunnels;
    private int mOldAndroidLayoutTunnelListItem;
    private org.amnezia.awg.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler mOldRowConfigurationHandler;
    // listeners
    // Inverse Binding Event Handlers

    public TunnelListFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private TunnelListFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[4]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[3]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            );
        this.mainContainer.setTag(null);
        this.mboundView2 = (android.widget.LinearLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.tunnelList.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.fragment == variableId) {
            setFragment((org.amnezia.awg.fragment.TunnelListFragment) variable);
        }
        else if (BR.tunnels == variableId) {
            setTunnels((org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel>) variable);
        }
        else if (BR.rowConfigurationHandler == variableId) {
            setRowConfigurationHandler((org.amnezia.awg.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable org.amnezia.awg.fragment.TunnelListFragment Fragment) {
        this.mFragment = Fragment;
    }
    public void setTunnels(@Nullable org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel> Tunnels) {
        updateRegistration(0, Tunnels);
        this.mTunnels = Tunnels;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.tunnels);
        super.requestRebind();
    }
    public void setRowConfigurationHandler(@Nullable org.amnezia.awg.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler RowConfigurationHandler) {
        this.mRowConfigurationHandler = RowConfigurationHandler;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.rowConfigurationHandler);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeTunnels((org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeTunnels(org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel> Tunnels, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        boolean tunnelsSizeInt0 = false;
        int tunnelsSize = 0;
        int tunnelsSizeInt0AndroidViewViewVISIBLEAndroidViewViewGONE = 0;
        boolean TunnelsSizeInt01 = false;
        org.amnezia.awg.databinding.ObservableKeyedArrayList<java.lang.String,org.amnezia.awg.model.ObservableTunnel> tunnels = mTunnels;
        org.amnezia.awg.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler<? extends androidx.databinding.ViewDataBinding,?> rowConfigurationHandler = mRowConfigurationHandler;
        int TunnelsSizeInt0AndroidViewViewVISIBLEAndroidViewViewGONE1 = 0;

        if ((dirtyFlags & 0xdL) != 0) {


            if ((dirtyFlags & 0x9L) != 0) {

                    if (tunnels != null) {
                        // read tunnels.size()
                        tunnelsSize = tunnels.size();
                    }


                    // read tunnels.size() == 0
                    tunnelsSizeInt0 = (tunnelsSize) == (0);
                    // read tunnels.size() > 0
                    TunnelsSizeInt01 = (tunnelsSize) > (0);
                if((dirtyFlags & 0x9L) != 0) {
                    if(tunnelsSizeInt0) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }
                if((dirtyFlags & 0x9L) != 0) {
                    if(TunnelsSizeInt01) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }


                    // read tunnels.size() == 0 ? android.view.View.VISIBLE : android.view.View.GONE
                    TunnelsSizeInt0AndroidViewViewVISIBLEAndroidViewViewGONE1 = ((tunnelsSizeInt0) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read tunnels.size() > 0 ? android.view.View.VISIBLE : android.view.View.GONE
                    tunnelsSizeInt0AndroidViewViewVISIBLEAndroidViewViewGONE = ((TunnelsSizeInt01) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x9L) != 0) {
            // api target 1

            this.mboundView2.setVisibility(TunnelsSizeInt0AndroidViewViewVISIBLEAndroidViewViewGONE1);
            this.tunnelList.setVisibility(tunnelsSizeInt0AndroidViewViewVISIBLEAndroidViewViewGONE);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            androidx.databinding.adapters.ViewBindingAdapter.setPaddingBottom(this.tunnelList, (tunnelList.getResources().getDimension(com.google.android.material.R.dimen.design_fab_size_normal)) * (1.1f));
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            org.amnezia.awg.databinding.BindingAdapters.setItems(this.tunnelList, this.mOldTunnels, this.mOldAndroidLayoutTunnelListItem, this.mOldRowConfigurationHandler, tunnels, R.layout.tunnel_list_item, rowConfigurationHandler);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            this.mOldTunnels = tunnels;
            this.mOldAndroidLayoutTunnelListItem = R.layout.tunnel_list_item;
            this.mOldRowConfigurationHandler = rowConfigurationHandler;
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): tunnels
        flag 1 (0x2L): fragment
        flag 2 (0x3L): rowConfigurationHandler
        flag 3 (0x4L): null
        flag 4 (0x5L): tunnels.size() > 0 ? android.view.View.VISIBLE : android.view.View.GONE
        flag 5 (0x6L): tunnels.size() > 0 ? android.view.View.VISIBLE : android.view.View.GONE
        flag 6 (0x7L): tunnels.size() == 0 ? android.view.View.VISIBLE : android.view.View.GONE
        flag 7 (0x8L): tunnels.size() == 0 ? android.view.View.VISIBLE : android.view.View.GONE
    flag mapping end*/
    //end
}