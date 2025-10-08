package pw.idrug.connections.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pw.idrug.connections.R
import pw.idrug.connections.ota.OtaMeta
import pw.idrug.connections.ota.UpdateEvent
import pw.idrug.connections.ota.UpdateViewModel
import pw.idrug.connections.util.styleAllSwitchesRecursively

class UpdateDialogFragment : DialogFragment() {

    private val viewModel: UpdateViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val autoCheck = arguments?.getBoolean(ARG_AUTO_CHECK) ?: false

        if (savedInstanceState == null) {
            val versionCode = arguments?.getInt(ARG_VERSION_CODE)
            val apkUrl = arguments?.getString(ARG_APK_URL)

            if (versionCode != null && apkUrl != null) {
                // эти два могут быть null в Bundle — приводим к non-null для модели
                val versionName = arguments?.getString(ARG_VERSION_NAME) ?: ""
                val changelog   = arguments?.getString(ARG_CHANGELOG) ?: ""

                // apkUrl уже проверили на null выше
                viewModel.setMeta(
                    OtaMeta(
                        versionCode = versionCode,
                        versionName = versionName,
                        apkUrl = apkUrl,
                        changelog = changelog
                    )
                )
            } else {
                viewModel.checkUpdate(auto = autoCheck)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null)
        (view as? ViewGroup)?.styleAllSwitchesRecursively()

        val title = view.findViewById<TextView>(R.id.update_title)
        val errorText = view.findViewById<TextView>(R.id.update_error)
        val changelogContainer = view.findViewById<ScrollView>(R.id.update_changelog_container)
        val changelog = view.findViewById<TextView>(R.id.update_changelog)
        val progressIndicator = view.findViewById<LinearProgressIndicator>(R.id.update_progress)
        val progressText = view.findViewById<TextView>(R.id.update_progress_text)
        val loadingIndicator = view.findViewById<CircularProgressIndicator>(R.id.update_loading)
        val installButton = view.findViewById<MaterialButton>(R.id.update_install)
        val laterButton = view.findViewById<MaterialButton>(R.id.update_later)

        installButton.setOnClickListener { viewModel.downloadAndInstall() }
        laterButton.setOnClickListener { dismissAllowingStateLoss() }

        // состояние UI
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                val showLoading = state.loading
                val showDownloading = state.downloading

                loadingIndicator.isVisible = showLoading || (showDownloading && state.downloadProgress == null)
                progressIndicator.isVisible = showDownloading && state.downloadProgress != null
                if (showDownloading && state.downloadProgress != null) {
                    progressIndicator.setProgressCompat(state.downloadProgress, true)
                } else {
                    progressIndicator.setProgressCompat(0, false)
                }

                progressText.isVisible = showDownloading && state.downloadProgress != null
                progressText.text = if (showDownloading && state.downloadProgress != null) {
                    getString(R.string.update_download_progress, state.downloadProgress)
                } else {
                    ""
                }

                title.isVisible = !showLoading
                changelogContainer.isVisible = state.updateAvailable && !showLoading && !showDownloading
                installButton.isVisible = state.updateAvailable && state.error == null && !showLoading && !showDownloading
                laterButton.isVisible = !showLoading && !showDownloading
                installButton.isEnabled = !showLoading && !showDownloading

                val label = when {
                    !state.versionName.isNullOrBlank() -> state.versionName
                    state.versionCode != null -> "(${state.versionCode})"
                    else -> ""
                }
                title.text = if (label.isNotEmpty()) {
                    getString(R.string.update_dialog_title, label)
                } else {
                    getString(R.string.update_dialog_title_generic)
                }

                changelog.text = if (state.changelog.isNotBlank()) {
                    state.changelog
                } else {
                    getString(R.string.update_changelog_empty)
                }

                errorText.isVisible = !state.error.isNullOrBlank() && !showLoading && !showDownloading
                if (!state.error.isNullOrBlank()) {
                    errorText.text = state.error
                }
            }
        }

        // события (тосты)
        lifecycleScope.launch {
            viewModel.events.collectLatest { event ->
                when (event) {
                    is UpdateEvent.NoUpdate -> {
                        Toast.makeText(context, R.string.update_latest_message, Toast.LENGTH_SHORT).show()
                        dismissAllowingStateLoss()
                    }
                    is UpdateEvent.Error -> {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                    is UpdateEvent.DownloadStarted -> {
                        Toast.makeText(context, R.string.update_download_started, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return MaterialAlertDialogBuilder(context, R.style.MonetAlertDialog)
            .setView(view)
            .create()
    }
    companion object {
        private const val ARG_AUTO_CHECK = "auto_check"
        private const val ARG_VERSION_CODE = "version_code"
        private const val ARG_VERSION_NAME = "version_name"
        private const val ARG_APK_URL = "apk_url"
        private const val ARG_CHANGELOG = "changelog"
        private const val TAG = "update_dialog"

        fun show(
            fragmentManager: androidx.fragment.app.FragmentManager,
            meta: OtaMeta? = null,
            auto: Boolean = false
        ) {
            val fragment = UpdateDialogFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_AUTO_CHECK, auto)
                    if (meta != null) {
                        putInt(ARG_VERSION_CODE, meta.versionCode)
                        putString(ARG_VERSION_NAME, meta.versionName)   // null допустим — в onCreate приведём к ""
                        putString(ARG_APK_URL, meta.apkUrl)              // non-null
                        putString(ARG_CHANGELOG, meta.changelog)         // null допустим — в onCreate приведём к ""
                    }
                }
            }
            fragment.show(fragmentManager, TAG)
        }
    }
}
