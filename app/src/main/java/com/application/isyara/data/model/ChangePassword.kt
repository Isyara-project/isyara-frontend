package com.application.isyara.data.model

// Model request untuk mengubah password
data class ChangePasswordRequest(
    val oldPass: String,
    val newPass: String
)

// Model response untuk hasil ubah password
data class ChangePasswordResponse(
    val message: String
)
