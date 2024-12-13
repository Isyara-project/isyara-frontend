package com.application.isyara.viewmodel.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0012R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/application/isyara/viewmodel/auth/ForgotPasswordViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/application/isyara/data/repository/PasswordRepository;", "(Lcom/application/isyara/data/repository/PasswordRepository;)V", "_emailError", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_forgotPasswordState", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/ForgotPasswordResponse;", "emailError", "Lkotlinx/coroutines/flow/StateFlow;", "getEmailError", "()Lkotlinx/coroutines/flow/StateFlow;", "forgotPasswordState", "getForgotPasswordState", "forgotPassword", "", "email", "processForgotPasswordResult", "resetState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ForgotPasswordViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.PasswordRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ForgotPasswordResponse>> _forgotPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ForgotPasswordResponse>> forgotPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _emailError = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> emailError = null;
    
    @javax.inject.Inject()
    public ForgotPasswordViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.PasswordRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ForgotPasswordResponse>> getForgotPasswordState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getEmailError() {
        return null;
    }
    
    public final void forgotPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    /**
     * Fungsi untuk memproses hasil forgot password dan menetapkan error email
     */
    public final void processForgotPasswordResult() {
    }
    
    public final void resetState() {
    }
}