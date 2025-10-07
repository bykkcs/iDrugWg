package pw.idrug.connections.dialog

import android.app.Dialog
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.R
import pw.idrug.connections.ota.OtaMeta
import pw.idrug.connections.ota.UpdateEvent
import pw.idrug.connections.ota.UpdateViewModel

class UpdateDialogFragment : DialogFragment() {
    private val viewModel: UpdateViewModel by activityViewModels()

    private var downloadReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val autoCheck = arguments?.getBoolean(ARG_AUTO_CHECK) ?: false
        if (savedInstanceState == null) {
            val versionCode = arguments?.getInt(ARG_VERSION_CODE)
            val apkUrl = arguments?.getString(ARG_APK_URL)
            if (versionCode != null && apkUrl != null) {
                val versionName = arguments?.getString(ARG_VERSION_NAME)
                val changelog = arguments?.getString(ARG_CHANGELOG)
                viewModel.setMeta(OtaMeta(versionCode, versionName, apkUrl, changelog))
            } else {
                viewModel.checkUpdate(auto = autoCheck)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null)
        val title = view.findViewById<TextView>(R.id.update_title)
        val errorText = view.findViewById<TextView>(R.id.update_error)
        val changelogContainer = view.findViewById<ScrollView>(R.id.update_changelog_container)
        val changelog = view.findViewById<TextView>(R.id.update_changelog)
        val progressBar = view.findViewById<ProgressBar>(R.id.update_progress)
        val installButton = view.findViewById<MaterialButton>(R.id.update_install)
        val laterButton = view.findViewById<MaterialButton>(R.id.update_later)

        installButton.setOnClickListener { viewModel.downloadAndInstall() }
        laterButton.setOnClickListener { dismissAllowingStateLoss() }

        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                progressBar.isVisible = state.loading
                title.isVisible = !state.loading
                changelogContainer.isVisible = state.updateAvailable && !state.loading
                installButton.isVisible = state.updateAvailable && state.error == null && !state.loading
                laterButton.isVisible = !state.loading

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

                errorText.isVisible = !state.error.isNullOrBlank() && !state.loading
                if (!state.error.isNullOrBlank()) {
                    errorText.text = state.error
                }
            }
        }

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
                        registerDownloadReceiver()
                    }
                }
            }
        }

        return MaterialAlertDialogBuilder(context, R.style.MonetAlertDialog)
            .setView(view)
            .create()
    }

    override fun onDestroy() {
        unregisterDownloadReceiver()
        super.onDestroy()
    }

    private fun registerDownloadReceiver() {
        if (downloadReceiver != null) return
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action != DownloadManager.ACTION_DOWNLOAD_COMPLETE) return
                val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                handleDownloadComplete(downloadId)
            }
        }
        downloadReceiver = receiver
        ContextCompat.registerReceiver(
            requireContext(),
            receiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    private fun unregisterDownloadReceiver() {
        val receiver = downloadReceiver ?: return
        runCatching { requireContext().unregisterReceiver(receiver) }
        downloadReceiver = null
    }

    private fun handleDownloadComplete(downloadId: Long) {
        val expectedId = viewModel.getCurrentDownloadId() ?: return
        if (downloadId != expectedId) return
        unregisterDownloadReceiver()
        val context = requireContext()
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val cursor = manager.query(DownloadManager.Query().setFilterById(downloadId))
        cursor?.use {
            if (!it.moveToFirst()) {
                showDownloadError()
                return
            }
            val statusIndex = it.getColumnIndex(DownloadManager.COLUMN_STATUS)
            if (statusIndex < 0) {
                showDownloadError()
                return
            }
            val status = it.getInt(statusIndex)
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                installDownloadedApk()
            } else {
                showDownloadError()
            }
        } ?: showDownloadError()
    }

    private fun installDownloadedApk() {
        val context = requireContext()
        val file = viewModel.getDownloadedFile()
        if (!file.exists()) {
            showDownloadError()
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            !context.packageManager.canRequestPackageInstalls()
        ) {
            val intent = Intent(
                Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                Uri.parse("package:${context.packageName}")
            )
            startActivity(intent)
            Toast.makeText(context, R.string.update_install_permission, Toast.LENGTH_LONG).show()
            return
        }
        val apkUri = FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider",
            file
        )
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(apkUri, APK_MIME_TYPE)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching { startActivity(intent) }
            .onSuccess {
                Toast.makeText(context, R.string.update_install_started, Toast.LENGTH_SHORT).show()
                dismissAllowingStateLoss()
            }
            .onFailure {
                showDownloadError()
            }
    }

    private fun showDownloadError() {
        Toast.makeText(requireContext(), R.string.update_download_failed, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val ARG_AUTO_CHECK = "auto_check"
        private const val ARG_VERSION_CODE = "version_code"
        private const val ARG_VERSION_NAME = "version_name"
        private const val ARG_APK_URL = "apk_url"
        private const val ARG_CHANGELOG = "changelog"
        private const val APK_MIME_TYPE = "application/vnd.android.package-archive"

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
                        putString(ARG_VERSION_NAME, meta.versionName)
                        putString(ARG_APK_URL, meta.apkUrl)
                        putString(ARG_CHANGELOG, meta.changelog)
                    }
                }
            }
            fragment.show(fragmentManager, TAG)
        }

        private const val TAG = "update_dialog"
    }
}
