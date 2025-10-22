package pw.idrug.connections.viewmodel;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\bU\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 k2\u00020\u00012\u00020\u0002:\u0002jkB\u0011\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0004\b\u0005\u0010\tB\t\b\u0016\u00a2\u0006\u0004\b\u0005\u0010\nJ\b\u0010b\u001a\u00020cH\u0016J\u0006\u0010d\u001a\u00020eJ\u0006\u0010f\u001a\u00020\bJ\u0018\u0010g\u001a\u00020e2\u0006\u0010h\u001a\u00020\u00042\u0006\u0010i\u001a\u00020cH\u0016R\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR&\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R&\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R&\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R&\u0010!\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R&\u0010$\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017R&\u0010\'\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0015\"\u0004\b)\u0010\u0017R&\u0010*\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0015\"\u0004\b,\u0010\u0017R&\u0010-\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0015\"\u0004\b/\u0010\u0017R&\u00100\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0015\"\u0004\b2\u0010\u0017R&\u00103\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0015\"\u0004\b5\u0010\u0017R&\u00106\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0015\"\u0004\b8\u0010\u0017R&\u00109\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0015\"\u0004\b;\u0010\u0017R&\u0010<\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0015\"\u0004\b>\u0010\u0017R&\u0010?\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0015\"\u0004\bA\u0010\u0017R&\u0010B\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0015\"\u0004\bD\u0010\u0017R&\u0010E\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0015\"\u0004\bG\u0010\u0017R&\u0010H\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0015\"\u0004\bJ\u0010\u0017R&\u0010K\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0015\"\u0004\bM\u0010\u0017R&\u0010N\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0015\"\u0004\bP\u0010\u0017R&\u0010Q\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0015\"\u0004\bS\u0010\u0017R&\u0010T\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0015\"\u0004\bV\u0010\u0017R&\u0010W\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\u0015\"\u0004\bY\u0010\u0017R&\u0010Z\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\u0015\"\u0004\b\\\u0010\u0017R&\u0010]\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r8G@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u0015\"\u0004\b_\u0010\u0017R\u0011\u0010`\u001a\u00020\r8G\u00a2\u0006\u0006\u001a\u0004\ba\u0010\u0015\u00a8\u0006l"}, d2 = {"Lpw/idrug/connections/viewmodel/InterfaceProxy;", "Landroidx/databinding/BaseObservable;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "<init>", "(Landroid/os/Parcel;)V", "other", "Lpw/idrug/connections/config/Interface;", "(Lpw/idrug/connections/config/Interface;)V", "()V", "excludedApplications", "Landroidx/databinding/ObservableList;", "", "getExcludedApplications", "()Landroidx/databinding/ObservableList;", "includedApplications", "getIncludedApplications", "value", "addresses", "getAddresses", "()Ljava/lang/String;", "setAddresses", "(Ljava/lang/String;)V", "dnsServers", "getDnsServers", "setDnsServers", "listenPort", "getListenPort", "setListenPort", "mtu", "getMtu", "setMtu", "junkPacketCount", "getJunkPacketCount", "setJunkPacketCount", "junkPacketMinSize", "getJunkPacketMinSize", "setJunkPacketMinSize", "junkPacketMaxSize", "getJunkPacketMaxSize", "setJunkPacketMaxSize", "initPacketJunkSize", "getInitPacketJunkSize", "setInitPacketJunkSize", "responsePacketJunkSize", "getResponsePacketJunkSize", "setResponsePacketJunkSize", "cookieReplyPacketJunkSize", "getCookieReplyPacketJunkSize", "setCookieReplyPacketJunkSize", "transportPacketJunkSize", "getTransportPacketJunkSize", "setTransportPacketJunkSize", "initPacketMagicHeader", "getInitPacketMagicHeader", "setInitPacketMagicHeader", "responsePacketMagicHeader", "getResponsePacketMagicHeader", "setResponsePacketMagicHeader", "underloadPacketMagicHeader", "getUnderloadPacketMagicHeader", "setUnderloadPacketMagicHeader", "transportPacketMagicHeader", "getTransportPacketMagicHeader", "setTransportPacketMagicHeader", "specialJunkPacket1", "getSpecialJunkPacket1", "setSpecialJunkPacket1", "specialJunkPacket2", "getSpecialJunkPacket2", "setSpecialJunkPacket2", "specialJunkPacket3", "getSpecialJunkPacket3", "setSpecialJunkPacket3", "specialJunkPacket4", "getSpecialJunkPacket4", "setSpecialJunkPacket4", "specialJunkPacket5", "getSpecialJunkPacket5", "setSpecialJunkPacket5", "controlledJunkPacket1", "getControlledJunkPacket1", "setControlledJunkPacket1", "controlledJunkPacket2", "getControlledJunkPacket2", "setControlledJunkPacket2", "controlledJunkPacket3", "getControlledJunkPacket3", "setControlledJunkPacket3", "itimeSeconds", "getItimeSeconds", "setItimeSeconds", "privateKey", "getPrivateKey", "setPrivateKey", "publicKey", "getPublicKey", "describeContents", "", "generateKeyPair", "", "resolve", "writeToParcel", "dest", "flags", "InterfaceProxyCreator", "Companion", "ui_release"})
public final class InterfaceProxy extends androidx.databinding.BaseObservable implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final androidx.databinding.ObservableList<java.lang.String> excludedApplications = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.databinding.ObservableList<java.lang.String> includedApplications = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String addresses = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String dnsServers = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String listenPort = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String mtu = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String junkPacketCount = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String junkPacketMinSize = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String junkPacketMaxSize = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String initPacketJunkSize = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String responsePacketJunkSize = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String cookieReplyPacketJunkSize = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transportPacketJunkSize = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String initPacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String responsePacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String underloadPacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transportPacketMagicHeader = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String specialJunkPacket1 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String specialJunkPacket2 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String specialJunkPacket3 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String specialJunkPacket4 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String specialJunkPacket5 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String controlledJunkPacket1 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String controlledJunkPacket2 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String controlledJunkPacket3 = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String itimeSeconds = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String privateKey = "";
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final android.os.Parcelable.Creator<pw.idrug.connections.viewmodel.InterfaceProxy> CREATOR = null;
    @org.jetbrains.annotations.NotNull()
    public static final pw.idrug.connections.viewmodel.InterfaceProxy.Companion Companion = null;
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableList<java.lang.String> getExcludedApplications() {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableList<java.lang.String> getIncludedApplications() {
        return null;
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAddresses() {
        return null;
    }
    
    public final void setAddresses(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDnsServers() {
        return null;
    }
    
    public final void setDnsServers(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getListenPort() {
        return null;
    }
    
    public final void setListenPort(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMtu() {
        return null;
    }
    
    public final void setMtu(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJunkPacketCount() {
        return null;
    }
    
    public final void setJunkPacketCount(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJunkPacketMinSize() {
        return null;
    }
    
    public final void setJunkPacketMinSize(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJunkPacketMaxSize() {
        return null;
    }
    
    public final void setJunkPacketMaxSize(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInitPacketJunkSize() {
        return null;
    }
    
    public final void setInitPacketJunkSize(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResponsePacketJunkSize() {
        return null;
    }
    
    public final void setResponsePacketJunkSize(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCookieReplyPacketJunkSize() {
        return null;
    }
    
    public final void setCookieReplyPacketJunkSize(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransportPacketJunkSize() {
        return null;
    }
    
    public final void setTransportPacketJunkSize(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInitPacketMagicHeader() {
        return null;
    }
    
    public final void setInitPacketMagicHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResponsePacketMagicHeader() {
        return null;
    }
    
    public final void setResponsePacketMagicHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUnderloadPacketMagicHeader() {
        return null;
    }
    
    public final void setUnderloadPacketMagicHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransportPacketMagicHeader() {
        return null;
    }
    
    public final void setTransportPacketMagicHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecialJunkPacket1() {
        return null;
    }
    
    public final void setSpecialJunkPacket1(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecialJunkPacket2() {
        return null;
    }
    
    public final void setSpecialJunkPacket2(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecialJunkPacket3() {
        return null;
    }
    
    public final void setSpecialJunkPacket3(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecialJunkPacket4() {
        return null;
    }
    
    public final void setSpecialJunkPacket4(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecialJunkPacket5() {
        return null;
    }
    
    public final void setSpecialJunkPacket5(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getControlledJunkPacket1() {
        return null;
    }
    
    public final void setControlledJunkPacket1(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getControlledJunkPacket2() {
        return null;
    }
    
    public final void setControlledJunkPacket2(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getControlledJunkPacket3() {
        return null;
    }
    
    public final void setControlledJunkPacket3(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getItimeSeconds() {
        return null;
    }
    
    public final void setItimeSeconds(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPrivateKey() {
        return null;
    }
    
    public final void setPrivateKey(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.databinding.Bindable()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPublicKey() {
        return null;
    }
    
    private InterfaceProxy(android.os.Parcel parcel) {
        super();
    }
    
    public InterfaceProxy(@org.jetbrains.annotations.NotNull()
    pw.idrug.connections.config.Interface other) {
        super();
    }
    
    public InterfaceProxy() {
        super();
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    public final void generateKeyPair() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {pw.idrug.connections.config.BadConfigException.class})
    @org.jetbrains.annotations.NotNull()
    public final pw.idrug.connections.config.Interface resolve() throws pw.idrug.connections.config.BadConfigException {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel dest, int flags) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lpw/idrug/connections/viewmodel/InterfaceProxy$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lpw/idrug/connections/viewmodel/InterfaceProxy;", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016\u00a2\u0006\u0002\u0010\f\u00a8\u0006\r"}, d2 = {"Lpw/idrug/connections/viewmodel/InterfaceProxy$InterfaceProxyCreator;", "Landroid/os/Parcelable$Creator;", "Lpw/idrug/connections/viewmodel/InterfaceProxy;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lpw/idrug/connections/viewmodel/InterfaceProxy;", "ui_release"})
    static final class InterfaceProxyCreator implements android.os.Parcelable.Creator<pw.idrug.connections.viewmodel.InterfaceProxy> {
        
        public InterfaceProxyCreator() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public pw.idrug.connections.viewmodel.InterfaceProxy createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public pw.idrug.connections.viewmodel.InterfaceProxy[] newArray(int size) {
            return null;
        }
    }
}