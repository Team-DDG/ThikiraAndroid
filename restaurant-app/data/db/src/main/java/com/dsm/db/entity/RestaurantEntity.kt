package com.dsm.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurant")
data class RestaurantEntity(

    @PrimaryKey
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

    @Embedded
    val address: AddressEntity
)