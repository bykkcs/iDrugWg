package org.amnezia.awg.util;

import android.content.Context;

/**
 * Compatibility shim for removed installer API from the external AAR.
 */
public final class ToolsInstaller {
    public static final int ERROR = 0;
    public static final int NO = 1;
    public static final int YES = 1 << 1;
    public static final int SYSTEM = 1 << 2;
    public static final int MAGISK = 1 << 3;

    public ToolsInstaller(final Context context, final RootShell rootShell) {
    }

    public int areInstalled() {
        return ERROR;
    }

    public int install() {
        return ERROR;
    }
}
