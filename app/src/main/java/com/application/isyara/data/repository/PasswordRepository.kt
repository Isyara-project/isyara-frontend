package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.model.EmailRequest
import com.application.isyara.data.model.ForgotPasswordResponse
import com.application.isyara.data.model.ResetPasswordRequest
import com.application.isyara.data.model.ResetPasswordResponse
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PasswordRepository @Inject constructor(@RetrofitMain private val apiService: ApiService) {

    suspend fun forgotPassword(email: String): Flow<Result<ForgotPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            val emailRequest = EmailRequest(email)
            val response = apiService.forgotPassword(emailRequest)

            if (response.message == "OTP sent to email") {
                emit(Result.Success(response))
            } else {
                emit(Result.Error("Failed: ${response.message}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Forgot password failed: ${e.message}"))
        }
    }

    suspend fun resetPassword(
        token: String,
        password: String
    ): Flow<Result<ResetPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            val resetPasswordRequest = ResetPasswordRequest(password)
            val response = apiService.resetPassword(token, resetPasswordRequest)

            if (response.message == "Password reset successfully.") {
                emit(Result.Success(response))
            } else {
                emit(Result.Error("Failed: ${response.message}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("Reset password failed: ${e.message}"))
        }
    }
}

