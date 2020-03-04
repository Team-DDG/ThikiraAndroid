package com.dsm.restaurant.data.repository

import com.dsm.restaurant.data.dataSource.AddressDataSource
import com.dsm.restaurant.domain.entity.AddressEntity
import com.dsm.restaurant.domain.repository.AddressRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddressRepositoryImpl(
    private val addressDataSource: AddressDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AddressRepository {

    override suspend fun searchAddress(query: String): List<AddressEntity> = withContext(ioDispatcher) {
        addressDataSource.searchAddress(query).toEntity()
    }
}