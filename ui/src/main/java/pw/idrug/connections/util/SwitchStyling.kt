package pw.idrug.connections.util

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.color.MaterialColors

fun View.applyVpnSwitchStyle() {
    when (this) {
        is com.google.android.material.materialswitch.MaterialSwitch -> {
            val ok = try {
                javaClass.getMethod("setUseMaterialThemeColors", Boolean::class.javaPrimitiveType)
                    .invoke(this, true); true
            } catch (_: Throwable) { false }
            if (!ok) applyFallbackTintForMaterialSwitch()
        }
        is com.google.android.material.switchmaterial.SwitchMaterial -> {
            val ok = try {
                javaClass.getMethod("setUseMaterialThemeColors", Boolean::class.javaPrimitiveType)
                    .invoke(this, true); true
            } catch (_: Throwable) { false }
            if (!ok) applyFallbackTintForSwitchMaterial()
        }
    }
}

private fun com.google.android.material.materialswitch.MaterialSwitch.applyFallbackTintForMaterialSwitch() {
    val primary = MaterialColors.getColor(this, com.google.android.material.R.attr.colorPrimary)
    val onSurface = MaterialColors.getColor(this, com.google.android.material.R.attr.colorOnSurface)
    val onSurfaceVariant = MaterialColors.getColor(this, com.google.android.material.R.attr.colorOnSurfaceVariant)
    val states = arrayOf(
        intArrayOf(android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    thumbTintList = ColorStateList(states, intArrayOf(primary, onSurfaceVariant, onSurface))
    trackTintList = ColorStateList(states, intArrayOf(primary, onSurfaceVariant, onSurface))
}

private fun com.google.android.material.switchmaterial.SwitchMaterial.applyFallbackTintForSwitchMaterial() {
    val primary = MaterialColors.getColor(this, com.google.android.material.R.attr.colorPrimary)
    val onSurface = MaterialColors.getColor(this, com.google.android.material.R.attr.colorOnSurface)
    val onSurfaceVariant = MaterialColors.getColor(this, com.google.android.material.R.attr.colorOnSurfaceVariant)
    val states = arrayOf(
        intArrayOf(android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf()
    )
    thumbTintList = ColorStateList(states, intArrayOf(primary, onSurfaceVariant, onSurface))
    trackTintList = ColorStateList(states, intArrayOf(primary, onSurfaceVariant, onSurface))
}

fun ViewGroup.styleAllSwitchesRecursively() {
    for (c in children) {
        if (c is ViewGroup) c.styleAllSwitchesRecursively()
        c.applyVpnSwitchStyle()
    }
}
