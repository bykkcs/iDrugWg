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
        sViewsWithIds.put(R.id.tunnel_detail_card, 57);
        sViewsWithIds.put(R.id.interface_title, 58);
        sViewsWithIds.put(R.id.interface_name_label, 59);
        sViewsWithIds.put(R.id.public_key_label, 60);
        sViewsWithIds.put(R.id.listen_port_mtu_barrier, 61);
        sViewsWithIds.put(R.id.idrugconnections_barrier, 62);
        sViewsWithIds.put(R.id.applications_label, 63);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    private androidx.databinding.ObservableList<pw.idrug.connections.viewmodel.PeerProxy> mOldConfigPeers;
    private int mOldAndroidLayoutTunnelDetailPeer;
    // listeners
    private OnClickListenerImpl mClipboardUtilsCopyTextViewAndroidViewViewOnClickListener;
    private OnBeforeCheckedChangeListenerImpl mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener;
    // Inverse Binding Event Handlers

    public TunnelDetailFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 64, sIncludes, sViewsWithIds));
    }
    private TunnelDetailFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[63]
            , (android.widget.TextView) bindings[55]
            , (android.widget.TextView) bindings[47]
            , (android.widget.TextView) bindings[48]
            , (android.widget.TextView) bindings[49]
            , (android.widget.TextView) bindings[50]
            , (android.widget.TextView) bindings[51]
            , (android.widget.TextView) bindings[52]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (androidx.constraintlayout.widget.Barrier) bindings[62]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[59]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (com.google.android.material.textview.MaterialTextView) bindings[58]
            , (android.widget.TextView) bindings[53]
            , (android.widget.TextView) bindings[54]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[11]
            , (androidx.constraintlayout.widget.Barrier) bindings[61]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[14]
            , (android.widget.LinearLayout) bindings[56]
            , (android.widget.TextView) bindings[60]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[42]
            , (android.widget.TextView) bindings[43]
            , (android.widget.TextView) bindings[44]
            , (android.widget.TextView) bindings[45]
            , (android.widget.TextView) bindings[46]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[35]
            , (android.widget.TextView) bindings[36]
            , (com.google.android.material.card.MaterialCardView) bindings[57]
            , (pw.idrug.connections.widget.ToggleSwitch) bindings[2]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[34]
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
        this.interfaceQuicBadge.setTag(null);
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
                mDirtyFlags = 0x800000000L;
                mDirtyFlags_1 = 0x0L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0 || mDirtyFlags_1 != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.config == variableId) {
            setConfig((pw.idrug.connections.viewmodel.ConfigProxy) variable);
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

    public void setConfig(@Nullable pw.idrug.connections.viewmodel.ConfigProxy Config) {
        this.mConfig = Config;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.config);
        super.requestRebind();
    }
    public void setFragment(@Nullable pw.idrug.connections.fragment.TunnelDetailFragment Fragment) {
        this.mFragment = Fragment;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.fragment);
        super.requestRebind();
    }
    public void setTunnel(@Nullable pw.idrug.connections.model.ObservableTunnel Tunnel) {
        updateRegistration(4, Tunnel);
        this.mTunnel = Tunnel;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.tunnel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeConfigInterfaceExcludedApplications((androidx.databinding.ObservableList<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeConfigInterface((pw.idrug.connections.viewmodel.InterfaceProxy) object, fieldId);
            case 2 :
                return onChangeConfigPeers((androidx.databinding.ObservableList<pw.idrug.connections.viewmodel.PeerProxy>) object, fieldId);
            case 3 :
                return onChangeConfigInterfaceIncludedApplications((androidx.databinding.ObservableList<java.lang.String>) object, fieldId);
            case 4 :
                return onChangeTunnel((pw.idrug.connections.model.ObservableTunnel) object, fieldId);
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
    private boolean onChangeConfigInterface(pw.idrug.connections.viewmodel.InterfaceProxy ConfigInterface, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.publicKey) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.addresses) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        else if (fieldId == BR.dnsServers) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
            }
            return true;
        }
        else if (fieldId == BR.dnsSearchDomains) {
            synchronized(this) {
                    mDirtyFlags |= 0x400L;
            }
            return true;
        }
        else if (fieldId == BR.listenPort) {
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
        else if (fieldId == BR.transportPacketJunkSize) {
            synchronized(this) {
                    mDirtyFlags |= 0x40000L;
            }
            return true;
        }
        else if (fieldId == BR.initPacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x80000L;
            }
            return true;
        }
        else if (fieldId == BR.responsePacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x100000L;
            }
            return true;
        }
        else if (fieldId == BR.underloadPacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x200000L;
            }
            return true;
        }
        else if (fieldId == BR.transportPacketMagicHeader) {
            synchronized(this) {
                    mDirtyFlags |= 0x400000L;
            }
            return true;
        }
        else if (fieldId == BR.specialJunkPacket1) {
            synchronized(this) {
                    mDirtyFlags |= 0x800000L;
            }
            return true;
        }
        else if (fieldId == BR.specialJunkPacket2) {
            synchronized(this) {
                    mDirtyFlags |= 0x1000000L;
            }
            return true;
        }
        else if (fieldId == BR.specialJunkPacket3) {
            synchronized(this) {
                    mDirtyFlags |= 0x2000000L;
            }
            return true;
        }
        else if (fieldId == BR.specialJunkPacket4) {
            synchronized(this) {
                    mDirtyFlags |= 0x4000000L;
            }
            return true;
        }
        else if (fieldId == BR.specialJunkPacket5) {
            synchronized(this) {
                    mDirtyFlags |= 0x8000000L;
            }
            return true;
        }
        else if (fieldId == BR.controlledJunkPacket1) {
            synchronized(this) {
                    mDirtyFlags |= 0x10000000L;
            }
            return true;
        }
        else if (fieldId == BR.controlledJunkPacket2) {
            synchronized(this) {
                    mDirtyFlags |= 0x20000000L;
            }
            return true;
        }
        else if (fieldId == BR.controlledJunkPacket3) {
            synchronized(this) {
                    mDirtyFlags |= 0x40000000L;
            }
            return true;
        }
        else if (fieldId == BR.itimeSeconds) {
            synchronized(this) {
                    mDirtyFlags |= 0x80000000L;
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
    private boolean onChangeConfigPeers(androidx.databinding.ObservableList<pw.idrug.connections.viewmodel.PeerProxy> ConfigPeers, int fieldId) {
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
    private boolean onChangeTunnel(pw.idrug.connections.model.ObservableTunnel Tunnel, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.quicReadyBadge) {
            synchronized(this) {
                    mDirtyFlags |= 0x100000000L;
            }
            return true;
        }
        else if (fieldId == BR.state) {
            synchronized(this) {
                    mDirtyFlags |= 0x200000000L;
            }
            return true;
        }
        else if (fieldId == BR.name) {
            synchronized(this) {
                    mDirtyFlags |= 0x400000000L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        long dirtyFlags_1 = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
            dirtyFlags_1 = mDirtyFlags_1;
            mDirtyFlags_1 = 0;
        }
        int configInterfaceSpecialJunkPacket5IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceSpecialJunkPacket5IsEmpty = false;
        androidx.databinding.ObservableList<java.lang.String> configInterfaceExcludedApplications = null;
        java.lang.String configInterfaceJunkPacketMaxSize = null;
        java.lang.String configInterfaceSpecialJunkPacket3 = null;
        java.lang.String configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        int configInterfaceIncludedApplicationsSize = 0;
        int configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceResponsePacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceUnderloadPacketMagicHeader = null;
        boolean configInterfaceResponsePacketJunkSizeIsEmpty = false;
        boolean configInterfaceControlledJunkPacket1IsEmpty = false;
        pw.idrug.connections.viewmodel.ConfigProxy config = mConfig;
        java.lang.String configInterfaceControlledJunkPacket1 = null;
        int configInterfaceTransportPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceTransportPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceSpecialJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceInitPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceExcludedApplicationsSize = 0;
        int configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceListenPortIsEmpty = false;
        int configInterfaceSpecialJunkPacket4IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        pw.idrug.connections.viewmodel.InterfaceProxy configInterface = null;
        int configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = null;
        boolean configInterfaceTransportPacketMagicHeaderIsEmpty = false;
        int configInterfaceUnderloadPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceSpecialJunkPacket4 = null;
        boolean configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse = false;
        int configInterfaceInitPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String tunnelName = null;
        boolean configInterfaceSpecialJunkPacket1IsEmpty = false;
        boolean configInterfaceInitPacketJunkSizeIsEmpty = false;
        androidx.databinding.ObservableList<pw.idrug.connections.viewmodel.PeerProxy> configPeers = null;
        boolean configInterfaceDnsSearchDomainsIsEmpty = false;
        boolean configInterfaceSpecialJunkPacket3IsEmpty = false;
        int configInterfaceMtuIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        androidx.databinding.ObservableList<java.lang.String> configInterfaceIncludedApplications = null;
        java.lang.String configInterfaceDnsServers = null;
        boolean configInterfaceJunkPacketCountIsEmpty = false;
        boolean configInterfaceInitPacketMagicHeaderIsEmpty = false;
        int configInterfaceResponsePacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceInitPacketMagicHeader = null;
        boolean configInterfaceExcludedApplicationsIsEmpty = false;
        java.lang.String configInterfaceTransportPacketJunkSize = null;
        java.lang.String configInterfaceTransportPacketMagicHeader = null;
        boolean configInterfaceIncludedApplicationsIsEmpty = false;
        int configInterfaceControlledJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceAddressesIsEmpty = false;
        boolean configInterfaceTransportPacketJunkSizeIsEmpty = false;
        boolean tunnelQuicReadyBadge = false;
        pw.idrug.connections.fragment.TunnelDetailFragment fragment = mFragment;
        int configInterfaceControlledJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceSpecialJunkPacket1 = null;
        java.lang.String configInterfaceSpecialJunkPacket5 = null;
        boolean tunnelStateStateUP = false;
        boolean configInterfaceResponsePacketMagicHeaderIsEmpty = false;
        java.lang.String applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = null;
        java.lang.String configInterfaceMtu = null;
        java.lang.String configInterfaceItimeSeconds = null;
        int configInterfaceJunkPacketCountIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        org.amnezia.awg.backend.Tunnel.State tunnelState = null;
        java.lang.String configInterfaceControlledJunkPacket3 = null;
        boolean configInterfaceMtuIsEmpty = false;
        boolean configInterfaceJunkPacketMinSizeIsEmpty = false;
        java.lang.String configInterfaceResponsePacketJunkSize = null;
        pw.idrug.connections.widget.ToggleSwitch.OnBeforeCheckedChangeListener fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = null;
        int configInterfaceJunkPacketMaxSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceResponsePacketMagicHeader = null;
        int configInterfaceSpecialJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceControlledJunkPacket3IsEmpty = false;
        int configInterfaceJunkPacketMinSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int configInterfaceListenPortIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceUnderloadPacketMagicHeaderIsEmpty = false;
        java.lang.String configInterfaceSpecialJunkPacket2 = null;
        boolean configInterfaceControlledJunkPacket2IsEmpty = false;
        java.lang.String configInterfaceDnsSearchDomains = null;
        int configInterfaceControlledJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        boolean configInterfaceItimeSecondsIsEmpty = false;
        java.lang.String configInterfaceJunkPacketCount = null;
        boolean configInterfaceSpecialJunkPacket4IsEmpty = false;
        java.lang.String configInterfacePublicKey = null;
        int configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceJunkPacketMinSize = null;
        int configInterfaceItimeSecondsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        java.lang.String configInterfaceControlledJunkPacket2 = null;
        boolean configInterfaceSpecialJunkPacket2IsEmpty = false;
        java.lang.String configInterfaceListenPort = null;
        int configInterfaceSpecialJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = 0;
        int tunnelQuicReadyBadgeViewVISIBLEViewGONE = 0;
        boolean configInterfaceDnsServersIsEmpty = false;
        boolean configInterfaceJunkPacketMaxSizeIsEmpty = false;
        java.lang.String configInterfaceAddresses = null;
        java.lang.String configInterfaceInitPacketJunkSize = null;
        pw.idrug.connections.model.ObservableTunnel tunnel = mTunnel;

        if ((dirtyFlags & 0x8ffffffafL) != 0) {


            if ((dirtyFlags & 0x8ffffffabL) != 0) {

                    if (config != null) {
                        // read config.interface
                        configInterface = config.getInterface();
                    }
                    updateRegistration(1, configInterface);

                if ((dirtyFlags & 0x800008022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.junkPacketMaxSize
                            configInterfaceJunkPacketMaxSize = configInterface.getJunkPacketMaxSize();
                        }


                        if (configInterfaceJunkPacketMaxSize != null) {
                            // read config.interface.junkPacketMaxSize.isEmpty()
                            configInterfaceJunkPacketMaxSizeIsEmpty = configInterfaceJunkPacketMaxSize.isEmpty();
                        }
                    if((dirtyFlags & 0x800008022L) != 0) {
                        if(configInterfaceJunkPacketMaxSizeIsEmpty) {
                                dirtyFlags_1 |= 0x800L;
                        }
                        else {
                                dirtyFlags_1 |= 0x400L;
                        }
                    }


                        // read config.interface.junkPacketMaxSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceJunkPacketMaxSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceJunkPacketMaxSizeIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x802000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.specialJunkPacket3
                            configInterfaceSpecialJunkPacket3 = configInterface.getSpecialJunkPacket3();
                        }


                        if (configInterfaceSpecialJunkPacket3 != null) {
                            // read config.interface.specialJunkPacket3.isEmpty()
                            configInterfaceSpecialJunkPacket3IsEmpty = configInterfaceSpecialJunkPacket3.isEmpty();
                        }
                    if((dirtyFlags & 0x802000022L) != 0) {
                        if(configInterfaceSpecialJunkPacket3IsEmpty) {
                                dirtyFlags_1 |= 0x2000L;
                        }
                        else {
                                dirtyFlags_1 |= 0x1000L;
                        }
                    }


                        // read config.interface.specialJunkPacket3.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceSpecialJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket3IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800200022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.underloadPacketMagicHeader
                            configInterfaceUnderloadPacketMagicHeader = configInterface.getUnderloadPacketMagicHeader();
                        }


                        if (configInterfaceUnderloadPacketMagicHeader != null) {
                            // read config.interface.underloadPacketMagicHeader.isEmpty()
                            configInterfaceUnderloadPacketMagicHeaderIsEmpty = configInterfaceUnderloadPacketMagicHeader.isEmpty();
                        }
                    if((dirtyFlags & 0x800200022L) != 0) {
                        if(configInterfaceUnderloadPacketMagicHeaderIsEmpty) {
                                dirtyFlags |= 0x800000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x400000000000000L;
                        }
                    }


                        // read config.interface.underloadPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceUnderloadPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceUnderloadPacketMagicHeaderIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x810000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.controlledJunkPacket1
                            configInterfaceControlledJunkPacket1 = configInterface.getControlledJunkPacket1();
                        }


                        if (configInterfaceControlledJunkPacket1 != null) {
                            // read config.interface.controlledJunkPacket1.isEmpty()
                            configInterfaceControlledJunkPacket1IsEmpty = configInterfaceControlledJunkPacket1.isEmpty();
                        }
                    if((dirtyFlags & 0x810000022L) != 0) {
                        if(configInterfaceControlledJunkPacket1IsEmpty) {
                                dirtyFlags_1 |= 0x80000L;
                        }
                        else {
                                dirtyFlags_1 |= 0x40000L;
                        }
                    }


                        // read config.interface.controlledJunkPacket1.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceControlledJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceControlledJunkPacket1IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x804000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.specialJunkPacket4
                            configInterfaceSpecialJunkPacket4 = configInterface.getSpecialJunkPacket4();
                        }


                        if (configInterfaceSpecialJunkPacket4 != null) {
                            // read config.interface.specialJunkPacket4.isEmpty()
                            configInterfaceSpecialJunkPacket4IsEmpty = configInterfaceSpecialJunkPacket4.isEmpty();
                        }
                    if((dirtyFlags & 0x804000022L) != 0) {
                        if(configInterfaceSpecialJunkPacket4IsEmpty) {
                                dirtyFlags |= 0x80000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x40000000000000L;
                        }
                    }


                        // read config.interface.specialJunkPacket4.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceSpecialJunkPacket4IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket4IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x80000002bL) != 0) {

                        if (configInterface != null) {
                            // read config.interface.includedApplications
                            configInterfaceIncludedApplications = configInterface.getIncludedApplications();
                        }
                        updateRegistration(3, configInterfaceIncludedApplications);


                        if (configInterfaceIncludedApplications != null) {
                            // read config.interface.includedApplications.isEmpty()
                            configInterfaceIncludedApplicationsIsEmpty = configInterfaceIncludedApplications.isEmpty();
                        }
                    if((dirtyFlags & 0x80000002bL) != 0) {
                        if(configInterfaceIncludedApplicationsIsEmpty) {
                                dirtyFlags |= 0x8000000000L;
                                dirtyFlags |= 0x2000000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x4000000000L;
                                dirtyFlags |= 0x1000000000000000L;
                        }
                    }
                }
                if ((dirtyFlags & 0x800000222L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.dnsServers
                            configInterfaceDnsServers = configInterface.getDnsServers();
                        }


                        if (configInterfaceDnsServers != null) {
                            // read config.interface.dnsServers.isEmpty()
                            configInterfaceDnsServersIsEmpty = configInterfaceDnsServers.isEmpty();
                        }
                    if((dirtyFlags & 0x800000222L) != 0) {
                        if(configInterfaceDnsServersIsEmpty) {
                                dirtyFlags |= 0x20000000000L;
                        }
                        else {
                                dirtyFlags |= 0x10000000000L;
                        }
                    }


                        // read config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceDnsServersIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800080022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.initPacketMagicHeader
                            configInterfaceInitPacketMagicHeader = configInterface.getInitPacketMagicHeader();
                        }


                        if (configInterfaceInitPacketMagicHeader != null) {
                            // read config.interface.initPacketMagicHeader.isEmpty()
                            configInterfaceInitPacketMagicHeaderIsEmpty = configInterfaceInitPacketMagicHeader.isEmpty();
                        }
                    if((dirtyFlags & 0x800080022L) != 0) {
                        if(configInterfaceInitPacketMagicHeaderIsEmpty) {
                                dirtyFlags |= 0x8000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x4000000000000L;
                        }
                    }


                        // read config.interface.initPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceInitPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceInitPacketMagicHeaderIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800040022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.transportPacketJunkSize
                            configInterfaceTransportPacketJunkSize = configInterface.getTransportPacketJunkSize();
                        }


                        if (configInterfaceTransportPacketJunkSize != null) {
                            // read config.interface.transportPacketJunkSize.isEmpty()
                            configInterfaceTransportPacketJunkSizeIsEmpty = configInterfaceTransportPacketJunkSize.isEmpty();
                        }
                    if((dirtyFlags & 0x800040022L) != 0) {
                        if(configInterfaceTransportPacketJunkSizeIsEmpty) {
                                dirtyFlags |= 0x200000000000L;
                        }
                        else {
                                dirtyFlags |= 0x100000000000L;
                        }
                    }


                        // read config.interface.transportPacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceTransportPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceTransportPacketJunkSizeIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800400022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.transportPacketMagicHeader
                            configInterfaceTransportPacketMagicHeader = configInterface.getTransportPacketMagicHeader();
                        }


                        if (configInterfaceTransportPacketMagicHeader != null) {
                            // read config.interface.transportPacketMagicHeader.isEmpty()
                            configInterfaceTransportPacketMagicHeaderIsEmpty = configInterfaceTransportPacketMagicHeader.isEmpty();
                        }
                    if((dirtyFlags & 0x800400022L) != 0) {
                        if(configInterfaceTransportPacketMagicHeaderIsEmpty) {
                                dirtyFlags |= 0x800000000000L;
                        }
                        else {
                                dirtyFlags |= 0x400000000000L;
                        }
                    }


                        // read config.interface.transportPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceTransportPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceTransportPacketMagicHeaderIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800800022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.specialJunkPacket1
                            configInterfaceSpecialJunkPacket1 = configInterface.getSpecialJunkPacket1();
                        }


                        if (configInterfaceSpecialJunkPacket1 != null) {
                            // read config.interface.specialJunkPacket1.isEmpty()
                            configInterfaceSpecialJunkPacket1IsEmpty = configInterfaceSpecialJunkPacket1.isEmpty();
                        }
                    if((dirtyFlags & 0x800800022L) != 0) {
                        if(configInterfaceSpecialJunkPacket1IsEmpty) {
                                dirtyFlags |= 0x2000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x1000000000000L;
                        }
                    }


                        // read config.interface.specialJunkPacket1.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceSpecialJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket1IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x808000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.specialJunkPacket5
                            configInterfaceSpecialJunkPacket5 = configInterface.getSpecialJunkPacket5();
                        }


                        if (configInterfaceSpecialJunkPacket5 != null) {
                            // read config.interface.specialJunkPacket5.isEmpty()
                            configInterfaceSpecialJunkPacket5IsEmpty = configInterfaceSpecialJunkPacket5.isEmpty();
                        }
                    if((dirtyFlags & 0x808000022L) != 0) {
                        if(configInterfaceSpecialJunkPacket5IsEmpty) {
                                dirtyFlags |= 0x2000000000L;
                        }
                        else {
                                dirtyFlags |= 0x1000000000L;
                        }
                    }


                        // read config.interface.specialJunkPacket5.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceSpecialJunkPacket5IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket5IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800001022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.mtu
                            configInterfaceMtu = configInterface.getMtu();
                        }


                        if (configInterfaceMtu != null) {
                            // read config.interface.mtu.isEmpty()
                            configInterfaceMtuIsEmpty = configInterfaceMtu.isEmpty();
                        }
                    if((dirtyFlags & 0x800001022L) != 0) {
                        if(configInterfaceMtuIsEmpty) {
                                dirtyFlags_1 |= 0x2L;
                        }
                        else {
                                dirtyFlags_1 |= 0x1L;
                        }
                    }


                        // read config.interface.mtu.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceMtuIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceMtuIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x880000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.itimeSeconds
                            configInterfaceItimeSeconds = configInterface.getItimeSeconds();
                        }


                        if (configInterfaceItimeSeconds != null) {
                            // read config.interface.itimeSeconds.isEmpty()
                            configInterfaceItimeSecondsIsEmpty = configInterfaceItimeSeconds.isEmpty();
                        }
                    if((dirtyFlags & 0x880000022L) != 0) {
                        if(configInterfaceItimeSecondsIsEmpty) {
                                dirtyFlags_1 |= 0x800000L;
                        }
                        else {
                                dirtyFlags_1 |= 0x400000L;
                        }
                    }


                        // read config.interface.itimeSeconds.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceItimeSecondsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceItimeSecondsIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x840000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.controlledJunkPacket3
                            configInterfaceControlledJunkPacket3 = configInterface.getControlledJunkPacket3();
                        }


                        if (configInterfaceControlledJunkPacket3 != null) {
                            // read config.interface.controlledJunkPacket3.isEmpty()
                            configInterfaceControlledJunkPacket3IsEmpty = configInterfaceControlledJunkPacket3.isEmpty();
                        }
                    if((dirtyFlags & 0x840000022L) != 0) {
                        if(configInterfaceControlledJunkPacket3IsEmpty) {
                                dirtyFlags_1 |= 0x20L;
                        }
                        else {
                                dirtyFlags_1 |= 0x10L;
                        }
                    }


                        // read config.interface.controlledJunkPacket3.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceControlledJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceControlledJunkPacket3IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800020022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.responsePacketJunkSize
                            configInterfaceResponsePacketJunkSize = configInterface.getResponsePacketJunkSize();
                        }


                        if (configInterfaceResponsePacketJunkSize != null) {
                            // read config.interface.responsePacketJunkSize.isEmpty()
                            configInterfaceResponsePacketJunkSizeIsEmpty = configInterfaceResponsePacketJunkSize.isEmpty();
                        }
                    if((dirtyFlags & 0x800020022L) != 0) {
                        if(configInterfaceResponsePacketJunkSizeIsEmpty) {
                                dirtyFlags_1 |= 0x8L;
                        }
                        else {
                                dirtyFlags_1 |= 0x4L;
                        }
                    }


                        // read config.interface.responsePacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceResponsePacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceResponsePacketJunkSizeIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800100022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.responsePacketMagicHeader
                            configInterfaceResponsePacketMagicHeader = configInterface.getResponsePacketMagicHeader();
                        }


                        if (configInterfaceResponsePacketMagicHeader != null) {
                            // read config.interface.responsePacketMagicHeader.isEmpty()
                            configInterfaceResponsePacketMagicHeaderIsEmpty = configInterfaceResponsePacketMagicHeader.isEmpty();
                        }
                    if((dirtyFlags & 0x800100022L) != 0) {
                        if(configInterfaceResponsePacketMagicHeaderIsEmpty) {
                                dirtyFlags |= 0x80000000000L;
                        }
                        else {
                                dirtyFlags |= 0x40000000000L;
                        }
                    }


                        // read config.interface.responsePacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceResponsePacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceResponsePacketMagicHeaderIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x801000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.specialJunkPacket2
                            configInterfaceSpecialJunkPacket2 = configInterface.getSpecialJunkPacket2();
                        }


                        if (configInterfaceSpecialJunkPacket2 != null) {
                            // read config.interface.specialJunkPacket2.isEmpty()
                            configInterfaceSpecialJunkPacket2IsEmpty = configInterfaceSpecialJunkPacket2.isEmpty();
                        }
                    if((dirtyFlags & 0x801000022L) != 0) {
                        if(configInterfaceSpecialJunkPacket2IsEmpty) {
                                dirtyFlags_1 |= 0x2000000L;
                        }
                        else {
                                dirtyFlags_1 |= 0x1000000L;
                        }
                    }


                        // read config.interface.specialJunkPacket2.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceSpecialJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceSpecialJunkPacket2IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800000422L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.dnsSearchDomains
                            configInterfaceDnsSearchDomains = configInterface.getDnsSearchDomains();
                        }


                        if (configInterfaceDnsSearchDomains != null) {
                            // read config.interface.dnsSearchDomains.isEmpty()
                            configInterfaceDnsSearchDomainsIsEmpty = configInterfaceDnsSearchDomains.isEmpty();
                        }
                    if((dirtyFlags & 0x800000422L) != 0) {
                        if(configInterfaceDnsSearchDomainsIsEmpty) {
                                dirtyFlags |= 0x20000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x10000000000000L;
                        }
                    }


                        // read config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceDnsSearchDomainsIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800002022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.junkPacketCount
                            configInterfaceJunkPacketCount = configInterface.getJunkPacketCount();
                        }


                        if (configInterfaceJunkPacketCount != null) {
                            // read config.interface.junkPacketCount.isEmpty()
                            configInterfaceJunkPacketCountIsEmpty = configInterfaceJunkPacketCount.isEmpty();
                        }
                    if((dirtyFlags & 0x800002022L) != 0) {
                        if(configInterfaceJunkPacketCountIsEmpty) {
                                dirtyFlags_1 |= 0x200L;
                        }
                        else {
                                dirtyFlags_1 |= 0x100L;
                        }
                    }


                        // read config.interface.junkPacketCount.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceJunkPacketCountIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceJunkPacketCountIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x8000000a2L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.publicKey
                            configInterfacePublicKey = configInterface.getPublicKey();
                        }
                }
                if ((dirtyFlags & 0x800004022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.junkPacketMinSize
                            configInterfaceJunkPacketMinSize = configInterface.getJunkPacketMinSize();
                        }


                        if (configInterfaceJunkPacketMinSize != null) {
                            // read config.interface.junkPacketMinSize.isEmpty()
                            configInterfaceJunkPacketMinSizeIsEmpty = configInterfaceJunkPacketMinSize.isEmpty();
                        }
                    if((dirtyFlags & 0x800004022L) != 0) {
                        if(configInterfaceJunkPacketMinSizeIsEmpty) {
                                dirtyFlags_1 |= 0x8000L;
                        }
                        else {
                                dirtyFlags_1 |= 0x4000L;
                        }
                    }


                        // read config.interface.junkPacketMinSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceJunkPacketMinSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceJunkPacketMinSizeIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x820000022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.controlledJunkPacket2
                            configInterfaceControlledJunkPacket2 = configInterface.getControlledJunkPacket2();
                        }


                        if (configInterfaceControlledJunkPacket2 != null) {
                            // read config.interface.controlledJunkPacket2.isEmpty()
                            configInterfaceControlledJunkPacket2IsEmpty = configInterfaceControlledJunkPacket2.isEmpty();
                        }
                    if((dirtyFlags & 0x820000022L) != 0) {
                        if(configInterfaceControlledJunkPacket2IsEmpty) {
                                dirtyFlags_1 |= 0x80L;
                        }
                        else {
                                dirtyFlags_1 |= 0x40L;
                        }
                    }


                        // read config.interface.controlledJunkPacket2.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceControlledJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceControlledJunkPacket2IsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800000822L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.listenPort
                            configInterfaceListenPort = configInterface.getListenPort();
                        }


                        if (configInterfaceListenPort != null) {
                            // read config.interface.listenPort.isEmpty()
                            configInterfaceListenPortIsEmpty = configInterfaceListenPort.isEmpty();
                        }
                    if((dirtyFlags & 0x800000822L) != 0) {
                        if(configInterfaceListenPortIsEmpty) {
                                dirtyFlags_1 |= 0x20000L;
                        }
                        else {
                                dirtyFlags_1 |= 0x10000L;
                        }
                    }


                        // read config.interface.listenPort.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceListenPortIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceListenPortIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800000122L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.addresses
                            configInterfaceAddresses = configInterface.getAddresses();
                        }


                        if (configInterfaceAddresses != null) {
                            // read config.interface.addresses.isEmpty()
                            configInterfaceAddressesIsEmpty = configInterfaceAddresses.isEmpty();
                        }
                    if((dirtyFlags & 0x800000122L) != 0) {
                        if(configInterfaceAddressesIsEmpty) {
                                dirtyFlags |= 0x200000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x100000000000000L;
                        }
                    }


                        // read config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceAddressesIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
                if ((dirtyFlags & 0x800010022L) != 0) {

                        if (configInterface != null) {
                            // read config.interface.initPacketJunkSize
                            configInterfaceInitPacketJunkSize = configInterface.getInitPacketJunkSize();
                        }


                        if (configInterfaceInitPacketJunkSize != null) {
                            // read config.interface.initPacketJunkSize.isEmpty()
                            configInterfaceInitPacketJunkSizeIsEmpty = configInterfaceInitPacketJunkSize.isEmpty();
                        }
                    if((dirtyFlags & 0x800010022L) != 0) {
                        if(configInterfaceInitPacketJunkSizeIsEmpty) {
                                dirtyFlags |= 0x8000000000000000L;
                        }
                        else {
                                dirtyFlags |= 0x4000000000000000L;
                        }
                    }


                        // read config.interface.initPacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
                        configInterfaceInitPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceInitPacketJunkSizeIsEmpty) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                }
            }
            if ((dirtyFlags & 0x800000024L) != 0) {

                    if (config != null) {
                        // read config.peers
                        configPeers = config.getPeers();
                    }
                    updateRegistration(2, configPeers);
            }
        }
        if ((dirtyFlags & 0x800000040L) != 0) {



                if (fragment != null) {
                    // read fragment::setTunnelState
                    fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = (((mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener == null) ? (mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener = new OnBeforeCheckedChangeListenerImpl()) : mFragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener).setValue(fragment));
                }
        }
        if ((dirtyFlags & 0xf00000010L) != 0) {


            if ((dirtyFlags & 0xc00000010L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.name
                        tunnelName = tunnel.getName();
                    }
            }
            if ((dirtyFlags & 0x900000010L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.quicReadyBadge
                        tunnelQuicReadyBadge = tunnel.getQuicReadyBadge();
                    }
                if((dirtyFlags & 0x900000010L) != 0) {
                    if(tunnelQuicReadyBadge) {
                            dirtyFlags_1 |= 0x8000000L;
                    }
                    else {
                            dirtyFlags_1 |= 0x4000000L;
                    }
                }


                    // read tunnel.quicReadyBadge ? View.VISIBLE : View.GONE
                    tunnelQuicReadyBadgeViewVISIBLEViewGONE = ((tunnelQuicReadyBadge) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0xa00000010L) != 0) {

                    if (tunnel != null) {
                        // read tunnel.state
                        tunnelState = tunnel.getState();
                    }


                    // read tunnel.state == State.UP
                    tunnelStateStateUP = (tunnelState) == (org.amnezia.awg.backend.Tunnel.State.UP);
            }
        }
        // batch finished

        if ((dirtyFlags & 0x2000008000000000L) != 0) {

                if (configInterface != null) {
                    // read config.interface.excludedApplications
                    configInterfaceExcludedApplications = configInterface.getExcludedApplications();
                }
                updateRegistration(0, configInterfaceExcludedApplications);

            if ((dirtyFlags & 0x8000000000L) != 0) {

                    if (configInterfaceExcludedApplications != null) {
                        // read config.interface.excludedApplications.size()
                        configInterfaceExcludedApplicationsSize = configInterfaceExcludedApplications.size();
                    }


                    // read @android:plurals/n_excluded_applications
                    applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
                    // read @android:plurals/n_excluded_applications
                    applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_excluded_applications, configInterfaceExcludedApplicationsSize, configInterfaceExcludedApplicationsSize);
            }
            if ((dirtyFlags & 0x2000000000000000L) != 0) {

                    if (configInterfaceExcludedApplications != null) {
                        // read config.interface.excludedApplications.isEmpty()
                        configInterfaceExcludedApplicationsIsEmpty = configInterfaceExcludedApplications.isEmpty();
                    }
            }
        }
        if ((dirtyFlags & 0x4000000000L) != 0) {

                if (configInterfaceIncludedApplications != null) {
                    // read config.interface.includedApplications.size()
                    configInterfaceIncludedApplicationsSize = configInterfaceIncludedApplications.size();
                }


                // read @android:plurals/n_included_applications
                applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_included_applications, configInterfaceIncludedApplicationsSize, configInterfaceIncludedApplicationsSize);
                // read @android:plurals/n_included_applications
                applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = applicationsText.getResources().getQuantityString(R.plurals.n_included_applications, configInterfaceIncludedApplicationsSize, configInterfaceIncludedApplicationsSize);
        }

        if ((dirtyFlags & 0x80000002bL) != 0) {

                // read config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
                configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize = ((configInterfaceIncludedApplicationsIsEmpty) ? (applicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSize) : (applicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize));
                // read config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
                configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse = ((configInterfaceIncludedApplicationsIsEmpty) ? (configInterfaceExcludedApplicationsIsEmpty) : (false));
            if((dirtyFlags & 0x80000002bL) != 0) {
                if(configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse) {
                        dirtyFlags_1 |= 0x200000L;
                }
                else {
                        dirtyFlags_1 |= 0x100000L;
                }
            }


                // read config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
                configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE = ((configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalse) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        // batch finished
        if ((dirtyFlags & 0x800000122L) != 0) {
            // api target 1

            this.addressesLabel.setVisibility(configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.addressesText, configInterfaceAddresses);
            this.addressesText.setVisibility(configInterfaceAddressesIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800000000L) != 0) {
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
        if ((dirtyFlags & 0x80000002bL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.applicationsText, configInterfaceIncludedApplicationsIsEmptyApplicationsTextAndroidPluralsNExcludedApplicationsConfigInterfaceExcludedApplicationsSizeConfigInterfaceExcludedApplicationsSizeApplicationsTextAndroidPluralsNIncludedApplicationsConfigInterfaceIncludedApplicationsSizeConfigInterfaceIncludedApplicationsSize);
            this.applicationsText.setVisibility(configInterfaceIncludedApplicationsIsEmptyConfigInterfaceExcludedApplicationsIsEmptyBooleanFalseAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x810000022L) != 0) {
            // api target 1

            this.controlledJunkPacket1Label.setVisibility(configInterfaceControlledJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.controlledJunkPacket1Text, configInterfaceControlledJunkPacket1);
            this.controlledJunkPacket1Text.setVisibility(configInterfaceControlledJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x820000022L) != 0) {
            // api target 1

            this.controlledJunkPacket2Label.setVisibility(configInterfaceControlledJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.controlledJunkPacket2Text, configInterfaceControlledJunkPacket2);
            this.controlledJunkPacket2Text.setVisibility(configInterfaceControlledJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x840000022L) != 0) {
            // api target 1

            this.controlledJunkPacket3Label.setVisibility(configInterfaceControlledJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.controlledJunkPacket3Text, configInterfaceControlledJunkPacket3);
            this.controlledJunkPacket3Text.setVisibility(configInterfaceControlledJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800020022L) != 0) {
            // api target 1

            this.cookieReplyPacketJunkSizeLabel.setVisibility(configInterfaceResponsePacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.cookieReplyPacketJunkSizeText, configInterfaceResponsePacketJunkSize);
            this.cookieReplyPacketJunkSizeText.setVisibility(configInterfaceResponsePacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            this.responsePacketJunkSizeLabel.setVisibility(configInterfaceResponsePacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.responsePacketJunkSizeText, configInterfaceResponsePacketJunkSize);
            this.responsePacketJunkSizeText.setVisibility(configInterfaceResponsePacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800000422L) != 0) {
            // api target 1

            this.dnsSearchDomainsLabel.setVisibility(configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.dnsSearchDomainsText, configInterfaceDnsSearchDomains);
            this.dnsSearchDomainsText.setVisibility(configInterfaceDnsSearchDomainsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800000222L) != 0) {
            // api target 1

            this.dnsServersLabel.setVisibility(configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.dnsServersText, configInterfaceDnsServers);
            this.dnsServersText.setVisibility(configInterfaceDnsServersIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800010022L) != 0) {
            // api target 1

            this.initPacketJunkSizeLabel.setVisibility(configInterfaceInitPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.initPacketJunkSizeText, configInterfaceInitPacketJunkSize);
            this.initPacketJunkSizeText.setVisibility(configInterfaceInitPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800080022L) != 0) {
            // api target 1

            this.initPacketMagicHeaderLabel.setVisibility(configInterfaceInitPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.initPacketMagicHeaderText, configInterfaceInitPacketMagicHeader);
            this.initPacketMagicHeaderText.setVisibility(configInterfaceInitPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0xc00000010L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.interfaceNameText, tunnelName);
        }
        if ((dirtyFlags & 0x900000010L) != 0) {
            // api target 1

            this.interfaceQuicBadge.setVisibility(tunnelQuicReadyBadgeViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0x880000022L) != 0) {
            // api target 1

            this.itimeLabel.setVisibility(configInterfaceItimeSecondsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.itimeText, configInterfaceItimeSeconds);
            this.itimeText.setVisibility(configInterfaceItimeSecondsIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800002022L) != 0) {
            // api target 1

            this.junkPacketCountLabel.setVisibility(configInterfaceJunkPacketCountIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.junkPacketCountText, configInterfaceJunkPacketCount);
            this.junkPacketCountText.setVisibility(configInterfaceJunkPacketCountIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800008022L) != 0) {
            // api target 1

            this.junkPacketMaxSizeLabel.setVisibility(configInterfaceJunkPacketMaxSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.junkPacketMaxSizeText, configInterfaceJunkPacketMaxSize);
            this.junkPacketMaxSizeText.setVisibility(configInterfaceJunkPacketMaxSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800004022L) != 0) {
            // api target 1

            this.junkPacketMinSizeLabel.setVisibility(configInterfaceJunkPacketMinSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.junkPacketMinSizeText, configInterfaceJunkPacketMinSize);
            this.junkPacketMinSizeText.setVisibility(configInterfaceJunkPacketMinSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800000822L) != 0) {
            // api target 1

            this.listenPortLabel.setVisibility(configInterfaceListenPortIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.listenPortText, configInterfaceListenPort);
            this.listenPortText.setVisibility(configInterfaceListenPortIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800001022L) != 0) {
            // api target 1

            this.mtuLabel.setVisibility(configInterfaceMtuIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mtuText, configInterfaceMtu);
            this.mtuText.setVisibility(configInterfaceMtuIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800000024L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setItems(this.peersLayout, this.mOldConfigPeers, this.mOldAndroidLayoutTunnelDetailPeer, configPeers, R.layout.tunnel_detail_peer);
        }
        if ((dirtyFlags & 0x8000000a2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.publicKeyText, configInterfacePublicKey);
        }
        if ((dirtyFlags & 0x800100022L) != 0) {
            // api target 1

            this.responsePacketMagicHeaderLabel.setVisibility(configInterfaceResponsePacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.responsePacketMagicHeaderText, configInterfaceResponsePacketMagicHeader);
            this.responsePacketMagicHeaderText.setVisibility(configInterfaceResponsePacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800800022L) != 0) {
            // api target 1

            this.specialJunkPacket1Label.setVisibility(configInterfaceSpecialJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.specialJunkPacket1Text, configInterfaceSpecialJunkPacket1);
            this.specialJunkPacket1Text.setVisibility(configInterfaceSpecialJunkPacket1IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x801000022L) != 0) {
            // api target 1

            this.specialJunkPacket2Label.setVisibility(configInterfaceSpecialJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.specialJunkPacket2Text, configInterfaceSpecialJunkPacket2);
            this.specialJunkPacket2Text.setVisibility(configInterfaceSpecialJunkPacket2IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x802000022L) != 0) {
            // api target 1

            this.specialJunkPacket3Label.setVisibility(configInterfaceSpecialJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.specialJunkPacket3Text, configInterfaceSpecialJunkPacket3);
            this.specialJunkPacket3Text.setVisibility(configInterfaceSpecialJunkPacket3IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x804000022L) != 0) {
            // api target 1

            this.specialJunkPacket4Label.setVisibility(configInterfaceSpecialJunkPacket4IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.specialJunkPacket4Text, configInterfaceSpecialJunkPacket4);
            this.specialJunkPacket4Text.setVisibility(configInterfaceSpecialJunkPacket4IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x808000022L) != 0) {
            // api target 1

            this.specialJunkPacket5Label.setVisibility(configInterfaceSpecialJunkPacket5IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.specialJunkPacket5Text, configInterfaceSpecialJunkPacket5);
            this.specialJunkPacket5Text.setVisibility(configInterfaceSpecialJunkPacket5IsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800040022L) != 0) {
            // api target 1

            this.transportPacketJunkSizeLabel.setVisibility(configInterfaceTransportPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transportPacketJunkSizeText, configInterfaceTransportPacketJunkSize);
            this.transportPacketJunkSizeText.setVisibility(configInterfaceTransportPacketJunkSizeIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800400022L) != 0) {
            // api target 1

            this.transportPacketMagicHeaderLabel.setVisibility(configInterfaceTransportPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.transportPacketMagicHeaderText, configInterfaceTransportPacketMagicHeader);
            this.transportPacketMagicHeaderText.setVisibility(configInterfaceTransportPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0xa00000010L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setChecked(this.tunnelSwitch, tunnelStateStateUP);
        }
        if ((dirtyFlags & 0x800000040L) != 0) {
            // api target 1

            pw.idrug.connections.databinding.BindingAdapters.setOnBeforeCheckedChanged(this.tunnelSwitch, fragmentSetTunnelStatePwIdrugConnectionsWidgetToggleSwitchOnBeforeCheckedChangeListener);
        }
        if ((dirtyFlags & 0x800200022L) != 0) {
            // api target 1

            this.underloadPacketMagicHeaderLabel.setVisibility(configInterfaceUnderloadPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.underloadPacketMagicHeaderText, configInterfaceUnderloadPacketMagicHeader);
            this.underloadPacketMagicHeaderText.setVisibility(configInterfaceUnderloadPacketMagicHeaderIsEmptyAndroidViewViewGONEAndroidViewViewVISIBLE);
        }
        if ((dirtyFlags & 0x800000024L) != 0) {
            this.mOldConfigPeers = configPeers;
            this.mOldAndroidLayoutTunnelDetailPeer = R.layout.tunnel_detail_peer;
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        @Override
        public void onClick(android.view.View arg0) {
            pw.idrug.connections.util.ClipboardUtils.copyTextView(arg0); 
        }
    }
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
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    private  long mDirtyFlags_1 = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): config.interface.excludedApplications
        flag 1 (0x2L): config.interface
        flag 2 (0x3L): config.peers
        flag 3 (0x4L): config.interface.includedApplications
        flag 4 (0x5L): tunnel
        flag 5 (0x6L): config
        flag 6 (0x7L): fragment
        flag 7 (0x8L): config.interface.publicKey
        flag 8 (0x9L): config.interface.addresses
        flag 9 (0xaL): config.interface.dnsServers
        flag 10 (0xbL): config.interface.dnsSearchDomains
        flag 11 (0xcL): config.interface.listenPort
        flag 12 (0xdL): config.interface.mtu
        flag 13 (0xeL): config.interface.junkPacketCount
        flag 14 (0xfL): config.interface.junkPacketMinSize
        flag 15 (0x10L): config.interface.junkPacketMaxSize
        flag 16 (0x11L): config.interface.initPacketJunkSize
        flag 17 (0x12L): config.interface.responsePacketJunkSize
        flag 18 (0x13L): config.interface.transportPacketJunkSize
        flag 19 (0x14L): config.interface.initPacketMagicHeader
        flag 20 (0x15L): config.interface.responsePacketMagicHeader
        flag 21 (0x16L): config.interface.underloadPacketMagicHeader
        flag 22 (0x17L): config.interface.transportPacketMagicHeader
        flag 23 (0x18L): config.interface.specialJunkPacket1
        flag 24 (0x19L): config.interface.specialJunkPacket2
        flag 25 (0x1aL): config.interface.specialJunkPacket3
        flag 26 (0x1bL): config.interface.specialJunkPacket4
        flag 27 (0x1cL): config.interface.specialJunkPacket5
        flag 28 (0x1dL): config.interface.controlledJunkPacket1
        flag 29 (0x1eL): config.interface.controlledJunkPacket2
        flag 30 (0x1fL): config.interface.controlledJunkPacket3
        flag 31 (0x20L): config.interface.itimeSeconds
        flag 32 (0x21L): tunnel.quicReadyBadge
        flag 33 (0x22L): tunnel.state
        flag 34 (0x23L): tunnel.name
        flag 35 (0x24L): null
        flag 36 (0x25L): config.interface.specialJunkPacket5.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 37 (0x26L): config.interface.specialJunkPacket5.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 38 (0x27L): config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
        flag 39 (0x28L): config.interface.includedApplications.isEmpty() ? @android:plurals/n_excluded_applications : @android:plurals/n_included_applications
        flag 40 (0x29L): config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 41 (0x2aL): config.interface.dnsServers.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 42 (0x2bL): config.interface.responsePacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 43 (0x2cL): config.interface.responsePacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 44 (0x2dL): config.interface.transportPacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 45 (0x2eL): config.interface.transportPacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 46 (0x2fL): config.interface.transportPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 47 (0x30L): config.interface.transportPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 48 (0x31L): config.interface.specialJunkPacket1.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 49 (0x32L): config.interface.specialJunkPacket1.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 50 (0x33L): config.interface.initPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 51 (0x34L): config.interface.initPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 52 (0x35L): config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 53 (0x36L): config.interface.dnsSearchDomains.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 54 (0x37L): config.interface.specialJunkPacket4.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 55 (0x38L): config.interface.specialJunkPacket4.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 56 (0x39L): config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 57 (0x3aL): config.interface.addresses.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 58 (0x3bL): config.interface.underloadPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 59 (0x3cL): config.interface.underloadPacketMagicHeader.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 60 (0x3dL): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
        flag 61 (0x3eL): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false
        flag 62 (0x3fL): config.interface.initPacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 63 (0x40L): config.interface.initPacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 64 (0x41L): config.interface.mtu.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 65 (0x42L): config.interface.mtu.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 66 (0x43L): config.interface.responsePacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 67 (0x44L): config.interface.responsePacketJunkSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 68 (0x45L): config.interface.controlledJunkPacket3.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 69 (0x46L): config.interface.controlledJunkPacket3.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 70 (0x47L): config.interface.controlledJunkPacket2.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 71 (0x48L): config.interface.controlledJunkPacket2.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 72 (0x49L): config.interface.junkPacketCount.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 73 (0x4aL): config.interface.junkPacketCount.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 74 (0x4bL): config.interface.junkPacketMaxSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 75 (0x4cL): config.interface.junkPacketMaxSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 76 (0x4dL): config.interface.specialJunkPacket3.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 77 (0x4eL): config.interface.specialJunkPacket3.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 78 (0x4fL): config.interface.junkPacketMinSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 79 (0x50L): config.interface.junkPacketMinSize.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 80 (0x51L): config.interface.listenPort.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 81 (0x52L): config.interface.listenPort.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 82 (0x53L): config.interface.controlledJunkPacket1.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 83 (0x54L): config.interface.controlledJunkPacket1.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 84 (0x55L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
        flag 85 (0x56L): config.interface.includedApplications.isEmpty() ? config.interface.excludedApplications.isEmpty() : false ? android.view.View.GONE : android.view.View.VISIBLE
        flag 86 (0x57L): config.interface.itimeSeconds.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 87 (0x58L): config.interface.itimeSeconds.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 88 (0x59L): config.interface.specialJunkPacket2.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 89 (0x5aL): config.interface.specialJunkPacket2.isEmpty() ? android.view.View.GONE : android.view.View.VISIBLE
        flag 90 (0x5bL): tunnel.quicReadyBadge ? View.VISIBLE : View.GONE
        flag 91 (0x5cL): tunnel.quicReadyBadge ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}