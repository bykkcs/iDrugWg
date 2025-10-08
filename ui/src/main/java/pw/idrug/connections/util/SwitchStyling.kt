package pw.idrug.connections.util

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.color.MaterialColors
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.switchmaterial.SwitchMaterial

fun View.applyM3SwitchStyle() {
    when (this) {
        is MaterialSwitch -> {
            val applied = try {
                javaClass.getMethod("setUseMaterialThemeColors", Boolean::class.javaPrimitiveType)
                    .invoke(this, true)
                true
            } catch (_: Throwable) {
                false
            }
            if (!applied) tintLikeM3For(this)
        }

        is SwitchMaterial -> {
            val applied = try {
                javaClass.getMethod("setUseMaterialThemeColors", Boolean::class.javaPrimitiveType)
                    .invoke(this, true)
                true
            } catch (_: Throwable) {
                false
            }
            if (!applied) tintLikeM3For(this)
        }
    }
}

private fun tintLikeM3For(view: android.widget.CompoundButton) {
    val primary = MaterialColors.getColor(view, com.google.android.material.R.attr.colorPrimary)
    val onSurface = MaterialColors.getColor(view, com.google.android.material.R.attr.colorOnSurface)
    val onSurfaceVariant = MaterialColors.getColor(view, com.google.android.material.R.attr.colorOnSurfaceVariant)
    val states = arrayOf(
        intArrayOf(android.R.attr.state_checked, android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    val colors = intArrayOf(primary, onSurfaceVariant, onSurface)
    view.thumbTintList = ColorStateList(states, colors)
    view.trackTintList = ColorStateList(states, colors)
}

fun ViewGroup.styleAllSwitchesRecursively() {
    for (child in children) {
        if (child is ViewGroup) child.styleAllSwitchesRecursively()
        child.applyM3SwitchStyle()
    }
}
