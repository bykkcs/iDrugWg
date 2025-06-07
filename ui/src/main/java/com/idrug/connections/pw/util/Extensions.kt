/*
 * Copyright © 2017-2023 WireGuard LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.idrug.connections.pw.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.lifecycle.lifecycleScope
import androidx.preference.Preference
import com.idrug.connections.pw.Application
import com.idrug.connections.pw.activity.SettingsActivity
import kotlinx.coroutines.CoroutineScope

fun Context.resolveAttribute(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data
}

val Any.applicationScope: CoroutineScope
    get() = Application.getCoroutineScope()

val Preference.activity: SettingsActivity
    get() = context as? SettingsActivity
        ?: throw IllegalStateException("Failed to resolve SettingsActivity")

val Preference.lifecycleScope: CoroutineScope
    get() = activity.lifecycleScope
