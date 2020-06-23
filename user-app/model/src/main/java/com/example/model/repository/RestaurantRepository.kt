package com.example.model.repository

import com.example.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurantList(sortOption: String, category: String): List<Restaurant>
}