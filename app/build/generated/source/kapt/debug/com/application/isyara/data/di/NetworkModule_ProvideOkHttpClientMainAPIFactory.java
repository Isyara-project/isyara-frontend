package com.application.isyara.data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideOkHttpClientMainAPIFactory implements Factory<OkHttpClient> {
  @Override
  public OkHttpClient get() {
    return provideOkHttpClientMainAPI();
  }

  public static NetworkModule_ProvideOkHttpClientMainAPIFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OkHttpClient provideOkHttpClientMainAPI() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClientMainAPI());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideOkHttpClientMainAPIFactory INSTANCE = new NetworkModule_ProvideOkHttpClientMainAPIFactory();
  }
}
