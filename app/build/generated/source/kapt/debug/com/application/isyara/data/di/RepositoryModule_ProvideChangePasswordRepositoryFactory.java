package com.application.isyara.data.di;

import com.application.isyara.data.preferences.SessionManager;
import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.ChangePasswordRepository;
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
public final class RepositoryModule_ProvideChangePasswordRepositoryFactory implements Factory<ChangePasswordRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public RepositoryModule_ProvideChangePasswordRepositoryFactory(
      Provider<ApiService> apiServiceProvider, Provider<SessionManager> sessionManagerProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ChangePasswordRepository get() {
    return provideChangePasswordRepository(apiServiceProvider.get(), sessionManagerProvider.get());
  }

  public static RepositoryModule_ProvideChangePasswordRepositoryFactory create(
      Provider<ApiService> apiServiceProvider, Provider<SessionManager> sessionManagerProvider) {
    return new RepositoryModule_ProvideChangePasswordRepositoryFactory(apiServiceProvider, sessionManagerProvider);
  }

  public static ChangePasswordRepository provideChangePasswordRepository(ApiService apiService,
      SessionManager sessionManager) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideChangePasswordRepository(apiService, sessionManager));
  }
}
