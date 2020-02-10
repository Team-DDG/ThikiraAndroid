package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.local.dao.RestaurantDao
import com.dsm.restaurant.data.local.dto.RestaurantLocalDto
import com.dsm.restaurant.data.remote.ThikiraApi
import com.dsm.restaurant.data.remote.dto.RestaurantDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
        try {
            restaurantDao.getRestaurantInfo()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun insertLocalRestaurantInfo(restaurant: RestaurantLocalDto) = withContext(ioDispatcher) {
        try {
            restaurantDao.insertRestaurantInfo(restaurant)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }


}