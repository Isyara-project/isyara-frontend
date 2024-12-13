package com.application.isyara.data.repository;

import com.application.isyara.data.remote.ApiService;
import com.application.isyara.utils.auth.ISessionManager;
import com.application.isyara.utils.auth.IUserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("com.application.isyara.data.di.RetrofitMain")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<ISessionManager> sessionManagerProvider;

  private final Provider<IUserPreferences> userPreferencesProvider;

  public AuthRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<ISessionManager> sessionManagerProvider,
      Provider<IUserPreferences> userPreferencesProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(apiServiceProvider.get(), sessionManagerProvider.get(), userPreferencesProvider.get());
  }

  public static AuthRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<ISessionManager> sessionManagerProvider,
      Provider<IUserPreferences> userPreferencesProvider) {
    return new AuthRepository_Factory(apiServiceProvider, sessionManagerProvider, userPreferencesProvider);
  }

  public static AuthRepository newInstance(ApiService apiService, ISessionManager sessionManager,
      IUserPreferences userPreferences) {
    return new AuthRepository(apiService, sessionManager, userPreferences);
  }
}
