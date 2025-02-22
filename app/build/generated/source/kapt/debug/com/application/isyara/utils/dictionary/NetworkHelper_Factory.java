package com.application.isyara.utils.dictionary;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class NetworkHelper_Factory implements Factory<NetworkHelper> {
  private final Provider<Context> contextProvider;

  public NetworkHelper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NetworkHelper get() {
    return newInstance(contextProvider.get());
  }

  public static NetworkHelper_Factory create(Provider<Context> contextProvider) {
    return new NetworkHelper_Factory(contextProvider);
  }

  public static NetworkHelper newInstance(Context context) {
    return new NetworkHelper(context);
  }
}
