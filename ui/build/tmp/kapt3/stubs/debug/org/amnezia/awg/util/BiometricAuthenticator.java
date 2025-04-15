package org.amnezia.awg.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J,\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/amnezia/awg/util/BiometricAuthenticator;", "", "()V", "TAG", "", "allowedAuthenticators", "", "authenticate", "", "dialogTitleRes", "fragment", "Landroidx/fragment/app/Fragment;", "callback", "Lkotlin/Function1;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result;", "Result", "ui_debug"})
public final class BiometricAuthenticator {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "AmneziaWG/BiometricAuthenticator";
    private static final int allowedAuthenticators = 33023;
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.util.BiometricAuthenticator INSTANCE = null;
    
    private BiometricAuthenticator() {
        super();
    }
    
    public final void authenticate(@androidx.annotation.StringRes
    int dialogTitleRes, @org.jetbrains.annotations.NotNull
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super org.amnezia.awg.util.BiometricAuthenticator.Result, kotlin.Unit> callback) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/amnezia/awg/util/BiometricAuthenticator$Result;", "", "()V", "Cancelled", "Failure", "HardwareUnavailableOrDisabled", "Success", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Cancelled;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Failure;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result$HardwareUnavailableOrDisabled;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Success;", "ui_debug"})
    public static abstract class Result {
        
        private Result() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Cancelled;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result;", "()V", "ui_debug"})
        public static final class Cancelled extends org.amnezia.awg.util.BiometricAuthenticator.Result {
            @org.jetbrains.annotations.NotNull
            public static final org.amnezia.awg.util.BiometricAuthenticator.Result.Cancelled INSTANCE = null;
            
            private Cancelled() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J$\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Failure;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result;", "code", "", "message", "", "(Ljava/lang/Integer;Ljava/lang/CharSequence;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/CharSequence;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/CharSequence;)Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Failure;", "equals", "", "other", "", "hashCode", "toString", "", "ui_debug"})
        public static final class Failure extends org.amnezia.awg.util.BiometricAuthenticator.Result {
            @org.jetbrains.annotations.Nullable
            private final java.lang.Integer code = null;
            @org.jetbrains.annotations.NotNull
            private final java.lang.CharSequence message = null;
            
            public Failure(@org.jetbrains.annotations.Nullable
            java.lang.Integer code, @org.jetbrains.annotations.NotNull
            java.lang.CharSequence message) {
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.lang.Integer getCode() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final java.lang.CharSequence getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable
            public final java.lang.Integer component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final java.lang.CharSequence component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final org.amnezia.awg.util.BiometricAuthenticator.Result.Failure copy(@org.jetbrains.annotations.Nullable
            java.lang.Integer code, @org.jetbrains.annotations.NotNull
            java.lang.CharSequence message) {
                return null;
            }
            
            @java.lang.Override
            public boolean equals(@org.jetbrains.annotations.Nullable
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override
            @org.jetbrains.annotations.NotNull
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/amnezia/awg/util/BiometricAuthenticator$Result$HardwareUnavailableOrDisabled;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result;", "()V", "ui_debug"})
        public static final class HardwareUnavailableOrDisabled extends org.amnezia.awg.util.BiometricAuthenticator.Result {
            @org.jetbrains.annotations.NotNull
            public static final org.amnezia.awg.util.BiometricAuthenticator.Result.HardwareUnavailableOrDisabled INSTANCE = null;
            
            private HardwareUnavailableOrDisabled() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/amnezia/awg/util/BiometricAuthenticator$Result$Success;", "Lorg/amnezia/awg/util/BiometricAuthenticator$Result;", "cryptoObject", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "(Landroidx/biometric/BiometricPrompt$CryptoObject;)V", "getCryptoObject", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ui_debug"})
        public static final class Success extends org.amnezia.awg.util.BiometricAuthenticator.Result {
            @org.jetbrains.annotations.Nullable
            private final androidx.biometric.BiometricPrompt.CryptoObject cryptoObject = null;
            
            public Success(@org.jetbrains.annotations.Nullable
            androidx.biometric.BiometricPrompt.CryptoObject cryptoObject) {
            }
            
            @org.jetbrains.annotations.Nullable
            public final androidx.biometric.BiometricPrompt.CryptoObject getCryptoObject() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable
            public final androidx.biometric.BiometricPrompt.CryptoObject component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final org.amnezia.awg.util.BiometricAuthenticator.Result.Success copy(@org.jetbrains.annotations.Nullable
            androidx.biometric.BiometricPrompt.CryptoObject cryptoObject) {
                return null;
            }
            
            @java.lang.Override
            public boolean equals(@org.jetbrains.annotations.Nullable
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override
            @org.jetbrains.annotations.NotNull
            public java.lang.String toString() {
                return null;
            }
        }
    }
}