package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.data.local.dto.RestaurantLocalDto
import com.dsm.restaurant.domain.model.RestaurantModel
import com.google.gson.annotations.SerializedName

data class RestaurantDto(

    val image: String,

    val name: String,

    val phone: String,

    @SerializedName("add_street")
    val roadAddress: String,

    @SerializedName("add_parcel")
    val address: String,

    val area: Array<String>,

    val category: String,

    @SerializedName("min_price")
    val minPrice: Int,

    @SerializedName("day_off")
    val dayOff: String,

    @SerializedName("online_payment")
    val onlinePayment: Boolean,

    @SerializedName("offline_payment")
    val offlinePayment: Boolean,

    @SerializedName("open_time")
    val openTime: String,

    @SerializedName("close_time")
    val closeTime: String,

    val description: String,

    val email: String
) {
    fun toModel() = RestaurantModel(
        image = image,
        name = name,
        phone = phone,
        roadAddress = roadAddress,
        address = address,
        area = area.reduce { q, w -> "$q,$w" },
        category = category,
        minPrice = minPrice,
        dayOff = dayOff,
        onlinePayment = onlinePayment,
        offlinePayment = offlinePayment,
        openTime = openTime,
        closeTime = closeTime,
        description = description,
        email = email
    )

    fun toLocalDto() = RestaurantLocalDto(
        image = image,
        name = name,
        phone = phone,
        roadAddress = roadAddress,
        address = address,
        area = area.reduce { q, w -> "$q,$w" },
        category = category,
        minPrice = minPrice,
        dayOff = dayOff,
        onlinePayment = onlinePayment,
        offlinePayment = offlinePayment,
        openTime = openTime,
        closeTime = closeTime,
        description = description,
        email = email
    )
}