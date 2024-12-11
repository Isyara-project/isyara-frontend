package com.application.isyara.viewmodel.auth;

import com.application.isyara.data.repository.PasswordRepository;
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
public final class ForgotPasswordViewModel_Factory implements Factory<ForgotPasswordViewModel> {
  private final Provider<PasswordRepository> repositoryProvider;

  public ForgotPasswordViewModel_Factory(Provider<PasswordRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ForgotPasswordViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ForgotPasswordViewModel_Factory create(
      Provider<PasswordRepository> repositoryProvider) {
    return new ForgotPasswordViewModel_Factory(repositoryProvider);
  }

  public static ForgotPasswordViewModel newInstance(PasswordRepository repository) {
    return new ForgotPasswordViewModel(repository);
  }
}
