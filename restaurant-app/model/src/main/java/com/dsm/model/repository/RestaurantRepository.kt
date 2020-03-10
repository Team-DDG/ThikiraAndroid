package com.dsm.model.repository

import androidx.lifecycle.LiveData
import com.dsm.model.Address
import com.dsm.model.Restaurant

interface RestaurantRepository { 
    
    suspend fun getAddress(): Address?

    fun observeRestaurantInfo(): LiveData<Restaurant>

    suspend fun refreshRestaurantInfo()
}