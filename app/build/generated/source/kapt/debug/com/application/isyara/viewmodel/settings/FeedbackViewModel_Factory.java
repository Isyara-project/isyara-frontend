package com.application.isyara.viewmodel.settings;

import com.application.isyara.data.repository.FeedbackRepository;
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
public final class FeedbackViewModel_Factory implements Factory<FeedbackViewModel> {
  private final Provider<FeedbackRepository> feedbackRepositoryProvider;

  public FeedbackViewModel_Factory(Provider<FeedbackRepository> feedbackRepositoryProvider) {
    this.feedbackRepositoryProvider = feedbackRepositoryProvider;
  }

  @Override
  public FeedbackViewModel get() {
    return newInstance(feedbackRepositoryProvider.get());
  }

  public static FeedbackViewModel_Factory create(
      Provider<FeedbackRepository> feedbackRepositoryProvider) {
    return new FeedbackViewModel_Factory(feedbackRepositoryProvider);
  }

  public static FeedbackViewModel newInstance(FeedbackRepository feedbackRepository) {
    return new FeedbackViewModel(feedbackRepository);
  }
}
