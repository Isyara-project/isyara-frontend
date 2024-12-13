package com.application.isyara.viewmodel.dictionary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\bR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R#\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lcom/application/isyara/viewmodel/dictionary/DictionaryPictureViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "(Lcom/application/isyara/data/repository/DictionaryPictureRepository;)V", "_downloadState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "", "_searchQuery", "dictionaryPictures", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/application/isyara/utils/state/Result;", "", "Lcom/application/isyara/data/model/DictionaryPictureItem;", "getDictionaryPictures", "()Lkotlinx/coroutines/flow/StateFlow;", "downloadState", "getDownloadState", "searchQuery", "getSearchQuery", "deleteDownloadedPicture", "", "imageUrl", "downloadPicture", "updateSearchQuery", "query", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DictionaryPictureViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryPictureRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> _downloadState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> downloadState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.data.model.DictionaryPictureItem>>> dictionaryPictures = null;
    
    @javax.inject.Inject()
    public DictionaryPictureViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryPictureRepository repository) {
        super();
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
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.data.model.DictionaryPictureItem>>> getDictionaryPictures() {
        return null;
    }
    
    public final void updateSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void downloadPicture(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
    
    public final void deleteDownloadedPicture(@org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
}