package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.local.dao.RestaurantDao
import com.dsm.restaurant.data.local.dto.RestaurantLocalDto
import com.dsm.restaurant.data.remote.ThikiraApi
import com.dsm.restaurant.data.remote.dto.RestaurantDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RestaurantDataSource {

    suspend fun getRemoteRestaurantInfo(): RestaurantDto

    suspend fun getLocalRestaurantInfo(): RestaurantLocalDto?

    suspend fun insertLocalRestaurantInfo(restaurant: RestaurantLocalDto)

    suspend fun deleteLocalRestaurantInfo()
}

class RestaurantDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val restaurantDao: RestaurantDao,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantDataSource {

    override suspend fun getRemoteRestaurantInfo(): RestaurantDto = withContext(ioDispatcher) {
        try {
            thikiraApi.getRestaurantInfo()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun getLocalRestaurantInfo(): RestaurantLocalDto? = withContext(ioDispatcher) {
        restaurantDao.getRestaurantInfo()
    }

    override suspend fun insertLocalRestaurantInfo(restaurant: RestaurantLocalDto) = withContext(ioDispatcher) {
        restaurantDao.insertRestaurantInfo(restaurant)
    }

    override suspend fun deleteLocalRestaurantInfo() = withContext(ioDispatcher) {
        restaurantDao.deleteRestaurantInfo()
    }
}