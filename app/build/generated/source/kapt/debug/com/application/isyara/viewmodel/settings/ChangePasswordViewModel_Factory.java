package com.application.isyara.viewmodel.settings;

import com.application.isyara.data.preferences.SessionManager;
import com.application.isyara.data.repository.ChangePasswordRepository;
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
public final class ChangePasswordViewModel_Factory implements Factory<ChangePasswordViewModel> {
  private final Provider<ChangePasswordRepository> repositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public ChangePasswordViewModel_Factory(Provider<ChangePasswordRepository> repositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.repositoryProvider = repositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public ChangePasswordViewModel get() {
    return newInstance(repositoryProvider.get(), sessionManagerProvider.get());
  }

  public static ChangePasswordViewModel_Factory create(
      Provider<ChangePasswordRepository> repositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new ChangePasswordViewModel_Factory(repositoryProvider, sessionManagerProvider);
  }

  public static ChangePasswordViewModel newInstance(ChangePasswordRepository repository,
      SessionManager sessionManager) {
    return new ChangePasswordViewModel(repository, sessionManager);
  }
}
