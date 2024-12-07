package com.application.isyara.data.remote

import com.application.isyara.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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
        @Body email: String // Mengubah parameter menjadi String (email)
    ): Response<ForgotPasswordResponse>

    // Fungsi Reset Password di repository
    @POST("reset-password/{token}")
    suspend fun resetPassword(
        @Path("token") token: String, // Token dikirim lewat path URL
        @Body resetPasswordRequest: ResetPasswordRequest // Request body berisi password
    ): ResetPasswordResponse

    // Endpoint untuk Kirim Feedback
    @POST("feedback")
    suspend fun sendFeedback(
        @Body feedbackRequest: FeedbackRequest // Request body berisi email dan feedback
    ): FeedbackResponse

    // Endpoint untuk Mendapatkan Riwayat Feedback
    @GET("feedback/histories")
    suspend fun getFeedbackHistories(): FeedbackHistoryResponse
}
