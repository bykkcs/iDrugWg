package pw.idrug.connections

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import pw.idrug.connections.backend.Tunnel
import pw.idrug.connections.util.applicationScope
import kotlinx.coroutines.launch

class ReconnectReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        applicationScope.launch {
            Application.getTunnelManager().lastUsedTunnel?.setStateAsync(Tunnel.State.UP)
        }
    }
}
