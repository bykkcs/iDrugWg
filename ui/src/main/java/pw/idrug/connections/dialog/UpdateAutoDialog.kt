package pw.idrug.connections.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pw.idrug.connections.R

class UpdateAutoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ctx = requireContext()
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        val view = LayoutInflater.from(ctx).inflate(R.layout.dialog_auto_update, null, false)

        return MaterialAlertDialogBuilder(ctx, R.style.MonetAlertDialog)
            .setTitle(R.string.auto_update_title)
            .setView(view)
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.auto_update_disable) { _, _ ->
                prefs.edit().putBoolean("auto_update_enabled", false).apply()
                Toast.makeText(ctx, R.string.auto_update_disabled_toast, Toast.LENGTH_SHORT).show()
            }
            .create()
    }

    companion object {
        fun show(fm: FragmentManager) {
            UpdateAutoDialog().show(fm, "UpdateAutoDialog")
        }
        fun showFromCompose(activityOrContext: Any) {
            val activity = when (activityOrContext) {
                is FragmentActivity -> activityOrContext
                is android.content.Context -> (activityOrContext as? FragmentActivity)
                else -> null
            } ?: return
            show(activity.supportFragmentManager)
        }
    }
}
