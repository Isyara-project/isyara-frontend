package com.application.isyara.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\f2\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\r0\f2\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010J=\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\r0\f\"\u0004\b\u0000\u0010\u001c2\u001c\u0010\u001d\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001eH\u0002\u00a2\u0006\u0002\u0010 J\"\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/application/isyara/data/repository/AuthRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "sessionManager", "Lcom/application/isyara/utils/auth/ISessionManager;", "userPreferences", "Lcom/application/isyara/utils/auth/IUserPreferences;", "(Lcom/application/isyara/data/remote/ApiService;Lcom/application/isyara/utils/auth/ISessionManager;Lcom/application/isyara/utils/auth/IUserPreferences;)V", "gson", "Lcom/google/gson/Gson;", "getProfile", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/ProfileResponse;", "token", "", "loginUser", "Lcom/application/isyara/data/model/LoginResponse;", "loginRequest", "Lcom/application/isyara/data/model/LoginRequest;", "registerUser", "Lcom/application/isyara/data/model/RegisterResponse;", "registerRequest", "Lcom/application/isyara/data/model/RegisterRequest;", "resendOtp", "Lcom/application/isyara/data/model/ResendOtpResponse;", "safeApiCall", "T", "apiCall", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "verifyOtp", "Lcom/application/isyara/data/model/OtpResponse;", "otpRequest", "Lcom/application/isyara/data/model/OtpRequest;", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.auth.ISessionManager sessionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.auth.IUserPreferences userPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public AuthRepository(@com.application.isyara.data.di.RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.auth.ISessionManager sessionManager, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.auth.IUserPreferences userPreferences) {
        super();
    }
    
    private final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<T>> safeApiCall(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> apiCall) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.RegisterResponse>> registerUser(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.RegisterRequest registerRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.OtpResponse>> verifyOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.OtpRequest otpRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResendOtpResponse>> resendOtp(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.LoginResponse>> loginUser(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.LoginRequest loginRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ProfileResponse>> getProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return null;
    }
}