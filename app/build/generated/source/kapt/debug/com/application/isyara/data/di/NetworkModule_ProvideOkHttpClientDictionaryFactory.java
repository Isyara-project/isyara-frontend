package com.application.isyara.data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideOkHttpClientDictionaryFactory implements Factory<OkHttpClient> {
  @Override
  public OkHttpClient get() {
    return provideOkHttpClientDictionary();
  }

  public static NetworkModule_ProvideOkHttpClientDictionaryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OkHttpClient provideOkHttpClientDictionary() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClientDictionary());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideOkHttpClientDictionaryFactory INSTANCE = new NetworkModule_ProvideOkHttpClientDictionaryFactory();
  }
}
