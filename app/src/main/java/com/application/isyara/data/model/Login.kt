package com.application.isyara.data.model

data class LoginRequest(
    val identifier: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val access_token: String
)
