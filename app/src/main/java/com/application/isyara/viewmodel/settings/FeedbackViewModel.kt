package com.application.isyara.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.FeedbackHistoryResponse
import com.application.isyara.data.model.FeedbackRequest
import com.application.isyara.data.model.FeedbackResponse
import com.application.isyara.data.repository.FeedbackRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(private val feedbackRepository: FeedbackRepository) :
    ViewModel() {


    private val _feedbackState = MutableStateFlow<Result<FeedbackResponse>>(Result.Loading)
    val feedbackState: StateFlow<Result<FeedbackResponse>> get() = _feedbackState

    private val _feedbackHistoriesState =
        MutableStateFlow<Result<FeedbackHistoryResponse>>(Result.Loading)
    val feedbackHistoriesState: StateFlow<Result<FeedbackHistoryResponse>> get() = _feedbackHistoriesState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState


    fun sendFeedback(feedbackRequest: FeedbackRequest) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                feedbackRepository.sendFeedback(feedbackRequest).collect { result ->
                    _feedbackState.value = result
                }
            } catch (e: Exception) {
                _feedbackState.value = Result.Error("Sending feedback failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    fun getFeedbackHistories() {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                feedbackRepository.getFeedbackHistories().collect { result ->
                    _feedbackHistoriesState.value = result
                }
            } catch (e: Exception) {
                _feedbackHistoriesState.value =
                    Result.Error("Fetching feedback histories failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

}