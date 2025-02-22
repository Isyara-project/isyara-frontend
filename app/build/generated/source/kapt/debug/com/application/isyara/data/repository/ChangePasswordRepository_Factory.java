package com.application.isyara.data.repository;

import com.application.isyara.data.preferences.SessionManager;
import com.application.isyara.data.remote.ApiService;
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
public final class ChangePasswordRepository_Factory implements Factory<ChangePasswordRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ChangePasswordRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ChangePasswordRepository get() {
    return newInstance(apiServiceProvider.get(), sessionManagerProvider.get());
  }

  public static ChangePasswordRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ChangePasswordRepository_Factory(apiServiceProvider, sessionManagerProvider);
  }

  public static ChangePasswordRepository newInstance(ApiService apiService,
      SessionManager sessionManager) {
    return new ChangePasswordRepository(apiService, sessionManager);
  }
}
