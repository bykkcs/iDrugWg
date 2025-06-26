package pw.idrug.connections.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import pw.idrug.connections.R
import pw.idrug.connections.fragment.TunnelDetailFragment
import pw.idrug.connections.fragment.TunnelEditorFragment
import pw.idrug.connections.fragment.TunnelListFragment
import pw.idrug.connections.fragment.AccountFragment
import pw.idrug.connections.onboarding.OnboardingFragment
import pw.idrug.connections.model.ObservableTunnel

/**
 * CRUD interface for iDrugConnections tunnels. This activity serves as the main entry point to the
 * iDrugConnections application, and contains several fragments for listing, viewing details of, and
 * editing the configuration and interface state of iDrugConnections tunnels.
 */
class MainActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener {
    private var actionBar: ActionBar? = null
    private var isTwoPaneLayout = false
    private var backPressedCallback: OnBackPressedCallback? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        actionBar = supportActionBar
        isTwoPaneLayout = findViewById<View?>(R.id.master_detail_wrapper) != null
        supportFragmentManager.addOnBackStackChangedListener(this)
        backPressedCallback = onBackPressedDispatcher.addCallback(this) { handleBackPressed() }
        onBackStackChanged()

        // --- BottomNavigationView setup ---
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_vpn -> {
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TunnelListFragment())
                        .commit()
                    true
                }
                R.id.nav_account -> {
                    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AccountFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Open VPN tab by default
        if (savedInstanceState == null) {
            val fragment = TunnelListFragment()
            val data = intent?.data
            if (intent?.action == Intent.ACTION_VIEW && data?.scheme == "idrug" && data.host == "apps") {
                fragment.arguments = Bundle().apply {
                    putString(TunnelListFragment.ARG_OPEN_TUNNEL_FOR_APPS, data.lastPathSegment)
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            bottomNavigation?.selectedItemId = R.id.nav_vpn

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, pw.idrug.connections.onboarding.OnboardingFragment())
                .addToBackStack(null)
                .commit()
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

}
