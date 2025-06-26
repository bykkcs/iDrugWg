package pw.idrug.connections.databinding;
import pw.idrug.connections.R;
import pw.idrug.connections.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class AppListDialogFragmentBindingImpl extends AppListDialogFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tabs, 3);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    private pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ApplicationData> mOldAppData;
    private int mOldAndroidLayoutAppListItem;
    // listeners
    // Inverse Binding Event Handlers

    public AppListDialogFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private AppListDialogFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            , (android.widget.ProgressBar) bindings[1]
            , (com.google.android.material.tabs.TabLayout) bindings[3]
            );
        this.appList.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setFragment((pw.idrug.connections.fragment.AppListDialogFragment) variable);
        }
        else if (BR.appData == variableId) {
            setAppData((pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ApplicationData>) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setFragment(@Nullable pw.idrug.connections.fragment.AppListDialogFragment Fragment) {
        this.mFragment = Fragment;
    }
    public void setAppData(@Nullable pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ApplicationData> AppData) {
        updateRegistration(0, AppData);
        this.mAppData = AppData;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.appData);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeAppData((pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ApplicationData>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeAppData(pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ApplicationData> AppData, int fieldId) {
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
        int appDataIsEmptyViewVISIBLEViewGONE = 0;
        boolean appDataIsEmpty = false;
        pw.idrug.connections.databinding.ObservableKeyedArrayList<java.lang.String,pw.idrug.connections.model.ApplicationData> appData = mAppData;

        if ((dirtyFlags & 0x5L) != 0) {



                if (appData != null) {
                    // read appData.isEmpty()
                    appDataIsEmpty = appData.isEmpty();
                }
            if((dirtyFlags & 0x5L) != 0) {
                if(appDataIsEmpty) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read appData.isEmpty() ? View.VISIBLE : View.GONE
                appDataIsEmptyViewVISIBLEViewGONE = ((appDataIsEmpty) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setItems(this.appList, this.mOldAppData, this.mOldAndroidLayoutAppListItem, (pw.idrug.connections.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler)null, appData, R.layout.app_list_item, (pw.idrug.connections.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler)null);
            this.progressBar.setVisibility(appDataIsEmptyViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            this.mOldAppData = appData;
            this.mOldAndroidLayoutAppListItem = R.layout.app_list_item;
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): appData
        flag 1 (0x2L): fragment
        flag 2 (0x3L): null
        flag 3 (0x4L): appData.isEmpty() ? View.VISIBLE : View.GONE
        flag 4 (0x5L): appData.isEmpty() ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}