package com.application.isyara.utils.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0001\u00a8\u0006\n"}, d2 = {"mimeTypeToExtension", "", "mimeType", "queryName", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "toMultipartData", "Lcom/application/isyara/utils/settings/MultipartData;", "partName", "app_debug"})
public final class UriExtensionsKt {
    
    /**
     * Mengonversi [Uri] menjadi [MultipartData].
     *
     * @param context Context untuk mengakses ContentResolver.
     * @param partName Nama bagian form data.
     * @return [MultipartData] atau null jika terjadi kesalahan.
     */
    @org.jetbrains.annotations.Nullable()
    public static final com.application.isyara.utils.settings.MultipartData toMultipartData(@org.jetbrains.annotations.NotNull()
    android.net.Uri $this$toMultipartData, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String partName) {
        return null;
    }
    
    /**
     * Mengonversi [mimeType] menjadi ekstensi file yang sesuai.
     *
     * @param mimeType Tipe MIME dari file.
     * @return Ekstensi file atau null jika tidak dikenali.
     */
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String mimeTypeToExtension(@org.jetbrains.annotations.NotNull()
    java.lang.String mimeType) {
        return null;
    }
    
    /**
     * Mengambil nama file asli dari [Uri].
     *
     * @param context Context untuk mengakses ContentResolver.
     * @return Nama file atau null jika tidak dapat diambil.
     */
    @android.annotation.SuppressLint(value = {"Range"})
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.String queryName(@org.jetbrains.annotations.NotNull()
    android.net.Uri $this$queryName, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}