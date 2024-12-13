package com.application.isyara.viewmodel.auth;

import com.application.isyara.data.repository.AuthRepository;
import com.application.isyara.utils.auth.ISessionManager;
import com.application.isyara.utils.auth.IUserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<ISessionManager> sessionManagerProvider;

  private final Provider<IUserPreferences> userPreferencesProvider;

  public AuthViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<ISessionManager> sessionManagerProvider,
      Provider<IUserPreferences> userPreferencesProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), sessionManagerProvider.get(), userPreferencesProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<ISessionManager> sessionManagerProvider,
      Provider<IUserPreferences> userPreferencesProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, sessionManagerProvider, userPreferencesProvider);
  }

  public static AuthViewModel newInstance(AuthRepository authRepository,
      ISessionManager sessionManager, IUserPreferences userPreferences) {
    return new AuthViewModel(authRepository, sessionManager, userPreferences);
  }
}
