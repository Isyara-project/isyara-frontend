package com.application.isyara.data.repository;

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

  public ChangePasswordRepository_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ChangePasswordRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static ChangePasswordRepository_Factory create(Provider<ApiService> apiServiceProvider) {
    return new ChangePasswordRepository_Factory(apiServiceProvider);
  }

  public static ChangePasswordRepository newInstance(ApiService apiService) {
    return new ChangePasswordRepository(apiService);
  }
}
