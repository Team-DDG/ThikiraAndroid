package com.dsm.api.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class CouponResponse(

    @SerializedName("expired_day")
    val expireDate: Date,

    @SerializedName("discount_amount")
    val price: Int,

    @SerializedName("is_expired")
    val isExpired: Boolean
)