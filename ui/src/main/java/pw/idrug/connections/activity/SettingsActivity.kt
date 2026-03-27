package pw.idrug.connections.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.MaterialColors
import pw.idrug.connections.Application
import pw.idrug.connections.QuickTileService
import pw.idrug.connections.R
import org.amnezia.awg.backend.AwgQuickBackend
import pw.idrug.connections.preference.PreferencesPreferenceDataStore
import pw.idrug.connections.util.AdminKnobs
import pw.idrug.connections.util.UsageAccessUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pw.idrug.connections.dialog.UpdateDialogFragment
import pw.idrug.connections.util.applyStatusBarPadding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_settings)
        val appBarContainer = findViewById<View>(R.id.settings_app_bar_container)
        appBarContainer?.applyStatusBarPadding()
        findViewById<MaterialToolbar>(R.id.settings_top_app_bar)?.let { toolbar ->
            setSupportActionBar(toolbar)
        }
        val statusColor = MaterialColors.getColor(
            appBarContainer ?: window.decorView,
            android.R.attr.colorBackground,
            Color.BLACK
        )
        @Suppress("DEPRECATION")
        window.statusBarColor = statusColor
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            ColorUtils.calculateLuminance(statusColor) > 0.5
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = getString(R.string.settings)
        if (supportFragmentManager.findFragmentById(R.id.settings_fragment_container) == null) {
            supportFragmentManager.commit {
                add(R.id.settings_fragment_container, SettingsFragment())
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

            preferenceManager.findPreference<SwitchPreferenceCompat>("live_usage_chip")?.apply {
                setOnPreferenceChangeListener { _, newValue ->
                    val enabled = newValue as Boolean
                    if (enabled && !UsageAccessUtils.hasUsageAccess(requireContext())) {
                        UsageAccessUtils.openUsageAccessSettings(requireContext())
                        Toast.makeText(requireContext(), R.string.usage_access_request, Toast.LENGTH_LONG).show()
                        false
                    } else {
                        true
                    }
                }
            }

            preferenceManager.findPreference<Preference>("ota_update")?.setOnPreferenceClickListener {
                UpdateDialogFragment.show(parentFragmentManager)
                true
            }
        }
    }
}
