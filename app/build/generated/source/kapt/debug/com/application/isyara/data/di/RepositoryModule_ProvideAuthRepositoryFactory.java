package com.application.isyara.data.di;

import com.application.isyara.data.preferences.SessionManager;
import com.application.isyara.data.preferences.UserPreferences;
import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.AuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RepositoryModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public RepositoryModule_ProvideAuthRepositoryFactory(Provider<ApiService> apiServiceProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(apiServiceProvider.get(), sessionManagerProvider.get(), userPreferencesProvider.get());
  }

  public static RepositoryModule_ProvideAuthRepositoryFactory create(
      Provider<ApiService> apiServiceProvider, Provider<SessionManager> sessionManagerProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new RepositoryModule_ProvideAuthRepositoryFactory(apiServiceProvider, sessionManagerProvider, userPreferencesProvider);
  }

  public static AuthRepository provideAuthRepository(ApiService apiService,
      SessionManager sessionManager, UserPreferences userPreferences) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideAuthRepository(apiService, sessionManager, userPreferences));
  }
}
