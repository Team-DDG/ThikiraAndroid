package com.dsm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dsm.api.dataSource.RemoteRestaurantDataSource
import com.dsm.db.dataSource.LocalRestaurantDataSource
import com.dsm.mapper.toEntity
import com.dsm.mapper.toRestaurant
import com.dsm.model.Address
import com.dsm.model.Restaurant
import com.dsm.model.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl(
    private val localRestaurantDataSource: LocalRestaurantDataSource,
    private val remoteRestaurantDataSource: RemoteRestaurantDataSource,
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

    override fun observeRestaurantInfo(): LiveData<Restaurant> =
        localRestaurantDataSource.observeRestaurantInfo().map {
            it?.toRestaurant() ?: Restaurant()
        }

    override suspend fun refreshRestaurantInfo() = withContext(ioDispatcher) {
        remoteRestaurantDataSource.getRestaurantInfo().let {
            localRestaurantDataSource.deleteRestaurantInfo()
            localRestaurantDataSource.insertRestaurantInfo(it.toEntity())
        }
    }
}