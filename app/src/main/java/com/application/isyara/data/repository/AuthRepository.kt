package com.application.isyara.data.repository

import com.application.isyara.data.model.*
import com.application.isyara.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.application.isyara.utils.auth.Result
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {

    // Fungsi Register User
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> =
        flow {
            emit(Result.Loading)  // Emit status loading sebelum request
            try {
                val response = apiService.registerUser(registerRequest)
                emit(Result.Success(response))  // Emit response jika berhasil
            } catch (e: Exception) {
                emit(Result.Error(e.message ?: "Unknown Error"))  // Emit error jika gagal
            }
        }

    // Fungsi Verifikasi OTP
    suspend fun verifyOtp(otpRequest: OtpRequest, token: String): Flow<Result<OtpResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.verifyOtp(token, otpRequest)  // Menggunakan token dari header
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }

    // Fungsi untuk login
    suspend fun loginUser(loginRequest: LoginRequest): Flow<Result<LoginResponse>> {
        return flow {
            try {
                val response = apiService.loginUser(loginRequest)
                emit(Result.Success(response)) // Mengirimkan respons login jika sukses
            } catch (e: Exception) {
                emit(Result.Error("Login failed: ${e.message}")) // Menangani error dan mengirimkan error message
            }
        }
    }

    // Fungsi Kirim Ulang OTP
    suspend fun resendOtp(token: String): Flow<Result<ResendOtpResponse>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.resendOtp(token, mapOf())  // Menggunakan token dari header
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown Error"))
        }
    }

    // Fungsi Lupa Kata Sandi
    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Flow<Result<ForgotPasswordResponse>> = flow {
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