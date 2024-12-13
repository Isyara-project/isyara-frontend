package com.application.isyara.data.di;

import com.application.isyara.data.local.DownloadDictionaryPictureDao;
import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.DictionaryPictureRepository;
import com.application.isyara.utils.dictionary.NetworkHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.application.isyara.data.di.RetrofitDictionary")
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
public final class DatabaseModule_ProvideDictionaryPictureRepositoryFactory implements Factory<DictionaryPictureRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<DownloadDictionaryPictureDao> downloadDictionaryPictureDaoProvider;

  private final Provider<NetworkHelper> networkHelperProvider;

  public DatabaseModule_ProvideDictionaryPictureRepositoryFactory(
      Provider<ApiService> apiServiceProvider,
      Provider<DownloadDictionaryPictureDao> downloadDictionaryPictureDaoProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.downloadDictionaryPictureDaoProvider = downloadDictionaryPictureDaoProvider;
    this.networkHelperProvider = networkHelperProvider;
  }

  @Override
  public DictionaryPictureRepository get() {
    return provideDictionaryPictureRepository(apiServiceProvider.get(), downloadDictionaryPictureDaoProvider.get(), networkHelperProvider.get());
  }

  public static DatabaseModule_ProvideDictionaryPictureRepositoryFactory create(
      Provider<ApiService> apiServiceProvider,
      Provider<DownloadDictionaryPictureDao> downloadDictionaryPictureDaoProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    return new DatabaseModule_ProvideDictionaryPictureRepositoryFactory(apiServiceProvider, downloadDictionaryPictureDaoProvider, networkHelperProvider);
  }

  public static DictionaryPictureRepository provideDictionaryPictureRepository(
      ApiService apiService, DownloadDictionaryPictureDao downloadDictionaryPictureDao,
      NetworkHelper networkHelper) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDictionaryPictureRepository(apiService, downloadDictionaryPictureDao, networkHelper));
  }
}
