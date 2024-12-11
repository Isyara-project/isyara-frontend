package com.application.isyara.data.di;

import android.content.Context;
import com.application.isyara.data.local.DownloadedDictionaryDao;
import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.DictionaryRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata({
    "com.application.isyara.data.di.RetrofitDictionary",
    "dagger.hilt.android.qualifiers.ApplicationContext"
})
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
public final class DatabaseModule_ProvideDictionaryRepositoryFactory implements Factory<DictionaryRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<Context> contextProvider;

  private final Provider<DownloadedDictionaryDao> downloadedDictionaryDaoProvider;

  public DatabaseModule_ProvideDictionaryRepositoryFactory(Provider<ApiService> apiServiceProvider,
      Provider<Context> contextProvider,
      Provider<DownloadedDictionaryDao> downloadedDictionaryDaoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.contextProvider = contextProvider;
    this.downloadedDictionaryDaoProvider = downloadedDictionaryDaoProvider;
  }

  @Override
  public DictionaryRepository get() {
    return provideDictionaryRepository(apiServiceProvider.get(), contextProvider.get(), downloadedDictionaryDaoProvider.get());
  }

  public static DatabaseModule_ProvideDictionaryRepositoryFactory create(
      Provider<ApiService> apiServiceProvider, Provider<Context> contextProvider,
      Provider<DownloadedDictionaryDao> downloadedDictionaryDaoProvider) {
    return new DatabaseModule_ProvideDictionaryRepositoryFactory(apiServiceProvider, contextProvider, downloadedDictionaryDaoProvider);
  }

  public static DictionaryRepository provideDictionaryRepository(ApiService apiService,
      Context context, DownloadedDictionaryDao downloadedDictionaryDao) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDictionaryRepository(apiService, context, downloadedDictionaryDao));
  }
}
