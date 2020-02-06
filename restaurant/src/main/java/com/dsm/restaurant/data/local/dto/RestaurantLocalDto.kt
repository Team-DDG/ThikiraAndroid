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

    val area: String,

    val category: String,

    val minPrice: Int,

    val dayOff: String,

    val onlinePayment: Boolean,

    val offlinePayment: Boolean,

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
        area = area,
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