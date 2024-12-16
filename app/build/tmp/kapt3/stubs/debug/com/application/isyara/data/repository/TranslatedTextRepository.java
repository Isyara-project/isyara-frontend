package com.application.isyara.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013J\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0017H\u0086@\u00a2\u0006\u0002\u0010\rJ\u0006\u0010\u0018\u001a\u00020\nJ\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0017H\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u00172\u0006\u0010\u001b\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/application/isyara/data/repository/TranslatedTextRepository;", "", "translatedTextDao", "Lcom/application/isyara/data/local/TranslatedTextDao;", "context", "Landroid/content/Context;", "(Lcom/application/isyara/data/local/TranslatedTextDao;Landroid/content/Context;)V", "dictionary", "Lcom/application/isyara/utils/translate/Dictionary;", "wordBreaker", "Lcom/application/isyara/utils/translate/WordBreaker;", "deleteAllTranslatedTexts", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTranslatedTextById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTranslatedTexts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/application/isyara/data/local/TranslatedText;", "getLatestTranslatedText", "Lcom/application/isyara/utils/state/Result;", "getWordBreaker", "initializeDictionary", "insertTranslatedText", "translatedText", "(Lcom/application/isyara/data/local/TranslatedText;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class TranslatedTextRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.local.TranslatedTextDao translatedTextDao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private com.application.isyara.utils.translate.Dictionary dictionary;
    private com.application.isyara.utils.translate.WordBreaker wordBreaker;
    
    @javax.inject.Inject()
    public TranslatedTextRepository(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.TranslatedTextDao translatedTextDao, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Memuat kamus dari assets dan menginisialisasi WordBreaker.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initializeDictionary(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.utils.state.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Mengembalikan instance WordBreaker.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.utils.translate.WordBreaker getWordBreaker() {
        return null;
    }
    
    /**
     * Menyimpan teks terjemahan ke database.
     * @param translatedText Objek TranslatedText yang akan disimpan.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTranslatedText(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.TranslatedText translatedText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.utils.state.Result<kotlin.Unit>> $completion) {
        return null;
    }
    
    /**
     * Mengambil teks terjemahan terbaru dari database.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLatestTranslatedText(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.utils.state.Result<com.application.isyara.data.local.TranslatedText>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAllTranslatedTexts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTranslatedTextById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.application.isyara.data.local.TranslatedText>> getAllTranslatedTexts() {
        return null;
    }
}