package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.entity.AddressEntity

interface AddressRepository {

    suspend fun searchAddress(query: String): List<AddressEntity>
}