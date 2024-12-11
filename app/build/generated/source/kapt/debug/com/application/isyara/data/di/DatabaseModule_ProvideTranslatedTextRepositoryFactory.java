package com.application.isyara.data.di;

import android.content.Context;
import com.application.isyara.data.local.TranslatedTextDao;
import com.application.isyara.data.repository.TranslatedTextRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideTranslatedTextRepositoryFactory implements Factory<TranslatedTextRepository> {
  private final Provider<TranslatedTextDao> translatedTextDaoProvider;

  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideTranslatedTextRepositoryFactory(
      Provider<TranslatedTextDao> translatedTextDaoProvider, Provider<Context> contextProvider) {
    this.translatedTextDaoProvider = translatedTextDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public TranslatedTextRepository get() {
    return provideTranslatedTextRepository(translatedTextDaoProvider.get(), contextProvider.get());
  }

  public static DatabaseModule_ProvideTranslatedTextRepositoryFactory create(
      Provider<TranslatedTextDao> translatedTextDaoProvider, Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideTranslatedTextRepositoryFactory(translatedTextDaoProvider, contextProvider);
  }

  public static TranslatedTextRepository provideTranslatedTextRepository(
      TranslatedTextDao translatedTextDao, Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTranslatedTextRepository(translatedTextDao, context));
  }
}
