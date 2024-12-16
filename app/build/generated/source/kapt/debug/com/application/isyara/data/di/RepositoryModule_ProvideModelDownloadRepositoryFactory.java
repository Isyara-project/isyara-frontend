package com.application.isyara.data.di;

import android.content.Context;
import com.application.isyara.data.repository.ModelDownloadRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class RepositoryModule_ProvideModelDownloadRepositoryFactory implements Factory<ModelDownloadRepository> {
  private final Provider<Context> contextProvider;

  public RepositoryModule_ProvideModelDownloadRepositoryFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ModelDownloadRepository get() {
    return provideModelDownloadRepository(contextProvider.get());
  }

  public static RepositoryModule_ProvideModelDownloadRepositoryFactory create(
      Provider<Context> contextProvider) {
    return new RepositoryModule_ProvideModelDownloadRepositoryFactory(contextProvider);
  }

  public static ModelDownloadRepository provideModelDownloadRepository(Context context) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideModelDownloadRepository(context));
  }
}
