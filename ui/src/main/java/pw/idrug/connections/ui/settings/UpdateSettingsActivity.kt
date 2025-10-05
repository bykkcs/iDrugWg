package pw.idrug.connections.ui.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import pw.idrug.connections.di.UpdateModules

class UpdateSettingsActivity : ComponentActivity() {
    private val viewModel: UpdateSettingsViewModel by viewModels {
        UpdateSettingsViewModel.factory(
            UpdateModules.provideUpdateManager(applicationContext),
            UpdateModules.provideApkInstaller(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    UpdateSettingsScreen(
                        viewModel = viewModel,
                        onBack = { finish() }
                    )
                }
            }
        }
    }
}
