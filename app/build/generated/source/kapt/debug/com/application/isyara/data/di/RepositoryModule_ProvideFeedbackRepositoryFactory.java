package com.application.isyara.data.di;

import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.FeedbackRepository;
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
public final class RepositoryModule_ProvideFeedbackRepositoryFactory implements Factory<FeedbackRepository> {
  private final Provider<ApiService> apiServiceProvider;

  public RepositoryModule_ProvideFeedbackRepositoryFactory(
      Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public FeedbackRepository get() {
    return provideFeedbackRepository(apiServiceProvider.get());
  }

  public static RepositoryModule_ProvideFeedbackRepositoryFactory create(
      Provider<ApiService> apiServiceProvider) {
    return new RepositoryModule_ProvideFeedbackRepositoryFactory(apiServiceProvider);
  }

  public static FeedbackRepository provideFeedbackRepository(ApiService apiService) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideFeedbackRepository(apiService));
  }
}
