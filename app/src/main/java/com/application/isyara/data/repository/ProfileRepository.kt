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

    /**
     * Mendapatkan profil pengguna.
     *
     * @return [Flow] yang mengemisi [Result] dengan [ProfileData].
     */
    fun getProfile(): Flow<Result<ProfileData>> = flow {
        emit(Result.Loading)
        try {
            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                emit(Result.Error("Token tidak ditemukan."))
                return@flow
            }

            val profileResponse = apiService.getProfile("Bearer $token")
            profileResponse.data?.let {
                emit(Result.Success(it))
            } ?: run {
                emit(Result.Error("Gagal mengambil profil pengguna."))
            }
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message()}")
            val errorMessage = parseError(e)
            emit(Result.Error(errorMessage))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Kesalahan jaringan: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.localizedMessage}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga: ${e.localizedMessage}"))
        }
    }

    /**
     * Memperbarui profil pengguna.
     *
     * @param file File gambar profil (opsional).
     * @param fullname Nama lengkap pengguna.
     * @param bio Biografi pengguna.
     * @return [Flow] yang mengemisi [Result] dengan [UpdateProfileResponse].
     */
    fun updateProfile(
        file: MultipartBody.Part?,
        fullname: String,
        bio: String
    ): Flow<Result<UpdateProfileResponse>> = flow {
        emit(Result.Loading)
        try {
            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                emit(Result.Error("Token tidak ditemukan."))
                return@flow
            }

            val fullnameRequest = fullname.toRequestBody("text/plain".toMediaTypeOrNull())
            val bioRequest = bio.toRequestBody("text/plain".toMediaTypeOrNull())

            val updateResponse = apiService.updateProfile(
                "Bearer $token",
                file,
                fullnameRequest,
                bioRequest
            )
            emit(Result.Success(updateResponse))
        } catch (e: HttpException) {
            Timber.e(e, "HTTP Exception: ${e.message()}")
            val errorMessage = parseError(e)
            emit(Result.Error(errorMessage))
        } catch (e: IOException) {
            Timber.e(e, "IO Exception: ${e.message}")
            emit(Result.Error("Kesalahan jaringan: ${e.localizedMessage}"))
        } catch (e: Exception) {
            Timber.e(e, "Unknown Exception: ${e.localizedMessage}")
            emit(Result.Error("Terjadi kesalahan yang tidak terduga: ${e.localizedMessage}"))
        }
    }

    /**
     * Menguraikan pesan kesalahan dari [HttpException].
     *
     * @param exception [HttpException] yang dilemparkan.
     * @return Pesan kesalahan yang diuraikan.
     */
    private fun parseError(exception: HttpException): String {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                gson.fromJson(it, ErrorResponse::class.java)
            }
            errorResponse?.message ?: "Terjadi kesalahan HTTP: ${exception.code()}"
        } catch (e: Exception) {
            "Terjadi kesalahan saat memproses kesalahan: ${e.localizedMessage}"
        }
    }
}
