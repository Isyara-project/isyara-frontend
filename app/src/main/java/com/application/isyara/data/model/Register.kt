package com.application.isyara.data.model

data class RegisterRequest(
    val fullname: String,
    val username: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val message: String,
    val data: UserData,
    val temporary_token: String?
)

data class UserData(
    val id: String,
    val fullname: String,
    val username: String,
    val email: String,
    val password: String,
    val created_at: String
)

