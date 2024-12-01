package com.application.isyara.data.model

data class ForgotPasswordRequest(
    val email: String
)

data class ForgotPasswordResponse(
    val message: String
)