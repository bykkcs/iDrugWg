package pw.idrug.connections.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import pw.idrug.connections.R
import pw.idrug.connections.data.UpdateMeta
import pw.idrug.connections.di.UpdateModules

@Composable
fun UpdateDialog(meta: UpdateMeta, onUpdate: () -> Unit, onLater: () -> Unit, onIgnore: () -> Unit) {
    AlertDialog(
        onDismissRequest = onLater,
        title = { Text(text = stringResource(id = R.string.update_dialog_title)) },
        text = { Text(text = stringResource(id = R.string.update_dialog_subtitle, meta.versionCode)) },
        confirmButton = {
            TextButton(onClick = onUpdate) {
                Text(text = stringResource(id = R.string.update_action_install))
            }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onLater) {
                    Text(text = stringResource(id = R.string.update_action_later))
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = onIgnore) {
                    Text(text = stringResource(id = R.string.update_action_ignore))
                }
            }
        }
    )
}

class UpdateDialogFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val meta = UpdateMeta(
            versionCode = requireArguments().getInt(ARG_VERSION),
            apkUrl = requireArguments().getString(ARG_URL).orEmpty()
        )
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Surface {
                        UpdateDialog(
                            meta = meta,
                            onUpdate = { handleUpdate(meta) },
                            onLater = { dismissAllowingStateLoss() },
                            onIgnore = { handleIgnore(meta) }
                        )
                    }
                }
            }
        }
    }

    private fun handleUpdate(meta: UpdateMeta) {
        val installer = UpdateModules.provideApkInstaller(requireContext())
        val result = installer.download(meta)
        result.onSuccess {
            Toast.makeText(requireContext(), R.string.update_download_started, Toast.LENGTH_SHORT).show()
            dismissAllowingStateLoss()
        }
        result.onFailure {
            Toast.makeText(
                requireContext(),
                getString(R.string.update_download_failed_with_reason, it.localizedMessage ?: it.javaClass.simpleName),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun handleIgnore(meta: UpdateMeta) {
        val updateManager = UpdateModules.provideUpdateManager(requireContext())
        updateManager.ignore(meta.versionCode)
        Toast.makeText(requireContext(), R.string.update_ignored, Toast.LENGTH_SHORT).show()
        dismissAllowingStateLoss()
    }

    companion object {
        private const val ARG_VERSION = "arg_version"
        private const val ARG_URL = "arg_url"
        private const val TAG = "update_dialog"

        fun show(manager: FragmentManager, meta: UpdateMeta) {
            val fragment = UpdateDialogFragment().apply {
                arguments = bundleOf(
                    ARG_VERSION to meta.versionCode,
                    ARG_URL to meta.apkUrl
                )
            }
            fragment.show(manager, TAG)
        }
    }
}
