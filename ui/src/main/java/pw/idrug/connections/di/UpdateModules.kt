package pw.idrug.connections.di

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import pw.idrug.connections.data.UpdateApi
import pw.idrug.connections.data.UpdateRepository
import pw.idrug.connections.domain.UpdateManager
import pw.idrug.connections.domain.UpdatePreferences
import pw.idrug.connections.installer.ApkInstaller
import pw.idrug.connections.work.UpdateWorker
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object UpdateModules {
    private const val PREF_NAME = "updater"
    private const val BASE_URL = "https://idrug.pw/"
    private const val WORK_NAME = "ota_update_worker"

    private val moshi: Moshi by lazy {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(7, TimeUnit.SECONDS)
            .readTimeout(7, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val updateApi: UpdateApi by lazy { retrofit.create(UpdateApi::class.java) }
    private val updateRepository: UpdateRepository by lazy { UpdateRepository(updateApi) }

    @Volatile
    private var updateManager: UpdateManager? = null

    @Volatile
    private var apkInstaller: ApkInstaller? = null

    fun provideUpdateManager(context: Context): UpdateManager {
        val appContext = context.applicationContext
        return updateManager ?: synchronized(this) {
            updateManager ?: UpdateManager(
                updateRepository,
                UpdatePreferences(appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE), moshi)
            ).also { updateManager = it }
        }
    }

    fun provideApkInstaller(context: Context): ApkInstaller {
        val appContext = context.applicationContext
        return apkInstaller ?: synchronized(this) {
            apkInstaller ?: ApkInstaller(appContext, provideUpdateManager(appContext)).also { apkInstaller = it }
        }
    }

    fun schedulePeriodicWork(context: Context) {
        val appContext = context.applicationContext
        val workManager = WorkManager.getInstance(appContext)
        val request = PeriodicWorkRequestBuilder<UpdateWorker>(1, TimeUnit.DAYS, 2, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .build()
        workManager.enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, request)
    }

    fun provideMoshi(): Moshi = moshi
}
