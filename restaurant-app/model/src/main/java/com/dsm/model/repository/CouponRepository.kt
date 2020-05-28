package com.dsm.model.repository

import androidx.lifecycle.LiveData
import com.dsm.model.Coupon

interface CouponRepository {

    fun observeCoupon(): LiveData<List<Coupon>>

    suspend fun issueCoupon(expireDate: String, price: Int)

    suspend fun refreshCoupons()
}