package com.example.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("address")
    val address: String,

    @SerializedName("road_address")
    val roadAddress: String,

    @SerializedName("created_time")
    val createTime: String,

    val nickname: String,

    val phone: String
)