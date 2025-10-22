package pw.idrug.connections

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.launch
import pw.idrug.connections.backend.Tunnel
import pw.idrug.connections.util.ErrorMessages
import pw.idrug.connections.util.applicationScope

class DisconnectTunnelsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        if (ACTION_SET_ALL_TUNNELS_DOWN != action) return

        applicationScope.launch {
            val manager = Application.getTunnelManager()
            manager.getTunnels().forEach {
                try {
                    manager.setTunnelState(it, Tunnel.State.DOWN)
                } catch (e: Throwable) {
                    Toast.makeText(context, ErrorMessages[e], Toast.LENGTH_LONG).show()
                    Log.e(TAG, ErrorMessages[e], e)
                }
            }
        }
    }

    companion object {
        private const val TAG = "iDrugConnections/DisconnectTunnelsReceiver"
        private const val ACTION_SET_ALL_TUNNELS_DOWN = "pw.idrug.connections.action.SET_ALL_TUNNELS_DOWN"
    }
}
