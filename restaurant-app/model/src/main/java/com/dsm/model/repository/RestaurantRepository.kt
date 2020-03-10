package com.dsm.model.repository

import com.dsm.model.Address

interface RestaurantRepository { 
    
    suspend fun getAddress(): Address?
}