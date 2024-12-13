package com.application.isyara.viewmodel.dictionary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bJ\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bJ\u0006\u0010\u001e\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u000bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\n0\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R#\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u00070\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013\u00a8\u0006!"}, d2 = {"Lcom/application/isyara/viewmodel/dictionary/DictionaryPictureViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "(Lcom/application/isyara/data/repository/DictionaryPictureRepository;)V", "_deleteState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/state/Result;", "", "_downloadState", "", "", "_searchQuery", "_state", "", "Lcom/application/isyara/data/model/DictionaryPicture;", "deleteState", "Lkotlinx/coroutines/flow/StateFlow;", "getDeleteState", "()Lkotlinx/coroutines/flow/StateFlow;", "downloadState", "getDownloadState", "searchQuery", "getSearchQuery", "state", "getState", "deleteDownloadedPicture", "", "imageUrl", "downloadPicture", "fetchDictionaryPictures", "updateSearchQuery", "query", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DictionaryPictureViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryPictureRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.data.model.DictionaryPicture>>> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> _downloadState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<java.lang.Boolean>> _deleteState = null;
    
    @javax.inject.Inject()
    public DictionaryPictureViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryPictureRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.data.model.DictionaryPicture>>> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> getDownloadState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<java.lang.Boolean>> getDeleteState() {
        return null;
    }
    
    /**
     * Mengambil gambar yang sudah diunduh (offline) atau gambar dari API jika tidak ada.
     */
    public final void fetchDictionaryPictures() {
    }
    
    /**
     * Mengunduh gambar dari API dan menyimpannya ke dalam database.
     */
    public final void downloadPicture(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
    
    /**
     * Menghapus gambar yang sudah diunduh berdasarkan URL.
     */
    public final void deleteDownloadedPicture(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
    
    /**
     * Update search query yang digunakan untuk filter gambar berdasarkan kata kunci.
     */
    public final void updateSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
}