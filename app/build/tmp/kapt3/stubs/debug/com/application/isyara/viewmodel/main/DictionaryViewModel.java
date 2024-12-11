package com.application.isyara.viewmodel.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00170\u001aJ*\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00170\u001aJ\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0002J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0017H\u0002J\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\bR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\fX\u0080\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013\u00a8\u0006#"}, d2 = {"Lcom/application/isyara/viewmodel/main/DictionaryViewModel;", "Landroidx/lifecycle/ViewModel;", "dictionaryRepository", "Lcom/application/isyara/data/repository/DictionaryRepository;", "(Lcom/application/isyara/data/repository/DictionaryRepository;)V", "_downloadStatus", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "", "_searchQuery", "combinedVideos", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/application/isyara/utils/state/Result;", "", "Lcom/application/isyara/utils/dictionary/VideoItems;", "getCombinedVideos$app_debug$annotations", "()V", "getCombinedVideos$app_debug", "()Lkotlinx/coroutines/flow/StateFlow;", "downloadStatus", "getDownloadStatus", "deleteDownloadedItem", "", "url", "onResult", "Lkotlin/Function1;", "downloadItem", "title", "extractTitleFromUrl", "getLocalPath", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadVideos", "onSearchQueryChange", "query", "app_debug"})
@kotlinx.coroutines.FlowPreview()
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DictionaryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.DictionaryRepository dictionaryRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> _downloadStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> downloadStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.utils.dictionary.VideoItems>>> combinedVideos = null;
    
    @javax.inject.Inject()
    public DictionaryViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.DictionaryRepository dictionaryRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.lang.Boolean>> getDownloadStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<java.util.List<com.application.isyara.utils.dictionary.VideoItems>>> getCombinedVideos$app_debug() {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    @java.lang.Deprecated()
    public static void getCombinedVideos$app_debug$annotations() {
    }
    
    public final void onSearchQueryChange(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLocalPath(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final void loadVideos() {
    }
    
    public final void downloadItem(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onResult) {
    }
    
    public final void deleteDownloadedItem(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onResult) {
    }
    
    private final java.lang.String extractTitleFromUrl(java.lang.String url) {
        return null;
    }
}