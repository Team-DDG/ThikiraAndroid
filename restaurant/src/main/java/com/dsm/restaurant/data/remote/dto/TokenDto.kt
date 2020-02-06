package com.dsm.restaurant.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TokenDto(

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)