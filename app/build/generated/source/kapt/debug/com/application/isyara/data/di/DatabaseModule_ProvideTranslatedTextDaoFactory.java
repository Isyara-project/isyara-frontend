package com.application.isyara.data.di;

import com.application.isyara.data.local.AppDatabase;
import com.application.isyara.data.local.TranslatedTextDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideTranslatedTextDaoFactory implements Factory<TranslatedTextDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideTranslatedTextDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TranslatedTextDao get() {
    return provideTranslatedTextDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideTranslatedTextDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideTranslatedTextDaoFactory(databaseProvider);
  }

  public static TranslatedTextDao provideTranslatedTextDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTranslatedTextDao(database));
  }
}
