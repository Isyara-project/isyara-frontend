package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.preferences.SessionManager
import com.application.isyara.data.model.ErrorResponse
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.UpdateProfileResponse
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val sessionManager: SessionManager,
    private val gson: Gson
) {

    private fun getAuthorizationHeader(): String? {
        val token = sessionManager.getToken()
        return if (!token.isNullOrEmpty()) "Bearer $token" else null
    }

    fun getProfile(): Flow<Result<ProfileData>> = flow {
        emit(Result.Loading)
        try {
            val authHeader = getAuthorizationHeader()
            if (authHeader == null) {
                emit(Result.Error("Token tidak ditemukan.", null))
                return@flow
            }

            val profileResponse = apiService.getProfile(authHeader)
            profileResponse.data?.let {
                emit(Result.Success(it))
            } ?: run {
                emit(Result.Error("Gagal mengambil profil pengguna.", null))
            }
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message()}")
            val errorMessage = parseError(e)
            emit(Result.Error(errorMessage, e.code()))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Kesalahan jaringan: ${e.localizedMessage}", null))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.localizedMessage}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga: ${e.localizedMessage}", null))
        }
    }

    fun updateProfile(
        file: MultipartBody.Part?
    ): Flow<Result<UpdateProfileResponse>> = flow {
        emit(Result.Loading)
        try {
            val authHeader = getAuthorizationHeader()
            if (authHeader == null) {
                emit(Result.Error("Token tidak ditemukan.", null))
                return@flow
            }

            val updateResponse = apiService.updateProfile(
                authHeader,
                file
            )
            emit(Result.Success(updateResponse))
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message()}")
            val errorMessage = parseError(e)
            emit(Result.Error(errorMessage, e.code()))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Kesalahan jaringan: ${e.localizedMessage}", null))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.localizedMessage}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga: ${e.localizedMessage}", null))
        }
    }


    private fun parseError(exception: HttpException): String {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                gson.fromJson(it, ErrorResponse::class.java)
            }
            if (exception.code() == 401) {
                sessionManager.clearToken()
            }
            errorResponse?.message ?: "Terjadi kesalahan HTTP: ${exception.code()}"
        } catch (e: Exception) {
            "Terjadi kesalahan saat memproses kesalahan: ${e.localizedMessage}"
        }
    }

}
