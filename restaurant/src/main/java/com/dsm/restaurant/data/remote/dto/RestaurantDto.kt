package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.data.local.dto.RestaurantLocalDto
import com.dsm.restaurant.domain.entity.RestaurantEntity
import com.google.gson.annotations.SerializedName

data class RestaurantDto(

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
) {
    fun toEntity() = RestaurantEntity(
        image = image,
        name = name,
        phone = phone,
        roadAddress = roadAddress,
        address = address,
        deliverableArea = deliverableArea.reduce { q, w -> "$q, $w" },
        category = category,
        minPrice = minPrice,
        dayOff = dayOff,
        isOnlinePayment = isOnlinePayment,
        isOfflinePayment = isOfflinePayment,
        businessHour = "$openTime~$closeTime",
        description = description,
        email = email
    )

    fun toLocalDto() = RestaurantLocalDto(
        image = image,
        name = name,
        phone = phone,
        roadAddress = roadAddress,
        address = address,
        deliverableArea = deliverableArea.reduce { q, w -> "$q, $w" },
        category = category,
        minPrice = minPrice,
        dayOff = dayOff,
        isOnlinePayment = isOnlinePayment,
        isOfflinePayment = isOfflinePayment,
        openTime = openTime,
        closeTime = closeTime,
        description = description,
        email = email
    )
}