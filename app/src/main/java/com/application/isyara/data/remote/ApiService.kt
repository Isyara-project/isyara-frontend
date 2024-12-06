package com.application.isyara.data.remote

import com.application.isyara.data.model.*
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
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse> // Menggunakan Response untuk error handling

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