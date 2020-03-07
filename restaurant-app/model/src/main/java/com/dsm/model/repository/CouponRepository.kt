package com.dsm.model.repository

import androidx.lifecycle.LiveData
import com.dsm.model.Coupon
import java.util.*

interface CouponRepository {

    fun observeCoupon(): LiveData<List<Coupon>>

    suspend fun issueCoupon(expireDate: Date, price: Int)

    suspend fun refreshCoupons()
}