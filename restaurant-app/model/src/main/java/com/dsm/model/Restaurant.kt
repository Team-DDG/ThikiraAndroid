package com.dsm.model

data class Restaurant(

    val name: String,

    val image: String,

    val phone: String,

    val deliverableArea: String,

    val category: String,

    val minPrice: Int,

    val dayOff: String,

    val isOnlinePayment: Boolean,

    val isOfflinePayment: Boolean,

    val openTime: String,

    val closeTime: String,

    val description: String,

    val email: String,

    val address: String,

    val roadAddress: String
)