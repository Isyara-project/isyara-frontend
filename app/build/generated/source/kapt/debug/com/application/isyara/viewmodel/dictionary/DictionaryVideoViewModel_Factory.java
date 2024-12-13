package com.application.isyara.viewmodel.dictionary;

import com.application.isyara.data.repository.DictionaryRepository;
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
public final class DictionaryVideoViewModel_Factory implements Factory<DictionaryVideoViewModel> {
  private final Provider<DictionaryRepository> dictionaryRepositoryProvider;

  public DictionaryVideoViewModel_Factory(
      Provider<DictionaryRepository> dictionaryRepositoryProvider) {
    this.dictionaryRepositoryProvider = dictionaryRepositoryProvider;
  }

  @Override
  public DictionaryVideoViewModel get() {
    return newInstance(dictionaryRepositoryProvider.get());
  }

  public static DictionaryVideoViewModel_Factory create(
      Provider<DictionaryRepository> dictionaryRepositoryProvider) {
    return new DictionaryVideoViewModel_Factory(dictionaryRepositoryProvider);
  }

  public static DictionaryVideoViewModel newInstance(DictionaryRepository dictionaryRepository) {
    return new DictionaryVideoViewModel(dictionaryRepository);
  }
}
