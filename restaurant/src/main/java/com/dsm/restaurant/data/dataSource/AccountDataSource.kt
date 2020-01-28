package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.remote.dto.AddressDto

interface AccountDataSource {

    suspend fun searchAddress(query: String): AddressDto

    suspend fun checkEmail(email: String)

    suspend fun register(body: Any)
}