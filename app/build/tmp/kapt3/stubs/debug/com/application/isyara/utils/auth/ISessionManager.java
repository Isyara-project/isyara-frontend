package com.application.isyara.utils.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0007\u001a\u00020\bH&J\n\u0010\t\u001a\u0004\u0018\u00010\u0004H&J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H&R\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\f"}, d2 = {"Lcom/application/isyara/utils/auth/ISessionManager;", "", "tokenFlow", "Lkotlinx/coroutines/flow/StateFlow;", "", "getTokenFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "clearToken", "", "getToken", "saveToken", "token", "app_debug"})
public abstract interface ISessionManager {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<java.lang.String> getTokenFlow();
    
    public abstract void saveToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getToken();
    
    public abstract void clearToken();
}