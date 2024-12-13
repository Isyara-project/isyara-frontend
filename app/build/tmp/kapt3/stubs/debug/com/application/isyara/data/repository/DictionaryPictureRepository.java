package com.application.isyara.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\"\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\t0\bH\u0086@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "downloadDictionaryPictureDao", "Lcom/application/isyara/data/local/DownloadDictionaryPictureDao;", "(Lcom/application/isyara/data/remote/ApiService;Lcom/application/isyara/data/local/DownloadDictionaryPictureDao;)V", "deleteImage", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/state/Result;", "", "imageUrl", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadImage", "getDictionaryPictures", "", "Lcom/application/isyara/data/model/DictionaryPicture;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DictionaryPictureRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.local.DownloadDictionaryPictureDao downloadDictionaryPictureDao = null;
    
    @javax.inject.Inject()
    public DictionaryPictureRepository(@com.application.isyara.data.di.RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadDictionaryPictureDao downloadDictionaryPictureDao) {
        super();
    }
    
    /**
     * Mengambil gambar yang sudah diunduh untuk offline, jika tidak ada, ambil dari API.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDictionaryPictures(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.state.Result<? extends java.util.List<com.application.isyara.data.model.DictionaryPicture>>>> $completion) {
        return null;
    }
    
    /**
     * Mengunduh gambar dari API dan menyimpannya ke dalam database.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object downloadImage(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.state.Result<java.lang.Boolean>>> $completion) {
        return null;
    }
    
    /**
     * Menghapus gambar yang sudah diunduh berdasarkan URL.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteImage(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.state.Result<java.lang.Boolean>>> $completion) {
        return null;
    }
}