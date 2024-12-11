package com.application.isyara.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018J\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0006\u0010\u001c\u001a\u00020\nJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001f\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/application/isyara/data/repository/TranslatedTextRepository;", "", "translatedTextDao", "Lcom/application/isyara/data/local/TranslatedTextDao;", "context", "Landroid/content/Context;", "(Lcom/application/isyara/data/local/TranslatedTextDao;Landroid/content/Context;)V", "dictionary", "Lcom/application/isyara/utils/translate/Dictionary;", "wordBreaker", "Lcom/application/isyara/utils/translate/WordBreaker;", "addCustomWord", "Lcom/application/isyara/utils/state/Result;", "", "word", "", "deleteAllTranslatedTexts", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomWord", "deleteTranslatedTextById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTranslatedTexts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/application/isyara/data/local/TranslatedText;", "getLatestTranslatedText", "getWordBreaker", "initializeDictionary", "insertTranslatedText", "translatedText", "(Lcom/application/isyara/data/local/TranslatedText;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
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
    
    /**
     * Menambahkan kata kustom ke kamus.
     * @param word Kata kustom yang ingin ditambahkan.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.utils.state.Result<kotlin.Unit> addCustomWord(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return null;
    }
    
    /**
     * Menghapus kata kustom dari kamus.
     * @param word Kata kustom yang ingin dihapus.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.utils.state.Result<kotlin.Unit> deleteCustomWord(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
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