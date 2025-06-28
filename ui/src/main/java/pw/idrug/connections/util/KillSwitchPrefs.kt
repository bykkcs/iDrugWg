package pw.idrug.connections.util

import android.content.Context
import pw.idrug.connections.Application

object KillSwitchPrefs {
    private const val PREFS = "kill_switch_prefs"
    private const val KEY_WHITELIST = "whitelist"

    private val prefs get() =
        Application.get().getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun getWhitelist(): Set<String> =
        prefs.getStringSet(KEY_WHITELIST, emptySet()) ?: emptySet()

    fun setWhitelist(packages: Set<String>) {
        prefs.edit().putStringSet(KEY_WHITELIST, packages).apply()
    }
}
