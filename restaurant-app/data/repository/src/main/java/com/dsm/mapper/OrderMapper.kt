package com.dsm.mapper

import com.dsm.api.response.OrderResponse
import com.dsm.model.Order
import com.dsm.model.OrderDetailItem
import com.dsm.model.OrderGroupItem
import com.dsm.model.OrderOptionItem

internal fun OrderResponse.toOrder() =
    Order(
        orderId = orderId,
        orderTime = orderTime,
        totalPrice = totalPrice,
        status = status,
        paymentType = paymentType,
        discountPrice = discountPrice,
        nick = nick,
        orderDetail = orderDetail.map {
            OrderDetailItem(
                name = it.name,
                price = it.price,
                quantity = it.quantity,
                subPrice = it.subPrice,
                group = it.group.map {
                    OrderGroupItem(
                        name = it.name,
                        option = it.option.map {
                            OrderOptionItem(
                                name = it.name,
                                price = it.price
                            )
                        }
                    )
                }
            )
        }
    )