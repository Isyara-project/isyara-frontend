package com.application.isyara.viewmodel.dashboard;

import com.application.isyara.data.repository.ProfileRepository;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<ProfileRepository> repositoryProvider;

  public ProfileViewModel_Factory(Provider<ProfileRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<ProfileRepository> repositoryProvider) {
    return new ProfileViewModel_Factory(repositoryProvider);
  }

  public static ProfileViewModel newInstance(ProfileRepository repository) {
    return new ProfileViewModel(repository);
  }
}
