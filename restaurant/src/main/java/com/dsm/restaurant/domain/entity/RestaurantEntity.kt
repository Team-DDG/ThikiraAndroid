package com.dsm.restaurant.domain.entity

import com.dsm.restaurant.presentation.model.RestaurantModel

data class RestaurantEntity(

    val image: String,

    val name: String,

    val phone: String,

    val roadAddress: String,

    val address: String,

    val deliverableArea: String,

    val category: String,

    val minPrice: Int,

    val dayOff: String,

    val isOnlinePayment: Boolean,

    val isOfflinePayment: Boolean,

    val businessHour: String,

    val description: String,

    val email: String
) {
    fun toModel() = RestaurantModel(
        image, name, phone, roadAddress, address, deliverableArea, category, minPrice, dayOff, isOnlinePayment, isOfflinePayment, businessHour, description, email
    )
}