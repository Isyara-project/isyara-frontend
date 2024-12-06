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
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.auth.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
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
            emit(Result.Loading)  // Emit status loading sebelum request
            try {
                // Memanggil endpoint API untuk lupa kata sandi
                val response = apiService.forgotPassword(forgotPasswordRequest)
                emit(Result.Success(response))  // Emit response jika berhasil
            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Forgot password failed"))  // Emit error jika gagal
            }
        }
}
