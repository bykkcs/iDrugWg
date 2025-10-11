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
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.R
import pw.idrug.connections.ota.OtaMeta
import pw.idrug.connections.ota.UpdateEvent
import pw.idrug.connections.ota.UpdateViewModel
import java.io.File

class UpdateDialogFragment : DialogFragment() {

    private val viewModel: UpdateViewModel by activityViewModels()
    private var downloadReceiver: BroadcastReceiver? = null
    private val manageUnknownSourcesLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        tryResumePendingInstall()
    }
    private var pendingApkUri: Uri? = null
    private var installTriggered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pendingApkUri = savedInstanceState?.getString(KEY_PENDING_APK_URI)?.let { Uri.parse(it) }

        val autoCheck = arguments?.getBoolean(ARG_AUTO_CHECK) ?: false

        if (savedInstanceState == null) {
            val versionCode = arguments?.getInt(ARG_VERSION_CODE)
            val apkUrl = arguments?.getString(ARG_APK_URL)

            if (versionCode != null && apkUrl != null) {
                val versionName = arguments?.getString(ARG_VERSION_NAME) ?: ""
                val changelog   = arguments?.getString(ARG_CHANGELOG) ?: ""
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

    override fun onResume() {
        super.onResume()
        tryResumePendingInstall()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        pendingApkUri?.let { outState.putString(KEY_PENDING_APK_URI, it.toString()) }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_update, null)

        // bind views
        val title = view.findViewById<TextView>(R.id.update_title)
        val errorText = view.findViewById<TextView>(R.id.update_error)
        val changelogContainer = view.findViewById<ScrollView>(R.id.update_changelog_container)
        val changelog = view.findViewById<TextView>(R.id.update_changelog)

        val progressIndicator = view.findViewById<LinearProgressIndicator>(R.id.update_progress_indicator)
        val progressLabel = view.findViewById<TextView>(R.id.update_progress_label)

        val installButton = view.findViewById<MaterialButton>(R.id.update_install)
        val laterButton = view.findViewById<MaterialButton>(R.id.update_later)

        installButton.setOnClickListener {
            installTriggered = false
            viewModel.downloadAndInstall()
        }
        laterButton.setOnClickListener { dismissAllowingStateLoss() }

        // state → UI
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    // заголовок
                    val label = when {
                        !state.versionName.isNullOrBlank() -> state.versionName
                        state.versionCode != null         -> "(${state.versionCode})"
                        else                              -> ""
                    }
                    title.text = if (label.isNotEmpty()) {
                        getString(R.string.update_dialog_title, label)
                    } else {
                        getString(R.string.update_dialog_title_generic)
                    }

                    // контент, кнопки, прогресс
                    if (state.loading) {
                        // мета грузится — прячем всё, индикатор загрузки APK тоже скрыт
                        title.isVisible = false
                        changelogContainer.isVisible = false
                        installButton.isVisible = false
                        laterButton.isVisible = false
                        progressIndicator.isVisible = false
                        progressLabel.isVisible = false
                        errorText.isVisible = false
                        return@collectLatest
                    }

                    // обычный контент
                    title.isVisible = true
                    changelogContainer.isVisible = state.updateAvailable
                    changelog.text = if (state.changelog.isNotBlank()) {
                        state.changelog
                    } else {
                        getString(R.string.update_changelog_empty)
                    }

                    // error
                    errorText.isVisible = !state.error.isNullOrBlank() && state.downloadProgress == null
                    if (!state.error.isNullOrBlank()) {
                        errorText.text = state.error
                    }

                    // кнопки
                    installButton.isEnabled = !state.downloading
                    installButton.isVisible = state.updateAvailable && state.error == null && !state.loading &&
                        !state.downloading && state.downloadProgress == null
                    laterButton.isVisible = true

                    // прогресс
                    val showProgress = state.downloading || state.downloadProgress != null
                    progressIndicator.isVisible = showProgress
                    progressLabel.isVisible = showProgress
                    if (showProgress) {
                        val p = state.downloadProgress
                        progressIndicator.isIndeterminate = p == null
                        if (p != null) {
                            progressIndicator.setProgressCompat(p, /*animated*/ true)
                            progressLabel.text = getString(R.string.update_download_percent, p)
                        } else {
                            progressIndicator.progress = 0
                            progressLabel.text = getString(R.string.update_download_pending)
                        }
                    }
                }
            }
        }

        // events (тосты/ресивер завершения)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
                        is UpdateEvent.DownloadFinished -> {
                            triggerAutoInstall()
                        }
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

    // --- Download receiver ---

    private fun registerDownloadReceiver() {
        if (downloadReceiver != null) return

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action != DownloadManager.ACTION_DOWNLOAD_COMPLETE) return
                val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (downloadId == -1L) return
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
                viewModel.clearDownloadUi()
                return
            }
            val statusIndex = it.getColumnIndex(DownloadManager.COLUMN_STATUS)
            if (statusIndex < 0) {
                showDownloadError()
                viewModel.clearDownloadUi()
                return
            }
            val status = it.getInt(statusIndex)
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                triggerAutoInstall()
            } else {
                showDownloadError()
                viewModel.clearDownloadUi()
            }
        } ?: run {
            showDownloadError()
            viewModel.clearDownloadUi()
        }
    }

    // --- Install ---
    private fun triggerAutoInstall() {
        pendingApkUri?.let { pending ->
            if (!tryResumePendingInstall()) {
                startInstallWithPermission(pending)
            }
            return
        }
        if (installTriggered) return
        if (installDownloadedApk()) {
            installTriggered = true
        }
    }

    private fun installDownloadedApk(): Boolean {
        val context = requireContext()
        val directUri = viewModel.getDownloadedApkUri(context)
        val apkUri = directUri ?: run {
            val file = viewModel.getDownloadedFile()
            if (!file.exists()) {
                showDownloadError()
                viewModel.clearDownloadUi()
                installTriggered = false
                return false
            }
            FileProvider.getUriForFile(
                context,
                "${BuildConfig.APPLICATION_ID}.provider",
                file
            )
        }
        return startInstallWithPermission(apkUri)
    }

    private fun showDownloadError() {
        Toast.makeText(requireContext(), R.string.update_download_failed, Toast.LENGTH_LONG).show()
        installTriggered = false
        pendingApkUri = null
    }

    companion object {
        private const val ARG_AUTO_CHECK = "auto_check"
        private const val ARG_VERSION_CODE = "version_code"
        private const val ARG_VERSION_NAME = "version_name"
        private const val ARG_APK_URL = "apk_url"
        private const val ARG_CHANGELOG = "changelog"
        private const val APK_MIME_TYPE = "application/vnd.android.package-archive"
        private const val TAG = "update_dialog"
        private const val KEY_PENDING_APK_URI = "pending_apk_uri"

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
                        putString(ARG_VERSION_NAME, meta.versionName ?: "")
                        putString(ARG_APK_URL, meta.apkUrl)
                        putString(ARG_CHANGELOG, meta.changelog ?: "")
                    }
                }
            }
            fragment.show(fragmentManager, TAG)
        }
    }

    private fun startInstallWithPermission(apkUri: Uri): Boolean {
        val pm = requireContext().packageManager
        return if (pm.canRequestPackageInstalls()) {
            pendingApkUri = null
            openInstaller(apkUri)
            true
        } else {
            pendingApkUri = apkUri
            val intent = Intent(
                Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                Uri.parse("package:${requireContext().packageName}")
            )
            manageUnknownSourcesLauncher.launch(intent)
            Toast.makeText(requireContext(), R.string.update_install_permission, Toast.LENGTH_LONG).show()
            false
        }
    }

    private fun tryResumePendingInstall(): Boolean {
        val uri = pendingApkUri ?: return false
        if (!isAdded) return false
        val pm = requireContext().packageManager
        if (pm.canRequestPackageInstalls()) {
            pendingApkUri = null
            openInstaller(uri)
            return true
        }
        return false
    }

    private fun openInstaller(apkUri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(apkUri, APK_MIME_TYPE)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        runCatching {
            installTriggered = true
            startActivity(intent)
        }
            .onSuccess {
                installTriggered = false
                Toast.makeText(requireContext(), R.string.update_install_started, Toast.LENGTH_SHORT).show()
                viewModel.clearDownloadUi()
                dismissAllowingStateLoss()
            }
            .onFailure {
                installTriggered = false
                showDownloadError()
                viewModel.clearDownloadUi()
            }
    }
}
