package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.entity.RestaurantEntity

interface RestaurantRepository {

    suspend fun getRestaurantInfo(forceUpdate: Boolean): RestaurantEntity
}