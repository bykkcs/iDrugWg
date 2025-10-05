package pw.idrug.connections.domain

import android.content.SharedPreferences
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import pw.idrug.connections.BuildConfig
import pw.idrug.connections.data.UpdateApi
import pw.idrug.connections.data.UpdateMeta
import pw.idrug.connections.data.UpdateRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class UpdateManagerTest {
    @Test
    fun checkReturnsAvailableWhenNewerVersion() = runBlocking {
        val meta = UpdateMeta(BuildConfig.VERSION_CODE + 1, "https://example.com/app.apk")
        val manager = createManager(meta)
        val state = manager.check(force = true)
        assertTrue(state is UpdateState.Available && state.meta == meta)
    }

    @Test
    fun checkRespectsIgnoredVersion() = runBlocking {
        val meta = UpdateMeta(BuildConfig.VERSION_CODE + 2, "https://example.com/app.apk")
        val manager = createManager(meta)
        manager.ignore(meta.versionCode)
        val state = manager.check(force = true)
        assertTrue(state is UpdateState.NoUpdate)
    }

    @Test
    fun checkReturnsNoUpdateForCurrentVersion() = runBlocking {
        val meta = UpdateMeta(BuildConfig.VERSION_CODE, "https://example.com/app.apk")
        val manager = createManager(meta)
        val state = manager.check(force = true)
        assertTrue(state is UpdateState.NoUpdate)
    }

    private fun createManager(meta: UpdateMeta?): UpdateManager {
        val api = object : UpdateApi {
            override suspend fun getMeta(): UpdateMeta {
                return meta ?: throw IllegalStateException("No meta")
            }
        }
        val repository = UpdateRepository(api)
        val prefs = InMemorySharedPreferences()
        val updatePreferences = UpdatePreferences(prefs, Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build())
        return UpdateManager(repository, updatePreferences)
    }
}

private class InMemorySharedPreferences : SharedPreferences {
    private val data = mutableMapOf<String, Any?>()
    private val listeners = mutableSetOf<SharedPreferences.OnSharedPreferenceChangeListener>()

    override fun getAll(): MutableMap<String, *> = data.toMutableMap()

    override fun getString(key: String?, defValue: String?): String? = data[key] as? String ?: defValue

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        @Suppress("UNCHECKED_CAST")
        return (data[key] as? MutableSet<String>) ?: defValues
    }

    override fun getInt(key: String?, defValue: Int): Int = data[key] as? Int ?: defValue

    override fun getLong(key: String?, defValue: Long): Long = data[key] as? Long ?: defValue

    override fun getFloat(key: String?, defValue: Float): Float = data[key] as? Float ?: defValue

    override fun getBoolean(key: String?, defValue: Boolean): Boolean = data[key] as? Boolean ?: defValue

    override fun contains(key: String?): Boolean = data.containsKey(key)

    override fun edit(): SharedPreferences.Editor = Editor()

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        listener?.let { listeners.add(it) }
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        listener?.let { listeners.remove(it) }
    }

    private inner class Editor : SharedPreferences.Editor {
        private val pendingUpdates = mutableMapOf<String, Any?>()
        private val removals = mutableSetOf<String>()
        private var clear = false

        override fun putString(key: String?, value: String?): SharedPreferences.Editor = apply {
            if (key != null) pendingUpdates[key] = value
        }

        override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor = apply {
            if (key != null) pendingUpdates[key] = values
        }

        override fun putInt(key: String?, value: Int): SharedPreferences.Editor = apply {
            if (key != null) pendingUpdates[key] = value
        }

        override fun putLong(key: String?, value: Long): SharedPreferences.Editor = apply {
            if (key != null) pendingUpdates[key] = value
        }

        override fun putFloat(key: String?, value: Float): SharedPreferences.Editor = apply {
            if (key != null) pendingUpdates[key] = value
        }

        override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor = apply {
            if (key != null) pendingUpdates[key] = value
        }

        override fun remove(key: String?): SharedPreferences.Editor = apply {
            if (key != null) removals.add(key)
        }

        override fun clear(): SharedPreferences.Editor = apply { clear = true }

        override fun commit(): Boolean {
            applyChanges()
            return true
        }

        override fun apply() {
            applyChanges()
        }

        private fun applyChanges() {
            synchronized(data) {
                if (clear) {
                    data.clear()
                }
                removals.forEach { data.remove(it) }
                pendingUpdates.forEach { (key, value) ->
                    if (value == null) {
                        data.remove(key)
                    } else {
                        data[key] = value
                    }
                }
            }
            if (pendingUpdates.isNotEmpty() || removals.isNotEmpty() || clear) {
                listeners.forEach { listener ->
                    pendingUpdates.keys.forEach { key -> listener.onSharedPreferenceChanged(this@InMemorySharedPreferences, key) }
                    removals.forEach { key -> listener.onSharedPreferenceChanged(this@InMemorySharedPreferences, key) }
                }
            }
            pendingUpdates.clear()
            removals.clear()
            clear = false
        }
    }
}
