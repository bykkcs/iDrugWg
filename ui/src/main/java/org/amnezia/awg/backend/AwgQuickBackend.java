package org.amnezia.awg.backend;

import android.content.Context;

import androidx.annotation.Nullable;

import org.amnezia.awg.config.Config;
import org.amnezia.awg.util.RootShell;
import org.amnezia.awg.util.ToolsInstaller;

import java.util.Set;

/**
 * Compatibility shim for code paths that still reference the legacy kernel backend.
 * The external AAR does not expose this backend anymore, so calls are delegated to GoBackend.
 */
public final class AwgQuickBackend implements Backend {
    private final GoBackend delegate;

    public AwgQuickBackend(final Context context, final RootShell rootShell, final ToolsInstaller toolsInstaller) {
        delegate = new GoBackend(context, new RootTunnelActionHandler(rootShell));
    }

    public static boolean hasKernelSupport() {
        return false;
    }

    public void setMultipleTunnels(final boolean on) {
        // Not supported by the external backend. Kept only for source compatibility.
    }

    @Override
    public Set<String> getRunningTunnelNames() {
        return delegate.getRunningTunnelNames();
    }

    @Override
    public Tunnel.State getState(final Tunnel tunnel) throws Exception {
        return delegate.getState(tunnel);
    }

    @Override
    public BackendMode getBackendMode() {
        return delegate.getBackendMode();
    }

    @Override
    public Statistics getStatistics(final Tunnel tunnel) throws Exception {
        return delegate.getStatistics(tunnel);
    }

    @Override
    public String getVersion() throws Exception {
        return delegate.getVersion();
    }

    @Override
    public Tunnel.State setState(final Tunnel tunnel, final Tunnel.State state, @Nullable final Config config) throws Exception {
        return delegate.setState(tunnel, state, config);
    }

    @Override
    public BackendMode setBackendMode(final BackendMode backendMode) throws Exception {
        return delegate.setBackendMode(backendMode);
    }

    @Override
    public boolean resolveDDNS(final Config config, final boolean isIpv4Preferred) throws Exception {
        return delegate.resolveDDNS(config, isIpv4Preferred);
    }

    @Override
    public boolean updateActiveTunnelPeers(final Config config) throws Exception {
        return delegate.updateActiveTunnelPeers(config);
    }
}
