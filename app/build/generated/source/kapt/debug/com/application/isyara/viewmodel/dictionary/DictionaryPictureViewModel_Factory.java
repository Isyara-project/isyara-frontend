package com.application.isyara.viewmodel.dictionary;

import com.application.isyara.data.repository.DictionaryPictureRepository;
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
public final class DictionaryPictureViewModel_Factory implements Factory<DictionaryPictureViewModel> {
  private final Provider<DictionaryPictureRepository> repositoryProvider;

  public DictionaryPictureViewModel_Factory(
      Provider<DictionaryPictureRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DictionaryPictureViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static DictionaryPictureViewModel_Factory create(
      Provider<DictionaryPictureRepository> repositoryProvider) {
    return new DictionaryPictureViewModel_Factory(repositoryProvider);
  }

  public static DictionaryPictureViewModel newInstance(DictionaryPictureRepository repository) {
    return new DictionaryPictureViewModel(repository);
  }
}
