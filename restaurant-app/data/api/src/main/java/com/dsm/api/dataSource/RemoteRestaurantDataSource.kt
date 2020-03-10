package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.api.response.RestaurantResponse
import com.dsm.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteRestaurantDataSource {

    suspend fun getRestaurantInfo(): RestaurantResponse
}

class RemoteRestaurantDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteRestaurantDataSource {

    override suspend fun getRestaurantInfo(): RestaurantResponse = withContext(ioDispatcher) {
        try {
            thikiraApi.getRestaurantInfo()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}
