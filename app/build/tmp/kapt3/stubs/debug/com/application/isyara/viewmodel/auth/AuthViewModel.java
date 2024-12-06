package com.application.isyara.viewmodel.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&J\u0016\u0010\'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)2\u0006\u0010%\u001a\u00020&R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0014R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0014\u00a8\u0006*"}, d2 = {"Lcom/application/isyara/viewmodel/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/application/isyara/data/repository/AuthRepository;", "(Lcom/application/isyara/data/repository/AuthRepository;)V", "_forgotPasswordState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/auth/Result;", "Lcom/application/isyara/data/model/ForgotPasswordResponse;", "_loadingState", "", "_otpState", "Lcom/application/isyara/data/model/OtpResponse;", "_registerState", "Lcom/application/isyara/data/model/RegisterResponse;", "_resendOtpState", "Lcom/application/isyara/data/model/ResendOtpResponse;", "forgotPasswordState", "Lkotlinx/coroutines/flow/StateFlow;", "getForgotPasswordState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadingState", "getLoadingState", "otpState", "getOtpState", "registerState", "getRegisterState", "resendOtpState", "getResendOtpState", "forgotPassword", "", "forgotPasswordRequest", "Lcom/application/isyara/data/model/ForgotPasswordRequest;", "registerUser", "registerRequest", "Lcom/application/isyara/data/model/RegisterRequest;", "resendOtp", "token", "", "verifyOtp", "otpRequest", "Lcom/application/isyara/data/model/OtpRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.RegisterResponse>> _registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.OtpResponse>> _otpState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.ResendOtpResponse>> _resendOtpState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.ForgotPasswordResponse>> _forgotPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _loadingState = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.RegisterResponse>> getRegisterState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.OtpResponse>> getOtpState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.ResendOtpResponse>> getResendOtpState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.auth.Result<com.application.isyara.data.model.ForgotPasswordResponse>> getForgotPasswordState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getLoadingState() {
        return null;
    }
    
    public final void registerUser(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.RegisterRequest registerRequest) {
    }
    
    public final void verifyOtp(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.OtpRequest otpRequest, @org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void resendOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void forgotPassword(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.ForgotPasswordRequest forgotPasswordRequest) {
    }
}