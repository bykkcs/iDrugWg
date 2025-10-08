package pw.idrug.connections.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.google.android.material.color.MaterialColors
import pw.idrug.connections.Application
import pw.idrug.connections.QuickTileService
import pw.idrug.connections.R
import pw.idrug.connections.backend.AwgQuickBackend
import pw.idrug.connections.preference.PreferencesPreferenceDataStore
import pw.idrug.connections.util.AdminKnobs
import pw.idrug.connections.util.UserKnobs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pw.idrug.connections.dialog.UpdateDialogFragment
import kotlinx.coroutines.flow.first

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
                UpdateDialogFragment.show(parentFragmentManager)
                true
            }

            preferenceManager.findPreference<SwitchPreferenceCompat>("updates_auto_check_enabled")?.let { pref ->
                lifecycleScope.launch {
                    pref.isChecked = UserKnobs.updatesAutoCheckEnabled.first()
                }
                pref.setOnPreferenceChangeListener { _, newValue ->
                    val enabled = (newValue as? Boolean) ?: return@setOnPreferenceChangeListener false
                    lifecycleScope.launch { UserKnobs.setUpdatesAutoCheckEnabled(enabled) }
                    true
                }
            }
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val activity = requireActivity()
            val window = activity.window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val surfaceColor = MaterialColors.getColor(view, com.google.android.material.R.attr.colorSurface)
            window.statusBarColor = surfaceColor
            window.navigationBarColor = surfaceColor
            val insetsController = WindowInsetsControllerCompat(window, window.decorView)
            val lightSurface = MaterialColors.isColorLight(surfaceColor)
            insetsController.isAppearanceLightStatusBars = lightSurface
            insetsController.isAppearanceLightNavigationBars = lightSurface
            view.setBackgroundColor(surfaceColor)
            ViewCompat.requestApplyInsets(view)
            val preferenceList = listView
            preferenceList.setBackgroundColor(surfaceColor)
            ViewCompat.setOnApplyWindowInsetsListener(preferenceList) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.updatePadding(
                    left = systemBars.left,
                    top = systemBars.top,
                    right = systemBars.right,
                    bottom = systemBars.bottom
                )
                insets
            }
            ViewCompat.requestApplyInsets(preferenceList)
        }
    }
}
