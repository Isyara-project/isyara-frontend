package com.application.isyara.data.di;

import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.PasswordRepository;
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
public final class RepositoryModule_ProvidePasswordRepositoryFactory implements Factory<PasswordRepository> {
  private final Provider<ApiService> apiServiceProvider;

  public RepositoryModule_ProvidePasswordRepositoryFactory(
      Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public PasswordRepository get() {
    return providePasswordRepository(apiServiceProvider.get());
  }

  public static RepositoryModule_ProvidePasswordRepositoryFactory create(
      Provider<ApiService> apiServiceProvider) {
    return new RepositoryModule_ProvidePasswordRepositoryFactory(apiServiceProvider);
  }

  public static PasswordRepository providePasswordRepository(ApiService apiService) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.providePasswordRepository(apiService));
  }
}
