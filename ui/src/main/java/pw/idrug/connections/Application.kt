/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
package pw.idrug.connections

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.core.content.ContextCompat
import com.google.android.material.color.DynamicColors
import org.amnezia.awg.backend.AbstractBackend
import org.amnezia.awg.backend.Backend
import org.amnezia.awg.backend.GoBackend
import org.amnezia.awg.backend.NoopTunnelActionHandler
import pw.idrug.connections.configStore.FileConfigStore
import pw.idrug.connections.model.TunnelManager
import org.amnezia.awg.util.RootShell
import org.amnezia.awg.util.ToolsInstaller
import pw.idrug.connections.catalog.CatalogRepository
import pw.idrug.connections.util.UserKnobs
import pw.idrug.connections.util.applicationScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.lang.ref.WeakReference
import java.util.Locale
import java.util.concurrent.TimeUnit

class Application : android.app.Application() {
    private val futureBackend = CompletableDeferred<Backend>()
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main.immediate)
    private var backend: Backend? = null
    private var isConnectionStatusServiceStarted = false
    private var isServiceMonitoringActive = true
    private lateinit var rootShell: RootShell
    private lateinit var preferencesDataStore: DataStore<Preferences>
    private lateinit var initialPreferencesSnapshot: Preferences
    private lateinit var toolsInstaller: ToolsInstaller
    private lateinit var tunnelManager: TunnelManager
    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        if (BuildConfig.MIN_SDK_VERSION > Build.VERSION.SDK_INT) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            System.exit(0)
        }
    }

    private suspend fun determineBackend(): Backend {
        val backend = GoBackend(applicationContext, NoopTunnelActionHandler())
        AbstractBackend.setAlwaysOnCallback { get().applicationScope.launch { get().tunnelManager.restoreState(true) } }
        return backend
    }

    private suspend fun processNotification() {
        val intent = Intent(this@Application, ConnectionStatusService::class.java)
        while (isServiceMonitoringActive) {
            if (UserKnobs.enableNotification.first()) {
                val hasRunningTunnels = withContext(Dispatchers.IO) { futureBackend.await().runningTunnelNames }.isNotEmpty()
                if (isConnectionStatusServiceStarted && !hasRunningTunnels) {
                    isConnectionStatusServiceStarted = false
                    stopService(intent)
                } else if (!isConnectionStatusServiceStarted && hasRunningTunnels) {
                    isConnectionStatusServiceStarted = true
                    ContextCompat.startForegroundService(this@Application, intent)
                }
            } else if (isConnectionStatusServiceStarted) {
                isConnectionStatusServiceStarted = false
                stopService(intent)
            }
            delay(100)
        }
    }

    override fun onCreate() {
        Log.i(TAG, USER_AGENT)
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        rootShell = RootShell(applicationContext)
        toolsInstaller = ToolsInstaller(applicationContext, rootShell)
        preferencesDataStore = PreferenceDataStoreFactory.create { applicationContext.preferencesDataStoreFile("settings") }
        initialPreferencesSnapshot = runBlocking(Dispatchers.IO) { preferencesDataStore.data.first() }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            runBlocking {
                AppCompatDelegate.setDefaultNightMode(if (UserKnobs.darkTheme.first()) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
            }
            UserKnobs.darkTheme.onEach {
                val newMode = if (it) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                if (AppCompatDelegate.getDefaultNightMode() != newMode) {
                    AppCompatDelegate.setDefaultNightMode(newMode)
                }
            }.launchIn(coroutineScope)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        tunnelManager = TunnelManager(FileConfigStore(applicationContext))
        tunnelManager.onCreate()
        coroutineScope.launch(Dispatchers.IO) {
            try {
                backend = determineBackend()
                futureBackend.complete(backend!!)
            } catch (e: Throwable) {
                Log.e(TAG, Log.getStackTraceString(e))
            }
        }

        applicationScope.launch {
            processNotification()
        }

        CatalogRepository.prefetch(this)

        if (BuildConfig.DEBUG) {
            StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
            StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().penaltyLog().build())
        }
    }

    override fun onTerminate() {
        isServiceMonitoringActive = false
        isConnectionStatusServiceStarted = false
        stopService(Intent(this, ConnectionStatusService::class.java))
        coroutineScope.cancel()
        super.onTerminate()
    }

    companion object {
        val USER_AGENT = String.format(Locale.ENGLISH, "iDrugConnections/%s (Android %d; %s; %s; %s %s; %s)", BuildConfig.VERSION_NAME, Build.VERSION.SDK_INT, if (Build.SUPPORTED_ABIS.isNotEmpty()) Build.SUPPORTED_ABIS[0] else "unknown ABI", Build.BOARD, Build.MANUFACTURER, Build.MODEL, Build.FINGERPRINT)
        private const val TAG = "iDrugConnections/Application"
        private lateinit var weakSelf: WeakReference<Application>

        fun get(): Application {
            return weakSelf.get()!!
        }

        suspend fun getBackend() = get().futureBackend.await()

        fun getRootShell() = get().rootShell

        fun getPreferencesDataStore() = get().preferencesDataStore
        fun getInitialPreferencesSnapshot() = get().initialPreferencesSnapshot

        fun getToolsInstaller() = get().toolsInstaller

        fun getTunnelManager() = get().tunnelManager

        fun getCoroutineScope() = get().coroutineScope

        fun getHttpClient() = get().httpClient
    }

    init {
        weakSelf = WeakReference(this)
    }
}
