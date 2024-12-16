package com.application.isyara.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00152\u0006\u0010\u0018\u001a\u00020\fJ\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00152\u0006\u0010\u0018\u001a\u00020\fJ\b\u0010\u001a\u001a\u00020\u0012H\u0002J\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000b0\u0015J\u0018\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u000b0\u00160\u0015R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "downloadDictionaryPictureDao", "Lcom/application/isyara/data/local/DownloadDictionaryPictureDao;", "networkHelper", "Lcom/application/isyara/utils/dictionary/NetworkHelper;", "(Lcom/application/isyara/data/remote/ApiService;Lcom/application/isyara/data/local/DownloadDictionaryPictureDao;Lcom/application/isyara/utils/dictionary/NetworkHelper;)V", "_apiPictures", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "apiPictures", "Lkotlinx/coroutines/flow/StateFlow;", "getApiPictures", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteAllDownloadedPictures", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteImage", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/state/Result;", "", "imageUrl", "downloadImage", "fetchApiPictures", "getAllDownloadedPictures", "Lcom/application/isyara/data/local/DownloadedDictionaryPicture;", "getDictionaryPictures", "Lcom/application/isyara/data/model/DictionaryPictureItem;", "app_debug"})
public final class DictionaryPictureRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.local.DownloadDictionaryPictureDao downloadDictionaryPictureDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.dictionary.NetworkHelper networkHelper = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _apiPictures = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> apiPictures = null;
    
    @javax.inject.Inject()
    public DictionaryPictureRepository(@com.application.isyara.data.di.RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadDictionaryPictureDao downloadDictionaryPictureDao, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.dictionary.NetworkHelper networkHelper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getApiPictures() {
        return null;
    }
    
    /**
     * Fungsi untuk mengambil gambar dari API dan mengupdate _apiPictures
     */
    private final void fetchApiPictures() {
    }
    
    /**
     * Mengambil daftar semua gambar dengan status unduhan secara reaktif.
     * Menggabungkan data dari API dengan status unduhan dari database lokal.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.data.model.DictionaryPictureItem>>> getDictionaryPictures() {
        return null;
    }
    
    /**
     * Mengunduh gambar dari API dan menyimpannya ke dalam database.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<java.lang.Boolean>> downloadImage(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
        return null;
    }
    
    /**
     * Menghapus gambar yang sudah diunduh berdasarkan URL.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<java.lang.Boolean>> deleteImage(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.application.isyara.data.local.DownloadedDictionaryPicture>> getAllDownloadedPictures() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAllDownloadedPictures(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}