/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package pw.idrug.connections.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pw.idrug.connections.Application

class PreferencesPreferenceDataStore(private val coroutineScope: CoroutineScope, private val dataStore: DataStore<Preferences>) : PreferenceDataStore() {
    private val initialSnapshot = Application.getInitialPreferencesSnapshot()
    private val prefsState = dataStore.data.stateIn(coroutineScope, SharingStarted.Eagerly, initialSnapshot)

    override fun putString(key: String?, value: String?) {
        if (key == null) return
        val pk = stringPreferencesKey(key)
        coroutineScope.launch {
            dataStore.edit {
                if (value == null) it.remove(pk)
                else it[pk] = value
            }
        }
    }

    override fun putStringSet(key: String?, values: Set<String?>?) {
        if (key == null) return
        val pk = stringSetPreferencesKey(key)
        val filteredValues = values?.filterNotNull()?.toSet()
        coroutineScope.launch {
            dataStore.edit {
                if (filteredValues == null || filteredValues.isEmpty()) it.remove(pk)
                else it[pk] = filteredValues
            }
        }
    }

    override fun putInt(key: String?, value: Int) {
        if (key == null) return
        val pk = intPreferencesKey(key)
        coroutineScope.launch {
            dataStore.edit {
                it[pk] = value
            }
        }
    }

    override fun putLong(key: String?, value: Long) {
        if (key == null) return
        val pk = longPreferencesKey(key)
        coroutineScope.launch {
            dataStore.edit {
                it[pk] = value
            }
        }
    }

    override fun putFloat(key: String?, value: Float) {
        if (key == null) return
        val pk = floatPreferencesKey(key)
        coroutineScope.launch {
            dataStore.edit {
                it[pk] = value
            }
        }
    }

    override fun putBoolean(key: String?, value: Boolean) {
        if (key == null) return
        val pk = booleanPreferencesKey(key)
        coroutineScope.launch {
            dataStore.edit {
                it[pk] = value
            }
        }
    }

    override fun getString(key: String?, defValue: String?): String? {
        if (key == null) return defValue
        val pk = stringPreferencesKey(key)
        val prefs = prefsState.value
        return prefs[pk] ?: defValue
    }

    override fun getStringSet(key: String?, defValues: Set<String?>?): Set<String?>? {
        if (key == null) return defValues
        val pk = stringSetPreferencesKey(key)
        val prefs = prefsState.value
        return prefs[pk] ?: defValues
    }

    override fun getInt(key: String?, defValue: Int): Int {
        if (key == null) return defValue
        val pk = intPreferencesKey(key)
        val prefs = prefsState.value
        return prefs[pk] ?: defValue
    }

    override fun getLong(key: String?, defValue: Long): Long {
        if (key == null) return defValue
        val pk = longPreferencesKey(key)
        val prefs = prefsState.value
        return prefs[pk] ?: defValue
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        if (key == null) return defValue
        val pk = floatPreferencesKey(key)
        val prefs = prefsState.value
        return prefs[pk] ?: defValue
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        if (key == null) return defValue
        val pk = booleanPreferencesKey(key)
        val prefs = prefsState.value
        return prefs[pk] ?: defValue
    }
}
