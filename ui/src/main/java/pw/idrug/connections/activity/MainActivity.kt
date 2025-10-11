package pw.idrug.connections.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import android.content.res.ColorStateList
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.MaterialColors
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.firebase.messaging.FirebaseMessaging
import android.graphics.drawable.ColorDrawable
import android.util.Log
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.first
import pw.idrug.connections.Application
import pw.idrug.connections.R
import pw.idrug.connections.fragment.TunnelDetailFragment
import pw.idrug.connections.fragment.TunnelEditorFragment
import pw.idrug.connections.fragment.TunnelListFragment
import pw.idrug.connections.activity.OnboardingActivity
import pw.idrug.connections.fragment.AccountFragment
import pw.idrug.connections.model.ObservableTunnel
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.dialog.UpdateDialogFragment
import pw.idrug.connections.di.UpdateModules
import pw.idrug.connections.ota.OtaMeta
import pw.idrug.connections.ota.UpdateState
import pw.idrug.connections.ota.UpdateViewModel
import pw.idrug.connections.util.UserKnobs

/**
 * CRUD interface for iDrugConnections tunnels. This activity serves as the main entry point to the
 * iDrugConnections application, and contains several fragments for listing, viewing details of, and
 * editing the configuration and interface state of iDrugConnections tunnels.
 */
class MainActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener {
    private var actionBar: ActionBar? = null
    private var isTwoPaneLayout = false
    private var backPressedCallback: OnBackPressedCallback? = null
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}
    private var updateDialogVersionShown: Int? = null
    private val updateViewModel: UpdateViewModel by viewModels()

    private fun handleBackPressed() {
        val backStackEntries = supportFragmentManager.backStackEntryCount
        // If the two-pane layout does not have an editor open, going back should exit the app.
        if (isTwoPaneLayout && backStackEntries <= 1) {
            finish()
            return
        }

        if (backStackEntries >= 1)
            supportFragmentManager.popBackStack()

        // Deselect the current tunnel on navigating back from the detail pane to the one-pane list.
        if (backStackEntries == 1)
            selectedTunnel = null
    }

    override fun onBackStackChanged() {
        val backStackEntries = supportFragmentManager.backStackEntryCount
        backPressedCallback?.isEnabled = backStackEntries >= 1
        if (actionBar == null) return
        // Do not show the home menu when the two-pane layout is at the detail view (see above).
        val minBackStackEntries = if (isTwoPaneLayout) 2 else 1
        actionBar!!.setDisplayHomeAsUpEnabled(backStackEntries >= minBackStackEntries)
    }

    override fun onResume() {
        super.onResume()
        syncSystemNavigationBarColor(findViewById(R.id.bottom_navigation))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val onboardingShown = prefs.getBoolean("onboarding_shown", false)
        val prefsAuth = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val tokenEmpty = prefsAuth.getString("token", null).isNullOrEmpty()
        if (!onboardingShown && tokenEmpty) {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.main_activity)
        actionBar = supportActionBar
        isTwoPaneLayout = findViewById<View?>(R.id.master_detail_wrapper) != null
        supportFragmentManager.addOnBackStackChangedListener(this)
        backPressedCallback = onBackPressedDispatcher.addCallback(this) { handleBackPressed() }
        onBackStackChanged()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notifPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            if (!notifPrefs.getBoolean("notification_permission_requested", false)) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
                notifPrefs.edit().putBoolean("notification_permission_requested", true).apply()
            }
        }

        // --- BottomNavigationView setup ---
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation?.let { nav ->
            val navColor = MaterialColors.getColor(
                nav,
                com.google.android.material.R.attr.colorSurfaceContainer
            )
            val shapeBackground = MaterialShapeDrawable.createWithElevationOverlay(nav.context).apply {
                fillColor = ColorStateList.valueOf(navColor)
                elevation = nav.elevation
            }
            nav.background = shapeBackground
            syncSystemNavigationBarColor(nav)
            nav.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
                syncSystemNavigationBarColor(nav)
            }
            nav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_vpn -> {
                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        safeReplaceFragment(TunnelListFragment())
                        true
                    }
                    R.id.nav_account -> {
                        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        safeReplaceFragment(AccountFragment())
                        true
                    }
                    else -> false
                }
            }
        }

        // Open initial tab
        if (savedInstanceState == null) {
            if (!isTwoPaneLayout) {
                val data = intent?.data
                val openedViaDeepLink = intent?.action == Intent.ACTION_VIEW && data != null
                val openAccountByIntent = intent?.getBooleanExtra(EXTRA_OPEN_ACCOUNT, false) == true ||
                    (openedViaDeepLink && data?.host == "auth")
                val openAccountByTunnels = runBlocking {
                    try {
                        Application.getTunnelManager().getTunnels().isEmpty()
                    } catch (e: Exception) {
                        Log.w("MainActivity", "Unable to check tunnels", e)
                        false
                    }
                }
                val openAccount = openAccountByIntent || openAccountByTunnels

                val fragment: Fragment = if (openAccount) {
                    AccountFragment()
                } else {
                    val def = TunnelListFragment()
                    if (openedViaDeepLink && data?.scheme == "idrug" && data.host == "apps") {
                        def.arguments = Bundle().apply {
                            putString(TunnelListFragment.ARG_OPEN_TUNNEL_FOR_APPS, data.lastPathSegment)
                        }
                    }
                    def
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
                bottomNavigation?.selectedItemId = if (openAccount) R.id.nav_account else R.id.nav_vpn
            }
        }

        registerForFcm()

        lifecycleScope.launch {
            updateViewModel.state.collectLatest { state ->
                if (state.updateAvailable && !state.loading) {
                    maybeShowUpdateDialog(state)
                }
            }
        }

        if (savedInstanceState == null) {
            lifecycleScope.launch { performAutoUpdateCheck() }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            syncSystemNavigationBarColor(findViewById(R.id.bottom_navigation))
        }
    }

    private fun syncSystemNavigationBarColor(bottomNavigation: BottomNavigationView?) {
        val window = window
        val baseView = bottomNavigation ?: window.decorView
        val background = bottomNavigation?.background
        val surface = MaterialColors.getColor(
            baseView,
            com.google.android.material.R.attr.colorSurface
        )
        val surfaceVariant = MaterialColors.getColor(
            baseView,
            com.google.android.material.R.attr.colorSurfaceVariant,
            surface
        )

        val color = when {
            bottomNavigation?.backgroundTintList?.defaultColor != null ->
                bottomNavigation.backgroundTintList!!.defaultColor
            background is MaterialShapeDrawable ->
                background.fillColor?.defaultColor
            background is ColorDrawable -> background.color
            else -> null
        } ?: surfaceVariant

        window.navigationBarColor = color
        val isLight = ColorUtils.calculateLuminance(color) > 0.5
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightNavigationBars = isLight
    }

    fun refreshSystemNavigationBarColor() {
        syncSystemNavigationBarColor(findViewById(R.id.bottom_navigation))
    }

    private fun subscribeToGlobalNotifications() {
        FirebaseMessaging.getInstance().subscribeToTopic("all-users")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM", "✅ Подписан на пуш-уведомления для всех ('all-users')")
                } else {
                    Log.e("FCM", "❌ Ошибка подписки на топик", task.exception)
                }
            }
    }

    private fun registerForFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d("FCM", "Current FCM token: $token")
        }
        subscribeToGlobalNotifications()
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val tgId = prefs.getString("telegram_id", null)
        if (!tgId.isNullOrEmpty()) {
            FirebaseMessaging.getInstance().subscribeToTopic("user_$tgId")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // The back arrow in the action bar should act the same as the back button.
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.menu_action_edit -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, TunnelEditorFragment())
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    addToBackStack(null)
                }
                true
            }
            // This menu item is handled by the editor fragment.
            R.id.menu_action_save -> false
            R.id.menu_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSelectedTunnelChanged(
        oldTunnel: ObservableTunnel?,
        newTunnel: ObservableTunnel?
    ): Boolean {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.isStateSaved) {
            return false
        }

        val backStackEntries = fragmentManager.backStackEntryCount
        if (newTunnel == null) {
            // Clear all editors and detail fragments
            fragmentManager.popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            return true
        }
        if (backStackEntries == 2) {
            // Pop the editor off the back stack to reveal the detail fragment.
            fragmentManager.popBackStackImmediate()
        } else if (backStackEntries == 0) {
            // Show detail fragment
            fragmentManager.commit {
                add(R.id.fragment_container, TunnelDetailFragment())
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
            }
        }
        return true
    }

    private fun safeReplaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        if (!fm.isStateSaved) {
            fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    private suspend fun performAutoUpdateCheck() {
        val autoCheckEnabled = withContext(Dispatchers.IO) {
            UserKnobs.updatesAutoCheckEnabled.first()
        }
        if (!autoCheckEnabled) return
        val result = withContext(Dispatchers.IO) { UpdateModules.repository.getMeta() }
        result.onSuccess { meta ->
            if (meta.versionCode > BuildConfig.VERSION_CODE && updateDialogVersionShown != meta.versionCode) {
                updateViewModel.setMeta(meta)
            }
        }.onFailure { throwable ->
            Log.w("MainActivity", "Failed to fetch OTA metadata", throwable)
        }
    }

    private fun maybeShowUpdateDialog(state: UpdateState) {
        if (!state.updateAvailable || state.loading) return
        val versionCode = state.versionCode ?: return
        val apkUrl = state.apkUrl ?: return
        if (state.error != null) return
        if (updateDialogVersionShown == versionCode) return
        if (supportFragmentManager.isStateSaved) return
        if (supportFragmentManager.findFragmentByTag(UPDATE_DIALOG_TAG) != null) return
        updateDialogVersionShown = versionCode
        val meta = OtaMeta(
            versionCode = versionCode,
            versionName = state.versionName,
            apkUrl = apkUrl,
            changelog = state.changelog
        )
        UpdateDialogFragment.show(supportFragmentManager, meta, auto = true)
    }

    companion object {
        const val EXTRA_OPEN_ACCOUNT = "open_account"
        private const val UPDATE_DIALOG_TAG = "update_dialog"
    }
}
