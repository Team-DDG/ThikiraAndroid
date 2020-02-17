package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsm.restaurant.domain.model.RestaurantModel

@Entity(tableName = "Restaurant")
data class RestaurantLocalDto(

    @PrimaryKey
    val name: String,

    val image: String,

    val phone: String,

    val roadAddress: String,

    val address: String,

    val deliverableArea: String,

    val category: String,

    val minPrice: Int,

    val dayOff: String,

    val isOnlinePayment: Boolean,

    val isOfflinePayment: Boolean,

    val openTime: String,

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
        deliverableArea = deliverableArea,
        category = category,
        minPrice = minPrice,
        dayOff = dayOff,
        isOnlinePayment = isOnlinePayment,
        isOfflinePayment = isOfflinePayment,
        businessHour = "$openTime~$closeTime",
        description = description,
        email = email
    )
}