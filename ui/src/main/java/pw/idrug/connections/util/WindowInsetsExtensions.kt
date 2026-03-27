package pw.idrug.connections.util

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.appcompat.R as AppCompatR
import androidx.core.view.WindowInsetsCompat.Builder


private data class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

private fun View.ensureInsetsApplied() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

private fun View.doOnApplyWindowInsets(
    block: (View, WindowInsetsCompat, InitialPadding) -> WindowInsetsCompat
) {
    val initialPadding = InitialPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        block(view, insets, initialPadding)
    }
    ensureInsetsApplied()
}

fun View.applyStatusBarPadding() {
    doOnApplyWindowInsets { view, insets, initial ->
        val topInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        view.updatePadding(top = initial.top + topInset)
        insets
    }
}

fun View.applyNavigationBarPadding(extraBottom: Int = 0) {
    applyNavigationBarAndImePadding(extraBottom = extraBottom, dispatchIme = false)
}

fun View.applyNavigationBarAndImePadding(extraBottom: Int = 0, dispatchIme: Boolean = true) {
    doOnApplyWindowInsets { view, insets, initial ->
        val navInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
        val imeInsets = if (dispatchIme) {
            insets.getInsets(WindowInsetsCompat.Type.ime())
        } else {
            Insets.NONE
        }
        val bottomInset = maxOf(navInsets.bottom, imeInsets.bottom)
        view.updatePadding(bottom = initial.bottom + bottomInset + extraBottom)
        insets
    }
}

fun AppCompatActivity.applyStatusBarInsetToActionBar(): Boolean {
    val overlay = findViewById<View>(AppCompatR.id.decor_content_parent)
    val actionBarContainer = findViewById<View>(AppCompatR.id.action_bar_container)

    if (overlay != null && actionBarContainer != null) {
        val initialTop = actionBarContainer.paddingTop
        ViewCompat.setOnApplyWindowInsetsListener(overlay) { _, insets ->
            val statusInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            actionBarContainer.updatePadding(top = initialTop + statusInsets.top)
            Builder(insets)
                .setInsets(WindowInsetsCompat.Type.statusBars(), Insets.NONE)
                .build()
        }
        ViewCompat.requestApplyInsets(overlay)
        return true
    } else {
        val actionBar = actionBarContainer ?: findViewById<View>(AppCompatR.id.action_bar)
        actionBar?.applyStatusBarPadding()
        return actionBar != null
    }
}
