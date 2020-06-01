package com.example.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("add_parcel")
    val address: String,

    @SerializedName("add_street")
    val roadAddress: String,

    @SerializedName("created_time")
    val createTime: String,

    val nickname: String,

    val phone: String
)