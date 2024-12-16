package com.application.isyara.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("data")
    val data: ProfileData?
)

data class ProfileData(
    @SerializedName("id")
    val id: String = "",

    @SerializedName("fullname")
    val fullname: String = "",

    @SerializedName("picture")
    val picture: String? = null,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("userId")
    val userId: String = ""
)

data class UpdateProfileResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("fileUrl")
    val fileUrl: String
)