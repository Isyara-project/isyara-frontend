package com.application.isyara.data.repository

import android.content.Context
import android.net.Uri
import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.ProfileUpdateResponse
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    fun getProfile(): Flow<Result<ProfileData>> = flow {
        try {
            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                emit(Result.Error("No token found"))
                return@flow
            }
            emit(Result.Loading)
            val profileResponse = apiService.getProfile("Bearer $token")
            if (profileResponse.data != null) {
                emit(Result.Success(profileResponse.data))
            } else {
                emit(Result.Error("Failed to retrieve profile"))
            }
        } catch (e: Exception) {
            println("Error occurred: ${e.localizedMessage}")
            emit(Result.Error("An error occurred: ${e.localizedMessage}"))
        }
    }

    suspend fun updateProfile(
        context: Context,
        fileUri: Uri?,
        fullname: String,
        bio: String
    ): Flow<Result<ProfileUpdateResponse>> = flow {
        try {
            val token = sessionManager.getToken()
            if (token.isNullOrEmpty()) {
                emit(Result.Error("No token found"))
                return@flow
            }
            emit(Result.Loading)

            val filePart = fileUri?.let { uri ->
                val file = getFileFromUri(context, uri)
                file?.let {
                    val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("file", it.name, requestFile)
                }
            }

            val fullnameRequestBody = fullname.toRequestBody("text/plain".toMediaTypeOrNull())
            val bioRequestBody = bio.toRequestBody("text/plain".toMediaTypeOrNull())

            // Mengirim permintaan update ke API
            val response = apiService.updateProfile(
                "Bearer $token",
                filePart,
                fullnameRequestBody,
                bioRequestBody
            )

            emit(Result.Success(response))
        } catch (e: Exception) {
            println("Error occurred: ${e.localizedMessage}")
            emit(Result.Error("An error occurred: ${e.localizedMessage}"))
        }
    }

    // Fungsi untuk mendapatkan file dari Uri
    fun getFileFromUri(context: Context, uri: Uri): File? {
        val contentResolver = context.contentResolver
        val file = File(context.cacheDir, "profile_picture.jpg")

        // Pastikan menggunakan InputStream untuk mendapatkan data dari Uri
        contentResolver.openInputStream(uri)?.use { inputStream ->
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
        }

        return if (file.exists()) file else null
    }

}
