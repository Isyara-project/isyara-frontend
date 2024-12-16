package com.application.isyara.viewmodel.history;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\fJ\u0010\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/application/isyara/viewmodel/history/HistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "translatedTextRepository", "Lcom/application/isyara/data/repository/TranslatedTextRepository;", "dictionaryRepository", "Lcom/application/isyara/data/repository/DictionaryRepository;", "dictionaryPictureRepository", "Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "(Lcom/application/isyara/data/repository/TranslatedTextRepository;Lcom/application/isyara/data/repository/DictionaryRepository;Lcom/application/isyara/data/repository/DictionaryPictureRepository;)V", "historyItems", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/application/isyara/utils/history/HistoryItem;", "getHistoryItems", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteAllDownloadedDictionaries", "", "deleteAllDownloadedPictures", "deleteAllTranslatedTexts", "deleteDownloadedDictionaryItemByUrl", "url", "", "deleteDownloadedPictureByUrl", "deleteHistoryItem", "item", "deleteTranslatedTextById", "id", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HistoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.TranslatedTextRepository translatedTextRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryRepository dictionaryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryPictureRepository dictionaryPictureRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.application.isyara.utils.history.HistoryItem>> historyItems = null;
    
    @javax.inject.Inject()
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.TranslatedTextRepository translatedTextRepository, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryRepository dictionaryRepository, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryPictureRepository dictionaryPictureRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.application.isyara.utils.history.HistoryItem>> getHistoryItems() {
        return null;
    }
    
    public final void deleteAllTranslatedTexts() {
    }
    
    public final void deleteAllDownloadedDictionaries() {
    }
    
    public final void deleteAllDownloadedPictures() {
    }
    
    private final void deleteTranslatedTextById(int id) {
    }
    
    private final void deleteDownloadedDictionaryItemByUrl(java.lang.String url) {
    }
    
    private final void deleteDownloadedPictureByUrl(java.lang.String url) {
    }
    
    public final void deleteHistoryItem(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.history.HistoryItem item) {
    }
}