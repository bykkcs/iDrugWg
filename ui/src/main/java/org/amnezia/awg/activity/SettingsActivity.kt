package com.idrug.connections.pw.activity

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
import com.idrug.connections.pw.Application
import com.idrug.connections.pw.QuickTileService
import com.idrug.connections.pw.R
import com.idrug.connections.pw.backend.AwgQuickBackend
import com.idrug.connections.pw.preference.PreferencesPreferenceDataStore
import com.idrug.connections.pw.util.AdminKnobs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

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
            val awgQuickOnlyPrefs = arrayOf(
                preferenceManager.findPreference("tools_installer"),
                preferenceManager.findPreference("restore_on_boot"),
                preferenceManager.findPreference<Preference>("multiple_tunnels")
            ).filterNotNull()
            awgQuickOnlyPrefs.forEach { it.isVisible = false }
            lifecycleScope.launch {
                if (Application.getBackend() is AwgQuickBackend) {
                    ++preferenceScreen.initialExpandedChildrenCount
                    awgQuickOnlyPrefs.forEach { it.isVisible = true }
                } else {
                    awgQuickOnlyPrefs.forEach { it.parent?.removePreference(it) }
                }
            }
            preferenceManager.findPreference<Preference>("log_viewer")?.setOnPreferenceClickListener {
                startActivity(Intent(requireContext(), LogViewerActivity::class.java))
                true
            }
            val kernelModuleEnabler = preferenceManager.findPreference<Preference>("kernel_module_enabler")
            if (AwgQuickBackend.hasKernelSupport()) {
                lifecycleScope.launch {
                    if (Application.getBackend() !is AwgQuickBackend) {
                        try {
                            withContext(Dispatchers.IO) { Application.getRootShell().start() }
                        } catch (_: Throwable) {
                            kernelModuleEnabler?.parent?.removePreference(kernelModuleEnabler)
                        }
                    }
                }
            } else {
                kernelModuleEnabler?.parent?.removePreference(kernelModuleEnabler)
            }

            preferenceManager.findPreference<Preference>("ota_update")?.setOnPreferenceClickListener {
                checkForOtaUpdate()
                true
            }
        }

private fun checkForOtaUpdate() {
    try {
        val context = requireContext()
        val url = "https://idrug.pw/ota/ui-debug.apk"
        val fileName = "ui-debug.apk"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
        if (file.exists()) {
            val deleted = file.delete()
            if (!deleted) {
                Toast.makeText(context, "Не удалось удалить старый файл обновления", Toast.LENGTH_SHORT).show()
            }
        }

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Загрузка обновления")
            .setDescription("Скачивается новая версия приложения")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(request)

        Toast.makeText(context, "Скачивание обновления начато", Toast.LENGTH_SHORT).show()

        // Вот тут исправленный вызов:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(
                downloadReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                Context.RECEIVER_NOT_EXPORTED
            )
        } else {
            context.registerReceiver(
                downloadReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
            )
        }
    } catch (e: Exception) {
        Toast.makeText(requireContext(), "Ошибка при запуске обновления: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
        e.printStackTrace()
    }
}


        private fun installApk() {
            val fileName = "ui-debug.apk"
            val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
            if (!file.exists()) {
                Toast.makeText(requireContext(), "APK не найден!", Toast.LENGTH_SHORT).show()
                return
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
                    Toast.makeText(requireContext(), "Дайте разрешение на установку из неизвестных источников и повторите попытку", Toast.LENGTH_LONG).show()
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
                Toast.makeText(requireContext(), "Не удалось открыть APK для установки: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
