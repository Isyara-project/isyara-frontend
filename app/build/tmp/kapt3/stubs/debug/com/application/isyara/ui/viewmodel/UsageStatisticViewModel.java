package com.application.isyara.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/application/isyara/ui/viewmodel/UsageStatisticViewModel;", "Landroidx/lifecycle/ViewModel;", "translatedTextRepository", "Lcom/application/isyara/data/repository/TranslatedTextRepository;", "dictionaryRepository", "Lcom/application/isyara/data/repository/DictionaryRepository;", "dictionaryPictureRepository", "Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "(Lcom/application/isyara/data/repository/TranslatedTextRepository;Lcom/application/isyara/data/repository/DictionaryRepository;Lcom/application/isyara/data/repository/DictionaryPictureRepository;)V", "_totalCharacters", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_totalDownloadedDictionaries", "_totalDownloadedPictureDictionaries", "totalCharacters", "Lkotlinx/coroutines/flow/StateFlow;", "getTotalCharacters", "()Lkotlinx/coroutines/flow/StateFlow;", "totalDownloadedDictionaries", "getTotalDownloadedDictionaries", "totalDownloadedPictureDictionaries", "getTotalDownloadedPictureDictionaries", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UsageStatisticViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.TranslatedTextRepository translatedTextRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryRepository dictionaryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryPictureRepository dictionaryPictureRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _totalCharacters = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalCharacters = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _totalDownloadedDictionaries = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalDownloadedDictionaries = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _totalDownloadedPictureDictionaries = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalDownloadedPictureDictionaries = null;
    
    @javax.inject.Inject()
    public UsageStatisticViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.TranslatedTextRepository translatedTextRepository, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryRepository dictionaryRepository, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryPictureRepository dictionaryPictureRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalCharacters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalDownloadedDictionaries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalDownloadedPictureDictionaries() {
        return null;
    }
}