package com.application.isyara.viewmodel.history;

import com.application.isyara.data.repository.DictionaryPictureRepository;
import com.application.isyara.data.repository.DictionaryRepository;
import com.application.isyara.data.repository.TranslatedTextRepository;
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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<TranslatedTextRepository> translatedTextRepositoryProvider;

  private final Provider<DictionaryRepository> dictionaryRepositoryProvider;

  private final Provider<DictionaryPictureRepository> dictionaryPictureRepositoryProvider;

  public HistoryViewModel_Factory(
      Provider<TranslatedTextRepository> translatedTextRepositoryProvider,
      Provider<DictionaryRepository> dictionaryRepositoryProvider,
      Provider<DictionaryPictureRepository> dictionaryPictureRepositoryProvider) {
    this.translatedTextRepositoryProvider = translatedTextRepositoryProvider;
    this.dictionaryRepositoryProvider = dictionaryRepositoryProvider;
    this.dictionaryPictureRepositoryProvider = dictionaryPictureRepositoryProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(translatedTextRepositoryProvider.get(), dictionaryRepositoryProvider.get(), dictionaryPictureRepositoryProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<TranslatedTextRepository> translatedTextRepositoryProvider,
      Provider<DictionaryRepository> dictionaryRepositoryProvider,
      Provider<DictionaryPictureRepository> dictionaryPictureRepositoryProvider) {
    return new HistoryViewModel_Factory(translatedTextRepositoryProvider, dictionaryRepositoryProvider, dictionaryPictureRepositoryProvider);
  }

  public static HistoryViewModel newInstance(TranslatedTextRepository translatedTextRepository,
      DictionaryRepository dictionaryRepository,
      DictionaryPictureRepository dictionaryPictureRepository) {
    return new HistoryViewModel(translatedTextRepository, dictionaryRepository, dictionaryPictureRepository);
  }
}
