package com.application.isyara.data.repository

import com.application.isyara.data.model.*
import com.application.isyara.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.application.isyara.utils.auth.Result
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {

    // Fungsi Register User
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> = flow {
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

    // Fungsi untuk Login
    suspend fun loginUser(loginRequest: LoginRequest): Flow<Result<LoginResponse>> = flow {
        emit(Result.Loading)
        try {
            val response: Response<LoginResponse> = apiService.loginUser(loginRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Error("Empty response body"))
            } else {
                val errorResponse = response.errorBody()?.string() ?: "Unknown Error"
                emit(Result.Error("Login failed: $errorResponse"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Login failed: ${e.message}"))
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
    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Flow<Result<ForgotPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            val response: Response<ForgotPasswordResponse> = apiService.forgotPassword(forgotPasswordRequest)
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
    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest, token: String): Flow<Result<ResetPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            // Memanggil API untuk reset password, mengirim token lewat header
            val response: ResetPasswordResponse = apiService.resetPassword("Bearer $token", resetPasswordRequest)
            // Mengirim hasil jika berhasil
            emit(Result.Success(response))
        } catch (e: Exception) {
            // Menangani kesalahan jika ada
            emit(Result.Error("Reset password failed: ${e.message}"))
        }
    }

}
