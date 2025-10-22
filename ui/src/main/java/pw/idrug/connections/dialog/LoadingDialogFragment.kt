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

    private val handler = Handler(Looper.getMainLooper())
    private val autoDismissRunnable = Runnable {
        if (isAdded && dialog?.isShowing == true) {
            dismissAllowingStateLoss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_loading_indicator, null, false)
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
        // Запускаем авто-закрытие через 3 секунд
        handler.postDelayed(autoDismissRunnable, 3000)
    }

    override fun onStop() {
        super.onStop()
        // Отменяем таймер, если диалог закрылся раньше
        handler.removeCallbacks(autoDismissRunnable)
    }

    @Suppress("DEPRECATION")
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

        fun show(manager: androidx.fragment.app.FragmentManager) {
            if (manager.findFragmentByTag(TAG) == null) {
                LoadingDialogFragment().show(manager, TAG)
            }
        }

        fun dismiss(manager: androidx.fragment.app.FragmentManager) {
            (manager.findFragmentByTag(TAG) as? LoadingDialogFragment)?.dismissAllowingStateLoss()
        }
    }
}
