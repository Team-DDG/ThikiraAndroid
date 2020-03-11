package com.dsm.model

import java.util.*

data class Order(

    val orderId: String,

    val orderTime: Date,

    val orderDetail: List<OrderDetailItem>,

    val paymentType: String,

    val discountPrice: Int,

    val status: String,

    val totalPrice: Int,

    val nick: String
)

data class OrderDetailItem(

    val name: String,

    val price: Int,

    val quantity: Int,

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