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
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class PasswordRepository @Inject constructor(@RetrofitMain private val apiService: ApiService) {

    fun forgotPassword(email: String): Flow<Result<ForgotPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            val emailRequest = EmailRequest(email)
            Timber.d("Sending forgot password request for email: $email")
            val response = apiService.forgotPassword(emailRequest)
            Timber.d("Response received: $response")

            if (response.token?.isNotEmpty() == true) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error("Failed: ${response.message}"))
            }
        } catch (e: HttpException) {
            Timber.e("Error in forgotPassword: HTTP ${e.code()} - ${e.message()}")
            when (e.code()) {
                500 -> emit(Result.Error("Email belum terdaftar", errorCode = 500))
                400 -> {
                    val errorMessage = parseErrorMessage(e.response()?.errorBody())
                    emit(Result.Error(errorMessage ?: "Bad request", errorCode = 400))
                }

                else -> emit(
                    Result.Error(
                        "Forgot password failed: ${e.message()}",
                        errorCode = e.code()
                    )
                )
            }
        } catch (e: Exception) {
            Timber.e("Error in forgotPassword: ${e.localizedMessage}")
            emit(Result.Error("Forgot password failed: ${e.localizedMessage}"))
        }
    }

    fun resetPassword(token: String, password: String): Flow<Result<ResetPasswordResponse>> = flow {
        emit(Result.Loading)
        try {
            val resetPasswordRequest = ResetPasswordRequest(password)
            Timber.d("Sending reset password request with token: $token")
            val response = apiService.resetPassword(token, resetPasswordRequest)
            Timber.d("Response received: ${response.message}")
            Timber.d("ResetPasswordResponse.message: '${response.message}'")

            if (response.message.contains("Reset password succesfuly", ignoreCase = true)) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error("Failed to reset password: ${response.message}"))
            }
        } catch (e: Exception) {
            Timber.e("Error in resetPassword: ${e.localizedMessage}")
            emit(Result.Error("Reset password failed: ${e.localizedMessage}"))
        }
    }

    private fun parseErrorMessage(errorBody: ResponseBody?): String? {
        return try {
            val json = errorBody?.string()
            if (!json.isNullOrEmpty()) {
                val jsonObject = JSONObject(json)
                jsonObject.getString("message")
            } else {
                null
            }
        } catch (e: Exception) {
            Timber.e("Error parsing error message: ${e.localizedMessage}")
            null
        }
    }
}
