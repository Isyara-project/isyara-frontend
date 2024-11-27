package com.application.isyara.data.repository

import com.application.isyara.data.model.LoginRequest
import com.application.isyara.data.model.LoginResponse
import com.application.isyara.data.model.RegisterRequest
import com.application.isyara.data.model.RegisterResponse
import com.application.isyara.data.remote.AuthApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService
) {

    suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = authApiService.register(request)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = authApiService.login(request)
            Result.success(response)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}
