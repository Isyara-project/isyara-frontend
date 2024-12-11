package com.application.isyara.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J$\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0004H\u0007J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0014"}, d2 = {"Lcom/application/isyara/data/di/DatabaseModule;", "", "()V", "provideDatabase", "Lcom/application/isyara/data/local/AppDatabase;", "context", "Landroid/content/Context;", "provideDictionaryRepository", "Lcom/application/isyara/data/repository/DictionaryRepository;", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "downloadedDictionaryDao", "Lcom/application/isyara/data/local/DownloadedDictionaryDao;", "provideDownloadedDictionaryDao", "database", "provideTranslatedTextDao", "Lcom/application/isyara/data/local/TranslatedTextDao;", "provideTranslatedTextRepository", "Lcom/application/isyara/data/repository/TranslatedTextRepository;", "translatedTextDao", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.application.isyara.data.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
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
    public final com.application.isyara.data.repository.DictionaryRepository provideDictionaryRepository(@RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.DownloadedDictionaryDao downloadedDictionaryDao) {
        return null;
    }
}