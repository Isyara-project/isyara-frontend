package com.application.isyara.data.model

data class ForgotPasswordResponse(
    val message: String,
    val token: String? = null
)

data class EmailRequest(
    val email: String
)
