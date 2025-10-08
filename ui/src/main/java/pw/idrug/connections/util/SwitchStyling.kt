package pw.idrug.connections.util

import android.view.View
import android.view.ViewGroup
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.switchmaterial.SwitchMaterial

fun View.applyVpnSwitchStyle() {
    when (this) {
        is MaterialSwitch -> setUseMaterialThemeColors(true)
        is SwitchMaterial -> setUseMaterialThemeColors(true)
    }
}

fun ViewGroup.styleAllSwitchesRecursively() {
    applyVpnSwitchStyle()
    for (index in 0 until childCount) {
        val child = getChildAt(index)
        child.applyVpnSwitchStyle()
        if (child is ViewGroup) {
            child.styleAllSwitchesRecursively()
        }
    }
}
