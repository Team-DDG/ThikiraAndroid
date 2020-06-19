package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.error.ErrorHandler
import com.example.model.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteRestaurantDataSource {
    suspend fun getRestaurantList(
        sortOption: String,
        category: String
    ): List<Restaurant>
}

class RemoteRestaurantDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteRestaurantDataSource {
    override suspend fun getRestaurantList(
        sortOption: String,
        category: String
    ): List<Restaurant> = withContext(ioDispatcher) {
        try {
            thikiraApi.getRestaurantList(sortOption, category)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }

    }
}