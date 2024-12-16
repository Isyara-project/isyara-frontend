package com.application.isyara.data.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\'\u00a8\u0006\u000b"}, d2 = {"Lcom/application/isyara/data/di/SharedPreferencesModule;", "", "()V", "bindSessionManager", "Lcom/application/isyara/utils/auth/ISessionManager;", "sessionManager", "Lcom/application/isyara/data/preferences/SessionManager;", "bindUserPreferences", "Lcom/application/isyara/utils/auth/IUserPreferences;", "userPreferences", "Lcom/application/isyara/data/preferences/UserPreferences;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class SharedPreferencesModule {
    
    public SharedPreferencesModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.application.isyara.utils.auth.ISessionManager bindSessionManager(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.preferences.SessionManager sessionManager);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.application.isyara.utils.auth.IUserPreferences bindUserPreferences(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.preferences.UserPreferences userPreferences);
}