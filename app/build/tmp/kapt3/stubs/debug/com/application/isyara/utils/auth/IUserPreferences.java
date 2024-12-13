package com.application.isyara.utils.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H&J\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/application/isyara/utils/auth/IUserPreferences;", "", "clearLastRegistrationAttempt", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastRegistrationAttempt", "Lkotlinx/coroutines/flow/Flow;", "", "setLastRegistrationAttempt", "timeMillis", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface IUserPreferences {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Long> getLastRegistrationAttempt();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setLastRegistrationAttempt(long timeMillis, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearLastRegistrationAttempt(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}