package com.dsm.api.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class OrderResponse(

    @SerializedName("od_id")
    val orderId: String,

    @SerializedName("create_time")
    val orderTime: Date,

    @SerializedName("order_detail")
    val orderDetail: List<OrderDetailItem>,

    @SerializedName("payment_type")
    val paymentType: String,

    @SerializedName("discount_amount")
    val discountPrice: Int,

    val status: String,

    @SerializedName("total_price")
    val totalPrice: Int,

    val nick: String
)

data class OrderDetailItem(

    val name: String,

    val price: Int,

    val quantity: Int,

    @SerializedName("sub_price")
    val subPrice: Int,

    val group: List<OrderGroupItem>
)

data class OrderGroupItem(

    val name: String,

    val option: List<OrderOptionItem>
)

data class OrderOptionItem(

    val name: String,

    val price: Int
)