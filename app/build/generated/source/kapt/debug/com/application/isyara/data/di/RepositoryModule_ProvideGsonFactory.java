package com.application.isyara.data.di;

import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class RepositoryModule_ProvideGsonFactory implements Factory<Gson> {
  @Override
  public Gson get() {
    return provideGson();
  }

  public static RepositoryModule_ProvideGsonFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Gson provideGson() {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideGson());
  }

  private static final class InstanceHolder {
    private static final RepositoryModule_ProvideGsonFactory INSTANCE = new RepositoryModule_ProvideGsonFactory();
  }
}
