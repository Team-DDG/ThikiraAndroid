package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.model.AddressModel

interface AccountRepository {

    suspend fun searchAddress(query: String): List<AddressModel>

    suspend fun checkEmail(email: String)

    suspend fun register(body: Any)
}