package com.application.isyara.viewmodel.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010-\u001a\u00020.J\u0010\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u000201H\u0002J\u000e\u00102\u001a\u00020.2\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\u00020.2\u0006\u00106\u001a\u000201J\u000e\u00107\u001a\u00020.2\u0006\u00108\u001a\u000209J\u0016\u0010:\u001a\u00020.2\u0006\u0010;\u001a\u00020<2\u0006\u00106\u001a\u000201R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u001cR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001cR\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001cR\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001cR\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u001c\u00a8\u0006="}, d2 = {"Lcom/application/isyara/viewmodel/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/application/isyara/data/repository/AuthRepository;", "(Lcom/application/isyara/data/repository/AuthRepository;)V", "_feedbackHistoriesState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/FeedbackHistoryResponse;", "_feedbackState", "Lcom/application/isyara/data/model/FeedbackResponse;", "_forgotPasswordState", "Lcom/application/isyara/data/model/ForgotPasswordResponse;", "_loadingState", "", "_loginState", "Lcom/application/isyara/data/model/LoginResponse;", "_otpState", "Lcom/application/isyara/data/model/OtpResponse;", "_registerState", "Lcom/application/isyara/data/model/RegisterResponse;", "_resendOtpState", "Lcom/application/isyara/data/model/ResendOtpResponse;", "_resetPasswordState", "Lcom/application/isyara/data/model/ResetPasswordResponse;", "feedbackHistoriesState", "Lkotlinx/coroutines/flow/StateFlow;", "getFeedbackHistoriesState", "()Lkotlinx/coroutines/flow/StateFlow;", "feedbackState", "getFeedbackState", "forgotPasswordState", "getForgotPasswordState", "loadingState", "getLoadingState", "loginState", "getLoginState", "otpState", "getOtpState", "registerState", "getRegisterState", "resendOtpState", "getResendOtpState", "resetPasswordState", "getResetPasswordState", "getFeedbackHistories", "", "isPasswordValid", "password", "", "registerUser", "registerRequest", "Lcom/application/isyara/data/model/RegisterRequest;", "resendOtp", "token", "sendFeedback", "feedbackRequest", "Lcom/application/isyara/data/model/FeedbackRequest;", "verifyOtp", "otpRequest", "Lcom/application/isyara/data/model/OtpRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.RegisterResponse>> _registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.RegisterResponse>> registerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.OtpResponse>> _otpState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.OtpResponse>> otpState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResendOtpResponse>> _resendOtpState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ForgotPasswordResponse>> _forgotPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.LoginResponse>> _loginState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResetPasswordResponse>> _resetPasswordState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackResponse>> _feedbackState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackHistoryResponse>> _feedbackHistoriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _loadingState = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.RegisterResponse>> getRegisterState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.OtpResponse>> getOtpState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResendOtpResponse>> getResendOtpState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ForgotPasswordResponse>> getForgotPasswordState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.LoginResponse>> getLoginState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResetPasswordResponse>> getResetPasswordState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackResponse>> getFeedbackState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackHistoryResponse>> getFeedbackHistoriesState() {
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
    
    public final void sendFeedback(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.FeedbackRequest feedbackRequest) {
    }
    
    public final void getFeedbackHistories() {
    }
    
    private final boolean isPasswordValid(java.lang.String password) {
        return false;
    }
}