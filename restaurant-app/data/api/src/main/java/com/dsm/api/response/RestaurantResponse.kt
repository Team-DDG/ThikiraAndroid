package com.dsm.api.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(

    val image: String,

    val name: String,

    val phone: String,

    @SerializedName("add_street")
    val roadAddress: String,

    @SerializedName("add_parcel")
    val address: String,

    @SerializedName("area")
    val deliverableArea: Array<String>,

    val category: String,

    @SerializedName("min_price")
    val minPrice: Int,

    @SerializedName("day_off")
    val dayOff: String,

    @SerializedName("online_payment")
    val isOnlinePayment: Boolean,

    @SerializedName("offline_payment")
    val isOfflinePayment: Boolean,

    @SerializedName("open_time")
    val openTime: String,

    @SerializedName("close_time")
    val closeTime: String,

    val description: String,

    val email: String
)