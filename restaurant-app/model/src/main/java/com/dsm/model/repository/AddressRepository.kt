package com.dsm.model.repository

import com.dsm.model.Address

interface AddressRepository {

    suspend fun searchAddress(query: String): List<Address>
}