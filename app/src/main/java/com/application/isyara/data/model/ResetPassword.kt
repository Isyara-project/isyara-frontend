package com.application.isyara.data.model

data class ResetPasswordRequest(
    val password: String
)

data class ResetPasswordResponse(
    val message: String
)
