package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.model.FeedbackHistoryResponse
import com.application.isyara.data.model.FeedbackRequest
import com.application.isyara.data.model.FeedbackResponse
import com.application.isyara.data.model.LoginRequest
import com.application.isyara.data.model.LoginResponse
import com.application.isyara.data.model.OtpRequest
import com.application.isyara.data.model.OtpResponse
import com.application.isyara.data.model.RegisterRequest
import com.application.isyara.data.model.RegisterResponse
import com.application.isyara.data.model.ResendOtpResponse
import com.application.isyara.data.model.ResetPasswordRequest
import com.application.isyara.data.model.ResetPasswordResponse
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
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
