package com.dsm.repository

import com.dsm.api.dataSource.RemoteOrderDataSource
import com.dsm.api.response.OrderResponse
import com.dsm.mapper.toOrder
import com.dsm.model.Order
import com.dsm.model.repository.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class OrderRepositoryImpl(
    private val remoteOrderDataSource: RemoteOrderDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrderRepository {

    override suspend fun getOrdersByDate(date: Date): List<Order> = withContext(ioDispatcher) {
        remoteOrderDataSource.getOrders(date).map(OrderResponse::toOrder)
    }

}