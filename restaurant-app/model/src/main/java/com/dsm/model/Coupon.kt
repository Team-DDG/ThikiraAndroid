package com.dsm.model

import java.util.*

data class Coupon(

    val expireDate: Date,

    val price: Int,

    val isExpired: Boolean
)