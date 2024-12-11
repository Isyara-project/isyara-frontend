package com.application.isyara.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J\u0010\u0010\r\u001a\u0004\u0018\u00010\fH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/application/isyara/data/local/TranslatedTextDao;", "", "deleteAllTranslatedTexts", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTranslatedTextById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTranslatedTexts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/application/isyara/data/local/TranslatedText;", "getLatestTranslatedText", "insertTranslatedText", "translatedText", "(Lcom/application/isyara/data/local/TranslatedText;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface TranslatedTextDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTranslatedText(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.TranslatedText translatedText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM translated_texts ORDER BY timestamp DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLatestTranslatedText(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.local.TranslatedText> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM translated_texts ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.application.isyara.data.local.TranslatedText>> getAllTranslatedTexts();
    
    @androidx.room.Query(value = "DELETE FROM translated_texts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllTranslatedTexts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM translated_texts WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTranslatedTextById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}