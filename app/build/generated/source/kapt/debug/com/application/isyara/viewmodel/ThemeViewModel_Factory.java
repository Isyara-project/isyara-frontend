package com.application.isyara.viewmodel;

import com.application.isyara.repository.ThemeRepository;
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
public final class ThemeViewModel_Factory implements Factory<ThemeViewModel> {
  private final Provider<ThemeRepository> themeRepositoryProvider;

  public ThemeViewModel_Factory(Provider<ThemeRepository> themeRepositoryProvider) {
    this.themeRepositoryProvider = themeRepositoryProvider;
  }

  @Override
  public ThemeViewModel get() {
    return newInstance(themeRepositoryProvider.get());
  }

  public static ThemeViewModel_Factory create(Provider<ThemeRepository> themeRepositoryProvider) {
    return new ThemeViewModel_Factory(themeRepositoryProvider);
  }

  public static ThemeViewModel newInstance(ThemeRepository themeRepository) {
    return new ThemeViewModel(themeRepository);
  }
}
