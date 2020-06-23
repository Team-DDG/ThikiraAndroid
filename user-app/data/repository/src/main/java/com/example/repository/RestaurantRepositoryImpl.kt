package com.example.repository

import com.example.api.datasource.RemoteRestaurantDataSource
import com.example.model.Restaurant
import com.example.model.repository.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl(
    private val remoteRestaurantDataSource: RemoteRestaurantDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantRepository {
    override suspend fun getRestaurantList(sortOption: String, category: String): List<Restaurant> = withContext(ioDispatcher) {
        remoteRestaurantDataSource.getRestaurantList(sortOption, category)
    }
}