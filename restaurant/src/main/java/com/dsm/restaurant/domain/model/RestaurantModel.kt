package com.dsm.restaurant.domain.model

data class RestaurantModel(

    val image: String,

    val name: String,

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
)