package com.dsm.restaurant.data.repository

import com.dsm.restaurant.data.dataSource.RestaurantDataSource
import com.dsm.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl(
    private val restaurantDataSource: RestaurantDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantRepository {

    override suspend fun getRestaurantInfo(forceUpdate: Boolean) = withContext(ioDispatcher) {
        if (!forceUpdate) {
            restaurantDataSource.getLocalRestaurantInfo()?.let {
                return@withContext it.toEntity()
            }
        }

        restaurantDataSource.getRemoteRestaurantInfo().let {
            restaurantDataSource.deleteLocalRestaurantInfo()
            restaurantDataSource.insertLocalRestaurantInfo(it.toLocalDto())
            return@withContext it.toEntity()
        }
    }
}