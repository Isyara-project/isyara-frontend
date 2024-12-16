package com.application.isyara.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class LanguageRepository_Factory implements Factory<LanguageRepository> {
  private final Provider<Context> contextProvider;

  public LanguageRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LanguageRepository get() {
    return newInstance(contextProvider.get());
  }

  public static LanguageRepository_Factory create(Provider<Context> contextProvider) {
    return new LanguageRepository_Factory(contextProvider);
  }

  public static LanguageRepository newInstance(Context context) {
    return new LanguageRepository(context);
  }
}
