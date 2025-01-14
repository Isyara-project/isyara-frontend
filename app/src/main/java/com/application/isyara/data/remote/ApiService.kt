package com.application.isyara.data.remote

import com.application.isyara.data.model.ChangePasswordRequest
import com.application.isyara.data.model.ChangePasswordResponse
import com.application.isyara.data.model.EmailRequest
import com.application.isyara.data.model.FeedbackHistoryResponse
import com.application.isyara.data.model.FeedbackRequest
import com.application.isyara.data.model.FeedbackResponse
import com.application.isyara.data.model.ForgotPasswordResponse
import com.application.isyara.data.model.LoginRequest
import com.application.isyara.data.model.LoginResponse
import com.application.isyara.data.model.OtpRequest
import com.application.isyara.data.model.OtpResponse
import com.application.isyara.data.model.ProfileResponse
import com.application.isyara.data.model.RegisterRequest
import com.application.isyara.data.model.RegisterResponse
import com.application.isyara.data.model.ResendOtpResponse
import com.application.isyara.data.model.ResetPasswordRequest
import com.application.isyara.data.model.ResetPasswordResponse
import com.application.isyara.data.model.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {


    @POST("register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): RegisterResponse


    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): LoginResponse


    @POST("/verification-otp/{token}")
    suspend fun verifyOtp(
        @Path("token") token: String,
        @Body request: OtpRequest
    ): OtpResponse


    @POST("resend-otp/{token}")
    suspend fun resendOtp(
        @Path("token") token: String
    ): ResendOtpResponse


    @POST("forgot-password")
    suspend fun forgotPassword(
        @Body emailRequest: EmailRequest
    ): ForgotPasswordResponse


    @POST("reset-password/{token}")
    suspend fun resetPassword(
        @Path("token") token: String,
        @Body resetPasswordRequest: ResetPasswordRequest
    ): ResetPasswordResponse


    @PATCH("change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body changePasswordRequest: ChangePasswordRequest
    ): ChangePasswordResponse


    @POST("feedback")
    suspend fun sendFeedback(
        @Body feedbackRequest: FeedbackRequest
    ): FeedbackResponse


    @GET("feedback/histories")
    suspend fun getFeedbackHistories(): FeedbackHistoryResponse


    @GET("get-profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse


    @Multipart
    @POST("edit-profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part?
    ): UpdateProfileResponse


    @GET("isyara_kamus_bucket/file-list.json")
    suspend fun getDictionaryVideo(): List<String>

    @GET("isyara_kamus_bucket/kamusGambar/kamusGambar.json")
    suspend fun getDictionaryPicture(): List<String>

}
