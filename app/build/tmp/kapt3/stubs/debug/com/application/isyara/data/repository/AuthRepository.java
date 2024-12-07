package com.application.isyara.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\"\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\t0\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\u001cJ*\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\t0\b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/application/isyara/data/repository/AuthRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "sessionManager", "Lcom/application/isyara/data/local/SessionManager;", "(Lcom/application/isyara/data/remote/ApiService;Lcom/application/isyara/data/local/SessionManager;)V", "forgotPassword", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/auth/Result;", "Lcom/application/isyara/data/model/ForgotPasswordResponse;", "forgotPasswordRequest", "Lcom/application/isyara/data/model/ForgotPasswordRequest;", "(Lcom/application/isyara/data/model/ForgotPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginUser", "Lcom/application/isyara/data/model/LoginResponse;", "loginRequest", "Lcom/application/isyara/data/model/LoginRequest;", "(Lcom/application/isyara/data/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerUser", "Lcom/application/isyara/data/model/RegisterResponse;", "registerRequest", "Lcom/application/isyara/data/model/RegisterRequest;", "(Lcom/application/isyara/data/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resendOtp", "Lcom/application/isyara/data/model/ResendOtpResponse;", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "Lcom/application/isyara/data/model/OtpResponse;", "otpRequest", "Lcom/application/isyara/data/model/OtpRequest;", "(Lcom/application/isyara/data/model/OtpRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.local.SessionManager sessionManager = null;
    
    @javax.inject.Inject()
    public AuthRepository(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.SessionManager sessionManager) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registerUser(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.RegisterRequest registerRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.auth.Result<com.application.isyara.data.model.RegisterResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object verifyOtp(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.OtpRequest otpRequest, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.auth.Result<com.application.isyara.data.model.OtpResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loginUser(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.LoginRequest loginRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.auth.Result<com.application.isyara.data.model.LoginResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resendOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.auth.Result<com.application.isyara.data.model.ResendOtpResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object forgotPassword(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.ForgotPasswordRequest forgotPasswordRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.auth.Result<com.application.isyara.data.model.ForgotPasswordResponse>>> $completion) {
        return null;
    }
}