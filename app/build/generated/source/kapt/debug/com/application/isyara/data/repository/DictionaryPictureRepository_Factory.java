package com.application.isyara.data.repository;

import com.application.isyara.data.local.DownloadDictionaryPictureDao;
import com.application.isyara.data.remote.ApiService;
import com.application.isyara.utils.dictionary.NetworkHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.application.isyara.data.di.RetrofitMain")
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
public final class DictionaryPictureRepository_Factory implements Factory<DictionaryPictureRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<DownloadDictionaryPictureDao> downloadDictionaryPictureDaoProvider;

  private final Provider<NetworkHelper> networkHelperProvider;

  public DictionaryPictureRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<DownloadDictionaryPictureDao> downloadDictionaryPictureDaoProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.downloadDictionaryPictureDaoProvider = downloadDictionaryPictureDaoProvider;
    this.networkHelperProvider = networkHelperProvider;
  }

  @Override
  public DictionaryPictureRepository get() {
    return newInstance(apiServiceProvider.get(), downloadDictionaryPictureDaoProvider.get(), networkHelperProvider.get());
  }

  public static DictionaryPictureRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<DownloadDictionaryPictureDao> downloadDictionaryPictureDaoProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    return new DictionaryPictureRepository_Factory(apiServiceProvider, downloadDictionaryPictureDaoProvider, networkHelperProvider);
  }

  public static DictionaryPictureRepository newInstance(ApiService apiService,
      DownloadDictionaryPictureDao downloadDictionaryPictureDao, NetworkHelper networkHelper) {
    return new DictionaryPictureRepository(apiService, downloadDictionaryPictureDao, networkHelper);
  }
}
