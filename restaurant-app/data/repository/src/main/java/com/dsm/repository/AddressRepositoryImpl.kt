package com.dsm.repository

import com.dsm.api.dataSource.RemoteAddressDataSource
import com.dsm.db.dataSource.LocalRestaurantDataSource
import com.dsm.mapper.toAddresses
import com.dsm.model.Address
import com.dsm.model.repository.AddressRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddressRepositoryImpl(
    private val remoteAddressDataSource: RemoteAddressDataSource,
    private val localRestaurantDataSource: LocalRestaurantDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AddressRepository {

    override suspend fun searchAddress(query: String): List<Address> = withContext(ioDispatcher) {
        remoteAddressDataSource.searchAddress(query).toAddresses()
    }

    override suspend fun changeAddress(address: String, roadAddress: String) = withContext(ioDispatcher) {
        remoteAddressDataSource.changeAddress(address, roadAddress)
        localRestaurantDataSource.changeAddress(address, roadAddress)
    }
}