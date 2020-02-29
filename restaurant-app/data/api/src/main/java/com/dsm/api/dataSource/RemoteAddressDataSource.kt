package com.dsm.api.dataSource

import com.dsm.api.NaverApi
import com.dsm.api.response.AddressResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteAddressDataSource  {

    suspend fun searchAddress(query: String): AddressResponse
}

class RemoteAddressDataSourceImpl(
    private val naverApi: NaverApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAddressDataSource {

    override suspend fun searchAddress(query: String) = withContext(ioDispatcher) {
        naverApi.searchAddress(query)
    }
}