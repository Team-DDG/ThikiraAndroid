package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.remote.dto.AddressDto

interface AddressDataSource {

    suspend fun searchAddress(query: String): AddressDto
}