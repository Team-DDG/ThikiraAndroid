package com.dsm.restaurant.domain.model

data class RestaurantModel(

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
)