package pw.idrug.connections.databinding;
import pw.idrug.connections.R;
import pw.idrug.connections.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TunnelDetailPeerBindingImpl extends TunnelDetailPeerBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.peer_title, 12);
        sViewsWithIds.put(R.id.public_key_label, 13);
        sViewsWithIds.put(R.id.transfer_label, 14);
        sViewsWithIds.put(R.id.latest_handshake_label, 15);
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public TunnelDetailPeerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private TunnelDetailPeerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[11]
            , (com.google.android.material.textview.MaterialTextView) bindings[12]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[10]
            );
        this.allowedIpsLabel.setTag(null);
        this.allowedIpsText.setTag(null);
        this.endpointLabel.setTag(null);
        this.endpointText.setTag(null);
        this.latestHandshakeText.setTag(null);
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.persistentKeepaliveLabel.setTag(null);
        this.persistentKeepaliveText.setTag(null);
        this.preSharedKeyLabel.setTag(null);
        this.preSharedKeyText.setTag(null);
        this.publicKeyText.setTag(null);
        this.transferText.setTag(null);
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
            setItem((pw.idrug.connections.viewmodel.PeerProxy) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable pw.idrug.connections.viewmodel.PeerProxy Item) {
        updateRegistration(0, Item);
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeItem((pw.idrug.connections.viewmodel.PeerProxy) object, fieldId);
        }
        return false;
    }
    private boolean onChangeItem(pw.idrug.connections.viewmodel.PeerProxy Item, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.publicKeyBase64) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.preSharedKey) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.allowedIps) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.endpoint) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.persistentKeepalive) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
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
        java.lang.String itemEndpoint = null;
        pw.idrug.connections.viewmodel.PeerProxy item = mItem;
        java.lang.String itemPublicKeyBase64 = null;
        boolean itemPreSharedKeyIsEmpty = false;
        boolean itemPersistentKeepaliveIsEmpty = false;
        int itemPreSharedKeyIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean itemEndpointIsEmpty = false;
        java.lang.String persistentKeepaliveTextAndroidPluralsPersistentKeepaliveSecondsUnitItemPersistentKeepaliveSecondsItemPersistentKeepaliveSeconds = null;
        java.lang.String itemPreSharedKey = null;
        int itemAllowedIpsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String itemPersistentKeepalive = null;
        java.lang.String itemAllowedIps = null;
        boolean itemAllowedIpsIsEmpty = false;
        int itemPersistentKeepaliveIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int itemEndpointIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int itemPersistentKeepaliveSeconds = 0;

        if ((dirtyFlags & 0x7fL) != 0) {


            if ((dirtyFlags & 0x51L) != 0) {

                    if (item != null) {
                        // read item.endpoint
                        itemEndpoint = item.getEndpoint();
                    }


                    if (itemEndpoint != null) {
                        // read item.endpoint.isEmpty()
                        itemEndpointIsEmpty = itemEndpoint.isEmpty();
                    }
                if((dirtyFlags & 0x51L) != 0) {
                    if(itemEndpointIsEmpty) {
                            dirtyFlags |= 0x4000L;
                    }
                    else {
                            dirtyFlags |= 0x2000L;
                    }
                }


                    // read item.endpoint.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                    itemEndpointIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((itemEndpointIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
            if ((dirtyFlags & 0x43L) != 0) {

                    if (item != null) {
                        // read item.publicKeyBase64
                        itemPublicKeyBase64 = item.getPublicKeyBase64();
                    }
            }
            if ((dirtyFlags & 0x45L) != 0) {

                    if (item != null) {
                        // read item.preSharedKey
                        itemPreSharedKey = item.getPreSharedKey();
                    }


                    if (itemPreSharedKey != null) {
                        // read item.preSharedKey.isEmpty()
                        itemPreSharedKeyIsEmpty = itemPreSharedKey.isEmpty();
                    }
                if((dirtyFlags & 0x45L) != 0) {
                    if(itemPreSharedKeyIsEmpty) {
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x80L;
                    }
                }


                    // read item.preSharedKey.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                    itemPreSharedKeyIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((itemPreSharedKeyIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
            if ((dirtyFlags & 0x61L) != 0) {

                    if (item != null) {
                        // read item.persistentKeepalive
                        itemPersistentKeepalive = item.getPersistentKeepalive();
                    }


                    if (itemPersistentKeepalive != null) {
                        // read item.persistentKeepalive.isEmpty()
                        itemPersistentKeepaliveIsEmpty = itemPersistentKeepalive.isEmpty();
                    }
                if((dirtyFlags & 0x61L) != 0) {
                    if(itemPersistentKeepaliveIsEmpty) {
                            dirtyFlags |= 0x1000L;
                    }
                    else {
                            dirtyFlags |= 0x800L;
                    }
                }


                    // read item.persistentKeepalive.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                    itemPersistentKeepaliveIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((itemPersistentKeepaliveIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
            if ((dirtyFlags & 0x49L) != 0) {

                    if (item != null) {
                        // read item.allowedIps
                        itemAllowedIps = item.getAllowedIps();
                    }


                    if (itemAllowedIps != null) {
                        // read item.allowedIps.isEmpty()
                        itemAllowedIpsIsEmpty = itemAllowedIps.isEmpty();
                    }
                if((dirtyFlags & 0x49L) != 0) {
                    if(itemAllowedIpsIsEmpty) {
                            dirtyFlags |= 0x400L;
                    }
                    else {
                            dirtyFlags |= 0x200L;
                    }
                }


                    // read item.allowedIps.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                    itemAllowedIpsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((itemAllowedIpsIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
            }
            if ((dirtyFlags & 0x41L) != 0) {

                    if (item != null) {
                        // read item.persistentKeepaliveSeconds
                        itemPersistentKeepaliveSeconds = item.getPersistentKeepaliveSeconds();
                    }


                    // read @android:plurals/persistent_keepalive_seconds_unit
                    persistentKeepaliveTextAndroidPluralsPersistentKeepaliveSecondsUnitItemPersistentKeepaliveSecondsItemPersistentKeepaliveSeconds = persistentKeepaliveText.getResources().getQuantityString(R.plurals.persistent_keepalive_seconds_unit, itemPersistentKeepaliveSeconds, itemPersistentKeepaliveSeconds);
                    // read @android:plurals/persistent_keepalive_seconds_unit
                    persistentKeepaliveTextAndroidPluralsPersistentKeepaliveSecondsUnitItemPersistentKeepaliveSecondsItemPersistentKeepaliveSeconds = persistentKeepaliveText.getResources().getQuantityString(R.plurals.persistent_keepalive_seconds_unit, itemPersistentKeepaliveSeconds, itemPersistentKeepaliveSeconds);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x49L) != 0) {
            // api target 1

            this.allowedIpsLabel.setVisibility(itemAllowedIpsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.allowedIpsText, itemAllowedIps);
            this.allowedIpsText.setVisibility(itemAllowedIpsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x40L) != 0) {
            // api target 1

            this.allowedIpsText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.endpointText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.latestHandshakeText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.persistentKeepaliveText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.publicKeyText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.transferText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
        }
        if ((dirtyFlags & 0x51L) != 0) {
            // api target 1

            this.endpointLabel.setVisibility(itemEndpointIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.endpointText, itemEndpoint);
            this.endpointText.setVisibility(itemEndpointIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x61L) != 0) {
            // api target 1

            this.persistentKeepaliveLabel.setVisibility(itemPersistentKeepaliveIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.persistentKeepaliveText.setVisibility(itemPersistentKeepaliveIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x41L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.persistentKeepaliveText, persistentKeepaliveTextAndroidPluralsPersistentKeepaliveSecondsUnitItemPersistentKeepaliveSecondsItemPersistentKeepaliveSeconds);
        }
        if ((dirtyFlags & 0x45L) != 0) {
            // api target 1

            this.preSharedKeyLabel.setVisibility(itemPreSharedKeyIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.preSharedKeyText.setVisibility(itemPreSharedKeyIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x43L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.publicKeyText, itemPublicKeyBase64);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        @Override
        public void onClick(android.view.View arg0) {
            pw.idrug.connections.util.ClipboardUtils.copyTextView(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): item.publicKeyBase64
        flag 2 (0x3L): item.preSharedKey
        flag 3 (0x4L): item.allowedIps
        flag 4 (0x5L): item.endpoint
        flag 5 (0x6L): item.persistentKeepalive
        flag 6 (0x7L): null
        flag 7 (0x8L): item.preSharedKey.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 8 (0x9L): item.preSharedKey.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 9 (0xaL): item.allowedIps.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 10 (0xbL): item.allowedIps.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 11 (0xcL): item.persistentKeepalive.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 12 (0xdL): item.persistentKeepalive.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 13 (0xeL): item.endpoint.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 14 (0xfL): item.endpoint.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
    flag mapping end*/
    //end
}