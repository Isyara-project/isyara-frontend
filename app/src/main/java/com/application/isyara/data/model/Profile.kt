package com.application.isyara.data.model

data class ProfileResponse(
    val data: ProfileData?
)

data class ProfileData(
    val id: String,
    val fullname: String,
    val picture: String?,
    val bio: String?,
    val userId: String
)

data class UpdateProfileRequest(
    val fullname: String,
    val bio: String
)

data class UpdateProfileResponse(
    val message: String,
    val fileUrl: String
)