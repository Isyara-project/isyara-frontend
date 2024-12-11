package com.application.isyara.data.repository;

import android.content.Context;
import com.application.isyara.data.local.DownloadedDictionaryDao;
import com.application.isyara.data.remote.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DictionaryRepository_Factory implements Factory<DictionaryRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<Context> contextProvider;

  private final Provider<DownloadedDictionaryDao> downloadedDictionaryDaoProvider;

  public DictionaryRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<Context> contextProvider,
      Provider<DownloadedDictionaryDao> downloadedDictionaryDaoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.contextProvider = contextProvider;
    this.downloadedDictionaryDaoProvider = downloadedDictionaryDaoProvider;
  }

  @Override
  public DictionaryRepository get() {
    return newInstance(apiServiceProvider.get(), contextProvider.get(), downloadedDictionaryDaoProvider.get());
  }

  public static DictionaryRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<Context> contextProvider,
      Provider<DownloadedDictionaryDao> downloadedDictionaryDaoProvider) {
    return new DictionaryRepository_Factory(apiServiceProvider, contextProvider, downloadedDictionaryDaoProvider);
  }

  public static DictionaryRepository newInstance(ApiService apiService, Context context,
      DownloadedDictionaryDao downloadedDictionaryDao) {
    return new DictionaryRepository(apiService, context, downloadedDictionaryDao);
  }
}
