package pw.idrug.connections.databinding;
import pw.idrug.connections.R;
import pw.idrug.connections.BR;
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
        sViewsWithIds.put(R.id.tunnel_detail_card, 56);
        sViewsWithIds.put(R.id.interface_title, 57);
        sViewsWithIds.put(R.id.interface_name_label, 58);
        sViewsWithIds.put(R.id.public_key_label, 59);
        sViewsWithIds.put(R.id.listen_port_mtu_barrier, 60);
        sViewsWithIds.put(R.id.idrugconnections_barrier, 61);
        sViewsWithIds.put(R.id.applications_label, 62);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    private java.util.List<pw.idrug.connections.config.Peer> mOldConfigPeers;
    private int mOldAndroidLayoutTunnelDetailPeer;
    // listeners
    private OnBeforeCheckedChangeListenerImpl mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener;
    private OnClickListenerImpl mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public TunnelDetailFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 63, sIncludes, sViewsWithIds));
    }
    private TunnelDetailFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[62]
            , (android.widget.TextView) bindings[54]
            , (android.widget.TextView) bindings[46]
            , (android.widget.TextView) bindings[47]
            , (android.widget.TextView) bindings[48]
            , (android.widget.TextView) bindings[49]
            , (android.widget.TextView) bindings[50]
            , (android.widget.TextView) bindings[51]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (androidx.constraintlayout.widget.Barrier) bindings[61]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[58]
            , (android.widget.TextView) bindings[2]
            , (com.google.android.material.textview.MaterialTextView) bindings[57]
            , (android.widget.TextView) bindings[52]
            , (android.widget.TextView) bindings[53]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[10]
            , (androidx.constraintlayout.widget.Barrier) bindings[60]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.LinearLayout) bindings[55]
            , (android.widget.TextView) bindings[59]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[42]
            , (android.widget.TextView) bindings[43]
            , (android.widget.TextView) bindings[44]
            , (android.widget.TextView) bindings[45]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[35]
            , (com.google.android.material.card.MaterialCardView) bindings[56]
            , (pw.idrug.connections.widget.ToggleSwitch) bindings[1]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[33]
            );
        this.addressesLabel.setTag(null);
        this.addressesText.setTag(null);
        this.applicationsText.setTag(null);
        this.controlledJunkPacket1Label.setTag(null);
        this.controlledJunkPacket1Text.setTag(null);
        this.controlledJunkPacket2Label.setTag(null);
        this.controlledJunkPacket2Text.setTag(null);
        this.controlledJunkPacket3Label.setTag(null);
        this.controlledJunkPacket3Text.setTag(null);
        this.cookieReplyPacketJunkSizeLabel.setTag(null);
        this.cookieReplyPacketJunkSizeText.setTag(null);
        this.dnsSearchDomainsLabel.setTag(null);
        this.dnsSearchDomainsText.setTag(null);
        this.dnsServersLabel.setTag(null);
        this.dnsServersText.setTag(null);
        this.initPacketJunkSizeLabel.setTag(null);
        this.initPacketJunkSizeText.setTag(null);
        this.initPacketMagicHeaderLabel.setTag(null);
        this.initPacketMagicHeaderText.setTag(null);
        this.interfaceNameText.setTag(null);
        this.itimeLabel.setTag(null);
        this.itimeText.setTag(null);
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
        this.specialJunkPacket1Label.setTag(null);
        this.specialJunkPacket1Text.setTag(null);
        this.specialJunkPacket2Label.setTag(null);
        this.specialJunkPacket2Text.setTag(null);
        this.specialJunkPacket3Label.setTag(null);
        this.specialJunkPacket3Text.setTag(null);
        this.specialJunkPacket4Label.setTag(null);
        this.specialJunkPacket4Text.setTag(null);
        this.specialJunkPacket5Label.setTag(null);
        this.specialJunkPacket5Text.setTag(null);
        this.transportPacketJunkSizeLabel.setTag(null);
        this.transportPacketJunkSizeText.setTag(null);
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
            setConfig((pw.idrug.connections.config.Config) variable);
        }
        else if (BR.fragment == variableId) {
            setFragment((pw.idrug.connections.fragment.TunnelDetailFragment) variable);
        }
        else if (BR.tunnel == variableId) {
            setTunnel((pw.idrug.connections.model.ObservableTunnel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setConfig(@Nullable pw.idrug.connections.config.Config Config) {
        this.mConfig = Config;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.config);
        super.requestRebind();
    }
    public void setFragment(@Nullable pw.idrug.connections.fragment.TunnelDetailFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setTunnel(@Nullable pw.idrug.connections.model.ObservableTunnel Tunnel) {
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
                return onChangeTunnel((pw.idrug.connections.model.ObservableTunnel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeTunnel(pw.idrug.connections.model.ObservableTunnel Tunnel, int fieldId) {
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
        boolean configInterfaceSpecialJunkPacket5IsPresent = false;
        java.util.Set<java.lang.String> configInterfaceExcludedApplications = null;
        boolean configInterfaceResponsePacketJunkSizeIsPresent = false;
        java.util.Optional<java.lang.Integer> configInterfaceJunkPacketMaxSize = null;
        int configInterfaceIncludedApplicationsSize = 0;
        boolean configInterfaceJunkPacketMaxSizeIsPresent = false;
        int configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        pw.idrug.connections.config.Config config = mConfig;
        int configInterfaceControlledJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.String> configInterfaceControlledJunkPacket1 = null;
        boolean configInterfaceSpecialJunkPacket3IsPresent = false;
        int configInterfaceItimeSecondsIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceUnderloadPacketMagicHeaderIsPresent = false;
        int configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        boolean configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse = false;
        boolean configInterfaceMtuIsPresent = false;
        int configInterfaceSpecialJunkPacket5IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.Integer> configInterfaceCookieReplyPacketJunkSize = null;
        java.util.Optional<java.lang.Long> configInterfaceInitPacketMagicHeader = null;
        java.util.Optional<java.lang.Integer> configInterfaceTransportPacketJunkSize = null;
        boolean configInterfaceSpecialJunkPacket2IsPresent = false;
        int configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.Long> configInterfaceTransportPacketMagicHeader = null;
        int configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        pw.idrug.connections.fragment.TunnelDetailFragment fragment = mFragment;
        java.util.Optional<java.lang.String> configInterfaceSpecialJunkPacket5 = null;
        boolean tunnelStateStateUP = false;
        int configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.Integer> configInterfaceMtu = null;
        pw.idrug.connections.backend.Tunnel.State tunnelState = null;
        boolean configInterfaceResponsePacketMagicHeaderIsPresent = false;
        java.util.Optional<java.lang.Integer> configInterfaceResponsePacketJunkSize = null;
        pw.idrug.connections.widget.ToggleSwitch.OnBeforeCheckedChangeListener fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = null;
        boolean configInterfaceJunkPacketCountIsPresent = false;
        boolean configInterfaceJunkPacketMinSizeIsPresent = false;
        boolean configInterfaceControlledJunkPacket1IsPresent = false;
        boolean configInterfaceTransportPacketJunkSizeIsPresent = false;
        boolean configInterfaceInitPacketMagicHeaderIsPresent = false;
        boolean ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1 = false;
        java.lang.String configInterfaceKeyPairPublicKeyToBase64 = null;
        boolean ConfigInterfaceJunkPacketCountIsPresent1 = false;
        java.util.Optional<java.lang.Integer> configInterfaceJunkPacketMinSize = null;
        java.util.Optional<java.lang.Integer> configInterfaceListenPort = null;
        boolean configInterfaceTransportPacketMagicHeaderIsPresent = false;
        boolean configInterfaceControlledJunkPacket2IsPresent = false;
        int configInterfaceSpecialJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean ConfigInterfaceJunkPacketMinSizeIsPresent1 = false;
        boolean configInterfaceControlledJunkPacket3IsPresent = false;
        boolean configInterfaceDnsServersIsEmpty = false;
        int configInterfaceCookieReplyPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean ConfigInterfaceControlledJunkPacket1IsPresent1 = false;
        boolean ConfigInterfaceMtuIsPresent1 = false;
        pw.idrug.connections.model.ObservableTunnel tunnel = mTunnel;
        boolean configInterfaceListenPortIsPresent = false;
        java.util.Optional<java.lang.String> configInterfaceSpecialJunkPacket3 = null;
        java.lang.String configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        java.util.Optional<java.lang.Long> configInterfaceUnderloadPacketMagicHeader = null;
        boolean ConfigInterfaceResponsePacketMagicHeaderIsPresent1 = false;
        pw.idrug.connections.crypto.KeyPair configInterfaceKeyPair = null;
        int configInterfaceSpecialJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceItimeSecondsIsPresent = false;
        int configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceExcludedApplicationsSize = 0;
        int configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceSpecialJunkPacket4IsPresent = false;
        pw.idrug.connections.config.Interface configInterface = null;
        int configInterfaceControlledJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceCookieReplyPacketJunkSizeIsPresent = false;
        java.util.Optional<java.lang.String> configInterfaceSpecialJunkPacket4 = null;
        boolean ConfigInterfaceInitPacketMagicHeaderIsPresent1 = false;
        boolean configInterfaceSpecialJunkPacket1IsPresent = false;
        boolean ConfigInterfaceControlledJunkPacket3IsPresent1 = false;
        boolean ConfigInterfaceItimeSecondsIsPresent1 = false;
        java.lang.String tunnelName = null;
        boolean ConfigInterfaceTransportPacketJunkSizeIsPresent1 = false;
        int configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.List<pw.idrug.connections.config.Peer> configPeers = null;
        int configInterfaceTransportPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceDnsSearchDomainsIsEmpty = false;
        int configInterfaceControlledJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Set<java.lang.String> configInterfaceIncludedApplications = null;
        java.util.Set<java.net.InetAddress> configInterfaceDnsServers = null;
        boolean ConfigInterfaceSpecialJunkPacket4IsPresent1 = false;
        boolean configInterfaceExcludedApplicationsIsEmpty = false;
        boolean ConfigInterfaceSpecialJunkPacket1IsPresent1 = false;
        boolean ConfigInterfaceListenPortIsPresent1 = false;
        boolean ConfigInterfaceSpecialJunkPacket3IsPresent1 = false;
        boolean configInterfaceIncludedApplicationsIsEmpty = false;
        boolean configInterfaceAddressesIsEmpty = false;
        pw.idrug.connections.crypto.Key configInterfaceKeyPairPublicKey = null;
        java.util.Optional<java.lang.String> configInterfaceSpecialJunkPacket1 = null;
        boolean configInterfaceInitPacketJunkSizeIsPresent = false;
        java.lang.String applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = null;
        java.util.Optional<java.lang.Integer> configInterfaceItimeSeconds = null;
        int configInterfaceSpecialJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.String> configInterfaceControlledJunkPacket3 = null;
        boolean ConfigInterfaceCookieReplyPacketJunkSizeIsPresent1 = false;
        int configInterfaceSpecialJunkPacket4IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.Long> configInterfaceResponsePacketMagicHeader = null;
        boolean ConfigInterfaceJunkPacketMaxSizeIsPresent1 = false;
        boolean ConfigInterfaceResponsePacketJunkSizeIsPresent1 = false;
        boolean ConfigInterfaceInitPacketJunkSizeIsPresent1 = false;
        boolean ConfigInterfaceSpecialJunkPacket2IsPresent1 = false;
        boolean ConfigInterfaceControlledJunkPacket2IsPresent1 = false;
        java.util.Optional<java.lang.String> configInterfaceSpecialJunkPacket2 = null;
        java.util.Set<java.lang.String> configInterfaceDnsSearchDomains = null;
        java.util.Optional<java.lang.Integer> configInterfaceJunkPacketCount = null;
        int configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.util.Optional<java.lang.String> configInterfaceControlledJunkPacket2 = null;
        boolean ConfigInterfaceSpecialJunkPacket5IsPresent1 = false;
        boolean ConfigInterfaceTransportPacketMagicHeaderIsPresent1 = false;
        java.util.Set<pw.idrug.connections.config.InetNetwork> configInterfaceAddresses = null;
        java.util.Optional<java.lang.Integer> configInterfaceInitPacketJunkSize = null;

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
                    // read config.interface.controlledJunkPacket1
                    configInterfaceControlledJunkPacket1 = configInterface.getControlledJunkPacket1();
                    // read config.interface.cookieReplyPacketJunkSize
                    configInterfaceCookieReplyPacketJunkSize = configInterface.getCookieReplyPacketJunkSize();
                    // read config.interface.initPacketMagicHeader
                    configInterfaceInitPacketMagicHeader = configInterface.getInitPacketMagicHeader();
                    // read config.interface.transportPacketJunkSize
                    configInterfaceTransportPacketJunkSize = configInterface.getTransportPacketJunkSize();
                    // read config.interface.transportPacketMagicHeader
                    configInterfaceTransportPacketMagicHeader = configInterface.getTransportPacketMagicHeader();
                    // read config.interface.specialJunkPacket5
                    configInterfaceSpecialJunkPacket5 = configInterface.getSpecialJunkPacket5();
                    // read config.interface.mtu
                    configInterfaceMtu = configInterface.getMtu();
                    // read config.interface.responsePacketJunkSize
                    configInterfaceResponsePacketJunkSize = configInterface.getResponsePacketJunkSize();
                    // read config.interface.junkPacketMinSize
                    configInterfaceJunkPacketMinSize = configInterface.getJunkPacketMinSize();
                    // read config.interface.listenPort
                    configInterfaceListenPort = configInterface.getListenPort();
                    // read config.interface.specialJunkPacket3
                    configInterfaceSpecialJunkPacket3 = configInterface.getSpecialJunkPacket3();
                    // read config.interface.underloadPacketMagicHeader
                    configInterfaceUnderloadPacketMagicHeader = configInterface.getUnderloadPacketMagicHeader();
                    // read config.interface.keyPair
                    configInterfaceKeyPair = configInterface.getKeyPair();
                    // read config.interface.specialJunkPacket4
                    configInterfaceSpecialJunkPacket4 = configInterface.getSpecialJunkPacket4();
                    // read config.interface.includedApplications
                    configInterfaceIncludedApplications = configInterface.getIncludedApplications();
                    // read config.interface.dnsServers
                    configInterfaceDnsServers = configInterface.getDnsServers();
                    // read config.interface.specialJunkPacket1
                    configInterfaceSpecialJunkPacket1 = configInterface.getSpecialJunkPacket1();
                    // read config.interface.itimeSeconds
                    configInterfaceItimeSeconds = configInterface.getItimeSeconds();
                    // read config.interface.controlledJunkPacket3
                    configInterfaceControlledJunkPacket3 = configInterface.getControlledJunkPacket3();
                    // read config.interface.responsePacketMagicHeader
                    configInterfaceResponsePacketMagicHeader = configInterface.getResponsePacketMagicHeader();
                    // read config.interface.specialJunkPacket2
                    configInterfaceSpecialJunkPacket2 = configInterface.getSpecialJunkPacket2();
                    // read config.interface.dnsSearchDomains
                    configInterfaceDnsSearchDomains = configInterface.getDnsSearchDomains();
                    // read config.interface.junkPacketCount
                    configInterfaceJunkPacketCount = configInterface.getJunkPacketCount();
                    // read config.interface.controlledJunkPacket2
                    configInterfaceControlledJunkPacket2 = configInterface.getControlledJunkPacket2();
                    // read config.interface.addresses
                    configInterfaceAddresses = configInterface.getAddresses();
                    // read config.interface.initPacketJunkSize
                    configInterfaceInitPacketJunkSize = configInterface.getInitPacketJunkSize();
                }


                if (configInterfaceJunkPacketMaxSize != null) {
                    // read config.interface.junkPacketMaxSize.isPresent()
                    configInterfaceJunkPacketMaxSizeIsPresent = configInterfaceJunkPacketMaxSize.isPresent();
                }
                if (configInterfaceControlledJunkPacket1 != null) {
                    // read config.interface.controlledJunkPacket1.isPresent()
                    configInterfaceControlledJunkPacket1IsPresent = configInterfaceControlledJunkPacket1.isPresent();
                }
                if (configInterfaceCookieReplyPacketJunkSize != null) {
                    // read config.interface.cookieReplyPacketJunkSize.isPresent()
                    ConfigInterfaceCookieReplyPacketJunkSizeIsPresent1 = configInterfaceCookieReplyPacketJunkSize.isPresent();
                }
                if (configInterfaceInitPacketMagicHeader != null) {
                    // read config.interface.initPacketMagicHeader.isPresent()
                    configInterfaceInitPacketMagicHeaderIsPresent = configInterfaceInitPacketMagicHeader.isPresent();
                }
                if (configInterfaceTransportPacketJunkSize != null) {
                    // read config.interface.transportPacketJunkSize.isPresent()
                    ConfigInterfaceTransportPacketJunkSizeIsPresent1 = configInterfaceTransportPacketJunkSize.isPresent();
                }
                if (configInterfaceTransportPacketMagicHeader != null) {
                    // read config.interface.transportPacketMagicHeader.isPresent()
                    ConfigInterfaceTransportPacketMagicHeaderIsPresent1 = configInterfaceTransportPacketMagicHeader.isPresent();
                }
                if (configInterfaceSpecialJunkPacket5 != null) {
                    // read config.interface.specialJunkPacket5.isPresent()
                    ConfigInterfaceSpecialJunkPacket5IsPresent1 = configInterfaceSpecialJunkPacket5.isPresent();
                }
                if (configInterfaceMtu != null) {
                    // read config.interface.mtu.isPresent()
                    ConfigInterfaceMtuIsPresent1 = configInterfaceMtu.isPresent();
                }
                if (configInterfaceResponsePacketJunkSize != null) {
                    // read config.interface.responsePacketJunkSize.isPresent()
                    ConfigInterfaceResponsePacketJunkSizeIsPresent1 = configInterfaceResponsePacketJunkSize.isPresent();
                }
                if (configInterfaceJunkPacketMinSize != null) {
                    // read config.interface.junkPacketMinSize.isPresent()
                    configInterfaceJunkPacketMinSizeIsPresent = configInterfaceJunkPacketMinSize.isPresent();
                }
                if (configInterfaceListenPort != null) {
                    // read config.interface.listenPort.isPresent()
                    ConfigInterfaceListenPortIsPresent1 = configInterfaceListenPort.isPresent();
                }
                if (configInterfaceSpecialJunkPacket3 != null) {
                    // read config.interface.specialJunkPacket3.isPresent()
                    configInterfaceSpecialJunkPacket3IsPresent = configInterfaceSpecialJunkPacket3.isPresent();
                }
                if (configInterfaceUnderloadPacketMagicHeader != null) {
                    // read config.interface.underloadPacketMagicHeader.isPresent()
                    configInterfaceUnderloadPacketMagicHeaderIsPresent = configInterfaceUnderloadPacketMagicHeader.isPresent();
                }
                if (configInterfaceKeyPair != null) {
                    // read config.interface.keyPair.publicKey
                    configInterfaceKeyPairPublicKey = configInterfaceKeyPair.getPublicKey();
                }
                if (configInterfaceSpecialJunkPacket4 != null) {
                    // read config.interface.specialJunkPacket4.isPresent()
                    ConfigInterfaceSpecialJunkPacket4IsPresent1 = configInterfaceSpecialJunkPacket4.isPresent();
                }
                if (configInterfaceIncludedApplications != null) {
                    // read config.interface.includedApplications.isEmpty()
                    configInterfaceIncludedApplicationsIsEmpty = configInterfaceIncludedApplications.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceIncludedApplicationsIsEmpty) {
                        dirtyFlags |= 0x200000L;
                        dirtyFlags |= 0x2000000000L;
                }
                else {
                        dirtyFlags |= 0x100000L;
                        dirtyFlags |= 0x1000000000L;
                }
            }
                if (configInterfaceDnsServers != null) {
                    // read config.interface.dnsServers.isEmpty()
                    configInterfaceDnsServersIsEmpty = configInterfaceDnsServers.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceDnsServersIsEmpty) {
                        dirtyFlags |= 0x200L;
                }
                else {
                        dirtyFlags |= 0x100L;
                }
            }
                if (configInterfaceSpecialJunkPacket1 != null) {
                    // read config.interface.specialJunkPacket1.isPresent()
                    configInterfaceSpecialJunkPacket1IsPresent = configInterfaceSpecialJunkPacket1.isPresent();
                }
                if (configInterfaceItimeSeconds != null) {
                    // read config.interface.itimeSeconds.isPresent()
                    ConfigInterfaceItimeSecondsIsPresent1 = configInterfaceItimeSeconds.isPresent();
                }
                if (configInterfaceControlledJunkPacket3 != null) {
                    // read config.interface.controlledJunkPacket3.isPresent()
                    ConfigInterfaceControlledJunkPacket3IsPresent1 = configInterfaceControlledJunkPacket3.isPresent();
                }
                if (configInterfaceResponsePacketMagicHeader != null) {
                    // read config.interface.responsePacketMagicHeader.isPresent()
                    configInterfaceResponsePacketMagicHeaderIsPresent = configInterfaceResponsePacketMagicHeader.isPresent();
                }
                if (configInterfaceSpecialJunkPacket2 != null) {
                    // read config.interface.specialJunkPacket2.isPresent()
                    ConfigInterfaceSpecialJunkPacket2IsPresent1 = configInterfaceSpecialJunkPacket2.isPresent();
                }
                if (configInterfaceDnsSearchDomains != null) {
                    // read config.interface.dnsSearchDomains.isEmpty()
                    configInterfaceDnsSearchDomainsIsEmpty = configInterfaceDnsSearchDomains.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceDnsSearchDomainsIsEmpty) {
                        dirtyFlags |= 0x8000L;
                }
                else {
                        dirtyFlags |= 0x4000L;
                }
            }
                if (configInterfaceJunkPacketCount != null) {
                    // read config.interface.junkPacketCount.isPresent()
                    ConfigInterfaceJunkPacketCountIsPresent1 = configInterfaceJunkPacketCount.isPresent();
                }
                if (configInterfaceControlledJunkPacket2 != null) {
                    // read config.interface.controlledJunkPacket2.isPresent()
                    ConfigInterfaceControlledJunkPacket2IsPresent1 = configInterfaceControlledJunkPacket2.isPresent();
                }
                if (configInterfaceAddresses != null) {
                    // read config.interface.addresses.isEmpty()
                    configInterfaceAddressesIsEmpty = configInterfaceAddresses.isEmpty();
                }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceAddressesIsEmpty) {
                        dirtyFlags |= 0x80000L;
                }
                else {
                        dirtyFlags |= 0x40000L;
                }
            }
                if (configInterfaceInitPacketJunkSize != null) {
                    // read config.interface.initPacketJunkSize.isPresent()
                    configInterfaceInitPacketJunkSizeIsPresent = configInterfaceInitPacketJunkSize.isPresent();
                }


                // read !config.interface.junkPacketMaxSize.isPresent()
                ConfigInterfaceJunkPacketMaxSizeIsPresent1 = !configInterfaceJunkPacketMaxSizeIsPresent;
                // read !config.interface.controlledJunkPacket1.isPresent()
                ConfigInterfaceControlledJunkPacket1IsPresent1 = !configInterfaceControlledJunkPacket1IsPresent;
                // read !config.interface.cookieReplyPacketJunkSize.isPresent()
                configInterfaceCookieReplyPacketJunkSizeIsPresent = !ConfigInterfaceCookieReplyPacketJunkSizeIsPresent1;
                // read !config.interface.initPacketMagicHeader.isPresent()
                ConfigInterfaceInitPacketMagicHeaderIsPresent1 = !configInterfaceInitPacketMagicHeaderIsPresent;
                // read !config.interface.transportPacketJunkSize.isPresent()
                configInterfaceTransportPacketJunkSizeIsPresent = !ConfigInterfaceTransportPacketJunkSizeIsPresent1;
                // read !config.interface.transportPacketMagicHeader.isPresent()
                configInterfaceTransportPacketMagicHeaderIsPresent = !ConfigInterfaceTransportPacketMagicHeaderIsPresent1;
                // read !config.interface.specialJunkPacket5.isPresent()
                configInterfaceSpecialJunkPacket5IsPresent = !ConfigInterfaceSpecialJunkPacket5IsPresent1;
                // read !config.interface.mtu.isPresent()
                configInterfaceMtuIsPresent = !ConfigInterfaceMtuIsPresent1;
                // read !config.interface.responsePacketJunkSize.isPresent()
                configInterfaceResponsePacketJunkSizeIsPresent = !ConfigInterfaceResponsePacketJunkSizeIsPresent1;
                // read !config.interface.junkPacketMinSize.isPresent()
                ConfigInterfaceJunkPacketMinSizeIsPresent1 = !configInterfaceJunkPacketMinSizeIsPresent;
                // read !config.interface.listenPort.isPresent()
                configInterfaceListenPortIsPresent = !ConfigInterfaceListenPortIsPresent1;
                // read !config.interface.specialJunkPacket3.isPresent()
                ConfigInterfaceSpecialJunkPacket3IsPresent1 = !configInterfaceSpecialJunkPacket3IsPresent;
                // read !config.interface.underloadPacketMagicHeader.isPresent()
                ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1 = !configInterfaceUnderloadPacketMagicHeaderIsPresent;
                // read !config.interface.specialJunkPacket4.isPresent()
                configInterfaceSpecialJunkPacket4IsPresent = !ConfigInterfaceSpecialJunkPacket4IsPresent1;
                // read config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceDnsServersIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.specialJunkPacket1.isPresent()
                ConfigInterfaceSpecialJunkPacket1IsPresent1 = !configInterfaceSpecialJunkPacket1IsPresent;
                // read !config.interface.itimeSeconds.isPresent()
                configInterfaceItimeSecondsIsPresent = !ConfigInterfaceItimeSecondsIsPresent1;
                // read !config.interface.controlledJunkPacket3.isPresent()
                configInterfaceControlledJunkPacket3IsPresent = !ConfigInterfaceControlledJunkPacket3IsPresent1;
                // read !config.interface.responsePacketMagicHeader.isPresent()
                ConfigInterfaceResponsePacketMagicHeaderIsPresent1 = !configInterfaceResponsePacketMagicHeaderIsPresent;
                // read !config.interface.specialJunkPacket2.isPresent()
                configInterfaceSpecialJunkPacket2IsPresent = !ConfigInterfaceSpecialJunkPacket2IsPresent1;
                // read config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceDnsSearchDomainsIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.junkPacketCount.isPresent()
                configInterfaceJunkPacketCountIsPresent = !ConfigInterfaceJunkPacketCountIsPresent1;
                // read !config.interface.controlledJunkPacket2.isPresent()
                configInterfaceControlledJunkPacket2IsPresent = !ConfigInterfaceControlledJunkPacket2IsPresent1;
                // read config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceAddressesIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketJunkSize.isPresent()
                ConfigInterfaceInitPacketJunkSizeIsPresent1 = !configInterfaceInitPacketJunkSizeIsPresent;
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceJunkPacketMaxSizeIsPresent1) {
                        dirtyFlags |= 0x80000000000L;
                }
                else {
                        dirtyFlags |= 0x40000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceControlledJunkPacket1IsPresent1) {
                        dirtyFlags |= 0x800L;
                }
                else {
                        dirtyFlags |= 0x400L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceCookieReplyPacketJunkSizeIsPresent) {
                        dirtyFlags |= 0x800000000L;
                }
                else {
                        dirtyFlags |= 0x400000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceInitPacketMagicHeaderIsPresent1) {
                        dirtyFlags |= 0x80L;
                }
                else {
                        dirtyFlags |= 0x40L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceTransportPacketJunkSizeIsPresent) {
                        dirtyFlags |= 0x8000000000000L;
                }
                else {
                        dirtyFlags |= 0x4000000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceTransportPacketMagicHeaderIsPresent) {
                        dirtyFlags |= 0x800000000000000L;
                }
                else {
                        dirtyFlags |= 0x400000000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceSpecialJunkPacket5IsPresent) {
                        dirtyFlags |= 0x800000L;
                }
                else {
                        dirtyFlags |= 0x400000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceMtuIsPresent) {
                        dirtyFlags |= 0x20000000000L;
                }
                else {
                        dirtyFlags |= 0x10000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceResponsePacketJunkSizeIsPresent) {
                        dirtyFlags |= 0x20000000L;
                }
                else {
                        dirtyFlags |= 0x10000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceJunkPacketMinSizeIsPresent1) {
                        dirtyFlags |= 0x2000000L;
                }
                else {
                        dirtyFlags |= 0x1000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceListenPortIsPresent) {
                        dirtyFlags |= 0x200000000000L;
                }
                else {
                        dirtyFlags |= 0x100000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceSpecialJunkPacket3IsPresent1) {
                        dirtyFlags |= 0x8000000000L;
                }
                else {
                        dirtyFlags |= 0x4000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1) {
                        dirtyFlags |= 0x2000000000000L;
                }
                else {
                        dirtyFlags |= 0x1000000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceSpecialJunkPacket4IsPresent) {
                        dirtyFlags |= 0x200000000000000L;
                }
                else {
                        dirtyFlags |= 0x100000000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceSpecialJunkPacket1IsPresent1) {
                        dirtyFlags |= 0x80000000L;
                }
                else {
                        dirtyFlags |= 0x40000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceItimeSecondsIsPresent) {
                        dirtyFlags |= 0x2000L;
                }
                else {
                        dirtyFlags |= 0x1000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceControlledJunkPacket3IsPresent) {
                        dirtyFlags |= 0x800000000000L;
                }
                else {
                        dirtyFlags |= 0x400000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceResponsePacketMagicHeaderIsPresent1) {
                        dirtyFlags |= 0x200000000L;
                }
                else {
                        dirtyFlags |= 0x100000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceSpecialJunkPacket2IsPresent) {
                        dirtyFlags |= 0x80000000000000L;
                }
                else {
                        dirtyFlags |= 0x40000000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceJunkPacketCountIsPresent) {
                        dirtyFlags |= 0x8000000L;
                }
                else {
                        dirtyFlags |= 0x4000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceControlledJunkPacket2IsPresent) {
                        dirtyFlags |= 0x20000000000000L;
                }
                else {
                        dirtyFlags |= 0x10000000000000L;
                }
            }
            if((dirtyFlags & 0x22L) != 0) {
                if(ConfigInterfaceInitPacketJunkSizeIsPresent1) {
                        dirtyFlags |= 0x20000L;
                }
                else {
                        dirtyFlags |= 0x10000L;
                }
            }
                if (configInterfaceKeyPairPublicKey != null) {
                    // read config.interface.keyPair.publicKey.toBase64
                    configInterfaceKeyPairPublicKeyToBase64 = configInterfaceKeyPairPublicKey.toBase64();
                }


                // read !config.interface.junkPacketMaxSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceJunkPacketMaxSizeIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.controlledJunkPacket1.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceControlledJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceControlledJunkPacket1IsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.cookieReplyPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceCookieReplyPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceCookieReplyPacketJunkSizeIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceInitPacketMagicHeaderIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.transportPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceTransportPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceTransportPacketJunkSizeIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.transportPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceTransportPacketMagicHeaderIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.specialJunkPacket5.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceSpecialJunkPacket5IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket5IsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.mtu.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceMtuIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.responsePacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceResponsePacketJunkSizeIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.junkPacketMinSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceJunkPacketMinSizeIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.listenPort.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceListenPortIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.specialJunkPacket3.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceSpecialJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceSpecialJunkPacket3IsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.underloadPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceUnderloadPacketMagicHeaderIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.specialJunkPacket4.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceSpecialJunkPacket4IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket4IsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.specialJunkPacket1.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceSpecialJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceSpecialJunkPacket1IsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.itimeSeconds.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceItimeSecondsIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceItimeSecondsIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.controlledJunkPacket3.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceControlledJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceControlledJunkPacket3IsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.responsePacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceResponsePacketMagicHeaderIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.specialJunkPacket2.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceSpecialJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket2IsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.junkPacketCount.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceJunkPacketCountIsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.controlledJunkPacket2.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceControlledJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceControlledJunkPacket2IsPresent) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                // read !config.interface.initPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE = ((ConfigInterfaceInitPacketJunkSizeIsPresent1) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        if ((dirtyFlags & 0x24L) != 0) {



                if (fragment != null) {
                    // read fragment::setTunnelState
                    fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = (((mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener == null) ? (mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = new OnBeforeCheckedChangeListenerImpl()) : mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener).setValue(fragment));
                }
        }
        if ((dirtyFlags & 0x39L) != 0) {


            if ((dirtyFlags & 0x29L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.state
                        tunnelState = tunnel.getState();
                    }


                    // read tunnel.state == State.UP
                    tunnelStateStateUP = (tunnelState) == (pw.idrug.connections.backend.Tunnel.State.UP);
            }
            if ((dirtyFlags & 0x31L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.name
                        tunnelName = tunnel.getName();
                    }
            }
        }
        // batch finished

        if ((dirtyFlags & 0x2000200000L) != 0) {

                if (configInterface != null) {
                    // read config.interface.excludedApplications
                    configInterfaceExcludedApplications = configInterface.getExcludedApplications();
                }

            if ((dirtyFlags & 0x2000000000L) != 0) {

                    if (configInterfaceExcludedApplications != null) {
                        // read config.interface.excludedApplications.size()
                        configInterfaceExcludedApplicationsSize = configInterfaceExcludedApplications.size();
                    }


                    // read @android:plurals/n_excluded_applications
                    applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
                    // read @android:plurals/n_excluded_applications
                    applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
            }
            if ((dirtyFlags & 0x200000L) != 0) {

                    if (configInterfaceExcludedApplications != null) {
                        // read config.interface.excludedApplications.isEmpty()
                        configInterfaceExcludedApplicationsIsEmpty = configInterfaceExcludedApplications.isEmpty();
                    }
            }
        }
        if ((dirtyFlags & 0x1000000000L) != 0) {

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

                // read config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
                configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse = ((configInterfaceIncludedApplicationsIsEmpty) ? (configInterfaceExcludedApplicationsIsEmpty) : (false));
                // read config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
                configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = ((configInterfaceIncludedApplicationsIsEmpty) ? (applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize) : (applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize));
            if((dirtyFlags & 0x22L) != 0) {
                if(configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse) {
                        dirtyFlags |= 0x2000000000000000L;
                }
                else {
                        dirtyFlags |= 0x1000000000000000L;
                }
            }


                // read config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x22L) != 0) {
            // api target 1

            this.addressesLabel.setVisibility(configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setInetNetworkSetText(this.addressesText, configInterfaceAddresses);
            this.addressesText.setVisibility(configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.applicationsText, configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize);
            this.applicationsText.setVisibility(configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.controlledJunkPacket1Label.setVisibility(configInterfaceControlledJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.controlledJunkPacket1Text, configInterfaceControlledJunkPacket1);
            this.controlledJunkPacket1Text.setVisibility(configInterfaceControlledJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.controlledJunkPacket2Label.setVisibility(configInterfaceControlledJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.controlledJunkPacket2Text, configInterfaceControlledJunkPacket2);
            this.controlledJunkPacket2Text.setVisibility(configInterfaceControlledJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.controlledJunkPacket3Label.setVisibility(configInterfaceControlledJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.controlledJunkPacket3Text, configInterfaceControlledJunkPacket3);
            this.controlledJunkPacket3Text.setVisibility(configInterfaceControlledJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.cookieReplyPacketJunkSizeLabel.setVisibility(configInterfaceCookieReplyPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.cookieReplyPacketJunkSizeText, configInterfaceCookieReplyPacketJunkSize);
            this.cookieReplyPacketJunkSizeText.setVisibility(configInterfaceCookieReplyPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.dnsSearchDomainsLabel.setVisibility(configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setStringSetText(this.dnsSearchDomainsText, configInterfaceDnsSearchDomains);
            this.dnsSearchDomainsText.setVisibility(configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.dnsServersLabel.setVisibility(configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setInetAddressSetText(this.dnsServersText, configInterfaceDnsServers);
            this.dnsServersText.setVisibility(configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.initPacketJunkSizeLabel.setVisibility(configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.initPacketJunkSizeText, configInterfaceInitPacketJunkSize);
            this.initPacketJunkSizeText.setVisibility(configInterfaceInitPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.initPacketMagicHeaderLabel.setVisibility(configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.initPacketMagicHeaderText, configInterfaceInitPacketMagicHeader);
            this.initPacketMagicHeaderText.setVisibility(configInterfaceInitPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.itimeLabel.setVisibility(configInterfaceItimeSecondsIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.itimeText, configInterfaceItimeSeconds);
            this.itimeText.setVisibility(configInterfaceItimeSecondsIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.junkPacketCountLabel.setVisibility(configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.junkPacketCountText, configInterfaceJunkPacketCount);
            this.junkPacketCountText.setVisibility(configInterfaceJunkPacketCountIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.junkPacketMaxSizeLabel.setVisibility(configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.junkPacketMaxSizeText, configInterfaceJunkPacketMaxSize);
            this.junkPacketMaxSizeText.setVisibility(configInterfaceJunkPacketMaxSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.junkPacketMinSizeLabel.setVisibility(configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.junkPacketMinSizeText, configInterfaceJunkPacketMinSize);
            this.junkPacketMinSizeText.setVisibility(configInterfaceJunkPacketMinSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.listenPortLabel.setVisibility(configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.listenPortText, configInterfaceListenPort);
            this.listenPortText.setVisibility(configInterfaceListenPortIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.mtuLabel.setVisibility(configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.mtuText, configInterfaceMtu);
            this.mtuText.setVisibility(configInterfaceMtuIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setItems(this.peersLayout, this.mOldConfigPeers, this.mOldAndroidLayoutTunnelDetailPeer, configPeers, R.layout.tunnel_detail_peer);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.publicKeyText, configInterfaceKeyPairPublicKeyToBase64);
            this.responsePacketJunkSizeLabel.setVisibility(configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.responsePacketJunkSizeText, configInterfaceResponsePacketJunkSize);
            this.responsePacketJunkSizeText.setVisibility(configInterfaceResponsePacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.responsePacketMagicHeaderLabel.setVisibility(configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.responsePacketMagicHeaderText, configInterfaceResponsePacketMagicHeader);
            this.responsePacketMagicHeaderText.setVisibility(configInterfaceResponsePacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.specialJunkPacket1Label.setVisibility(configInterfaceSpecialJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.specialJunkPacket1Text, configInterfaceSpecialJunkPacket1);
            this.specialJunkPacket1Text.setVisibility(configInterfaceSpecialJunkPacket1IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.specialJunkPacket2Label.setVisibility(configInterfaceSpecialJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.specialJunkPacket2Text, configInterfaceSpecialJunkPacket2);
            this.specialJunkPacket2Text.setVisibility(configInterfaceSpecialJunkPacket2IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.specialJunkPacket3Label.setVisibility(configInterfaceSpecialJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.specialJunkPacket3Text, configInterfaceSpecialJunkPacket3);
            this.specialJunkPacket3Text.setVisibility(configInterfaceSpecialJunkPacket3IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.specialJunkPacket4Label.setVisibility(configInterfaceSpecialJunkPacket4IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.specialJunkPacket4Text, configInterfaceSpecialJunkPacket4);
            this.specialJunkPacket4Text.setVisibility(configInterfaceSpecialJunkPacket4IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.specialJunkPacket5Label.setVisibility(configInterfaceSpecialJunkPacket5IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.specialJunkPacket5Text, configInterfaceSpecialJunkPacket5);
            this.specialJunkPacket5Text.setVisibility(configInterfaceSpecialJunkPacket5IsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.transportPacketJunkSizeLabel.setVisibility(configInterfaceTransportPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.transportPacketJunkSizeText, configInterfaceTransportPacketJunkSize);
            this.transportPacketJunkSizeText.setVisibility(configInterfaceTransportPacketJunkSizeIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.transportPacketMagicHeaderLabel.setVisibility(configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.transportPacketMagicHeaderText, configInterfaceTransportPacketMagicHeader);
            this.transportPacketMagicHeaderText.setVisibility(configInterfaceTransportPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.underloadPacketMagicHeaderLabel.setVisibility(configInterfaceUnderloadPacketMagicHeaderIsPresentAndroidViewViewGONEAndroidViewViewVISIBLE);
            pw.idrug.connections.databinding.BindingAdapters.setOptionalText(this.underloadPacketMagicHeaderText, configInterfaceUnderloadPacketMagicHeader);
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

            pw.idrug.connections.databinding.BindingAdapters.setChecked(this.tunnelSwitch, tunnelStateStateUP);
        }
        if ((dirtyFlags & 0x24L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setOnBeforeCheckedChanged(this.tunnelSwitch, fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener);
        }
        if ((dirtyFlags & 0x22L) != 0) {
            this.mOldConfigPeers = configPeers;
            this.mOldAndroidLayoutTunnelDetailPeer = R.layout.tunnel_detail_peer;
        }
    }
    // Listener Stub Implementations
    public static class OnBeforeCheckedChangeListenerImpl implements pw.idrug.connections.widget.ToggleSwitch.OnBeforeCheckedChangeListener{
        private pw.idrug.connections.fragment.TunnelDetailFragment value;
        public OnBeforeCheckedChangeListenerImpl setValue(pw.idrug.connections.fragment.TunnelDetailFragment value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onBeforeCheckedChanged(pw.idrug.connections.widget.ToggleSwitch arg0, boolean arg1) {
            this.value.setTunnelState(arg0, arg1); 
        }
    }
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
        flag 0 (0x1L): tunnel
        flag 1 (0x2L): config
        flag 2 (0x3L): fragment
        flag 3 (0x4L): tunnel.state
        flag 4 (0x5L): tunnel.name
        flag 5 (0x6L): null
        flag 6 (0x7L): !config.interface.initPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 7 (0x8L): !config.interface.initPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 8 (0x9L): config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 9 (0xaL): config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 10 (0xbL): !config.interface.controlledJunkPacket1.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 11 (0xcL): !config.interface.controlledJunkPacket1.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 12 (0xdL): !config.interface.itimeSeconds.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 13 (0xeL): !config.interface.itimeSeconds.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 14 (0xfL): config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 15 (0x10L): config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 16 (0x11L): !config.interface.initPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 17 (0x12L): !config.interface.initPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 18 (0x13L): config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 19 (0x14L): config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 20 (0x15L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
        flag 21 (0x16L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
        flag 22 (0x17L): !config.interface.specialJunkPacket5.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 23 (0x18L): !config.interface.specialJunkPacket5.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 24 (0x19L): !config.interface.junkPacketMinSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 25 (0x1aL): !config.interface.junkPacketMinSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 26 (0x1bL): !config.interface.junkPacketCount.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 27 (0x1cL): !config.interface.junkPacketCount.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 28 (0x1dL): !config.interface.responsePacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 29 (0x1eL): !config.interface.responsePacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 30 (0x1fL): !config.interface.specialJunkPacket1.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 31 (0x20L): !config.interface.specialJunkPacket1.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 32 (0x21L): !config.interface.responsePacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 33 (0x22L): !config.interface.responsePacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 34 (0x23L): !config.interface.cookieReplyPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 35 (0x24L): !config.interface.cookieReplyPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 36 (0x25L): config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
        flag 37 (0x26L): config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
        flag 38 (0x27L): !config.interface.specialJunkPacket3.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 39 (0x28L): !config.interface.specialJunkPacket3.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 40 (0x29L): !config.interface.mtu.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 41 (0x2aL): !config.interface.mtu.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 42 (0x2bL): !config.interface.junkPacketMaxSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 43 (0x2cL): !config.interface.junkPacketMaxSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 44 (0x2dL): !config.interface.listenPort.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 45 (0x2eL): !config.interface.listenPort.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 46 (0x2fL): !config.interface.controlledJunkPacket3.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 47 (0x30L): !config.interface.controlledJunkPacket3.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 48 (0x31L): !config.interface.underloadPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 49 (0x32L): !config.interface.underloadPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 50 (0x33L): !config.interface.transportPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 51 (0x34L): !config.interface.transportPacketJunkSize.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 52 (0x35L): !config.interface.controlledJunkPacket2.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 53 (0x36L): !config.interface.controlledJunkPacket2.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 54 (0x37L): !config.interface.specialJunkPacket2.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 55 (0x38L): !config.interface.specialJunkPacket2.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 56 (0x39L): !config.interface.specialJunkPacket4.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 57 (0x3aL): !config.interface.specialJunkPacket4.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 58 (0x3bL): !config.interface.transportPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 59 (0x3cL): !config.interface.transportPacketMagicHeader.isPresent() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 60 (0x3dL): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
        flag 61 (0x3eL): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
    flag mapping end*/
    //end
}