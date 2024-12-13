package com.application.isyara.utils.auth

import kotlinx.coroutines.flow.StateFlow

interface ISessionManager {
    val tokenFlow: StateFlow<String?>
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}
