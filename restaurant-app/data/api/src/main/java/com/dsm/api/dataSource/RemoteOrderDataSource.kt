package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.api.response.OrderResponse
import com.example.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

interface RemoteOrderDataSource {

    suspend fun getOrders(date: Date): List<OrderResponse>
}

class RemoteOrderDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteOrderDataSource {

    override suspend fun getOrders(date: Date): List<OrderResponse> = withContext(ioDispatcher) {
        try {
            thikiraApi.getOrders(date)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}