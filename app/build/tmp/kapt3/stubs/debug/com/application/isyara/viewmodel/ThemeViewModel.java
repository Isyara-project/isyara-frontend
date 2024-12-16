package com.application.isyara.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/application/isyara/viewmodel/ThemeViewModel;", "Landroidx/lifecycle/ViewModel;", "themeRepository", "Lcom/application/isyara/repository/ThemeRepository;", "(Lcom/application/isyara/repository/ThemeRepository;)V", "_themeState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/utils/settings/AppTheme;", "themeState", "Lkotlinx/coroutines/flow/StateFlow;", "getThemeState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadTheme", "", "updateTheme", "theme", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ThemeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.repository.ThemeRepository themeRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.utils.settings.AppTheme>> _themeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.utils.settings.AppTheme>> themeState = null;
    
    @javax.inject.Inject()
    public ThemeViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.repository.ThemeRepository themeRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.utils.settings.AppTheme>> getThemeState() {
        return null;
    }
    
    private final void loadTheme() {
    }
    
    public final void updateTheme(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.settings.AppTheme theme) {
    }
}