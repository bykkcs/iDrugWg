package pw.idrug.connections.activity

import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pw.idrug.connections.Application
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.QuickTileService
import pw.idrug.connections.R
import pw.idrug.connections.preference.PreferencesPreferenceDataStore
import pw.idrug.connections.util.AdminKnobs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import java.security.MessageDigest
import org.json.JSONObject

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportFragmentManager.findFragmentById(android.R.id.content) == null) {
            supportFragmentManager.commit {
                add(android.R.id.content, SettingsFragment())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private var downloadId: Long = -1
        private var expectedHash: String? = null

        private val downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1) ?: return
                if (id == downloadId) {
                    installApk()
                    requireContext().unregisterReceiver(this)
                }
            }
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, key: String?) {
            preferenceManager.preferenceDataStore = PreferencesPreferenceDataStore(lifecycleScope, Application.getPreferencesDataStore())
            addPreferencesFromResource(R.xml.preferences)
            preferenceScreen.initialExpandedChildrenCount = 5

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || QuickTileService.isAdded) {
                val quickTile = preferenceManager.findPreference<Preference>("quick_tile")
                quickTile?.parent?.removePreference(quickTile)
                --preferenceScreen.initialExpandedChildrenCount
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val darkTheme = preferenceManager.findPreference<Preference>("dark_theme")
                darkTheme?.parent?.removePreference(darkTheme)
                --preferenceScreen.initialExpandedChildrenCount
            }
            if (AdminKnobs.disableConfigExport) {
                val zipExporter = preferenceManager.findPreference<Preference>("zip_exporter")
                zipExporter?.parent?.removePreference(zipExporter)
            }
            preferenceManager.findPreference<Preference>("log_viewer")?.setOnPreferenceClickListener {
                startActivity(Intent(requireContext(), LogViewerActivity::class.java))
                true
            }

            preferenceManager.findPreference<Preference>("ota_update")?.setOnPreferenceClickListener {
                checkForOtaUpdate()
                true
            }
        }

private fun checkForOtaUpdate() {
    lifecycleScope.launch {
        try {
            val context = requireContext()
            val manifestText = withContext(kotlinx.coroutines.Dispatchers.IO) { URL("https://idrug.pw/ota/manifest.json").readText() }
            val obj = JSONObject(manifestText)
            val version = obj.getInt("versionCode")
            if (version <= BuildConfig.VERSION_CODE) {
                Toast.makeText(context, getString(R.string.update_not_available), Toast.LENGTH_SHORT).show()
                return@launch
            }
            val apkUrl = obj.getString("apkUrl")
            expectedHash = obj.optString("sha256")

            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.update_confirmation_title)
                .setMessage(R.string.update_confirmation_message)
                .setPositiveButton(android.R.string.ok) { _, _ -> startDownload(apkUrl) }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), getString(R.string.update_check_error, e.localizedMessage), Toast.LENGTH_LONG).show()
        }
    }
}

private fun startDownload(url: String) {
    try {
        val context = requireContext()
        val fileName = "iDrugConnections.apk"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
        if (file.exists()) {
            if (!file.delete()) {
                Toast.makeText(context, context.getString(R.string.update_delete_old_failed), Toast.LENGTH_SHORT).show()
            }
        }

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Downloading update")
            .setDescription("Downloading new application version")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(request)

        Toast.makeText(context, context.getString(R.string.update_download_started), Toast.LENGTH_SHORT).show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(downloadReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE), Context.RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(downloadReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        }
    } catch (e: Exception) {
        Toast.makeText(requireContext(), getString(R.string.update_start_error, e.localizedMessage), Toast.LENGTH_LONG).show()
    }
}


        private fun installApk() {
            val fileName = "iDrugConnections.apk"
            val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
            if (!file.exists()) {
                Toast.makeText(requireContext(), getString(R.string.apk_not_found), Toast.LENGTH_SHORT).show()
                return
            }
            expectedHash?.let { expected ->
                try {
                    val digest = MessageDigest.getInstance("SHA-256")
                    file.inputStream().use { input ->
                        val buf = ByteArray(8192)
                        var r: Int
                        while (input.read(buf).also { r = it } > 0) {
                            digest.update(buf, 0, r)
                        }
                    }
                    val hash = digest.digest().joinToString("") { "%02x".format(it) }
                    if (!hash.equals(expected, ignoreCase = true)) {
                        Toast.makeText(requireContext(), getString(R.string.update_hash_mismatch), Toast.LENGTH_LONG).show()
                        return
                    }
                } catch (_: Exception) {
                    // ignore and proceed
                }
            }
            val apkUri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.provider",
                file
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!requireContext().packageManager.canRequestPackageInstalls()) {
                    val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                    intent.data = Uri.parse("package:" + requireContext().packageName)
                    startActivity(intent)
                    Toast.makeText(requireContext(), getString(R.string.permission_unknown_sources), Toast.LENGTH_LONG).show()
                    return
                }
            }
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), getString(R.string.apk_open_error, e.message), Toast.LENGTH_LONG).show()
            }
        }
    }
}
