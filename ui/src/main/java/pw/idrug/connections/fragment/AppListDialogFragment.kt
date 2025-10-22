/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections.fragment

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.Toast
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.databinding.Observable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import pw.idrug.connections.BR
import pw.idrug.connections.R
import pw.idrug.connections.databinding.AppListDialogFragmentBinding
import pw.idrug.connections.databinding.ObservableKeyedArrayList
import pw.idrug.connections.databinding.AppListItemBinding
import pw.idrug.connections.databinding.ObservableKeyedRecyclerViewAdapter.RowConfigurationHandler
import pw.idrug.connections.model.ApplicationData
import pw.idrug.connections.util.ErrorMessages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppListDialogFragment : DialogFragment() {
    private val appData = ObservableKeyedArrayList<String, ApplicationData>()
    private var currentlySelectedApps = emptyList<String>()
    private var initiallyExcluded = false
    private var selectButton: Button? = null
    private var tabs: TabLayout? = null
    private var popularCount = 0

    private val headerRowConfigurationHandler = object : RowConfigurationHandler<AppListItemBinding, ApplicationData> {
        override fun onConfigureRow(binding: AppListItemBinding, item: ApplicationData, position: Int) {
            val headerView = binding.sectionHeader
            val context = headerView.context
            val showPopularHeader = popularCount > 0 && position == 0
            val showAllHeader = popularCount > 0 && position == popularCount
            when {
                showPopularHeader -> {
                    headerView.text = context.getString(R.string.apps_header_popular)
                    headerView.visibility = View.VISIBLE
                }
                showAllHeader -> {
                    headerView.text = context.getString(R.string.apps_header_all)
                    headerView.visibility = View.VISIBLE
                }
                position == 0 -> {
                    headerView.text = context.getString(R.string.apps_header_all)
                    headerView.visibility = View.VISIBLE
                }
                else -> headerView.visibility = View.GONE
            }
        }
    }

    val rowConfigurationHandler: RowConfigurationHandler<AppListItemBinding, ApplicationData>
        get() = headerRowConfigurationHandler

    private fun loadData() {
        val activity = activity ?: return
        val pm = activity.packageManager
        lifecycleScope.launch(Dispatchers.Default) {
            try {
                val applicationData: MutableList<ApplicationData> = ArrayList()
                withContext(Dispatchers.IO) {
                    val packageInfos = getPackagesHoldingPermissions(pm, arrayOf(Manifest.permission.INTERNET))
                    packageInfos.forEach {
                        val packageName = it.packageName
                        // Only show applications that have launcher activities
                        if (pm.getLaunchIntentForPackage(packageName) == null)
                            return@forEach
                        val appInfo = it.applicationInfo ?: return@forEach
                        val appData = ApplicationData(
                            appInfo.loadIcon(pm),
                            appInfo.loadLabel(pm).toString(),
                            packageName,
                            currentlySelectedApps.contains(packageName)
                        )
                        applicationData.add(appData)
                        appData.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                                if (propertyId == BR.selected)
                                    setButtonText()
                            }
                        })
                    }
                }
                applicationData.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
                val (ordered, popular) = prioritizePopularApps(applicationData)
                withContext(Dispatchers.Main.immediate) {
                    popularCount = popular
                    appData.clear()
                    appData.addAll(ordered)
                    setButtonText()
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main.immediate) {
                    val error = ErrorMessages[e]
                    val message = activity.getString(R.string.error_fetching_apps, error)
                    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                    dismissAllowingStateLoss()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentlySelectedApps = (arguments?.getStringArrayList(KEY_SELECTED_APPS) ?: emptyList())
        initiallyExcluded = arguments?.getBoolean(KEY_IS_EXCLUDED) ?: true
    }

    private fun getPackagesHoldingPermissions(pm: PackageManager, permissions: Array<String>): List<PackageInfo> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pm.getPackagesHoldingPermissions(permissions, PackageInfoFlags.of(0L))
        } else {
            @Suppress("DEPRECATION")
            pm.getPackagesHoldingPermissions(permissions, 0)
        }
    }

    private fun setButtonText() {
        val numSelected = appData.count { it.isSelected }
        selectButton?.text = if (numSelected == 0)
            getString(R.string.use_all_applications)
        else when (tabs?.selectedTabPosition) {
            0 -> resources.getQuantityString(R.plurals.exclude_n_applications, numSelected, numSelected)
            1 -> resources.getQuantityString(R.plurals.include_n_applications, numSelected, numSelected)
            else -> null
        }
    }

     override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = MaterialAlertDialogBuilder(requireActivity(), R.style.MonetAlertDialog)
        val binding = AppListDialogFragmentBinding.inflate(requireActivity().layoutInflater, null, false)
        binding.executePendingBindings()
        alertDialogBuilder.setView(binding.root)
        tabs = binding.tabs
        tabs?.apply {
            selectTab(binding.tabs.getTabAt(if (initiallyExcluded) 0 else 1))
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                override fun onTabSelected(tab: TabLayout.Tab?) = setButtonText()
            })
        }
        // remove explicit cancel button to keep buttons compact
        alertDialogBuilder.setNeutralButton(R.string.toggle_all, null)
        alertDialogBuilder.setNegativeButton(R.string.use_all_applications, null)
        binding.fragment = this
        binding.appData = appData
        loadData()
        val dialog = alertDialogBuilder.create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.visibility = View.GONE
            val invertButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
            val selectAllButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            selectButton = selectAllButton

            invertButton.setOnClickListener {
                appData.forEach { it.isSelected = !it.isSelected }
                setButtonText()
            }

            selectAllButton.setOnClickListener {
                val numSelected = appData.count { it.isSelected }
                if (numSelected == 0) {
                    appData.forEach { it.isSelected = true }
                    setButtonText()
                } else {
                    sendResult()
                    dismiss()
                }
            }

            setButtonText()
        }
        return dialog
    }

    private fun sendResult() {
        val selectedApps = appData.filter { it.isSelected }.map { it.packageName }
        setFragmentResult(
            REQUEST_SELECTION, bundleOf(
                KEY_SELECTED_APPS to selectedApps.toTypedArray(),
                KEY_IS_EXCLUDED to (tabs?.selectedTabPosition == 0)
            )
        )
    }

    override fun onDismiss(dialog: DialogInterface) {
        sendResult()
        super.onDismiss(dialog)
    }

    companion object {
        const val KEY_SELECTED_APPS = "selected_apps"
        const val KEY_IS_EXCLUDED = "is_excluded"
        const val REQUEST_SELECTION = "request_selection"

        private val POPULAR_APP_ORDER = listOf(
            "com.google.android.youtube",
            "com.google.android.youtube.tv",
            "com.google.android.youtube.tvmusic",
            "com.google.android.gms",
            "com.google.android.apps.youtube.music",
            "com.android.chrome",
            "org.mozilla.firefox",
            "com.brave.browser",
            "com.microsoft.emmx",
            "com.vivaldi.browser",
            "com.netflix.mediaclient",
            "com.google.android.apps.photos",
            "com.instagram.android",
            "com.zhiliaoapp.musically",
            "com.ss.android.ugc.trill",
            "com.spotify.music",
            "com.facebook.katana",
            "com.twitter.android",
            "org.telegram.messenger",
            "org.thunderdog.challegram",
            "com.discord"
        )
        private val POPULAR_ORDER_MAP = POPULAR_APP_ORDER.withIndex().associate { it.value to it.index }

        private fun prioritizePopularApps(apps: List<ApplicationData>): Pair<List<ApplicationData>, Int> {
            if (POPULAR_ORDER_MAP.isEmpty()) return apps to 0
            val popular = ArrayList<ApplicationData>()
            val others = ArrayList<ApplicationData>()
            apps.forEach { app ->
                if (POPULAR_ORDER_MAP.containsKey(app.packageName)) {
                    popular.add(app)
                } else {
                    others.add(app)
                }
            }
            popular.sortWith(
                compareBy<ApplicationData> { POPULAR_ORDER_MAP[it.packageName] ?: Int.MAX_VALUE }
                    .thenBy(String.CASE_INSENSITIVE_ORDER) { it.name }
            )
            others.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
            return (popular + others) to popular.size
        }

        fun newInstance(selectedApps: ArrayList<String?>?, isExcluded: Boolean): AppListDialogFragment {
            val extras = Bundle()
            extras.putStringArrayList(KEY_SELECTED_APPS, selectedApps)
            extras.putBoolean(KEY_IS_EXCLUDED, isExcluded)
            val fragment = AppListDialogFragment()
            fragment.arguments = extras
            return fragment
        }
    }
}
