package com.application.isyara.data.di;

import com.application.isyara.data.local.AppDatabase;
import com.application.isyara.data.local.DownloadedDictionaryDao;
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
public final class DatabaseModule_ProvideDownloadedDictionaryDaoFactory implements Factory<DownloadedDictionaryDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideDownloadedDictionaryDaoFactory(
      Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DownloadedDictionaryDao get() {
    return provideDownloadedDictionaryDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideDownloadedDictionaryDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideDownloadedDictionaryDaoFactory(databaseProvider);
  }

  public static DownloadedDictionaryDao provideDownloadedDictionaryDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDownloadedDictionaryDao(database));
  }
}
