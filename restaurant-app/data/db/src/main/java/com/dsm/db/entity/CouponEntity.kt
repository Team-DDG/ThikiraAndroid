package com.dsm.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Coupon")
data class CouponEntity(

    @PrimaryKey(autoGenerate = true)
    val couponId: Int,

    val expireDate: Date,

    val price: Int,

    val isExpired: Boolean
)