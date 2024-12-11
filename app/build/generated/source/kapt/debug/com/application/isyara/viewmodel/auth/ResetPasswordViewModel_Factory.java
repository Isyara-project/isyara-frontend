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
public final class ResetPasswordViewModel_Factory implements Factory<ResetPasswordViewModel> {
  private final Provider<PasswordRepository> repositoryProvider;

  public ResetPasswordViewModel_Factory(Provider<PasswordRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ResetPasswordViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ResetPasswordViewModel_Factory create(
      Provider<PasswordRepository> repositoryProvider) {
    return new ResetPasswordViewModel_Factory(repositoryProvider);
  }

  public static ResetPasswordViewModel newInstance(PasswordRepository repository) {
    return new ResetPasswordViewModel(repository);
  }
}
