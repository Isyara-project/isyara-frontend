package com.application.isyara.data.di;

import android.content.Context;
import com.application.isyara.data.local.SessionManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SharedPreferencesModule_ProvideSessionManagerFactory implements Factory<SessionManager> {
  private final Provider<Context> contextProvider;

  public SharedPreferencesModule_ProvideSessionManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SessionManager get() {
    return provideSessionManager(contextProvider.get());
  }

  public static SharedPreferencesModule_ProvideSessionManagerFactory create(
      Provider<Context> contextProvider) {
    return new SharedPreferencesModule_ProvideSessionManagerFactory(contextProvider);
  }

  public static SessionManager provideSessionManager(Context context) {
    return Preconditions.checkNotNullFromProvides(SharedPreferencesModule.INSTANCE.provideSessionManager(context));
  }
}
