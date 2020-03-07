package com.dsm.model.repository

import java.util.*

interface CouponRepository {

    suspend fun issueCoupon(expireDate: Date, price: Int)
}