package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.model.AddressModel

interface AddressRepository {

    suspend fun searchAddress(query: String): List<AddressModel>
}