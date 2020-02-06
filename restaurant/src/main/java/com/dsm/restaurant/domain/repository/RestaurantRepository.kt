package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.model.RestaurantModel

interface RestaurantRepository {

    suspend fun getRestaurantInfo(forceUpdate: Boolean): RestaurantModel
}