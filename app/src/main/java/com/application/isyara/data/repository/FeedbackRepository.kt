package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.model.FeedbackHistoryResponse
import com.application.isyara.data.model.FeedbackRequest
import com.application.isyara.data.model.FeedbackResponse
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeedbackRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService
) {

    suspend fun sendFeedback(feedbackRequest: FeedbackRequest): Flow<Result<FeedbackResponse>> =
        flow {
            emit(Result.Loading)
            try {
                val response = apiService.sendFeedback(feedbackRequest)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown Error"))
            }
        }

    suspend fun getFeedbackHistories(): Flow<Result<FeedbackHistoryResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getFeedbackHistories()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }
}
