/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections

object TunnelSyncManager {
    private var job = kotlinx.coroutines.SupervisorJob()
    val scope = kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main + job)

    fun cancelAll() {
        job.cancel()
        job = kotlinx.coroutines.SupervisorJob()
    }
}
