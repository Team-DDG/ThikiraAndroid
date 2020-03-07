package com.dsm.mapper

import com.dsm.api.response.CouponResponse
import com.dsm.db.entity.CouponEntity
import com.dsm.model.Coupon

internal fun CouponResponse.toCouponEntity() =
    CouponEntity(
        expireDate = expireDate,
        price = price,
        isExpired = isExpired,
        couponId = 0
    )

internal fun CouponEntity.toCoupon() =
    Coupon(
        expireDate = expireDate,
        price = price,
        isExpired = isExpired
    )