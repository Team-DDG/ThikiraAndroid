package com.dsm.model.repository

import com.dsm.model.Order
import java.util.*

interface OrderRepository {

    suspend fun getOrdersByDate(date: Date): List<Order>
}