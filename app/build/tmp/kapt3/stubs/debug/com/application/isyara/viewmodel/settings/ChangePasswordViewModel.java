package com.application.isyara.viewmodel.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/application/isyara/viewmodel/settings/ChangePasswordViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/application/isyara/data/repository/ChangePasswordRepository;", "(Lcom/application/isyara/data/repository/ChangePasswordRepository;)V", "_changePasswordState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/ChangePasswordResponse;", "_loadingState", "", "changePasswordState", "Lkotlinx/coroutines/flow/StateFlow;", "getChangePasswordState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadingState", "getLoadingState", "changePassword", "", "oldPassword", "", "newPassword", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ChangePasswordViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.ChangePasswordRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ChangePasswordResponse>> _changePasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ChangePasswordResponse>> changePasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _loadingState = null;
    
    @javax.inject.Inject()
    public ChangePasswordViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.ChangePasswordRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ChangePasswordResponse>> getChangePasswordState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getLoadingState() {
        return null;
    }
    
    public final void changePassword(@org.jetbrains.annotations.NotNull()
    java.lang.String oldPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
    }
}