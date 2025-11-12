package pw.idrug.connections;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pw.idrug.connections.databinding.AppListDialogFragmentBindingImpl;
import pw.idrug.connections.databinding.AppListItemBindingImpl;
import pw.idrug.connections.databinding.ConfigNamingDialogFragmentBindingImpl;
import pw.idrug.connections.databinding.TunnelDetailFragmentBindingImpl;
import pw.idrug.connections.databinding.TunnelDetailPeerBindingImpl;
import pw.idrug.connections.databinding.TunnelEditorFragmentBindingImpl;
import pw.idrug.connections.databinding.TunnelEditorPeerBindingImpl;
import pw.idrug.connections.databinding.TunnelListFragmentBindingImpl;
import pw.idrug.connections.databinding.TunnelListItemBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_APPLISTDIALOGFRAGMENT = 1;

  private static final int LAYOUT_APPLISTITEM = 2;

  private static final int LAYOUT_CONFIGNAMINGDIALOGFRAGMENT = 3;

  private static final int LAYOUT_TUNNELDETAILFRAGMENT = 4;

  private static final int LAYOUT_TUNNELDETAILPEER = 5;

  private static final int LAYOUT_TUNNELEDITORFRAGMENT = 6;

  private static final int LAYOUT_TUNNELEDITORPEER = 7;

  private static final int LAYOUT_TUNNELLISTFRAGMENT = 8;

  private static final int LAYOUT_TUNNELLISTITEM = 9;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(9);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.app_list_dialog_fragment, LAYOUT_APPLISTDIALOGFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.app_list_item, LAYOUT_APPLISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.config_naming_dialog_fragment, LAYOUT_CONFIGNAMINGDIALOGFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.tunnel_detail_fragment, LAYOUT_TUNNELDETAILFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.tunnel_detail_peer, LAYOUT_TUNNELDETAILPEER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.tunnel_editor_fragment, LAYOUT_TUNNELEDITORFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.tunnel_editor_peer, LAYOUT_TUNNELEDITORPEER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.tunnel_list_fragment, LAYOUT_TUNNELLISTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pw.idrug.connections.R.layout.tunnel_list_item, LAYOUT_TUNNELLISTITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_APPLISTDIALOGFRAGMENT: {
          if ("layout/app_list_dialog_fragment_0".equals(tag)) {
            return new AppListDialogFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for app_list_dialog_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_APPLISTITEM: {
          if ("layout/app_list_item_0".equals(tag)) {
            return new AppListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for app_list_item is invalid. Received: " + tag);
        }
        case  LAYOUT_CONFIGNAMINGDIALOGFRAGMENT: {
          if ("layout/config_naming_dialog_fragment_0".equals(tag)) {
            return new ConfigNamingDialogFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for config_naming_dialog_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_TUNNELDETAILFRAGMENT: {
          if ("layout/tunnel_detail_fragment_0".equals(tag)) {
            return new TunnelDetailFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tunnel_detail_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_TUNNELDETAILPEER: {
          if ("layout/tunnel_detail_peer_0".equals(tag)) {
            return new TunnelDetailPeerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tunnel_detail_peer is invalid. Received: " + tag);
        }
        case  LAYOUT_TUNNELEDITORFRAGMENT: {
          if ("layout/tunnel_editor_fragment_0".equals(tag)) {
            return new TunnelEditorFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tunnel_editor_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_TUNNELEDITORPEER: {
          if ("layout/tunnel_editor_peer_0".equals(tag)) {
            return new TunnelEditorPeerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tunnel_editor_peer is invalid. Received: " + tag);
        }
        case  LAYOUT_TUNNELLISTFRAGMENT: {
          if ("layout/tunnel_list_fragment_0".equals(tag)) {
            return new TunnelListFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tunnel_list_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_TUNNELLISTITEM: {
          if ("layout/tunnel_list_item_0".equals(tag)) {
            return new TunnelListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for tunnel_list_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(52);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "ableToExcludePrivateIps");
      sKeys.put(2, "addresses");
      sKeys.put(3, "allowedIps");
      sKeys.put(4, "appData");
      sKeys.put(5, "collection");
      sKeys.put(6, "config");
      sKeys.put(7, "controlledJunkPacket1");
      sKeys.put(8, "controlledJunkPacket2");
      sKeys.put(9, "controlledJunkPacket3");
      sKeys.put(10, "cookieReplyPacketJunkSize");
      sKeys.put(11, "dnsSearchDomains");
      sKeys.put(12, "dnsServers");
      sKeys.put(13, "endpoint");
      sKeys.put(14, "excludedApplications");
      sKeys.put(15, "excludingPrivateIps");
      sKeys.put(16, "fragment");
      sKeys.put(17, "includedApplications");
      sKeys.put(18, "initPacketJunkSize");
      sKeys.put(19, "initPacketMagicHeader");
      sKeys.put(20, "item");
      sKeys.put(21, "itimeSeconds");
      sKeys.put(22, "junkPacketCount");
      sKeys.put(23, "junkPacketMaxSize");
      sKeys.put(24, "junkPacketMinSize");
      sKeys.put(25, "key");
      sKeys.put(26, "lastUsedTunnel");
      sKeys.put(27, "listenPort");
      sKeys.put(28, "mtu");
      sKeys.put(29, "name");
      sKeys.put(30, "persistentKeepalive");
      sKeys.put(31, "preSharedKey");
      sKeys.put(32, "privateKey");
      sKeys.put(33, "publicKey");
      sKeys.put(34, "publicKeyBase64");
      sKeys.put(35, "quicReadyBadge");
      sKeys.put(36, "responsePacketJunkSize");
      sKeys.put(37, "responsePacketMagicHeader");
      sKeys.put(38, "rowConfigurationHandler");
      sKeys.put(39, "selected");
      sKeys.put(40, "specialJunkPacket1");
      sKeys.put(41, "specialJunkPacket2");
      sKeys.put(42, "specialJunkPacket3");
      sKeys.put(43, "specialJunkPacket4");
      sKeys.put(44, "specialJunkPacket5");
      sKeys.put(45, "state");
      sKeys.put(46, "statistics");
      sKeys.put(47, "transportPacketJunkSize");
      sKeys.put(48, "transportPacketMagicHeader");
      sKeys.put(49, "tunnel");
      sKeys.put(50, "tunnels");
      sKeys.put(51, "underloadPacketMagicHeader");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(9);

    static {
      sKeys.put("layout/app_list_dialog_fragment_0", pw.idrug.connections.R.layout.app_list_dialog_fragment);
      sKeys.put("layout/app_list_item_0", pw.idrug.connections.R.layout.app_list_item);
      sKeys.put("layout/config_naming_dialog_fragment_0", pw.idrug.connections.R.layout.config_naming_dialog_fragment);
      sKeys.put("layout/tunnel_detail_fragment_0", pw.idrug.connections.R.layout.tunnel_detail_fragment);
      sKeys.put("layout/tunnel_detail_peer_0", pw.idrug.connections.R.layout.tunnel_detail_peer);
      sKeys.put("layout/tunnel_editor_fragment_0", pw.idrug.connections.R.layout.tunnel_editor_fragment);
      sKeys.put("layout/tunnel_editor_peer_0", pw.idrug.connections.R.layout.tunnel_editor_peer);
      sKeys.put("layout/tunnel_list_fragment_0", pw.idrug.connections.R.layout.tunnel_list_fragment);
      sKeys.put("layout/tunnel_list_item_0", pw.idrug.connections.R.layout.tunnel_list_item);
    }
  }
}
