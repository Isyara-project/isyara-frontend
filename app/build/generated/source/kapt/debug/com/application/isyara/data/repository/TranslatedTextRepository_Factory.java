package com.application.isyara.data.repository;

import android.content.Context;
import com.application.isyara.data.local.TranslatedTextDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TranslatedTextRepository_Factory implements Factory<TranslatedTextRepository> {
  private final Provider<TranslatedTextDao> translatedTextDaoProvider;

  private final Provider<Context> contextProvider;

  public TranslatedTextRepository_Factory(Provider<TranslatedTextDao> translatedTextDaoProvider,
      Provider<Context> contextProvider) {
    this.translatedTextDaoProvider = translatedTextDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public TranslatedTextRepository get() {
    return newInstance(translatedTextDaoProvider.get(), contextProvider.get());
  }

  public static TranslatedTextRepository_Factory create(
      Provider<TranslatedTextDao> translatedTextDaoProvider, Provider<Context> contextProvider) {
    return new TranslatedTextRepository_Factory(translatedTextDaoProvider, contextProvider);
  }

  public static TranslatedTextRepository newInstance(TranslatedTextDao translatedTextDao,
      Context context) {
    return new TranslatedTextRepository(translatedTextDao, context);
  }
}
