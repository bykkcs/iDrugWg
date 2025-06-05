package org.amnezia.awg.databinding;
import org.amnezia.awg.R;
import org.amnezia.awg.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class TunnelDetailFragmentBindingImpl extends TunnelDetailFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tunnel_detail_card, 34);
        sViewsWithIds.put(R.id.interface_title, 35);
        sViewsWithIds.put(R.id.interface_name_label, 36);
        sViewsWithIds.put(R.id.public_key_label, 37);
        sViewsWithIds.put(R.id.listen_port_mtu_barrier, 38);
        sViewsWithIds.put(R.id.amnezia_barrier, 39);
        sViewsWithIds.put(R.id.applications_label, 40);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    private java.util.List<org.amnezia.awg.config.Peer> mOldConfigPeers;
    private int mOldAndroidLayoutTunnelDetailPeer;
    // listeners
    private OnBeforeCheckedChangeListenerImpl mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener;
    private OnClickListenerImpl mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public TunnelDetailFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 41, sIncludes, sViewsWithIds));
    }
    private TunnelDetailFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (androidx.constraintlayout.widget.Barrier) bindings[39]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[2]
            , (com.google.android.material.textview.MaterialTextView) bindings[35]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[10]
            , (androidx.constraintlayout.widget.Barrier) bindings[38]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.LinearLayout) bindings[33]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[31]
            , (com.google.android.material.card.MaterialCardView) bindings[34]
            , (org.amnezia.awg.widget.ToggleSwitch) bindings[1]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[29]
            );
        this.addressesLabel.setTag(null);
        this.addressesText.setTag(null);
        this.applicationsText.setTag(null);
        this.dnsSearchDomainsLabel.setTag(null);
        this.dnsSearchDomainsText.setTag(null);
        this.dnsServersLabel.setTag(null);
        this.dnsServersText.setTag(null);
        this.initPacketJunkSizeLabel.setTag(null);
        this.initPacketJunkSizeText.setTag(null);
        this.initPacketMagicHeaderLabel.setTag(null);
        this.initPacketMagicHeaderText.setTag(null);
        this.interfaceNameText.setTag(null);
        this.junkPacketCountLabel.setTag(null);
        this.junkPacketCountText.setTag(null);
        this.junkPacketMaxSizeLabel.setTag(null);
        this.junkPacketMaxSizeText.setTag(null);
        this.junkPacketMinSizeLabel.setTag(null);
        this.junkPacketMinSizeText.setTag(null);
        this.listenPortLabel.setTag(null);
        this.listenPortText.setTag(null);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.mtuLabel.setTag(null);
        this.mtuText.setTag(null);
        this.peersLayout.setTag(null);
        this.publicKeyText.setTag(null);
        this.responsePacketJunkSizeLabel.setTag(null);
        this.responsePacketJunkSizeText.setTag(null);
        this.responsePacketMagicHeaderLabel.setTag(null);
        this.responsePacketMagicHeaderText.setTag(null);
        this.transportPacketMagicHeaderLabel.setTag(null);
        this.transportPacketMagicHeaderText.setTag(null);
        this.tunnelSwitch.setTag(null);
        this.underloadPacketMagicHeaderLabel.setTag(null);
        this.underloadPacketMagicHeaderText.setTag(null);
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
        if (BR.config == variableId) {
            setConfig((org.amnezia.awg.config.Config) variable);
        }
        else if (BR.fragment == variableId) {
            setFragment((org.amnezia.awg.fragment.TunnelDetailFragment) variable);
        }
        else if (BR.tunnel == variableId) {
            setTunnel((org.amnezia.awg.model.ObservableTunnel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setConfig(@Nullable org.amnezia.awg.config.Config Config) {
        this.mConfig = Config;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.config);
        super.requestRebind();
    }
    public void setFragment(@Nullable org.amnezia.awg.fragment.TunnelDetailFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setTunnel(@Nullable org.amnezia.awg.model.ObservableTunnel Tunnel) {
        updateRegistration(0, Tunnel);
        this.mTunnel = Tunnel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.tunnel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeTunnel((org.amnezia.awg.model.ObservableTunnel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeTunnel(org.amnezia.awg.model.ObservableTunnel Tunnel, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.state) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.name) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
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
        java.util.Set<java.lang.String> configInterfaceExcludedApplications = null;
        boolean configInterfaceResponsePacketJunkSizeIsPresent = false;
        boolean configInterfaceListenPortIsPresent = false;
        java.util.Optional<java.lang.Integer> configInterfaceJunkPacketMaxSize = null;
        java.lang.String configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        int configInterfaceIncludedApplicationsSize = 0;
        boolean configInterfaceJunkPacketMaxSizeIsPresent = false;
        int configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.Long> configInterfaceUnderloadPacketMagicHeader = null;
        org.amnezia.awg.config.Config config = mConfig;
        boolean configInterfaceResponsePacketMagicHeaderIsPresent = false;
        org.amnezia.awg.crypto.KeyPair configInterfaceKeyPair = null;
        int configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceExcludedApplicationsSize = 0;
        org.amnezia.awg.widget.ToggleSwitch.OnBeforeCheckedChangeListener fragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener = null;
        int configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceUnderloadPacketMagicHeaderIsPresent = false;
        int configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        org.amnezia.awg.config.Interface configInterface = null;
        int configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        boolean configInterfaceInitPacketMagicHeaderIsPresent = false;
        boolean configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse = false;
        boolean configInterfaceMtuIsPresent = false;
        java.lang.String tunnelName = null;
        int configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.List<org.amnezia.awg.config.Peer> configPeers = null;
        boolean configInterfaceDnsSearchDomainsIsEmpty = false;
        java.util.Set<java.lang.String> configInterfaceIncludedApplications = null;
        java.util.Set<java.net.InetAddress> configInterfaceDnsServers = null;
        java.util.Optional<java.lang.Long> configInterfaceInitPacketMagicHeader = null;
        boolean configInterfaceExcludedApplicationsIsEmpty = false;
        boolean ConfigInterfaceListenPortIsPresent1 = false;
        int configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.Long> configInterfaceTransportPacketMagicHeader = null;
        boolean configInterfaceIncludedApplicationsIsEmpty = false;
        boolean configInterfaceAddressesIsEmpty = false;
        org.amnezia.awg.crypto.Key configInterfaceKeyPairPublicKey = null;
        int configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        org.amnezia.awg.fragment.TunnelDetailFragment fragment = mFragment;
        boolean configInterfaceInitPacketJunkSizeIsPresent = false;
        boolean tunnelStateStateUP = false;
        int configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = null;
        java.util.Optional<java.lang.Integer> configInterfaceMtu = null;
        org.amnezia.awg.backend.Tunnel.State tunnelState = null;
        boolean ConfigInterfaceResponsePacketMagicHeaderIsPresent1 = false;
        java.util.Optional<java.lang.Integer> configInterfaceResponsePacketJunkSize = null;
        java.util.Optional<java.lang.Long> configInterfaceResponsePacketMagicHeader = null;
        boolean configInterfaceJunkPacketCountIsPresent = false;
        boolean configInterfaceJunkPacketMinSizeIsPresent = false;
        boolean ConfigInterfaceJunkPacketMaxSizeIsPresent1 = false;
        boolean ConfigInterfaceResponsePacketJunkSizeIsPresent1 = false;
        boolean ConfigInterfaceInitPacketJunkSizeIsPresent1 = false;
        java.util.Set<java.lang.String> configInterfaceDnsSearchDomains = null;
        boolean ConfigInterfaceInitPacketMagicHeaderIsPresent1 = false;
        java.util.Optional<java.lang.Integer> configInterfaceJunkPacketCount = null;
        int configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1 = false;
        java.lang.String configInterfaceKeyPairPublicKeyToBase64 = null;
        int configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean ConfigInterfaceJunkPacketCountIsPresent1 = false;
        java.util.Optional<java.lang.Integer> configInterfaceJunkPacketMinSize = null;
        java.util.Optional<java.lang.Integer> configInterfaceListenPort = null;
        boolean configInterfaceTransportPacketMagicHeaderIsPresent = false;
        int configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean ConfigInterfaceJunkPacketMinSizeIsPresent1 = false;
        boolean configInterfaceDnsServersIsEmpty = false;
        boolean ConfigInterfaceTransportPacketMagicHeaderIsPresent1 = false;
        java.util.Set<org.amnezia.awg.config.InetNetwork> configInterfaceAddresses = null;
        java.util.Optional<java.lang.Integer> configInterfaceInitPacketJunkSize = null;
        boolean ConfigInterfaceMtuIsPresent1 = false;
        org.amnezia.awg.model.ObservableTunnel tunnel = mTunnel;

        if ((dirtyFlags & 0x22L) != 0) {



                if (config != null) {
                    // read config.interface
                    configInterface = config.getInterface();
                    // read config.peers
                    configPeers = config.getPeers();
                }


                if (configInterface != null) {
                    // read config.interface.junkPacketMaxSize
                    configInterfaceJunkPacketMaxSize = configInterface.getJunkPacketMaxSize();
                    // read config.interface.underloadPacketMagicHeader
                    configInterfaceUnderloadPacketMagicHeader = configInterface.getUnderloadPacketMagicHeader();
                    // read config.interface.keyPair
                    configInterfaceKeyPair = configInterface.getKeyPair();
                    // read config.interface.includedApplications
                    configInterfaceIncludedApplications = configInterface.getIncludedApplications();
                    // read config.interface.dnsServers
                    configInterfaceDnsServers = configInterface.getDnsServers();
                    // read config.interface.initPacketMagicHeader
                    configInterfaceInitPacketMagicHeader = configInterface.getInitPacketMagicHeader();
                    // read config.interface.transportPacketMagicHeader
                    configInterfaceTransportPacketMagicHeader = configInterface.getTransportPacketMagicHeader();
                    // read config.interface.mtu
                    configInterfaceMtu = configInterface.getMtu();
                    // read config.interface.responsePacketJunkSize
                    configInterfaceResponsePacketJunkSize = configInterface.getResponsePacketJunkSize();
                    // read config.interface.responsePacketMagicHeader
                    configInterfaceResponsePacketMagicHeader = configInterface.getResponsePacketMagicHeader();
                    // read config.interface.dnsSearchDomains
                    configInterfaceDnsSearchDomains = configInterface.getDnsSearchDomains();
                    // read config.interface.junkPacketCount
                    configInterfaceJunkPacketCount = configInterface.getJunkPacketCount();
                    // read config.interface.junkPacketMinSize
                    configInterfaceJunkPacketMinSize = configInterface.getJunkPacketMinSize();
                    // read config.interface.listenPort
                    configInterfaceListenPort = configInterface.getListenPort();
                    // read config.interface.addresses
                    configInterfaceAddresses = configInterface.getAddresses();
                    // read config.interface.initPacketJunkSize
                    configInterfaceInitPacketJunkSize = configInterface.getInitPacketJunkSize();
                }


                if (configInterfaceJunkPacketMaxSize != null) {
                    // read config.interface.junkPacketMaxSize.isPresent()
                    configInterfaceJunkPacketMaxSizeIsPresent = configInterfaceJunkPacketMaxSize.isPresent();
                }
                if (configInterfaceUnderloadPacketMagicHeader != null) {
                    // read config.interface.underloadPacketMagicHeader.isPresent()
                    configInterfaceUnderloadPacketMagicHeaderIsPresent = configInterfaceUnderloadPacketMagicHeader.isPresent();
                }
                if (configInterfaceKeyPair != null) {
                    // read config.interface.keyPair.publicKey
                    configInterfaceKeyPairPublicKey = configInterfaceKeyPair.getPublicKey();
                }
                if (configInterfaceIncludedApplications != null) {
                    // read config.interface.includedApplications.isEmpty()
                    configInterfaceIncludedApplicationsIsEmpty = configInterfaceIncludedApplications.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceIncludedApplicationsIsEmpty) {
                        dirtyFlags |= 0x80L;
                        dirtyFlags |= 0x2000000L;
                }
                else {
                        dirtyFlags |= 0x40L;
                        dirtyFlags |= 0x1000000L;
                }
            }
                if (configInterfaceDnsServers != null) {
                    // read config.interface.dnsServers.isEmpty()
                    configInterfaceDnsServersIsEmpty = configInterfaceDnsServers.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceDnsServersIsEmpty) {
                        dirtyFlags |= 0x800L;
                }
                else {
                        dirtyFlags |= 0x400L;
                }
            }
                if (configInterfaceInitPacketMagicHeader != null) {
                    // read config.interface.initPacketMagicHeader.isPresent()
                    ConfigInterfaceInitPacketMagicHeaderIsPresent1 = configInterfaceInitPacketMagicHeader.isPresent();
                }
                if (configInterfaceTransportPacketMagicHeader != null) {
                    // read config.interface.transportPacketMagicHeader.isPresent()
                    ConfigInterfaceTransportPacketMagicHeaderIsPresent1 = configInterfaceTransportPacketMagicHeader.isPresent();
                }
                if (configInterfaceMtu != null) {
                    // read config.interface.mtu.isPresent()
                    ConfigInterfaceMtuIsPresent1 = configInterfaceMtu.isPresent();
                }
                if (configInterfaceResponsePacketJunkSize != null) {
                    // read config.interface.responsePacketJunkSize.isPresent()
                    ConfigInterfaceResponsePacketJunkSizeIsPresent1 = configInterfaceResponsePacketJunkSize.isPresent();
                }
                if (configInterfaceResponsePacketMagicHeader != null) {
                    // read config.interface.responsePacketMagicHeader.isPresent()
                    ConfigInterfaceResponsePacketMagicHeaderIsPresent1 = configInterfaceResponsePacketMagicHeader.isPresent();
                }
                if (configInterfaceDnsSearchDomains != null) {
                    // read config.interface.dnsSearchDomains.isEmpty()
                    configInterfaceDnsSearchDomainsIsEmpty = configInterfaceDnsSearchDomains.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceDnsSearchDomainsIsEmpty) {
                        dirtyFlags |= 0x80000L;
                }
                else {
                        dirtyFlags |= 0x40000L;
                }
            }
                if (configInterfaceJunkPacketCount != null) {
                    // read config.interface.junkPacketCount.isPresent()
                    ConfigInterfaceJunkPacketCountIsPresent1 = configInterfaceJunkPacketCount.isPresent();
                }
                if (configInterfaceJunkPacketMinSize != null) {
                    // read config.interface.junkPacketMinSize.isPresent()
                    configInterfaceJunkPacketMinSizeIsPresent = configInterfaceJunkPacketMinSize.isPresent();
                }
                if (configInterfaceListenPort != null) {
                    // read config.interface.listenPort.isPresent()
                    ConfigInterfaceListenPortIsPresent1 = configInterfaceListenPort.isPresent();
                }
                if (configInterfaceAddresses != null) {
                    // read config.interface.addresses.isEmpty()
                    configInterfaceAddressesIsEmpty = configInterfaceAddresses.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceAddressesIsEmpty) {
                        dirtyFlags |= 0x800000L;
                }
                else {
                        dirtyFlags |= 0x400000L;
                }
            }
                if (configInterfaceInitPacketJunkSize != null) {
                    // read config.interface.initPacketJunkSize.isPresent()
                    configInterfaceInitPacketJunkSizeIsPresent = configInterfaceInitPacketJunkSize.isPresent();
                }


                // read !config.interface.junkPacketMaxSize.isPresent()
                ConfigInterfaceJunkPacketMaxSizeIsPresent1 = !configInterfaceJunkPacketMaxSizeIsPresent;
                // read !config.interface.underloadPacketMagicHeader.isPresent()
                ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1 = !configInterfaceUnderloadPacketMagicHeaderIsPresent;
                // read config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceDnsServersIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketMagicHeader.isPresent()
                configInterfaceInitPacketMagicHeaderIsPresent = !ConfigInterfaceInitPacketMagicHeaderIsPresent1;
                // read !config.interface.transportPacketMagicHeader.isPresent()
                configInterfaceTransportPacketMagicHeaderIsPresent = !ConfigInterfaceTransportPacketMagicHeaderIsPresent1;
                // read !config.interface.mtu.isPresent()
                configInterfaceMtuIsPresent = !ConfigInterfaceMtuIsPresent1;
                // read !config.interface.responsePacketJunkSize.isPresent()
                configInterfaceResponsePacketJunkSizeIsPresent = !ConfigInterfaceResponsePacketJunkSizeIsPresent1;
                // read !config.interface.responsePacketMagicHeader.isPresent()
                configInterfaceResponsePacketMagicHeaderIsPresent = !ConfigInterfaceResponsePacketMagicHeaderIsPresent1;
                // read config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceDnsSearchDomainsIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.junkPacketCount.isPresent()
                configInterfaceJunkPacketCountIsPresent = !ConfigInterfaceJunkPacketCountIsPresent1;
                // read !config.interface.junkPacketMinSize.isPresent()
                ConfigInterfaceJunkPacketMinSizeIsPresent1 = !configInterfaceJunkPacketMinSizeIsPresent;
                // read !config.interface.listenPort.isPresent()
                configInterfaceListenPortIsPresent = !ConfigInterfaceListenPortIsPresent1;
                // read config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceAddressesIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketJunkSize.isPresent()
                ConfigInterfaceInitPacketJunkSizeIsPresent1 = !configInterfaceInitPacketJunkSizeIsPresent;
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceJunkPacketMaxSizeIsPresent1) {
                        dirtyFlags |= 0x8000L;
                }
                else {
                        dirtyFlags |= 0x4000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1) {
                        dirtyFlags |= 0x8000000L;
                }
                else {
                        dirtyFlags |= 0x4000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceInitPacketMagicHeaderIsPresent) {
                        dirtyFlags |= 0x200L;
                }
                else {
                        dirtyFlags |= 0x100L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceTransportPacketMagicHeaderIsPresent) {
                        dirtyFlags |= 0x800000000L;
                }
                else {
                        dirtyFlags |= 0x400000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceMtuIsPresent) {
                        dirtyFlags |= 0x2000L;
                }
                else {
                        dirtyFlags |= 0x1000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceResponsePacketJunkSizeIsPresent) {
                        dirtyFlags |= 0x200000000L;
                }
                else {
                        dirtyFlags |= 0x100000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceResponsePacketMagicHeaderIsPresent) {
                        dirtyFlags |= 0x8000000000L;
                }
                else {
                        dirtyFlags |= 0x4000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceJunkPacketCountIsPresent) {
                        dirtyFlags |= 0x80000000L;
                }
                else {
                        dirtyFlags |= 0x40000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceJunkPacketMinSizeIsPresent1) {
                        dirtyFlags |= 0x20000000L;
                }
                else {
                        dirtyFlags |= 0x10000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceListenPortIsPresent) {
                        dirtyFlags |= 0x20000L;
                }
                else {
                        dirtyFlags |= 0x10000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceInitPacketJunkSizeIsPresent1) {
                        dirtyFlags |= 0x200000L;
                }
                else {
                        dirtyFlags |= 0x100000L;
                }
            }
                if (configInterfaceKeyPairPublicKey != null) {
                    // read config.interface.keyPair.publicKey.toBase64
                    configInterfaceKeyPairPublicKeyToBase64 = configInterfaceKeyPairPublicKey.toBase64();
                }


                // read !config.interface.junkPacketMaxSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceJunkPacketMaxSizeIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.underloadPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceInitPacketMagicHeaderIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.transportPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceTransportPacketMagicHeaderIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.mtu.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceMtuIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.responsePacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceResponsePacketJunkSizeIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.responsePacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceResponsePacketMagicHeaderIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.junkPacketCount.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceJunkPacketCountIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.junkPacketMinSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceJunkPacketMinSizeIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.listenPort.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceListenPortIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceInitPacketJunkSizeIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        if ((dirtyFlags & 0x24L) != 0) {



                if (fragment != null) {
                    // read fragment::setTunnelState
                    fragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener = (((mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener == null) ? (mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener = new OnBeforeCheckedChangeListenerImpl()) : mFragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener).setValue(fragment));
                }
        }
        if ((dirtyFlags & 0x39L) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.name
                        tunnelName = tunnel.getName();
                    }
            }
            if ((dirtyFlags & 0x29L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.state
                        tunnelState = tunnel.getState();
                    }


                    // read tunnel.state == State.UP
                    tunnelStateStateUP = (tunnelState) == (org.amnezia.awg.backend.Tunnel.State.UP);
            }
        }
        // batch finished

        if ((dirtyFlags & 0x2000080L) != 0) {

                if (configInterface != null) {
                    // read config.interface.excludedApplications
                    configInterfaceExcludedApplications = configInterface.getExcludedApplications();
                }

            if ((dirtyFlags & 0x80L) != 0) {

                    if (configInterfaceExcludedApplications != null) {
                        // read config.interface.excludedApplications.size()
                        configInterfaceExcludedApplicationsSize = configInterfaceExcludedApplications.size();
                    }


                    // read @android:plurals/n_excluded_applications
                    applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
                    // read @android:plurals/n_excluded_applications
                    applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
            }
            if ((dirtyFlags & 0x2000000L) != 0) {

                    if (configInterfaceExcludedApplications != null) {
                        // read config.interface.excludedApplications.isEmpty()
                        configInterfaceExcludedApplicationsIsEmpty = configInterfaceExcludedApplications.isEmpty();
                    }
            }
        }
        if ((dirtyFlags & 0x40L) != 0) {

                if (configInterfaceIncludedApplications != null) {
                    // read config.interface.includedApplications.size()
                    configInterfaceIncludedApplicationsSize = configInterfaceIncludedApplications.size();
                }


                // read @android:plurals/n_included_applications
                applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_included_applications, configInterfaceIncludedApplicationsSize, configInterfaceIncludedApplicationsSize);
                // read @android:plurals/n_included_applications
                applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_included_applications, configInterfaceIncludedApplicationsSize, configInterfaceIncludedApplicationsSize);
        }

        if ((dirtyFlags & 0x22L) != 0) {

                // read config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
                configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = ((configInterfaceIncludedApplicationsIsEmpty) ? (applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize) : (applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize));
                // read config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
                configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse = ((configInterfaceIncludedApplicationsIsEmpty) ? (configInterfaceExcludedApplicationsIsEmpty) : (false));
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse) {
                        dirtyFlags |= 0x2000000000L;
                }
                else {
                        dirtyFlags |= 0x1000000000L;
                }
            }


                // read config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x22L) != 0) {
            // api target 1

            this.addressesLabel.setVisibility(configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setInetNetworkSetText(this.addressesText, configInterfaceAddresses);
            this.addressesText.setVisibility(configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.applicationsText, configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize);
            this.applicationsText.setVisibility(configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.dnsSearchDomainsLabel.setVisibility(configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setStringSetText(this.dnsSearchDomainsText, configInterfaceDnsSearchDomains);
            this.dnsSearchDomainsText.setVisibility(configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.dnsServersLabel.setVisibility(configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setInetAddressSetText(this.dnsServersText, configInterfaceDnsServers);
            this.dnsServersText.setVisibility(configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.initPacketJunkSizeLabel.setVisibility(configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.initPacketJunkSizeText, configInterfaceInitPacketJunkSize);
            this.initPacketJunkSizeText.setVisibility(configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.initPacketMagicHeaderLabel.setVisibility(configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.initPacketMagicHeaderText, configInterfaceInitPacketMagicHeader);
            this.initPacketMagicHeaderText.setVisibility(configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.junkPacketCountLabel.setVisibility(configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.junkPacketCountText, configInterfaceJunkPacketCount);
            this.junkPacketCountText.setVisibility(configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.junkPacketMaxSizeLabel.setVisibility(configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.junkPacketMaxSizeText, configInterfaceJunkPacketMaxSize);
            this.junkPacketMaxSizeText.setVisibility(configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.junkPacketMinSizeLabel.setVisibility(configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.junkPacketMinSizeText, configInterfaceJunkPacketMinSize);
            this.junkPacketMinSizeText.setVisibility(configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.listenPortLabel.setVisibility(configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.listenPortText, configInterfaceListenPort);
            this.listenPortText.setVisibility(configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.mtuLabel.setVisibility(configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.mtuText, configInterfaceMtu);
            this.mtuText.setVisibility(configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setItems(this.peersLayout, this.mOldConfigPeers, this.mOldAndroidLayoutTunnelDetailPeer, configPeers, R.layout.tunnel_detail_peer);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.publicKeyText, configInterfaceKeyPairPublicKeyToBase64);
            this.responsePacketJunkSizeLabel.setVisibility(configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.responsePacketJunkSizeText, configInterfaceResponsePacketJunkSize);
            this.responsePacketJunkSizeText.setVisibility(configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.responsePacketMagicHeaderLabel.setVisibility(configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.responsePacketMagicHeaderText, configInterfaceResponsePacketMagicHeader);
            this.responsePacketMagicHeaderText.setVisibility(configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.transportPacketMagicHeaderLabel.setVisibility(configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.transportPacketMagicHeaderText, configInterfaceTransportPacketMagicHeader);
            this.transportPacketMagicHeaderText.setVisibility(configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.underloadPacketMagicHeaderLabel.setVisibility(configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            org.amnezia.awg.databinding.BindingAdapters.setOptionalText(this.underloadPacketMagicHeaderText, configInterfaceUnderloadPacketMagicHeader);
            this.underloadPacketMagicHeaderText.setVisibility(configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x20L) != 0) {
            // api target 1

            this.addressesText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.applicationsText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.dnsSearchDomainsText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.dnsServersText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.interfaceNameText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.listenPortText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.mtuText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
            this.publicKeyText.setOnClickListener((((mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener == null) ? (mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener)));
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.interfaceNameText, tunnelName);
        }
        if ((dirtyFlags & 0x29L) != 0) {
            // api target 1

            org.amnezia.awg.databinding.BindingAdapters.setChecked(this.tunnelSwitch, tunnelStateStateUP);
        }
        if ((dirtyFlags & 0x24L) != 0) {
            // api target 1

            org.amnezia.awg.databinding.BindingAdapters.setOnBeforeCheckedChanged(this.tunnelSwitch, fragmentSetTunnelStateOrgAmneziaAwgWidgetToggleSwitchOnBeforeCheckedChangeListener);
        }
        if ((dirtyFlags & 0x22L) != 0) {
            this.mOldConfigPeers = configPeers;
            this.mOldAndroidLayoutTunnelDetailPeer = R.layout.tunnel_detail_peer;
        }
    }
    // Listener Stub Implementations
    public static class OnBeforeCheckedChangeListenerImpl implements org.amnezia.awg.widget.ToggleSwitch.OnBeforeCheckedChangeListener{
        private org.amnezia.awg.fragment.TunnelDetailFragment value;
        public OnBeforeCheckedChangeListenerImpl setValue(org.amnezia.awg.fragment.TunnelDetailFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onBeforeCheckedChanged(org.amnezia.awg.widget.ToggleSwitch arg0, boolean arg1) {
            this.value.setTunnelState(arg0, arg1); 
        }
    }
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        @Override
        public void onClick(android.view.View arg0) {
            org.amnezia.awg.util.ClipboardUtils.copyTextView(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): tunnel
        flag 1 (0x2L): config
        flag 2 (0x3L): fragment
        flag 3 (0x4L): tunnel.state
        flag 4 (0x5L): tunnel.name
        flag 5 (0x6L): null
        flag 6 (0x7L): config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
        flag 7 (0x8L): config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
        flag 8 (0x9L): !config.interface.initPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 9 (0xaL): !config.interface.initPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 10 (0xbL): config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 11 (0xcL): config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 12 (0xdL): !config.interface.mtu.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 13 (0xeL): !config.interface.mtu.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 14 (0xfL): !config.interface.junkPacketMaxSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 15 (0x10L): !config.interface.junkPacketMaxSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 16 (0x11L): !config.interface.listenPort.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 17 (0x12L): !config.interface.listenPort.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 18 (0x13L): config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 19 (0x14L): config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 20 (0x15L): !config.interface.initPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 21 (0x16L): !config.interface.initPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 22 (0x17L): config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 23 (0x18L): config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 24 (0x19L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
        flag 25 (0x1aL): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
        flag 26 (0x1bL): !config.interface.underloadPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 27 (0x1cL): !config.interface.underloadPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 28 (0x1dL): !config.interface.junkPacketMinSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 29 (0x1eL): !config.interface.junkPacketMinSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 30 (0x1fL): !config.interface.junkPacketCount.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 31 (0x20L): !config.interface.junkPacketCount.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 32 (0x21L): !config.interface.responsePacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 33 (0x22L): !config.interface.responsePacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 34 (0x23L): !config.interface.transportPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 35 (0x24L): !config.interface.transportPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 36 (0x25L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
        flag 37 (0x26L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
        flag 38 (0x27L): !config.interface.responsePacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 39 (0x28L): !config.interface.responsePacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
    flag mapping end*/
    //end
}