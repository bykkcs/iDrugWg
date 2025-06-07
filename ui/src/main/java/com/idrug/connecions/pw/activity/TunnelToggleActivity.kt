/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package com.idrug.connecions.pw.activity

import android.content.ComponentName
import android.os.Bundle
import android.service.quicksettings.TileService
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.idrug.connecions.pw.Application
import com.idrug.connecions.pw.QuickTileService
import com.idrug.connecions.pw.R
import com.idrug.connecions.pw.backend.GoBackend
import com.idrug.connecions.pw.backend.Tunnel
import com.idrug.connecions.pw.util.ErrorMessages
import kotlinx.coroutines.launch

class TunnelToggleActivity : AppCompatActivity() {
    private val permissionActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { toggleTunnelWithPermissionsResult() }

    private fun toggleTunnelWithPermissionsResult() {
        val tunnel = Application.getTunnelManager().lastUsedTunnel ?: return
        lifecycleScope.launch {
            try {
                tunnel.setStateAsync(Tunnel.State.TOGGLE)
            } catch (e: Throwable) {
                TileService.requestListeningState(this@TunnelToggleActivity, ComponentName(this@TunnelToggleActivity, QuickTileService::class.java))
                val error = ErrorMessages[e]
                val message = getString(R.string.toggle_error, error)
                Log.e(TAG, message, e)
                Toast.makeText(this@TunnelToggleActivity, message, Toast.LENGTH_LONG).show()
                finishAffinity()
                return@launch
            }
            TileService.requestListeningState(this@TunnelToggleActivity, ComponentName(this@TunnelToggleActivity, QuickTileService::class.java))
            finishAffinity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            if (Application.getBackend() is GoBackend) {
                val intent = GoBackend.VpnService.prepare(this@TunnelToggleActivity)
                if (intent != null) {
                    permissionActivityResultLauncher.launch(intent)
                    return@launch
                }
            }
            toggleTunnelWithPermissionsResult()
        }
    }

    companion object {
        private const val TAG = "iDrugWg/TunnelToggleActivity"
    }
}
