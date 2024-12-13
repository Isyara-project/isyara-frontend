package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.model.*
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.auth.ApiType
import com.application.isyara.utils.auth.ISessionManager
import com.application.isyara.utils.auth.IUserPreferences
import com.application.isyara.utils.state.Result
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AuthRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val sessionManager: ISessionManager,
    private val userPreferences: IUserPreferences
) {
    private val gson = Gson()

    private fun <T> safeApiCall(
        apiCall: suspend () -> T,
        apiType: ApiType = ApiType.DEFAULT
    ): Flow<Result<T>> = flow {
        emit(Result.Loading)
        try {
            val response = apiCall()
            emit(Result.Success(response))
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message}")
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = try {
                gson.fromJson(errorBody, ErrorResponse::class.java)
            } catch (ex: Exception) {
                null
            }
            val errorMessage = when (e.code()) {
                400 -> {
                    if (errorResponse?.message?.contains("OTP salah", ignoreCase = true) == true) {
                        "Kode OTP salah."
                    } else {
                        "Permintaan tidak valid."
                    }
                }

                409 -> {
                    if (errorResponse?.message?.contains(
                            "User already exists",
                            ignoreCase = true
                        ) == true
                    ) {
                        "Email atau username sudah terdaftar."
                    } else {
                        "Terjadi konflik saat memproses permintaan."
                    }
                }

                401 -> {
                    when (apiType) {
                        ApiType.LOGIN -> "Periksa kembali email dan password."
                        else -> "Token tidak valid atau sudah kadaluarsa."
                    }
                }

                else -> "Terjadi kesalahan, silahkan coba lagi!"
            }
            emit(Result.Error(errorMessage))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Tidak dapat terhubung ke server. Periksa koneksi internet Anda."))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.message}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga."))
        }
    }


    fun registerUser(registerRequest: RegisterRequest): Flow<Result<RegisterResponse>> =
        safeApiCall(
            apiCall = { apiService.registerUser(registerRequest) },
            apiType = ApiType.REGISTER
        )
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        userPreferences.setLastRegistrationAttempt(System.currentTimeMillis())
                        result
                    }

                    is Result.Error -> {
                        userPreferences.setLastRegistrationAttempt(System.currentTimeMillis())
                        result
                    }

                    else -> result
                }
            }

    fun verifyOtp(token: String, otpRequest: OtpRequest): Flow<Result<OtpResponse>> =
        safeApiCall(
            apiCall = {
                Timber.d("Verifying OTP with token: $token and otp: ${otpRequest.otp}")
                apiService.verifyOtp(token, otpRequest)
            },
            apiType = ApiType.VERIFY_OTP
        )

    fun resendOtp(token: String): Flow<Result<ResendOtpResponse>> =
        safeApiCall(
            apiCall = {
                Timber.d("Resend OTP called with token: $token")
                apiService.resendOtp(token)
            },
            apiType = ApiType.VERIFY_OTP
        )


    fun loginUser(loginRequest: LoginRequest): Flow<Result<LoginResponse>> =
        safeApiCall(
            apiCall = { apiService.loginUser(loginRequest) },
            apiType = ApiType.LOGIN
        )
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val token = result.data.access_token
                        if (token.isBlank()) {
                            Timber.e("Token is null atau kosong dalam response")
                            Result.Error("Login gagal: Token tidak tersedia.")
                        } else {
                            Timber.d("Token diterima: $token")
                            sessionManager.saveToken(token)
                            Timber.d("Token disimpan berhasil, token saat ini: ${sessionManager.getToken()}")
                            Result.Success(result.data)
                        }
                    }

                    is Result.Error -> result
                    is Result.Loading -> result
                    is Result.Idle -> result
                }
            }


    fun getProfile(token: String): Flow<Result<ProfileResponse>> =
        safeApiCall(
            apiCall = { apiService.getProfile("Bearer $token") },
            apiType = ApiType.DEFAULT
        )
}
