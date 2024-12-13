package com.application.isyara.data.model

data class ChangePasswordRequest(
    val oldPass: String,
    val newPass: String
)

data class ChangePasswordResponse(
    val message: String
)
