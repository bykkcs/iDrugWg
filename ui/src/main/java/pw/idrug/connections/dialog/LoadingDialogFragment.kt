package pw.idrug.connections.dialog

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pw.idrug.connections.R
import pw.idrug.connections.activity.MainActivity

class LoadingDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_loading_indicator, null, false)
        return MaterialAlertDialogBuilder(requireContext(), R.style.MonetAlertDialog)
            .setView(view)
            .create().apply {
                setCanceledOnTouchOutside(false)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
    }

    override fun onStart() {
        super.onStart()
        applyNavigationBarColor()
    }

    private fun applyNavigationBarColor() {
        val activity = activity as? MainActivity ?: return
        activity.refreshSystemNavigationBarColor()
        val navColor = activity.window.navigationBarColor
        val dialogWindow = dialog?.window ?: return
        dialogWindow.navigationBarColor = navColor
        val isLight = ColorUtils.calculateLuminance(navColor) > 0.5
        WindowCompat.getInsetsController(dialogWindow, dialogWindow.decorView)
            ?.isAppearanceLightNavigationBars = isLight
    }

    companion object {
        private const val TAG = "loading_dialog"
        private const val AUTO_DISMISS_MS = 5_000L
        private val handler = Handler(Looper.getMainLooper())
        private var pendingDismiss: Runnable? = null

        fun show(manager: androidx.fragment.app.FragmentManager) {
            if (manager.findFragmentByTag(TAG) == null) {
                LoadingDialogFragment().show(manager, TAG)
            }
            scheduleAutoDismiss(manager)
        }

        fun dismiss(manager: androidx.fragment.app.FragmentManager) {
            pendingDismiss?.let { handler.removeCallbacks(it) }
            pendingDismiss = null
            (manager.findFragmentByTag(TAG) as? LoadingDialogFragment)?.dismissAllowingStateLoss()
        }

        private fun scheduleAutoDismiss(manager: androidx.fragment.app.FragmentManager) {
            pendingDismiss?.let { handler.removeCallbacks(it) }
            val runnable = Runnable { dismiss(manager) }
            pendingDismiss = runnable
            handler.postDelayed(runnable, AUTO_DISMISS_MS)
        }
    }

}
