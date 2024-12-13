package com.application.isyara.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val data: ProfileData?
)

data class ProfileData(
    @SerializedName("id")
    val id: String,

    @SerializedName("fullname")
    val fullname: String,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("userId")
    val userId: String
)

data class UpdateProfileResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("fileUrl")
    val fileUrl: String
)