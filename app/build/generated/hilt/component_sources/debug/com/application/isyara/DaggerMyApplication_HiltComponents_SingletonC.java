package com.application.isyara;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.application.isyara.data.di.DatabaseModule_ProvideDatabaseFactory;
import com.application.isyara.data.di.DatabaseModule_ProvideDictionaryPictureRepositoryFactory;
import com.application.isyara.data.di.DatabaseModule_ProvideDictionaryRepositoryFactory;
import com.application.isyara.data.di.DatabaseModule_ProvideDownloadDictionaryPictureDaoFactory;
import com.application.isyara.data.di.DatabaseModule_ProvideDownloadedDictionaryDaoFactory;
import com.application.isyara.data.di.DatabaseModule_ProvideTranslatedTextDaoFactory;
import com.application.isyara.data.di.DatabaseModule_ProvideTranslatedTextRepositoryFactory;
import com.application.isyara.data.di.NetworkModule_ProvideApiServiceDictionaryFactory;
import com.application.isyara.data.di.NetworkModule_ProvideApiServiceMainAPIFactory;
import com.application.isyara.data.di.NetworkModule_ProvideOkHttpClientDictionaryFactory;
import com.application.isyara.data.di.NetworkModule_ProvideOkHttpClientMainAPIFactory;
import com.application.isyara.data.di.NetworkModule_ProvideRetrofitDictionaryFactory;
import com.application.isyara.data.di.NetworkModule_ProvideRetrofitMainAPIFactory;
import com.application.isyara.data.di.RepositoryModule_ProvideAuthRepositoryFactory;
import com.application.isyara.data.di.RepositoryModule_ProvideChangePasswordRepositoryFactory;
import com.application.isyara.data.di.RepositoryModule_ProvideFeedbackRepositoryFactory;
import com.application.isyara.data.di.RepositoryModule_ProvideGsonFactory;
import com.application.isyara.data.di.RepositoryModule_ProvidePasswordRepositoryFactory;
import com.application.isyara.data.local.AppDatabase;
import com.application.isyara.data.local.DownloadDictionaryPictureDao;
import com.application.isyara.data.local.DownloadedDictionaryDao;
import com.application.isyara.data.local.SessionManager;
import com.application.isyara.data.local.TranslatedTextDao;
import com.application.isyara.data.local.UserPreferences;
import com.application.isyara.data.remote.ApiService;
import com.application.isyara.data.repository.AuthRepository;
import com.application.isyara.data.repository.ChangePasswordRepository;
import com.application.isyara.data.repository.DictionaryPictureRepository;
import com.application.isyara.data.repository.DictionaryRepository;
import com.application.isyara.data.repository.FeedbackRepository;
import com.application.isyara.data.repository.PasswordRepository;
import com.application.isyara.data.repository.ProfileRepository;
import com.application.isyara.data.repository.TranslatedTextRepository;
import com.application.isyara.ui.MainActivity;
import com.application.isyara.viewmodel.auth.AuthViewModel;
import com.application.isyara.viewmodel.auth.AuthViewModel_HiltModules;
import com.application.isyara.viewmodel.auth.ForgotPasswordViewModel;
import com.application.isyara.viewmodel.auth.ForgotPasswordViewModel_HiltModules;
import com.application.isyara.viewmodel.auth.ResetPasswordViewModel;
import com.application.isyara.viewmodel.auth.ResetPasswordViewModel_HiltModules;
import com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel;
import com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel_HiltModules;
import com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel;
import com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel_HiltModules;
import com.application.isyara.viewmodel.history.HistoryViewModel;
import com.application.isyara.viewmodel.history.HistoryViewModel_HiltModules;
import com.application.isyara.viewmodel.main.ProfileViewModel;
import com.application.isyara.viewmodel.main.ProfileViewModel_HiltModules;
import com.application.isyara.viewmodel.settings.ChangePasswordViewModel;
import com.application.isyara.viewmodel.settings.ChangePasswordViewModel_HiltModules;
import com.application.isyara.viewmodel.settings.FeedbackViewModel;
import com.application.isyara.viewmodel.settings.FeedbackViewModel_HiltModules;
import com.application.isyara.viewmodel.translate.TranslateViewModel;
import com.application.isyara.viewmodel.translate.TranslateViewModel_HiltModules;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerMyApplication_HiltComponents_SingletonC {
  private DaggerMyApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public MyApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MyApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements MyApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MyApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MyApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MyApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MyApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements MyApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MyApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MyApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends MyApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends MyApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MyApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(10).put(LazyClassKeyProvider.com_application_isyara_viewmodel_auth_AuthViewModel, AuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_settings_ChangePasswordViewModel, ChangePasswordViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_dictionary_DictionaryPictureViewModel, DictionaryPictureViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_dictionary_DictionaryVideoViewModel, DictionaryVideoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_settings_FeedbackViewModel, FeedbackViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_auth_ForgotPasswordViewModel, ForgotPasswordViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_history_HistoryViewModel, HistoryViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_main_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_auth_ResetPasswordViewModel, ResetPasswordViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_application_isyara_viewmodel_translate_TranslateViewModel, TranslateViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_application_isyara_viewmodel_dictionary_DictionaryPictureViewModel = "com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel";

      static String com_application_isyara_viewmodel_auth_ResetPasswordViewModel = "com.application.isyara.viewmodel.auth.ResetPasswordViewModel";

      static String com_application_isyara_viewmodel_settings_FeedbackViewModel = "com.application.isyara.viewmodel.settings.FeedbackViewModel";

      static String com_application_isyara_viewmodel_auth_AuthViewModel = "com.application.isyara.viewmodel.auth.AuthViewModel";

      static String com_application_isyara_viewmodel_auth_ForgotPasswordViewModel = "com.application.isyara.viewmodel.auth.ForgotPasswordViewModel";

      static String com_application_isyara_viewmodel_history_HistoryViewModel = "com.application.isyara.viewmodel.history.HistoryViewModel";

      static String com_application_isyara_viewmodel_translate_TranslateViewModel = "com.application.isyara.viewmodel.translate.TranslateViewModel";

      static String com_application_isyara_viewmodel_settings_ChangePasswordViewModel = "com.application.isyara.viewmodel.settings.ChangePasswordViewModel";

      static String com_application_isyara_viewmodel_dictionary_DictionaryVideoViewModel = "com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel";

      static String com_application_isyara_viewmodel_main_ProfileViewModel = "com.application.isyara.viewmodel.main.ProfileViewModel";

      @KeepFieldType
      DictionaryPictureViewModel com_application_isyara_viewmodel_dictionary_DictionaryPictureViewModel2;

      @KeepFieldType
      ResetPasswordViewModel com_application_isyara_viewmodel_auth_ResetPasswordViewModel2;

      @KeepFieldType
      FeedbackViewModel com_application_isyara_viewmodel_settings_FeedbackViewModel2;

      @KeepFieldType
      AuthViewModel com_application_isyara_viewmodel_auth_AuthViewModel2;

      @KeepFieldType
      ForgotPasswordViewModel com_application_isyara_viewmodel_auth_ForgotPasswordViewModel2;

      @KeepFieldType
      HistoryViewModel com_application_isyara_viewmodel_history_HistoryViewModel2;

      @KeepFieldType
      TranslateViewModel com_application_isyara_viewmodel_translate_TranslateViewModel2;

      @KeepFieldType
      ChangePasswordViewModel com_application_isyara_viewmodel_settings_ChangePasswordViewModel2;

      @KeepFieldType
      DictionaryVideoViewModel com_application_isyara_viewmodel_dictionary_DictionaryVideoViewModel2;

      @KeepFieldType
      ProfileViewModel com_application_isyara_viewmodel_main_ProfileViewModel2;
    }
  }

  private static final class ViewModelCImpl extends MyApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<ChangePasswordViewModel> changePasswordViewModelProvider;

    private Provider<DictionaryPictureViewModel> dictionaryPictureViewModelProvider;

    private Provider<DictionaryVideoViewModel> dictionaryVideoViewModelProvider;

    private Provider<FeedbackViewModel> feedbackViewModelProvider;

    private Provider<ForgotPasswordViewModel> forgotPasswordViewModelProvider;

    private Provider<HistoryViewModel> historyViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<ResetPasswordViewModel> resetPasswordViewModelProvider;

    private Provider<TranslateViewModel> translateViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private ProfileRepository profileRepository() {
      return new ProfileRepository(singletonCImpl.provideApiServiceMainAPIProvider.get(), singletonCImpl.sessionManagerProvider.get(), singletonCImpl.provideGsonProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.changePasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.dictionaryPictureViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.dictionaryVideoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.feedbackViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.forgotPasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.historyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.resetPasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.translateViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(10).put(LazyClassKeyProvider.com_application_isyara_viewmodel_auth_AuthViewModel, ((Provider) authViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_settings_ChangePasswordViewModel, ((Provider) changePasswordViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_dictionary_DictionaryPictureViewModel, ((Provider) dictionaryPictureViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_dictionary_DictionaryVideoViewModel, ((Provider) dictionaryVideoViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_settings_FeedbackViewModel, ((Provider) feedbackViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_auth_ForgotPasswordViewModel, ((Provider) forgotPasswordViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_history_HistoryViewModel, ((Provider) historyViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_main_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_auth_ResetPasswordViewModel, ((Provider) resetPasswordViewModelProvider)).put(LazyClassKeyProvider.com_application_isyara_viewmodel_translate_TranslateViewModel, ((Provider) translateViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_application_isyara_viewmodel_settings_ChangePasswordViewModel = "com.application.isyara.viewmodel.settings.ChangePasswordViewModel";

      static String com_application_isyara_viewmodel_translate_TranslateViewModel = "com.application.isyara.viewmodel.translate.TranslateViewModel";

      static String com_application_isyara_viewmodel_auth_AuthViewModel = "com.application.isyara.viewmodel.auth.AuthViewModel";

      static String com_application_isyara_viewmodel_dictionary_DictionaryPictureViewModel = "com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel";

      static String com_application_isyara_viewmodel_auth_ForgotPasswordViewModel = "com.application.isyara.viewmodel.auth.ForgotPasswordViewModel";

      static String com_application_isyara_viewmodel_auth_ResetPasswordViewModel = "com.application.isyara.viewmodel.auth.ResetPasswordViewModel";

      static String com_application_isyara_viewmodel_dictionary_DictionaryVideoViewModel = "com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel";

      static String com_application_isyara_viewmodel_main_ProfileViewModel = "com.application.isyara.viewmodel.main.ProfileViewModel";

      static String com_application_isyara_viewmodel_history_HistoryViewModel = "com.application.isyara.viewmodel.history.HistoryViewModel";

      static String com_application_isyara_viewmodel_settings_FeedbackViewModel = "com.application.isyara.viewmodel.settings.FeedbackViewModel";

      @KeepFieldType
      ChangePasswordViewModel com_application_isyara_viewmodel_settings_ChangePasswordViewModel2;

      @KeepFieldType
      TranslateViewModel com_application_isyara_viewmodel_translate_TranslateViewModel2;

      @KeepFieldType
      AuthViewModel com_application_isyara_viewmodel_auth_AuthViewModel2;

      @KeepFieldType
      DictionaryPictureViewModel com_application_isyara_viewmodel_dictionary_DictionaryPictureViewModel2;

      @KeepFieldType
      ForgotPasswordViewModel com_application_isyara_viewmodel_auth_ForgotPasswordViewModel2;

      @KeepFieldType
      ResetPasswordViewModel com_application_isyara_viewmodel_auth_ResetPasswordViewModel2;

      @KeepFieldType
      DictionaryVideoViewModel com_application_isyara_viewmodel_dictionary_DictionaryVideoViewModel2;

      @KeepFieldType
      ProfileViewModel com_application_isyara_viewmodel_main_ProfileViewModel2;

      @KeepFieldType
      HistoryViewModel com_application_isyara_viewmodel_history_HistoryViewModel2;

      @KeepFieldType
      FeedbackViewModel com_application_isyara_viewmodel_settings_FeedbackViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.application.isyara.viewmodel.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.provideAuthRepositoryProvider.get(), singletonCImpl.sessionManagerProvider.get(), singletonCImpl.userPreferencesProvider.get());

          case 1: // com.application.isyara.viewmodel.settings.ChangePasswordViewModel 
          return (T) new ChangePasswordViewModel(singletonCImpl.provideChangePasswordRepositoryProvider.get());

          case 2: // com.application.isyara.viewmodel.dictionary.DictionaryPictureViewModel 
          return (T) new DictionaryPictureViewModel(singletonCImpl.provideDictionaryPictureRepositoryProvider.get());

          case 3: // com.application.isyara.viewmodel.dictionary.DictionaryVideoViewModel 
          return (T) new DictionaryVideoViewModel(singletonCImpl.provideDictionaryRepositoryProvider.get());

          case 4: // com.application.isyara.viewmodel.settings.FeedbackViewModel 
          return (T) new FeedbackViewModel(singletonCImpl.provideFeedbackRepositoryProvider.get());

          case 5: // com.application.isyara.viewmodel.auth.ForgotPasswordViewModel 
          return (T) new ForgotPasswordViewModel(singletonCImpl.providePasswordRepositoryProvider.get());

          case 6: // com.application.isyara.viewmodel.history.HistoryViewModel 
          return (T) new HistoryViewModel(singletonCImpl.provideTranslatedTextRepositoryProvider.get(), singletonCImpl.provideDictionaryRepositoryProvider.get());

          case 7: // com.application.isyara.viewmodel.main.ProfileViewModel 
          return (T) new ProfileViewModel(viewModelCImpl.profileRepository());

          case 8: // com.application.isyara.viewmodel.auth.ResetPasswordViewModel 
          return (T) new ResetPasswordViewModel(singletonCImpl.providePasswordRepositoryProvider.get());

          case 9: // com.application.isyara.viewmodel.translate.TranslateViewModel 
          return (T) new TranslateViewModel(singletonCImpl.provideTranslatedTextRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends MyApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MyApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends MyApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OkHttpClient> provideOkHttpClientMainAPIProvider;

    private Provider<Retrofit> provideRetrofitMainAPIProvider;

    private Provider<ApiService> provideApiServiceMainAPIProvider;

    private Provider<SessionManager> sessionManagerProvider;

    private Provider<UserPreferences> userPreferencesProvider;

    private Provider<AuthRepository> provideAuthRepositoryProvider;

    private Provider<ChangePasswordRepository> provideChangePasswordRepositoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientDictionaryProvider;

    private Provider<Retrofit> provideRetrofitDictionaryProvider;

    private Provider<ApiService> provideApiServiceDictionaryProvider;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<DownloadDictionaryPictureDao> provideDownloadDictionaryPictureDaoProvider;

    private Provider<DictionaryPictureRepository> provideDictionaryPictureRepositoryProvider;

    private Provider<DownloadedDictionaryDao> provideDownloadedDictionaryDaoProvider;

    private Provider<DictionaryRepository> provideDictionaryRepositoryProvider;

    private Provider<FeedbackRepository> provideFeedbackRepositoryProvider;

    private Provider<PasswordRepository> providePasswordRepositoryProvider;

    private Provider<TranslatedTextDao> provideTranslatedTextDaoProvider;

    private Provider<TranslatedTextRepository> provideTranslatedTextRepositoryProvider;

    private Provider<Gson> provideGsonProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideOkHttpClientMainAPIProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.provideRetrofitMainAPIProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 2));
      this.provideApiServiceMainAPIProvider = DoubleCheck.provider(new SwitchingProvider<ApiService>(singletonCImpl, 1));
      this.sessionManagerProvider = DoubleCheck.provider(new SwitchingProvider<SessionManager>(singletonCImpl, 4));
      this.userPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<UserPreferences>(singletonCImpl, 5));
      this.provideAuthRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, 0));
      this.provideChangePasswordRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ChangePasswordRepository>(singletonCImpl, 6));
      this.provideOkHttpClientDictionaryProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 10));
      this.provideRetrofitDictionaryProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 9));
      this.provideApiServiceDictionaryProvider = DoubleCheck.provider(new SwitchingProvider<ApiService>(singletonCImpl, 8));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 12));
      this.provideDownloadDictionaryPictureDaoProvider = DoubleCheck.provider(new SwitchingProvider<DownloadDictionaryPictureDao>(singletonCImpl, 11));
      this.provideDictionaryPictureRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<DictionaryPictureRepository>(singletonCImpl, 7));
      this.provideDownloadedDictionaryDaoProvider = DoubleCheck.provider(new SwitchingProvider<DownloadedDictionaryDao>(singletonCImpl, 14));
      this.provideDictionaryRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<DictionaryRepository>(singletonCImpl, 13));
      this.provideFeedbackRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<FeedbackRepository>(singletonCImpl, 15));
      this.providePasswordRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PasswordRepository>(singletonCImpl, 16));
      this.provideTranslatedTextDaoProvider = DoubleCheck.provider(new SwitchingProvider<TranslatedTextDao>(singletonCImpl, 18));
      this.provideTranslatedTextRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TranslatedTextRepository>(singletonCImpl, 17));
      this.provideGsonProvider = DoubleCheck.provider(new SwitchingProvider<Gson>(singletonCImpl, 19));
    }

    @Override
    public void injectMyApplication(MyApplication myApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.application.isyara.data.repository.AuthRepository 
          return (T) RepositoryModule_ProvideAuthRepositoryFactory.provideAuthRepository(singletonCImpl.provideApiServiceMainAPIProvider.get(), singletonCImpl.sessionManagerProvider.get(), singletonCImpl.userPreferencesProvider.get());

          case 1: // @com.application.isyara.data.di.RetrofitMain com.application.isyara.data.remote.ApiService 
          return (T) NetworkModule_ProvideApiServiceMainAPIFactory.provideApiServiceMainAPI(singletonCImpl.provideRetrofitMainAPIProvider.get());

          case 2: // @com.application.isyara.data.di.RetrofitMain retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitMainAPIFactory.provideRetrofitMainAPI(singletonCImpl.provideOkHttpClientMainAPIProvider.get());

          case 3: // @com.application.isyara.data.di.RetrofitMain okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientMainAPIFactory.provideOkHttpClientMainAPI();

          case 4: // com.application.isyara.data.local.SessionManager 
          return (T) new SessionManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.application.isyara.data.local.UserPreferences 
          return (T) new UserPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.application.isyara.data.repository.ChangePasswordRepository 
          return (T) RepositoryModule_ProvideChangePasswordRepositoryFactory.provideChangePasswordRepository(singletonCImpl.provideApiServiceMainAPIProvider.get());

          case 7: // com.application.isyara.data.repository.DictionaryPictureRepository 
          return (T) DatabaseModule_ProvideDictionaryPictureRepositoryFactory.provideDictionaryPictureRepository(singletonCImpl.provideApiServiceDictionaryProvider.get(), singletonCImpl.provideDownloadDictionaryPictureDaoProvider.get());

          case 8: // @com.application.isyara.data.di.RetrofitDictionary com.application.isyara.data.remote.ApiService 
          return (T) NetworkModule_ProvideApiServiceDictionaryFactory.provideApiServiceDictionary(singletonCImpl.provideRetrofitDictionaryProvider.get());

          case 9: // @com.application.isyara.data.di.RetrofitDictionary retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitDictionaryFactory.provideRetrofitDictionary(singletonCImpl.provideOkHttpClientDictionaryProvider.get());

          case 10: // @com.application.isyara.data.di.RetrofitDictionary okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientDictionaryFactory.provideOkHttpClientDictionary();

          case 11: // com.application.isyara.data.local.DownloadDictionaryPictureDao 
          return (T) DatabaseModule_ProvideDownloadDictionaryPictureDaoFactory.provideDownloadDictionaryPictureDao(singletonCImpl.provideDatabaseProvider.get());

          case 12: // com.application.isyara.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 13: // com.application.isyara.data.repository.DictionaryRepository 
          return (T) DatabaseModule_ProvideDictionaryRepositoryFactory.provideDictionaryRepository(singletonCImpl.provideApiServiceDictionaryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideDownloadedDictionaryDaoProvider.get());

          case 14: // com.application.isyara.data.local.DownloadedDictionaryDao 
          return (T) DatabaseModule_ProvideDownloadedDictionaryDaoFactory.provideDownloadedDictionaryDao(singletonCImpl.provideDatabaseProvider.get());

          case 15: // com.application.isyara.data.repository.FeedbackRepository 
          return (T) RepositoryModule_ProvideFeedbackRepositoryFactory.provideFeedbackRepository(singletonCImpl.provideApiServiceMainAPIProvider.get());

          case 16: // com.application.isyara.data.repository.PasswordRepository 
          return (T) RepositoryModule_ProvidePasswordRepositoryFactory.providePasswordRepository(singletonCImpl.provideApiServiceMainAPIProvider.get());

          case 17: // com.application.isyara.data.repository.TranslatedTextRepository 
          return (T) DatabaseModule_ProvideTranslatedTextRepositoryFactory.provideTranslatedTextRepository(singletonCImpl.provideTranslatedTextDaoProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 18: // com.application.isyara.data.local.TranslatedTextDao 
          return (T) DatabaseModule_ProvideTranslatedTextDaoFactory.provideTranslatedTextDao(singletonCImpl.provideDatabaseProvider.get());

          case 19: // com.google.gson.Gson 
          return (T) RepositoryModule_ProvideGsonFactory.provideGson();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
