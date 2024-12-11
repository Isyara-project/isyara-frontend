package com.application.isyara.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0012\u0010\n\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u000eH\u0007J\u0012\u0010\u0010\u001a\u00020\t2\b\b\u0001\u0010\u0011\u001a\u00020\u000eH\u0007J\u0012\u0010\u0012\u001a\u00020\t2\b\b\u0001\u0010\u0011\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/application/isyara/data/di/NetworkModule;", "", "()V", "DICTIONARY_BASE_URL", "", "MAIN_API_BASE_URL", "provideApiServiceDictionary", "Lcom/application/isyara/data/remote/ApiService;", "retrofit", "Lretrofit2/Retrofit;", "provideApiServiceMainAPI", "provideLoggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "provideOkHttpClientDictionary", "Lokhttp3/OkHttpClient;", "provideOkHttpClientMainAPI", "provideRetrofitDictionary", "okHttpClient", "provideRetrofitMainAPI", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DICTIONARY_BASE_URL = "https://storage.googleapis.com/";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MAIN_API_BASE_URL = "https://isyara-backend-main-480373187876.asia-southeast2.run.app/";
    @org.jetbrains.annotations.NotNull()
    public static final com.application.isyara.data.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    private final okhttp3.logging.HttpLoggingInterceptor provideLoggingInterceptor() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOkHttpClientDictionary() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOkHttpClientMainAPI() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideRetrofitDictionary(@RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideRetrofitMainAPI(@RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.remote.ApiService provideApiServiceDictionary(@RetrofitDictionary()
    @org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.remote.ApiService provideApiServiceMainAPI(@RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
}