package com.application.isyara.data.remote

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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    // Endpoint untuk Register
    @POST("register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): RegisterResponse

    // Endpoint untuk Login
    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse

    // Endpoint untuk Verifikasi OTP
    @POST("verification-otp")
    suspend fun verifyOtp(
        @Header("Authorization") token: String, // Menggunakan header Authorization untuk token
        @Body otpRequest: OtpRequest
    ): OtpResponse

    // Endpoint untuk Kirim Ulang OTP
    @POST("resend-otp")
    suspend fun resendOtp(
        @Header("Authorization") token: String, // Menggunakan header Authorization untuk token
        @Body resendOtpRequest: Map<String, String>
    ): ResendOtpResponse

    // Endpoint untuk Lupa Password
    @POST("forgot-password")
    suspend fun forgotPassword(
        @Body emailRequest: ForgotPasswordRequest
    ): Response<ForgotPasswordResponse> // Menggunakan Response untuk error handling dan konsistensi



    // Fungsi Reset Password di repository
    @POST("reset-password")
    suspend fun resetPassword(
        @Header("Authorization") token: String, // Token dikirim lewat header
        @Body resetPasswordRequest: ResetPasswordRequest // Request body berisi password
    ): ResetPasswordResponse

}