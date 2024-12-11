package com.application.isyara.viewmodel.history;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/application/isyara/viewmodel/history/HistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "translatedTextRepository", "Lcom/application/isyara/data/repository/TranslatedTextRepository;", "dictionaryRepository", "Lcom/application/isyara/data/repository/DictionaryRepository;", "(Lcom/application/isyara/data/repository/TranslatedTextRepository;Lcom/application/isyara/data/repository/DictionaryRepository;)V", "historyItems", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/application/isyara/utils/history/HistoryItem;", "getHistoryItems", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteAllDownloadedDictionaries", "", "deleteAllTranslatedTexts", "deleteDownloadedDictionaryItemByUrl", "url", "", "deleteTranslatedTextById", "id", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HistoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.TranslatedTextRepository translatedTextRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryRepository dictionaryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.application.isyara.utils.history.HistoryItem>> historyItems = null;
    
    @javax.inject.Inject()
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.TranslatedTextRepository translatedTextRepository, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryRepository dictionaryRepository) {
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
    
    public final void deleteTranslatedTextById(int id) {
    }
    
    public final void deleteDownloadedDictionaryItemByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
}