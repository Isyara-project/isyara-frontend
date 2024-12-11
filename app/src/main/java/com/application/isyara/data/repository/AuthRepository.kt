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

class AuthRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> =
        flow {
            try {
                emit(Result.Loading)
                val response = apiService.registerUser(registerRequest)
                emit(Result.Success(response))
            } catch (e: HttpException) {
                emit(Result.Error("Terjadi kesalahan pada server"))
            } catch (e: IOException) {
                emit(Result.Error("Tidak dapat terhubung ke server"))
            }
        }


    suspend fun verifyOtp(token: String, otpRequest: OtpRequest): Flow<Result<OtpResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.verifyOtp(token, otpRequest)
            if (response.message.isNotEmpty()) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error("OTP tidak valid atau sudah kadaluarsa"))
            }
        } catch (e: HttpException) {
            emit(Result.Error("Terjadi kesalahan pada server: ${e.message}"))
        } catch (e: IOException) {
            emit(Result.Error("Tidak dapat terhubung ke server: ${e.message}"))
        } catch (e: Exception) {
            emit(Result.Error("Terjadi kesalahan: ${e.message}"))
        }
    }



    suspend fun loginUser(loginRequest: LoginRequest): Flow<Result<LoginResponse>> {
        return flow {
            emit(Result.Loading)

            try {
                val response: LoginResponse = apiService.loginUser(loginRequest)
                Timber.d("Login Response: ${response.message}")
                val token = response.access_token
                if (token.isEmpty()) {
                    Timber.e("Token is null or empty in response")
                    emit(Result.Error("Login failed: Token is null or empty"))
                } else {
                    Timber.d("Token received: $token")
                    sessionManager.saveToken(token)
                    Timber.d("Token saved successfully, current token: ${sessionManager.getToken()}")
                    emit(Result.Success(response))
                }
            } catch (e: IOException) {
                Timber.e("Network error: ${e.message}")
                emit(Result.Error("Network error: ${e.message}"))
            } catch (e: Exception) {
                Timber.e("Login failed: ${e.message}")
                emit(Result.Error("Login failed: ${e.message}"))
            }
        }
    }

    // Fungsi Kirim Ulang OTP
    suspend fun resendOtp(token: String): Flow<Result<ResendOtpResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.resendOtp(token, mapOf())
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }

    // Fungsi Reset Password
    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest,
        token: String
    ): Flow<Result<ResetPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            val response: ResetPasswordResponse =
                apiService.resetPassword(token, resetPasswordRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("Reset password failed: ${e.message}"))
        }
    }

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
