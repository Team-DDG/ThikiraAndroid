package com.dsm.db.dataSource

import androidx.lifecycle.LiveData
import com.dsm.db.dao.RestaurantDao
import com.dsm.db.entity.AddressEntity
import com.dsm.db.entity.RestaurantEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalRestaurantDataSource {

    suspend fun getAddress(): AddressEntity?

    suspend fun changeAddress(address: String, roadAddress: String)

    fun observeRestaurantInfo(): LiveData<RestaurantEntity>

    suspend fun insertRestaurantInfo(restaurant: RestaurantEntity)

    suspend fun deleteRestaurantInfo()
}

class LocalRestaurantDataSourceImpl(
    private val restaurantDao: RestaurantDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalRestaurantDataSource {

    override suspend fun getAddress(): AddressEntity? = withContext(ioDispatcher) {
        restaurantDao.address()
    }

    override suspend fun changeAddress(address: String, roadAddress: String) = withContext(ioDispatcher) {
        restaurantDao.updateAddress(address, roadAddress)
    }

    override fun observeRestaurantInfo(): LiveData<RestaurantEntity> =
        restaurantDao.observeRestaurant()

    override suspend fun insertRestaurantInfo(restaurant: RestaurantEntity) = withContext(ioDispatcher) {
        restaurantDao.insert(restaurant)
    }

    override suspend fun deleteRestaurantInfo() = withContext(ioDispatcher) {
        restaurantDao.delete()
    }
}