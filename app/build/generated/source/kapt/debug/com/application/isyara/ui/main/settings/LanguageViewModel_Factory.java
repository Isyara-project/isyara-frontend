package com.application.isyara.ui.main.settings;

import com.application.isyara.data.repository.LanguageRepository;
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
public final class LanguageViewModel_Factory implements Factory<LanguageViewModel> {
  private final Provider<LanguageRepository> repositoryProvider;

  public LanguageViewModel_Factory(Provider<LanguageRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LanguageViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static LanguageViewModel_Factory create(Provider<LanguageRepository> repositoryProvider) {
    return new LanguageViewModel_Factory(repositoryProvider);
  }

  public static LanguageViewModel newInstance(LanguageRepository repository) {
    return new LanguageViewModel(repository);
  }
}
