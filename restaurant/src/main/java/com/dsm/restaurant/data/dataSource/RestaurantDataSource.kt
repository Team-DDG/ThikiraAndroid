package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.local.dto.RestaurantLocalDto
import com.dsm.restaurant.data.remote.dto.RestaurantDto

interface RestaurantDataSource {

    suspend fun getRemoteRestaurantInfo(): RestaurantDto

    suspend fun getLocalRestaurantInfo(): RestaurantLocalDto?

    suspend fun insertLocalRestaurantInfo(restaurant: RestaurantLocalDto)
}