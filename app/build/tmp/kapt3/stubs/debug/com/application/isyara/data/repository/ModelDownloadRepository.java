package com.application.isyara.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JA\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u000e\u00a2\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\b0\rH\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/application/isyara/data/repository/ModelDownloadRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "client", "Lokhttp3/OkHttpClient;", "downloadModel", "", "url", "", "fileName", "onProgress", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "progress", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getModelFile", "Ljava/io/File;", "isModelDownloaded", "", "app_debug"})
public final class ModelDownloadRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    
    @javax.inject.Inject()
    public ModelDownloadRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Mengunduh file dari URL dan menyimpannya ke direktori internal aplikasi.
     *
     * @param url URL file yang akan diunduh.
     * @param fileName Nama file yang akan disimpan.
     * @throws IOException jika terjadi kesalahan saat mengunduh atau menyimpan file.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object downloadModel(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onProgress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Memeriksa apakah file model sudah ada di penyimpanan internal.
     *
     * @param fileName Nama file yang akan diperiksa.
     * @return True jika file ada, False jika tidak.
     */
    public final boolean isModelDownloaded(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
        return false;
    }
    
    /**
     * Mendapatkan File objek untuk model yang diunduh.
     *
     * @param fileName Nama file model.
     * @return File objek jika ada, null jika tidak.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.io.File getModelFile(@org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
        return null;
    }
}