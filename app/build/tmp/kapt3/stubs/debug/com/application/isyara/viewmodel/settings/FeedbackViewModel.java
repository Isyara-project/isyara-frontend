package com.application.isyara.viewmodel.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lcom/application/isyara/viewmodel/settings/FeedbackViewModel;", "Landroidx/lifecycle/ViewModel;", "feedbackRepository", "Lcom/application/isyara/data/repository/FeedbackRepository;", "(Lcom/application/isyara/data/repository/FeedbackRepository;)V", "_feedbackHistoriesState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/FeedbackHistoryResponse;", "_feedbackState", "Lcom/application/isyara/data/model/FeedbackResponse;", "_loadingState", "", "feedbackHistoriesState", "Lkotlinx/coroutines/flow/StateFlow;", "getFeedbackHistoriesState", "()Lkotlinx/coroutines/flow/StateFlow;", "feedbackState", "getFeedbackState", "loadingState", "getLoadingState", "getFeedbackHistories", "", "sendFeedback", "feedbackRequest", "Lcom/application/isyara/data/model/FeedbackRequest;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class FeedbackViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.repository.FeedbackRepository feedbackRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackResponse>> _feedbackState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.FeedbackHistoryResponse>> _feedbackHistoriesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _loadingState = null;
    
    @javax.inject.Inject()
    public FeedbackViewModel(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.repository.FeedbackRepository feedbackRepository) {
        super();
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
    
    public final void sendFeedback(@org.jetbrains.annotations.NotNull()
    com.application.isyara.data.model.FeedbackRequest feedbackRequest) {
    }
    
    public final void getFeedbackHistories() {
    }
}