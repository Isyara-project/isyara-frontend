package com.application.isyara.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0086@\u00a2\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/application/isyara/data/repository/FeedbackRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "(Lcom/application/isyara/data/remote/ApiService;)V", "getFeedbackHistories", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/FeedbackHistoryResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendFeedback", "Lcom/application/isyara/data/model/FeedbackResponse;", "feedbackRequest", "Lcom/application/isyara/data/model/FeedbackRequest;", "(Lcom/application/isyara/data/model/FeedbackRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class FeedbackRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    
    @javax.inject.Inject()
    public FeedbackRepository(@com.application.isyara.data.di.RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendFeedback(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.FeedbackRequest feedbackRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFeedbackHistories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackHistoryResponse>>> $completion) {
        return null;
    }
}