package com.dsm.api.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)