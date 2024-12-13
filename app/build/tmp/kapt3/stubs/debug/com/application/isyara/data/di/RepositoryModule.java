package com.application.isyara.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\u0012\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/application/isyara/data/di/RepositoryModule;", "", "()V", "provideAuthRepository", "Lcom/application/isyara/data/repository/AuthRepository;", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "sessionManager", "Lcom/application/isyara/data/local/SessionManager;", "userPreferences", "Lcom/application/isyara/data/local/UserPreferences;", "provideChangePasswordRepository", "Lcom/application/isyara/data/repository/ChangePasswordRepository;", "provideFeedbackRepository", "Lcom/application/isyara/data/repository/FeedbackRepository;", "provideGson", "Lcom/google/gson/Gson;", "providePasswordRepository", "Lcom/application/isyara/data/repository/PasswordRepository;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class RepositoryModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.application.isyara.data.di.RepositoryModule INSTANCE = null;
    
    private RepositoryModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.AuthRepository provideAuthRepository(@RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.SessionManager sessionManager, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.UserPreferences userPreferences) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.PasswordRepository providePasswordRepository(@RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.FeedbackRepository provideFeedbackRepository(@RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.data.repository.ChangePasswordRepository provideChangePasswordRepository(@RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.google.gson.Gson provideGson() {
        return null;
    }
}