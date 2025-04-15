package org.amnezia.awg.util;

/**
 * Encapsulates the logic of scanning a barcode from a file,
 * @property contentResolver - Resolver to read the incoming data
 * @property reader - An instance of zxing's [Reader] class to parse the image
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/amnezia/awg/util/QrCodeFromFileScanner;", "", "contentResolver", "Landroid/content/ContentResolver;", "reader", "Lcom/google/zxing/Reader;", "(Landroid/content/ContentResolver;Lcom/google/zxing/Reader;)V", "doScan", "Lcom/google/zxing/Result;", "data", "Landroid/net/Uri;", "downscaleBitmap", "Landroid/graphics/Bitmap;", "source", "scaledSize", "", "scan", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanBitmapForResult", "Companion", "ui_debug"})
public final class QrCodeFromFileScanner {
    @org.jetbrains.annotations.NotNull
    private final android.content.ContentResolver contentResolver = null;
    @org.jetbrains.annotations.NotNull
    private final com.google.zxing.Reader reader = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "QrCodeFromFileScanner";
    @org.jetbrains.annotations.NotNull
    public static final org.amnezia.awg.util.QrCodeFromFileScanner.Companion Companion = null;
    
    public QrCodeFromFileScanner(@org.jetbrains.annotations.NotNull
    android.content.ContentResolver contentResolver, @org.jetbrains.annotations.NotNull
    com.google.zxing.Reader reader) {
        super();
    }
    
    private final com.google.zxing.Result scanBitmapForResult(android.graphics.Bitmap source) {
        return null;
    }
    
    private final android.graphics.Bitmap downscaleBitmap(android.graphics.Bitmap source, int scaledSize) {
        return null;
    }
    
    private final com.google.zxing.Result doScan(android.net.Uri data) {
        return null;
    }
    
    /**
     * Attempts to parse incoming data
     * @return result of the decoding operation
     * @throws NotFoundException when parser didn't find QR code in the image
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object scan(@org.jetbrains.annotations.NotNull
    android.net.Uri data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.google.zxing.Result> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/amnezia/awg/util/QrCodeFromFileScanner$Companion;", "", "()V", "TAG", "", "validContentType", "", "contentResolver", "Landroid/content/ContentResolver;", "data", "Landroid/net/Uri;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Given a reference to a file, check if this file could be parsed by this class
         * @return true if the file can be parsed, false if not
         */
        public final boolean validContentType(@org.jetbrains.annotations.NotNull
        android.content.ContentResolver contentResolver, @org.jetbrains.annotations.NotNull
        android.net.Uri data) {
            return false;
        }
    }
}