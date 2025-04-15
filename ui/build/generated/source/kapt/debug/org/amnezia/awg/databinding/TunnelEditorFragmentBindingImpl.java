package org.amnezia.awg.databinding;
import org.amnezia.awg.R;
import org.amnezia.awg.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TunnelEditorFragmentBindingImpl extends TunnelEditorFragmentBinding implements org.amnezia.awg.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.interface_title, 20);
        sViewsWithIds.put(R.id.interface_name_layout, 21);
        sViewsWithIds.put(R.id.private_key_text_layout, 22);
        sViewsWithIds.put(R.id.public_key_label_layout, 23);
        sViewsWithIds.put(R.id.addresses_label_layout, 24);
        sViewsWithIds.put(R.id.listen_port_label_layout, 25);
        sViewsWithIds.put(R.id.dns_servers_label_layout, 26);
        sViewsWithIds.put(R.id.mtu_label_layout, 27);
        sViewsWithIds.put(R.id.junk_packet_count_layout, 28);
        sViewsWithIds.put(R.id.junk_packet_min_size_layout, 29);
        sViewsWithIds.put(R.id.junk_packet_max_size_layout, 30);
        sViewsWithIds.put(R.id.init_packet_junk_size_layout, 31);
        sViewsWithIds.put(R.id.response_packet_junk_size_layout, 32);
        sViewsWithIds.put(R.id.init_packet_magic_header_layout, 33);
        sViewsWithIds.put(R.id.response_packet_magic_header_layout, 34);
        sViewsWithIds.put(R.id.underload_packet_magic_header_layout, 35);
        sViewsWithIds.put(R.id.transport_packet_magic_header_layout, 36);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    private androidx.databinding.ObservableList<org.amnezia.awg.viewmodel.PeerProxy> mOldConfigPeers;
    private int mOldAndroidLayoutTunnelEditorPeer;
    private org.amnezia.awg.fragment.TunnelEditorFragment mOldFragment;
    // listeners
    private OnClickListenerImpl mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mFragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener;
    private OnFocusChangeListenerImpl mFragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener;
    private OnClickListenerImpl2 mFragmentOnKeyClickAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener addressesLabelTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.addresses
            //         is config.interface.setAddresses((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(addressesLabelText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.addresses
            java.lang.String configInterfaceAddresses = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setAddresses(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener dnsServersTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.dnsServers
            //         is config.interface.setDnsServers((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(dnsServersText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.dnsServers
            java.lang.String configInterfaceDnsServers = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setDnsServers(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener initPacketJunkSizeTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.initPacketJunkSize
            //         is config.interface.setInitPacketJunkSize((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(initPacketJunkSizeText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.initPacketJunkSize
            java.lang.String configInterfaceInitPacketJunkSize = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setInitPacketJunkSize(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener initPacketMagicHeaderTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.initPacketMagicHeader
            //         is config.interface.setInitPacketMagicHeader((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(initPacketMagicHeaderText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config.interface.initPacketMagicHeader
            java.lang.String configInterfaceInitPacketMagicHeader = null;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setInitPacketMagicHeader(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener interfaceNameTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of name
            //         is org.amnezia.awg.databinding.TunnelEditorFragmentBindingImpl.this.setName(callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(interfaceNameText);
            // localize variables for thread safety
            // name
            java.lang.String name = mName;


            if ((org.amnezia.awg.databinding.TunnelEditorFragmentBindingImpl.this) != (null)) {



                org.amnezia.awg.databinding.TunnelEditorFragmentBindingImpl.this.setName(callbackArg_0);
            }
        }
    };
    private androidx.databinding.InverseBindingListener junkPacketCountTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.junkPacketCount
            //         is config.interface.setJunkPacketCount((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(junkPacketCountText);
            // localize variables for thread safety
            // config.interface.junkPacketCount
            java.lang.String configInterfaceJunkPacketCount = null;
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setJunkPacketCount(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener junkPacketMaxSizeTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.junkPacketMaxSize
            //         is config.interface.setJunkPacketMaxSize((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(junkPacketMaxSizeText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;
            // config.interface.junkPacketMaxSize
            java.lang.String configInterfaceJunkPacketMaxSize = null;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setJunkPacketMaxSize(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener junkPacketMinSizeTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.junkPacketMinSize
            //         is config.interface.setJunkPacketMinSize((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(junkPacketMinSizeText);
            // localize variables for thread safety
            // config.interface.junkPacketMinSize
            java.lang.String configInterfaceJunkPacketMinSize = null;
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setJunkPacketMinSize(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener listenPortTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.listenPort
            //         is config.interface.setListenPort((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(listenPortText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;
            // config.interface.listenPort
            java.lang.String configInterfaceListenPort = null;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setListenPort(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mtuTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.mtu
            //         is config.interface.setMtu((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mtuText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.mtu
            java.lang.String configInterfaceMtu = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setMtu(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener privateKeyTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.privateKey
            //         is config.interface.setPrivateKey((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(privateKeyText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.privateKey
            java.lang.String configInterfacePrivateKey = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setPrivateKey(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener responsePacketJunkSizeTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.responsePacketJunkSize
            //         is config.interface.setResponsePacketJunkSize((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(responsePacketJunkSizeText);
            // localize variables for thread safety
            // config.interface.responsePacketJunkSize
            java.lang.String configInterfaceResponsePacketJunkSize = null;
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setResponsePacketJunkSize(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener responsePacketMagicHeaderTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.responsePacketMagicHeader
            //         is config.interface.setResponsePacketMagicHeader((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(responsePacketMagicHeaderText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.responsePacketMagicHeader
            java.lang.String configInterfaceResponsePacketMagicHeader = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setResponsePacketMagicHeader(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener transportPacketMagicHeaderTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.transportPacketMagicHeader
            //         is config.interface.setTransportPacketMagicHeader((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(transportPacketMagicHeaderText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;
            // config.interface.transportPacketMagicHeader
            java.lang.String configInterfaceTransportPacketMagicHeader = null;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setTransportPacketMagicHeader(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener underloadPacketMagicHeaderTextandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of config.interface.underloadPacketMagicHeader
            //         is config.interface.setUnderloadPacketMagicHeader((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(underloadPacketMagicHeaderText);
            // localize variables for thread safety
            // config != null
            boolean configJavaLangObjectNull = false;
            // config.interface
            org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
            // config.interface.underloadPacketMagicHeader
            java.lang.String configInterfaceUnderloadPacketMagicHeader = null;
            // config.interface != null
            boolean configInterfaceJavaLangObjectNull = false;
            // config
            org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



            configJavaLangObjectNull = (config) != (null);
            if (configJavaLangObjectNull) {


                configInterface = config.getInterface();

                configInterfaceJavaLangObjectNull = (configInterface) != (null);
                if (configInterfaceJavaLangObjectNull) {




                    configInterface.setUnderloadPacketMagicHeader(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public TunnelEditorFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds));
    }
    private TunnelEditorFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (com.google.android.material.button.MaterialButton) bindings[19]
            , (com.google.android.material.textfield.TextInputLayout) bindings[24]
            , (com.google.android.material.textfield.TextInputEditText) bindings[4]
            , (com.google.android.material.textfield.TextInputLayout) bindings[26]
            , (com.google.android.material.textfield.TextInputEditText) bindings[6]
            , (com.google.android.material.textfield.TextInputLayout) bindings[31]
            , (com.google.android.material.textfield.TextInputEditText) bindings[11]
            , (com.google.android.material.textfield.TextInputLayout) bindings[33]
            , (com.google.android.material.textfield.TextInputEditText) bindings[13]
            , (com.google.android.material.textfield.TextInputLayout) bindings[21]
            , (com.google.android.material.textfield.TextInputEditText) bindings[1]
            , (com.google.android.material.textview.MaterialTextView) bindings[20]
            , (com.google.android.material.textfield.TextInputLayout) bindings[28]
            , (com.google.android.material.textfield.TextInputEditText) bindings[8]
            , (com.google.android.material.textfield.TextInputLayout) bindings[30]
            , (com.google.android.material.textfield.TextInputEditText) bindings[10]
            , (com.google.android.material.textfield.TextInputLayout) bindings[29]
            , (com.google.android.material.textfield.TextInputEditText) bindings[9]
            , (com.google.android.material.textfield.TextInputLayout) bindings[25]
            , (com.google.android.material.textfield.TextInputEditText) bindings[5]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (com.google.android.material.textfield.TextInputLayout) bindings[27]
            , (com.google.android.material.textfield.TextInputEditText) bindings[7]
            , (android.widget.LinearLayout) bindings[18]
            , (com.google.android.material.textfield.TextInputEditText) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[22]
            , (com.google.android.material.textfield.TextInputLayout) bindings[23]
            , (com.google.android.material.textfield.TextInputEditText) bindings[3]
            , (com.google.android.material.textfield.TextInputLayout) bindings[32]
            , (com.google.android.material.textfield.TextInputEditText) bindings[12]
            , (com.google.android.material.textfield.TextInputLayout) bindings[34]
            , (com.google.android.material.textfield.TextInputEditText) bindings[14]
            , (com.google.android.material.button.MaterialButton) bindings[17]
            , (com.google.android.material.textfield.TextInputLayout) bindings[36]
            , (com.google.android.material.textfield.TextInputEditText) bindings[16]
            , (com.google.android.material.textfield.TextInputLayout) bindings[35]
            , (com.google.android.material.textfield.TextInputEditText) bindings[15]
            );
        this.addPeerButton.setTag(null);
        this.addressesLabelText.setTag(null);
        this.dnsServersText.setTag(null);
        this.initPacketJunkSizeText.setTag(null);
        this.initPacketMagicHeaderText.setTag(null);
        this.interfaceNameText.setTag(null);
        this.junkPacketCountText.setTag(null);
        this.junkPacketMaxSizeText.setTag(null);
        this.junkPacketMinSizeText.setTag(null);
        this.listenPortText.setTag(null);
        this.mainContainer.setTag(null);
        this.mtuText.setTag(null);
        this.peersLayout.setTag(null);
        this.privateKeyText.setTag(null);
        this.publicKeyText.setTag(null);
        this.responsePacketJunkSizeText.setTag(null);
        this.responsePacketMagicHeaderText.setTag(null);
        this.setExcludedApplications.setTag(null);
        this.transportPacketMagicHeaderText.setTag(null);
        this.underloadPacketMagicHeaderText.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new org.amnezia.awg.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x400000L;
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
        if (BR.config == variableId) {
            setConfig((org.amnezia.awg.viewmodel.ConfigProxy) variable);
        }
        else if (BR.fragment == variableId) {
            setFragment((org.amnezia.awg.fragment.TunnelEditorFragment) variable);
        }
        else if (BR.name == variableId) {
            setName((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setConfig(@Nullable org.amnezia.awg.viewmodel.ConfigProxy Config) {
        this.mConfig = Config;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.config);
        super.requestRebind();
    }
    public void setFragment(@Nullable org.amnezia.awg.fragment.TunnelEditorFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setName(@Nullable java.lang.String Name) {
        this.mName = Name;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.name);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeConfigInterfaceExcludedApplications((androidx.databinding.ObservableList<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeConfigInterface((org.amnezia.awg.viewmodel.InterfaceProxy) object, fieldId);
            case 2 :
                return onChangeConfigPeers((androidx.databinding.ObservableList<org.amnezia.awg.viewmodel.PeerProxy>) object, fieldId);
            case 3 :
                return onChangeConfigInterfaceIncludedApplications((androidx.databinding.ObservableList<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeConfigInterfaceExcludedApplications(androidx.databinding.ObservableList<java.lang.String> ConfigInterfaceExcludedApplications, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConfigInterface(org.amnezia.awg.viewmodel.InterfaceProxy ConfigInterface, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.privateKey) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.publicKey) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        else if (fieldId == BR.addresses) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        else if (fieldId == BR.listenPort) {
            synchronized(this) {
                    mDirtyFlags |= 0x400L;
            }
            return true;
        }
        else if (fieldId == BR.dnsServers) {
            synchronized(this) {
                    mDirtyFlags |= 0x800L;
            }
            return true;
        }
        else if (fieldId == BR.mtu) {
            synchronized(this) {
                    mDirtyFlags |= 0x1000L;
            }
            return true;
        }
        else if (fieldId == BR.junkPacketCount) {
            synchronized(this) {
                    mDirtyFlags |= 0x2000L;
            }
            return true;
        }
        else if (fieldId == BR.junkPacketMinSize) {
            synchronized(this) {
                    mDirtyFlags |= 0x4000L;
            }
            return true;
        }
        else if (fieldId == BR.junkPacketMaxSize) {
            synchronized(this) {
                    mDirtyFlags |= 0x8000L;
            }
            return true;
        }
        else if (fieldId == BR.initPacketJunkSize) {
            synchronized(this) {
                    mDirtyFlags |= 0x10000L;
            }
            return true;
        }
        else if (fieldId == BR.responsePacketJunkSize) {
            synchronized(this) {
                    mDirtyFlags |= 0x20000L;
            }
            return true;
        }
        else if (fieldId == BR.initPacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x40000L;
            }
            return true;
        }
        else if (fieldId == BR.responsePacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x80000L;
            }
            return true;
        }
        else if (fieldId == BR.underloadPacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x100000L;
            }
            return true;
        }
        else if (fieldId == BR.transportPacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x200000L;
            }
            return true;
        }
        else if (fieldId == BR.includedApplications) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.excludedApplications) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConfigPeers(androidx.databinding.ObservableList<org.amnezia.awg.viewmodel.PeerProxy> ConfigPeers, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeConfigInterfaceIncludedApplications(androidx.databinding.ObservableList<java.lang.String> ConfigInterfaceIncludedApplications, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        androidx.databinding.ObservableList<java.lang.String> configInterfaceExcludedApplications = null;
        java.lang.String configInterfaceJunkPacketMaxSize = null;
        java.lang.String configInterfaceUnderloadPacketMagicHeader = null;
        org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;
        java.lang.String configInterfacePrivateKey = null;
        boolean configInterfaceIncludedApplicationsSizeInt0 = false;
        org.amnezia.awg.viewmodel.InterfaceProxy configInterface = null;
        java.lang.String configInterfaceIncludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeSetExcludedApplicationsAndroidStringAllApplications = null;
        android.view.View.OnClickListener fragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener = null;
        androidx.databinding.ObservableList<org.amnezia.awg.viewmodel.PeerProxy> configPeers = null;
        androidx.databinding.ObservableList<java.lang.String> configInterfaceIncludedApplications = null;
        java.lang.String configInterfaceDnsServers = null;
        java.lang.String configInterfaceInitPacketMagicHeader = null;
        java.lang.String configInterfaceTransportPacketMagicHeader = null;
        org.amnezia.awg.fragment.TunnelEditorFragment fragment = mFragment;
        java.lang.String configInterfaceMtu = null;
        java.lang.String configInterfaceResponsePacketJunkSize = null;
        java.lang.String configInterfaceResponsePacketMagicHeader = null;
        android.view.View.OnFocusChangeListener fragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener = null;
        java.lang.String setExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = null;
        java.lang.String name = mName;
        int configInterfaceExcludedApplicationsSize = 0;
        java.lang.String configInterfaceExcludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeSetExcludedApplicationsAndroidStringAllApplications = null;
        java.lang.String configInterfaceJunkPacketCount = null;
        java.lang.String configInterfacePublicKey = null;
        int configInterfaceIncludedApplicationsSize = 0;
        boolean configInterfaceExcludedApplicationsSizeInt0 = false;
        java.lang.String configInterfaceJunkPacketMinSize = null;
        java.lang.String setExcludedApplicationsAndroidPluralsSetIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        android.view.View.OnClickListener fragmentOnKeyClickAndroidViewViewOnClickListener = null;
        java.lang.String configInterfaceListenPort = null;
        java.lang.String configInterfaceAddresses = null;
        java.lang.String configInterfaceInitPacketJunkSize = null;

        if ((dirtyFlags & 0x7fffbfL) != 0) {


            if ((dirtyFlags & 0x7fff9bL) != 0) {

                    if (config != null) {
                        // read config.interface
                        configInterface = config.getInterface();
                    }
                    updateRegistration(1, configInterface);

                if ((dirtyFlags & 0x408012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.junkPacketMaxSize
                            configInterfaceJunkPacketMaxSize = configInterface.getJunkPacketMaxSize();
                        }
                }
                if ((dirtyFlags & 0x500012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.underloadPacketMagicHeader
                            configInterfaceUnderloadPacketMagicHeader = configInterface.getUnderloadPacketMagicHeader();
                        }
                }
                if ((dirtyFlags & 0x400092L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.privateKey
                            configInterfacePrivateKey = configInterface.getPrivateKey();
                        }
                }
                if ((dirtyFlags & 0x40001bL) != 0) {

                        if (configInterface != null) {
                            // read config.interface.includedApplications
                            configInterfaceIncludedApplications = configInterface.getIncludedApplications();
                        }
                        updateRegistration(3, configInterfaceIncludedApplications);


                        if (configInterfaceIncludedApplications != null) {
                            // read config.interface.includedApplications.size
                            configInterfaceIncludedApplicationsSize = configInterfaceIncludedApplications.size();
                        }


                        // read config.interface.includedApplications.size > 0
                        configInterfaceIncludedApplicationsSizeInt0 = (configInterfaceIncludedApplicationsSize) > (0);
                    if((dirtyFlags & 0x40001bL) != 0) {
                        if(configInterfaceIncludedApplicationsSizeInt0) {
                                dirtyFlags |= 0x1000000L;
                        }
                        else {
                                dirtyFlags |= 0x800000L;
                        }
                    }
                }
                if ((dirtyFlags & 0x400812L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.dnsServers
                            configInterfaceDnsServers = configInterface.getDnsServers();
                        }
                }
                if ((dirtyFlags & 0x440012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.initPacketMagicHeader
                            configInterfaceInitPacketMagicHeader = configInterface.getInitPacketMagicHeader();
                        }
                }
                if ((dirtyFlags & 0x600012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.transportPacketMagicHeader
                            configInterfaceTransportPacketMagicHeader = configInterface.getTransportPacketMagicHeader();
                        }
                }
                if ((dirtyFlags & 0x401012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.mtu
                            configInterfaceMtu = configInterface.getMtu();
                        }
                }
                if ((dirtyFlags & 0x420012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.responsePacketJunkSize
                            configInterfaceResponsePacketJunkSize = configInterface.getResponsePacketJunkSize();
                        }
                }
                if ((dirtyFlags & 0x480012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.responsePacketMagicHeader
                            configInterfaceResponsePacketMagicHeader = configInterface.getResponsePacketMagicHeader();
                        }
                }
                if ((dirtyFlags & 0x402012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.junkPacketCount
                            configInterfaceJunkPacketCount = configInterface.getJunkPacketCount();
                        }
                }
                if ((dirtyFlags & 0x400112L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.publicKey
                            configInterfacePublicKey = configInterface.getPublicKey();
                        }
                }
                if ((dirtyFlags & 0x404012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.junkPacketMinSize
                            configInterfaceJunkPacketMinSize = configInterface.getJunkPacketMinSize();
                        }
                }
                if ((dirtyFlags & 0x400412L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.listenPort
                            configInterfaceListenPort = configInterface.getListenPort();
                        }
                }
                if ((dirtyFlags & 0x400212L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.addresses
                            configInterfaceAddresses = configInterface.getAddresses();
                        }
                }
                if ((dirtyFlags & 0x410012L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.initPacketJunkSize
                            configInterfaceInitPacketJunkSize = configInterface.getInitPacketJunkSize();
                        }
                }
            }
            if ((dirtyFlags & 0x400034L) != 0) {

                    if (config != null) {
                        // read config.peers
                        configPeers = config.getPeers();
                    }
                    updateRegistration(2, configPeers);
            }
        }
        if ((dirtyFlags & 0x400034L) != 0) {


            if ((dirtyFlags & 0x400020L) != 0) {

                    if (fragment != null) {
                        // read fragment::onRequestSetExcludedIncludedApplications
                        fragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener = (((mFragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener == null) ? (mFragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener = new OnClickListenerImpl1()) : mFragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener).setValue(fragment));
                        // read fragment::onKeyFocusChange
                        fragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener = (((mFragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener == null) ? (mFragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener = new OnFocusChangeListenerImpl()) : mFragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener).setValue(fragment));
                        // read fragment::onKeyClick
                        fragmentOnKeyClickAndroidViewViewOnClickListener = (((mFragmentOnKeyClickAndroidViewViewOnClickListener == null) ? (mFragmentOnKeyClickAndroidViewViewOnClickListener = new OnClickListenerImpl2()) : mFragmentOnKeyClickAndroidViewViewOnClickListener).setValue(fragment));
                    }
            }
        }
        if ((dirtyFlags & 0x400040L) != 0) {
        }
        // batch finished

        if ((dirtyFlags & 0x800000L) != 0) {



                if (config != null) {
                    // read config.interface
                    configInterface = config.getInterface();
                }
                updateRegistration(1, configInterface);


                if (configInterface != null) {
                    // read config.interface.excludedApplications
                    configInterfaceExcludedApplications = configInterface.getExcludedApplications();
                }
                updateRegistration(0, configInterfaceExcludedApplications);


                if (configInterfaceExcludedApplications != null) {
                    // read config.interface.excludedApplications.size
                    configInterfaceExcludedApplicationsSize = configInterfaceExcludedApplications.size();
                }


                // read config.interface.excludedApplications.size > 0
                configInterfaceExcludedApplicationsSizeInt0 = (configInterfaceExcludedApplicationsSize) > (0);
            if((dirtyFlags & 0x800000L) != 0) {
                if(configInterfaceExcludedApplicationsSizeInt0) {
                        dirtyFlags |= 0x4000000L;
                }
                else {
                        dirtyFlags |= 0x2000000L;
                }
            }
        }
        if ((dirtyFlags & 0x1000000L) != 0) {

                // read @android:plurals/set_included_applications
                setExcludedApplicationsAndroidPluralsSetIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = setExcludedApplications.getResources().getQuantityString(R.plurals.set_included_applications, configInterfaceIncludedApplicationsSize, configInterfaceIncludedApplicationsSize);
        }
        // batch finished

        if ((dirtyFlags & 0x4000000L) != 0) {

                // read @android:plurals/set_excluded_applications
                setExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = setExcludedApplications.getResources().getQuantityString(R.plurals.set_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
        }

        if ((dirtyFlags & 0x800000L) != 0) {

                // read config.interface.excludedApplications.size > 0 ? @android:plurals/set_excluded_applications : @android:string/all_applications
                configInterfaceExcludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeSetExcludedApplicationsAndroidStringAllApplications = ((configInterfaceExcludedApplicationsSizeInt0) ? (setExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize) : (setExcludedApplications.getResources().getString(R.string.all_applications)));
        }

        if ((dirtyFlags & 0x40001bL) != 0) {

                // read config.interface.includedApplications.size > 0 ? @android:plurals/set_included_applications : config.interface.excludedApplications.size > 0 ? @android:plurals/set_excluded_applications : @android:string/all_applications
                configInterfaceIncludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeSetExcludedApplicationsAndroidStringAllApplications = ((configInterfaceIncludedApplicationsSizeInt0) ? (setExcludedApplicationsAndroidPluralsSetIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize) : (configInterfaceExcludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeSetExcludedApplicationsAndroidStringAllApplications));
        }
        // batch finished
        if ((dirtyFlags & 0x400000L) != 0) {
            // api target 1

            this.addPeerButton.setOnClickListener(mCallback2);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.addressesLabelText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, addressesLabelTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.dnsServersText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, dnsServersTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.initPacketJunkSizeText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, initPacketJunkSizeTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.initPacketMagicHeaderText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, initPacketMagicHeaderTextandroidTextAttrChanged);
            org.amnezia.awg.databinding.BindingAdapters.setFilter(this.interfaceNameText, org.amnezia.awg.widget.NameInputFilter.newInstance());
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.interfaceNameText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, interfaceNameTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.junkPacketCountText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, junkPacketCountTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.junkPacketMaxSizeText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, junkPacketMaxSizeTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.junkPacketMinSizeText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, junkPacketMinSizeTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.listenPortText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, listenPortTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mtuText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mtuTextandroidTextAttrChanged);
            org.amnezia.awg.databinding.BindingAdapters.setFilter(this.privateKeyText, org.amnezia.awg.widget.KeyInputFilter.newInstance());
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.privateKeyText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, privateKeyTextandroidTextAttrChanged);
            this.publicKeyText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.responsePacketJunkSizeText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, responsePacketJunkSizeTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.responsePacketMagicHeaderText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, responsePacketMagicHeaderTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.transportPacketMagicHeaderText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, transportPacketMagicHeaderTextandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.underloadPacketMagicHeaderText, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, underloadPacketMagicHeaderTextandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x400212L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.addressesLabelText, configInterfaceAddresses);
        }
        if ((dirtyFlags & 0x400812L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.dnsServersText, configInterfaceDnsServers);
        }
        if ((dirtyFlags & 0x410012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.initPacketJunkSizeText, configInterfaceInitPacketJunkSize);
        }
        if ((dirtyFlags & 0x440012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.initPacketMagicHeaderText, configInterfaceInitPacketMagicHeader);
        }
        if ((dirtyFlags & 0x400040L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.interfaceNameText, name);
        }
        if ((dirtyFlags & 0x402012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.junkPacketCountText, configInterfaceJunkPacketCount);
        }
        if ((dirtyFlags & 0x408012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.junkPacketMaxSizeText, configInterfaceJunkPacketMaxSize);
        }
        if ((dirtyFlags & 0x404012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.junkPacketMinSizeText, configInterfaceJunkPacketMinSize);
        }
        if ((dirtyFlags & 0x400412L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.listenPortText, configInterfaceListenPort);
        }
        if ((dirtyFlags & 0x401012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mtuText, configInterfaceMtu);
        }
        if ((dirtyFlags & 0x400034L) != 0) {
            // api target 1

            org.amnezia.awg.databinding.BindingAdapters.setItems(this.peersLayout, this.mOldConfigPeers, this.mOldAndroidLayoutTunnelEditorPeer, this.mOldFragment, configPeers, R.layout.tunnel_editor_peer, fragment);
        }
        if ((dirtyFlags & 0x400020L) != 0) {
            // api target 1

            this.privateKeyText.setOnClickListener(fragmentOnKeyClickAndroidViewViewOnClickListener);
            org.amnezia.awg.databinding.BindingAdapters.setOnFocusChange(this.privateKeyText, fragmentOnKeyFocusChangeAndroidViewViewOnFocusChangeListener);
            this.setExcludedApplications.setOnClickListener(fragmentOnRequestSetExcludedIncludedApplicationsAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0x400092L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.privateKeyText, configInterfacePrivateKey);
        }
        if ((dirtyFlags & 0x400112L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.publicKeyText, configInterfacePublicKey);
        }
        if ((dirtyFlags & 0x420012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.responsePacketJunkSizeText, configInterfaceResponsePacketJunkSize);
        }
        if ((dirtyFlags & 0x480012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.responsePacketMagicHeaderText, configInterfaceResponsePacketMagicHeader);
        }
        if ((dirtyFlags & 0x40001bL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.setExcludedApplications, configInterfaceIncludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeInt0SetExcludedApplicationsAndroidPluralsSetExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeSetExcludedApplicationsAndroidStringAllApplications);
        }
        if ((dirtyFlags & 0x600012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transportPacketMagicHeaderText, configInterfaceTransportPacketMagicHeader);
        }
        if ((dirtyFlags & 0x500012L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.underloadPacketMagicHeaderText, configInterfaceUnderloadPacketMagicHeader);
        }
        if ((dirtyFlags & 0x400034L) != 0) {
            this.mOldConfigPeers = configPeers;
            this.mOldAndroidLayoutTunnelEditorPeer = R.layout.tunnel_editor_peer;
            this.mOldFragment = fragment;
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        @Override
        public void onClick(android.view.View arg0) {
            org.amnezia.awg.util.ClipboardUtils.copyTextView(arg0); 
        }
    }
    public static class OnClickListenerImpl1 implements android.view.View.OnClickListener{
        private org.amnezia.awg.fragment.TunnelEditorFragment value;
        public OnClickListenerImpl1 setValue(org.amnezia.awg.fragment.TunnelEditorFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onRequestSetExcludedIncludedApplications(arg0); 
        }
    }
    public static class OnFocusChangeListenerImpl implements android.view.View.OnFocusChangeListener{
        private org.amnezia.awg.fragment.TunnelEditorFragment value;
        public OnFocusChangeListenerImpl setValue(org.amnezia.awg.fragment.TunnelEditorFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onFocusChange(android.view.View arg0, boolean arg1) {
            this.value.onKeyFocusChange(arg0, arg1); 
        }
    }
    public static class OnClickListenerImpl2 implements android.view.View.OnClickListener{
        private org.amnezia.awg.fragment.TunnelEditorFragment value;
        public OnClickListenerImpl2 setValue(org.amnezia.awg.fragment.TunnelEditorFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onKeyClick(arg0); 
        }
    }
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // config.addPeer()
        org.amnezia.awg.viewmodel.PeerProxy configAddPeer = null;
        // config != null
        boolean configJavaLangObjectNull = false;
        // config
        org.amnezia.awg.viewmodel.ConfigProxy config = mConfig;



        configJavaLangObjectNull = (config) != (null);
        if (configJavaLangObjectNull) {


            configAddPeer = config.addPeer();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): config.interface.excludedApplications
        flag 1 (0x2L): config.interface
        flag 2 (0x3L): config.peers
        flag 3 (0x4L): config.interface.includedApplications
        flag 4 (0x5L): config
        flag 5 (0x6L): fragment
        flag 6 (0x7L): name
        flag 7 (0x8L): config.interface.privateKey
        flag 8 (0x9L): config.interface.publicKey
        flag 9 (0xaL): config.interface.addresses
        flag 10 (0xbL): config.interface.listenPort
        flag 11 (0xcL): config.interface.dnsServers
        flag 12 (0xdL): config.interface.mtu
        flag 13 (0xeL): config.interface.junkPacketCount
        flag 14 (0xfL): config.interface.junkPacketMinSize
        flag 15 (0x10L): config.interface.junkPacketMaxSize
        flag 16 (0x11L): config.interface.initPacketJunkSize
        flag 17 (0x12L): config.interface.responsePacketJunkSize
        flag 18 (0x13L): config.interface.initPacketMagicHeader
        flag 19 (0x14L): config.interface.responsePacketMagicHeader
        flag 20 (0x15L): config.interface.underloadPacketMagicHeader
        flag 21 (0x16L): config.interface.transportPacketMagicHeader
        flag 22 (0x17L): null
        flag 23 (0x18L): config.interface.includedApplications.size > 0 ? @android:plurals/set_included_applications : config.interface.excludedApplications.size > 0 ? @android:plurals/set_excluded_applications : @android:string/all_applications
        flag 24 (0x19L): config.interface.includedApplications.size > 0 ? @android:plurals/set_included_applications : config.interface.excludedApplications.size > 0 ? @android:plurals/set_excluded_applications : @android:string/all_applications
        flag 25 (0x1aL): config.interface.excludedApplications.size > 0 ? @android:plurals/set_excluded_applications : @android:string/all_applications
        flag 26 (0x1bL): config.interface.excludedApplications.size > 0 ? @android:plurals/set_excluded_applications : @android:string/all_applications
    flag mapping end*/
    //end
}