package com.dsm.model

data class Restaurant(

    val name: String = "",

    val image: String = "",

    val phone: String = "",

    val deliverableArea: String = "",

    val category: String = "",

    val minPrice: Int = 0,

    val dayOff: String = "",

    val isOnlinePayment: Boolean = false,

    val isOfflinePayment: Boolean = false,

    val openTime: String = "",

    val closeTime: String = "",

    val description: String = "",

    val email: String = "",

    val address: String = "",

    val roadAddress: String = ""
)