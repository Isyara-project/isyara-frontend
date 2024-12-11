package com.application.isyara.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lcom/application/isyara/data/local/DownloadedDictionaryDao;", "", "deleteAllDownloadedItems", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDownloadedItem", "item", "Lcom/application/isyara/data/local/DownloadedDictionary;", "(Lcom/application/isyara/data/local/DownloadedDictionary;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDownloadedItems", "Lkotlinx/coroutines/flow/Flow;", "", "getDownloadedItemByUrl", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertDownloadedItem", "app_debug"})
@androidx.room.Dao()
public abstract interface DownloadedDictionaryDao {
    
    @androidx.room.Query(value = "SELECT * FROM downloaded_dictionary")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.application.isyara.data.local.DownloadedDictionary>> getAllDownloadedItems();
    
    @androidx.room.Query(value = "SELECT * FROM downloaded_dictionary WHERE url = :url LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDownloadedItemByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.local.DownloadedDictionary> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDownloadedItem(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadedDictionary item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDownloadedItem(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadedDictionary item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM downloaded_dictionary")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllDownloadedItems(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}