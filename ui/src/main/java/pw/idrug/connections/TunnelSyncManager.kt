/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections

object TunnelSyncManager {
    private var job = kotlinx.coroutines.SupervisorJob()
    private var _scope = kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main + job)

    val scope: kotlinx.coroutines.CoroutineScope
        get() = _scope

    fun cancelAll() {
        job.cancel()
        job = kotlinx.coroutines.SupervisorJob()
        _scope = kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main + job)
    }
}
