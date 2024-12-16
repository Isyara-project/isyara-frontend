package com.application.isyara.data.repository;

import com.application.isyara.data.preferences.SessionManager;
import com.application.isyara.data.remote.ApiService;
import com.google.gson.Gson;
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
public final class ProfileRepository_Factory implements Factory<ProfileRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<Gson> gsonProvider;

  public ProfileRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<SessionManager> sessionManagerProvider, Provider<Gson> gsonProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public ProfileRepository get() {
    return newInstance(apiServiceProvider.get(), sessionManagerProvider.get(), gsonProvider.get());
  }

  public static ProfileRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<SessionManager> sessionManagerProvider, Provider<Gson> gsonProvider) {
    return new ProfileRepository_Factory(apiServiceProvider, sessionManagerProvider, gsonProvider);
  }

  public static ProfileRepository newInstance(ApiService apiService, SessionManager sessionManager,
      Gson gson) {
    return new ProfileRepository(apiService, sessionManager, gson);
  }
}
