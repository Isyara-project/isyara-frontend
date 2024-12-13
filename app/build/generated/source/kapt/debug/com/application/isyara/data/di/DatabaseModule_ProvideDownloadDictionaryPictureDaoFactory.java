package com.application.isyara.data.di;

import com.application.isyara.data.local.AppDatabase;
import com.application.isyara.data.local.DownloadDictionaryPictureDao;
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
public final class DatabaseModule_ProvideDownloadDictionaryPictureDaoFactory implements Factory<DownloadDictionaryPictureDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideDownloadDictionaryPictureDaoFactory(
      Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DownloadDictionaryPictureDao get() {
    return provideDownloadDictionaryPictureDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideDownloadDictionaryPictureDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideDownloadDictionaryPictureDaoFactory(databaseProvider);
  }

  public static DownloadDictionaryPictureDao provideDownloadDictionaryPictureDao(
      AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDownloadDictionaryPictureDao(database));
  }
}
