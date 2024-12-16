package com.application.isyara.data.repository

import com.application.isyara.data.di.RetrofitMain
import com.application.isyara.data.model.ChangePasswordRequest
import com.application.isyara.data.preferences.SessionManager
import com.application.isyara.data.remote.ApiService
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChangePasswordRepository @Inject constructor(
    @RetrofitMain private val apiService: ApiService,
    private val sessionManager: SessionManager
) {

    suspend fun changePassword(oldPassword: String, newPassword: String) = flow {
        emit(Result.Loading)

        val token = sessionManager.getToken()
        if (token.isNullOrEmpty()) {
            emit(Result.Error("No token found. Please log in again."))
            return@flow
        }

        if (oldPassword.isEmpty()) {
            emit(Result.Error("Password lama harus diisi."))
            return@flow
        }

        if (!validateNewPassword(newPassword)) {
            emit(Result.Error("Password baru harus antara 8-16 karakter, mengandung 1 huruf besar, 1 angka, dan 1 karakter spesial."))
            return@flow
        }

        try {
            val response = apiService.changePassword(
                token = "Bearer $token",
                changePasswordRequest = ChangePasswordRequest(oldPassword, newPassword)
            )
            emit(Result.Success(response))
        } catch (e: HttpException) {
            emit(Result.Error("Terjadi kesalahan: ${e.message()}", e.code()))
        } catch (e: IOException) {
            emit(Result.Error("Koneksi gagal. Cek jaringan Anda."))
        }
    }

    private fun validateNewPassword(newPassword: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,16}$".toRegex()
        return newPassword.matches(passwordPattern)
    }
}
