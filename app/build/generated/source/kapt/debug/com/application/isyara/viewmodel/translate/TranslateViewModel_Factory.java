package com.application.isyara.viewmodel.translate;

import android.content.Context;
import com.application.isyara.data.repository.TranslatedTextRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class TranslateViewModel_Factory implements Factory<TranslateViewModel> {
  private final Provider<TranslatedTextRepository> translatedTextRepositoryProvider;

  private final Provider<Context> contextProvider;

  public TranslateViewModel_Factory(
      Provider<TranslatedTextRepository> translatedTextRepositoryProvider,
      Provider<Context> contextProvider) {
    this.translatedTextRepositoryProvider = translatedTextRepositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public TranslateViewModel get() {
    return newInstance(translatedTextRepositoryProvider.get(), contextProvider.get());
  }

  public static TranslateViewModel_Factory create(
      Provider<TranslatedTextRepository> translatedTextRepositoryProvider,
      Provider<Context> contextProvider) {
    return new TranslateViewModel_Factory(translatedTextRepositoryProvider, contextProvider);
  }

  public static TranslateViewModel newInstance(TranslatedTextRepository translatedTextRepository,
      Context context) {
    return new TranslateViewModel(translatedTextRepository, context);
  }
}