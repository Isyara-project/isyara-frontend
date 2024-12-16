package com.application.isyara.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0007J\"\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J$\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\bH\u0007J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\bH\u0007J\u0012\u0010\u001a\u001a\u00020\u00122\b\b\u0001\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\bH\u0007J\u001a\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001c2\b\b\u0001\u0010\t\u001a\u00020\nH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006 "}, d2 = {"Lcom/application/isyara/data/di/DatabaseModule;", "", "()V", "MIGRATION_3_4", "Landroidx/room/migration/Migration;", "getMIGRATION_3_4", "()Landroidx/room/migration/Migration;", "provideDatabase", "Lcom/application/isyara/data/local/AppDatabase;", "context", "Landroid/content/Context;", "provideDictionaryPictureRepository", "Lcom/application/isyara/data/repository/DictionaryPictureRepository;", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "downloadDictionaryPictureDao", "Lcom/application/isyara/data/local/DownloadDictionaryPictureDao;", "networkHelper", "Lcom/application/isyara/utils/dictionary/NetworkHelper;", "provideDictionaryRepository", "Lcom/application/isyara/data/repository/DictionaryRepository;", "downloadedDictionaryDao", "Lcom/application/isyara/data/local/DownloadedDictionaryDao;", "provideDownloadDictionaryPictureDao", "database", "provideDownloadedDictionaryDao", "provideNetworkHelper", "provideTranslatedTextDao", "Lcom/application/isyara/data/local/TranslatedTextDao;", "provideTranslatedTextRepository", "Lcom/application/isyara/data/repository/TranslatedTextRepository;", "translatedTextDao", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.application.isyara.data.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.room.migration.Migration getMIGRATION_3_4() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.local.AppDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.local.DownloadedDictionaryDao provideDownloadedDictionaryDao(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.AppDatabase database) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.local.TranslatedTextDao provideTranslatedTextDao(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.AppDatabase database) {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.local.DownloadDictionaryPictureDao provideDownloadDictionaryPictureDao(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.AppDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.TranslatedTextRepository provideTranslatedTextRepository(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.TranslatedTextDao translatedTextDao, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.utils.dictionary.NetworkHelper provideNetworkHelper(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.DictionaryRepository provideDictionaryRepository(@RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadedDictionaryDao downloadedDictionaryDao) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.DictionaryPictureRepository provideDictionaryPictureRepository(@RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadDictionaryPictureDao downloadDictionaryPictureDao, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.dictionary.NetworkHelper networkHelper) {
        return null;
    }
}