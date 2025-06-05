package org.amnezia.awg.configStore;

/**
 * Configuration store that uses a `awg-quick`-style file for each configured tunnel.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/amnezia/awg/configStore/FileConfigStore;", "Lorg/amnezia/awg/configStore/ConfigStore;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "Lorg/amnezia/awg/config/Config;", "name", "", "config", "delete", "", "enumerate", "", "fileFor", "Ljava/io/File;", "load", "rename", "replacement", "save", "Companion", "ui_debug"})
public final class FileConfigStore implements org.amnezia.awg.configStore.ConfigStore {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/FileConfigStore";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.configStore.FileConfigStore.Companion Companion = null;
    
    public FileConfigStore(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    @org.jetbrains.annotations.NotNull
    public org.amnezia.awg.config.Config create(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    org.amnezia.awg.config.Config config) throws java.io.IOException {
        return null;
    }
    
    @java.lang.Override
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    public void delete(@org.jetbrains.annotations.NotNull
    java.lang.String name) throws java.io.IOException {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.util.Set<java.lang.String> enumerate() {
        return null;
    }
    
    private final java.io.File fileFor(java.lang.String name) {
        return null;
    }
    
    @java.lang.Override
    @kotlin.jvm.Throws(exceptionClasses = {org.amnezia.awg.config.BadConfigException.class, java.io.IOException.class})
    @org.jetbrains.annotations.NotNull
    public org.amnezia.awg.config.Config load(@org.jetbrains.annotations.NotNull
    java.lang.String name) throws org.amnezia.awg.config.BadConfigException, java.io.IOException {
        return null;
    }
    
    @java.lang.Override
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    public void rename(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String replacement) throws java.io.IOException {
    }
    
    @java.lang.Override
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    @org.jetbrains.annotations.NotNull
    public org.amnezia.awg.config.Config save(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    org.amnezia.awg.config.Config config) throws java.io.IOException {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/amnezia/awg/configStore/FileConfigStore$Companion;", "", "()V", "TAG", "", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}