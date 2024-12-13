package com.application.isyara.viewmodel.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010&\u001a\u00020\'J\u0016\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*J\u0006\u0010,\u001a\u00020\'J\u000e\u0010-\u001a\u00020\'2\u0006\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020\'2\u0006\u00101\u001a\u00020*J\u0016\u00102\u001a\u00020\'2\u0006\u00101\u001a\u00020*2\u0006\u00103\u001a\u000204R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/application/isyara/viewmodel/auth/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/application/isyara/data/repository/AuthRepository;", "sessionManager", "Lcom/application/isyara/utils/auth/ISessionManager;", "userPreferences", "Lcom/application/isyara/utils/auth/IUserPreferences;", "(Lcom/application/isyara/data/repository/AuthRepository;Lcom/application/isyara/utils/auth/ISessionManager;Lcom/application/isyara/utils/auth/IUserPreferences;)V", "_loginState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/LoginResponse;", "_otpState", "Lcom/application/isyara/data/model/OtpResponse;", "_profileState", "Lcom/application/isyara/data/model/ProfileResponse;", "_registerState", "Lcom/application/isyara/data/model/RegisterResponse;", "_resendOtpState", "Lcom/application/isyara/data/model/ResendOtpResponse;", "lastRegistrationAttempt", "Lkotlinx/coroutines/flow/StateFlow;", "", "getLastRegistrationAttempt", "()Lkotlinx/coroutines/flow/StateFlow;", "loginState", "getLoginState", "otpState", "getOtpState", "profileState", "getProfileState", "registerState", "getRegisterState", "resendOtpState", "getResendOtpState", "getSessionManager$app_debug", "()Lcom/application/isyara/utils/auth/ISessionManager;", "checkUserLoggedIn", "", "login", "identifier", "", "password", "logout", "registerUser", "registerRequest", "Lcom/application/isyara/data/model/RegisterRequest;", "resendOtp", "token", "verifyOtp", "otpRequest", "Lcom/application/isyara/data/model/OtpRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.auth.ISessionManager sessionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.auth.IUserPreferences userPreferences = null;
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
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResendOtpResponse>> resendOtpState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.LoginResponse>> _loginState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.LoginResponse>> loginState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ProfileResponse>> _profileState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ProfileResponse>> profileState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> lastRegistrationAttempt = null;
    
    @javax.inject.Inject()
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.auth.ISessionManager sessionManager, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.auth.IUserPreferences userPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.application.isyara.utils.auth.ISessionManager getSessionManager$app_debug() {
        return null;
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
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.LoginResponse>> getLoginState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ProfileResponse>> getProfileState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getLastRegistrationAttempt() {
        return null;
    }
    
    /**
     * Fungsi untuk mendaftarkan pengguna baru.
     * @param registerRequest Data yang dibutuhkan untuk registrasi.
     */
    public final void registerUser(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.RegisterRequest registerRequest) {
    }
    
    /**
     * Fungsi untuk memverifikasi OTP yang dikirimkan.
     * @param token Token yang diperlukan untuk verifikasi OTP.
     * @param otpRequest Data OTP yang dikirimkan oleh pengguna.
     */
    public final void verifyOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.OtpRequest otpRequest) {
    }
    
    /**
     * Fungsi untuk meminta pengiriman ulang OTP.
     * @param token Token yang diperlukan untuk resend OTP.
     */
    public final void resendOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    /**
     * Fungsi untuk login pengguna.
     * @param identifier Username atau email pengguna.
     * @param password Kata sandi pengguna.
     */
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String identifier, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    /**
     * Fungsi untuk memeriksa apakah pengguna sudah login berdasarkan token yang disimpan.
     */
    public final void checkUserLoggedIn() {
    }
    
    /**
     * Fungsi untuk logout pengguna.
     */
    public final void logout() {
    }
}