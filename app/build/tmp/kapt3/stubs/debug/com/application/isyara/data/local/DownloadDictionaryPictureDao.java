package com.application.isyara.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/application/isyara/data/local/DownloadDictionaryPictureDao;", "", "deleteDownloadedPictureByUrl", "", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllDownloadedPictures", "", "Lcom/application/isyara/data/local/DownloadedDictionaryPicture;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDownloadedPictureByUrl", "insertDownloadedPicture", "picture", "(Lcom/application/isyara/data/local/DownloadedDictionaryPicture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPictures", "pictures", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DownloadDictionaryPictureDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDownloadedPicture(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadedDictionaryPicture picture, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPictures(@org.jetbrains.annotations.NotNull()
    java.util.List<com.application.isyara.data.local.DownloadedDictionaryPicture> pictures, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM downloaded_dictionary_pictures")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllDownloadedPictures(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.application.isyara.data.local.DownloadedDictionaryPicture>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM downloaded_dictionary_pictures WHERE imageUrl = :url")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDownloadedPictureByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.local.DownloadedDictionaryPicture> $completion);
    
    @androidx.room.Query(value = "DELETE FROM downloaded_dictionary_pictures WHERE imageUrl = :url")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDownloadedPictureByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}