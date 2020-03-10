package com.dsm.db.dataSource

import com.dsm.db.dao.RestaurantDao
import com.dsm.db.entity.AddressEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalRestaurantDataSource {

    suspend fun getAddress(): AddressEntity?

    suspend fun changeAddress(address: String, roadAddress: String)
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
}