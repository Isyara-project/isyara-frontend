package com.application.isyara.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\"\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u00062\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/application/isyara/data/repository/PasswordRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "(Lcom/application/isyara/data/remote/ApiService;)V", "forgotPassword", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/ForgotPasswordResponse;", "email", "", "parseErrorMessage", "errorBody", "Lokhttp3/ResponseBody;", "resetPassword", "Lcom/application/isyara/data/model/ResetPasswordResponse;", "token", "password", "app_debug"})
public final class PasswordRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    
    @javax.inject.Inject()
    public PasswordRepository(@com.application.isyara.data.di.RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ForgotPasswordResponse>> forgotPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ResetPasswordResponse>> resetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    private final java.lang.String parseErrorMessage(okhttp3.ResponseBody errorBody) {
        return null;
    }
}