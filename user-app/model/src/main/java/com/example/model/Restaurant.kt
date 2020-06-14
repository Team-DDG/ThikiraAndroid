package com.example.model

import com.google.gson.annotations.SerializedName

data class Restaurant(

    @field:SerializedName("area")
    val area: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("create_time")
    val createTime: String,

    @field:SerializedName("star")
    val star: Int,

    @field:SerializedName("offline_payment")
    val offlinePayment: Boolean,

    @field:SerializedName("day_off")
    val dayOff: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("open_time")
    val openTime: String,

    @field:SerializedName("online_payment")
    val onlinePayment: Boolean,

    @field:SerializedName("close_time")
    val closeTime: String,

    @field:SerializedName("road_address")
    val roadAddress: String,

    @field:SerializedName("min_price")
    val minPrice: Int,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("r_id")
    val rId: Int,

    @field:SerializedName("email")
    val email: String
)
