package com.example.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Restaurant(

    @SerializedName("area")
    val area: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("create_time")
    val createTime: String,

    @SerializedName("star")
    val star: Int,

    @SerializedName("offline_payment")
    val offlinePayment: Boolean,

    @SerializedName("day_off")
    val dayOff: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("open_time")
    val openTime: String,

    @SerializedName("online_payment")
    val onlinePayment: Boolean,

    @SerializedName("close_time")
    val closeTime: String,

    @SerializedName("road_address")
    val roadAddress: String,

    @SerializedName("min_price")
    val minPrice: Int,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("r_id")
    val rId: Int,

    @SerializedName("email")
    val email: String
): Serializable
