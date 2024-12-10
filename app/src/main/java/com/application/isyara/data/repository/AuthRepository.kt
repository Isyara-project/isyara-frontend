package com.application.isyara.data.repository

import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.model.ForgotPasswordRequest
import com.application.isyara.data.model.ForgotPasswordResponse
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
import com.application.isyara.utils.auth.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    // Fungsi Register User
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> =
        flow {
            emit(Result.Loading)
            try {
                val response = apiService.registerUser(registerRequest)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown Error"))
            }
        }

    // Fungsi Verifikasi OTP
    suspend fun verifyOtp(otpRequest: OtpRequest, token: String): Flow<Result<OtpResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.verifyOtp(token, otpRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
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

    // Fungsi Lupa Kata Sandi
    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Flow<Result<ForgotPasswordResponse>> =
        flow {
            emit(Result.Loading)
            try {
                // Mengambil email dari ForgotPasswordRequest dan mengirimkan sebagai String
                val response: Response<ForgotPasswordResponse> =
                    apiService.forgotPassword(forgotPasswordRequest.email) // Menggunakan email langsung
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(it))
                    } ?: emit(Result.Error("Empty response body"))
                } else {
                    val errorResponse = response.errorBody()?.string() ?: "Unknown Error"
                    emit(Result.Error("Forgot password failed: $errorResponse"))
                }
            } catch (e: Exception) {
                emit(Result.Error("Forgot password failed: ${e.message}"))
            }
        }

    // Fungsi Reset Password
    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest,
        token: String
    ): Flow<Result<ResetPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            // Memanggil API untuk reset password, mengirim token lewat URL path
            val response: ResetPasswordResponse =
                apiService.resetPassword(token, resetPasswordRequest)
            // Mengirim hasil jika berhasil
            emit(Result.Success(response))
        } catch (e: Exception) {
            // Menangani kesalahan jika ada
            emit(Result.Error("Reset password failed: ${e.message}"))
        }
    }

    // Fungsi Kirim Feedback
    suspend fun sendFeedback(feedbackRequest: FeedbackRequest): Flow<Result<FeedbackResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.sendFeedback(feedbackRequest)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }

    // Fungsi Mendapatkan Riwayat Feedback
    suspend fun getFeedbackHistories(): Flow<Result<FeedbackHistoryResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getFeedbackHistories()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }


    Fungsi untuk mengubah password
    suspend fun changePassword(oldPass: String, newPass: String): Flow<Result<ChangePasswordResponse>> {
        return flow {
            try {
                val token = sessionManager.getToken()
                if (token.isNullOrEmpty()) {
                    emit(Result.Error("No token found. Please log in again."))
                    return@flow
                }

                val response = apiService.changePassword(
                    token = "Bearer $token",
                    changePasswordRequest = ChangePasswordRequest(oldPass, newPass)
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(it))
                    } ?: emit(Result.Error("Empty response from server."))
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    emit(Result.Error("Failed to change password: $errorMessage"))
                }
            } catch (e: HttpException) {
                emit(Result.Error("HTTP error: ${e.message}"))
            } catch (e: Exception) {
                emit(Result.Error("Unexpected error: ${e.message}"))
            }
        }
    }
}
