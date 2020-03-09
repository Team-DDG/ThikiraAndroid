package com.dsm.repository

import com.dsm.db.dataSource.LocalRestaurantDataSource
import com.dsm.model.Address
import com.dsm.model.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl(
    private val localRestaurantDataSource: LocalRestaurantDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantRepository {

    override suspend fun getAddress(): Address? = withContext(ioDispatcher) {
        localRestaurantDataSource.getAddress()?.let {
            Address(
                title = "",
                roadAddress = it.roadAddress,
                address = it.address
            )
        }
    }

}