package com.application.isyara.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J.\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0013\u001a\u00020\u00142\u0014\b\u0001\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u001a\u001a\u00020\u001bH\u00a7@\u00a2\u0006\u0002\u0010\u001c\u00a8\u0006\u001d"}, d2 = {"Lcom/application/isyara/data/remote/ApiService;", "", "forgotPassword", "Lcom/application/isyara/data/model/ForgotPasswordResponse;", "emailRequest", "Lcom/application/isyara/data/model/ForgotPasswordRequest;", "(Lcom/application/isyara/data/model/ForgotPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginUser", "Lcom/application/isyara/data/model/LoginResponse;", "loginRequest", "Lcom/application/isyara/data/model/LoginRequest;", "(Lcom/application/isyara/data/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerUser", "Lcom/application/isyara/data/model/RegisterResponse;", "registerRequest", "Lcom/application/isyara/data/model/RegisterRequest;", "(Lcom/application/isyara/data/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resendOtp", "Lcom/application/isyara/data/model/ResendOtpResponse;", "token", "", "resendOtpRequest", "", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "Lcom/application/isyara/data/model/OtpResponse;", "otpRequest", "Lcom/application/isyara/data/model/OtpRequest;", "(Ljava/lang/String;Lcom/application/isyara/data/model/OtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registerUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.RegisterRequest registerRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.model.RegisterResponse> $completion);
    
    @retrofit2.http.POST(value = "login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object loginUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.LoginRequest loginRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.model.LoginResponse> $completion);
    
    @retrofit2.http.POST(value = "verification-otp")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyOtp(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.OtpRequest otpRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.model.OtpResponse> $completion);
    
    @retrofit2.http.POST(value = "resend-otp")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resendOtp(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> resendOtpRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.model.ResendOtpResponse> $completion);
    
    @retrofit2.http.POST(value = "forgot-password")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object forgotPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.ForgotPasswordRequest emailRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.application.isyara.data.model.ForgotPasswordResponse> $completion);
}