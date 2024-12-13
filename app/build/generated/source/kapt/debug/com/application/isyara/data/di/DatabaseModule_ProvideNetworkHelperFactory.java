package com.application.isyara.data.di;

import android.content.Context;
import com.application.isyara.utils.dictionary.NetworkHelper;
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
public final class DatabaseModule_ProvideNetworkHelperFactory implements Factory<NetworkHelper> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideNetworkHelperFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NetworkHelper get() {
    return provideNetworkHelper(contextProvider.get());
  }

  public static DatabaseModule_ProvideNetworkHelperFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideNetworkHelperFactory(contextProvider);
  }

  public static NetworkHelper provideNetworkHelper(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideNetworkHelper(context));
  }
}
