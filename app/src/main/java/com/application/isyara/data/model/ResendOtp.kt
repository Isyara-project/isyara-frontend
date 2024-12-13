package com.application.isyara.data.model

data class ResendOtpResponse(
    val message: String
)

data class ResendOtpRequest(
    val email: String
)
