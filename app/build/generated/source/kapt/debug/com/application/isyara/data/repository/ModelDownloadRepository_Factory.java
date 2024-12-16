package com.application.isyara.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ModelDownloadRepository_Factory implements Factory<ModelDownloadRepository> {
  private final Provider<Context> contextProvider;

  public ModelDownloadRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ModelDownloadRepository get() {
    return newInstance(contextProvider.get());
  }

  public static ModelDownloadRepository_Factory create(Provider<Context> contextProvider) {
    return new ModelDownloadRepository_Factory(contextProvider);
  }

  public static ModelDownloadRepository newInstance(Context context) {
    return new ModelDownloadRepository(context);
  }
}
