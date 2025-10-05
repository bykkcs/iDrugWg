package pw.idrug.connections.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import pw.idrug.connections.di.UpdateModules
import pw.idrug.connections.domain.UpdateState
import pw.idrug.connections.notification.UpdateNotifier

class UpdateWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val updateManager = UpdateModules.provideUpdateManager(applicationContext)
        return when (val state = updateManager.check()) {
            is UpdateState.Available -> {
                UpdateNotifier(applicationContext).showUpdateAvailable(state.meta)
                Result.success()
            }
            is UpdateState.Error -> Result.success()
            UpdateState.NoUpdate -> Result.success()
        }
    }
}
