package com.application.isyara.data.model

// Data class untuk request Reset Password
data class ResetPasswordRequest(
    val password: String
)

// Data class untuk response Reset Password
data class ResetPasswordResponse(
    val message: String
)
