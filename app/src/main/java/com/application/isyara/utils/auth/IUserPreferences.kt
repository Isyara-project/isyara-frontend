package com.application.isyara.utils.auth

import kotlinx.coroutines.flow.Flow

interface IUserPreferences {
    fun getLastRegistrationAttempt(): Flow<Long?>
    suspend fun setLastRegistrationAttempt(timeMillis: Long)
    suspend fun clearLastRegistrationAttempt()
}
