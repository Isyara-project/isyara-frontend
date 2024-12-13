package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.model.ErrorResponse
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.UpdateProfileResponse
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val sessionManager: SessionManager,
    private val gson: Gson
) {

    fun getProfile(): Flow<Result<ProfileData>> = flow {
        try {
            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                emit(Result.Error("Token tidak ditemukan."))
                return@flow
            }
            emit(Result.Loading)
            val profileResponse = apiService.getProfile("Bearer $token")
            if (profileResponse.data != null) {
                emit(Result.Success(profileResponse.data))
            } else {
                emit(Result.Error("Gagal mengambil profil pengguna."))
            }
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message()}")
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                try {
                    gson.fromJson(it, ErrorResponse::class.java)
                } catch (ex: Exception) {
                    null
                }
            }
            val errorMessage = errorResponse?.message ?: "Terjadi kesalahan HTTP: ${e.code()}"
            emit(Result.Error(errorMessage))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Kesalahan jaringan: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.localizedMessage}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga: ${e.localizedMessage}"))
        }
    }

    fun updateProfile(
        file: MultipartBody.Part?,
        fullname: String,
        bio: String
    ): Flow<Result<UpdateProfileResponse>> = flow {
        try {
            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                emit(Result.Error("Token tidak ditemukan."))
                return@flow
            }
            emit(Result.Loading)
            val updateResponse = apiService.updateProfile(
                "Bearer $token",
                file,
                fullname.toRequestBody("text/plain".toMediaTypeOrNull()),
                bio.toRequestBody("text/plain".toMediaTypeOrNull())
            )
            emit(Result.Success(updateResponse))
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message()}")
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                try {
                    gson.fromJson(it, ErrorResponse::class.java)
                } catch (ex: Exception) {
                    null
                }
            }
            val errorMessage = errorResponse?.message ?: "Terjadi kesalahan HTTP: ${e.code()}"
            emit(Result.Error(errorMessage))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Kesalahan jaringan: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.localizedMessage}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga: ${e.localizedMessage}"))
        }
    }
}
