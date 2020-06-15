package com.dsm.api.dataSource

import com.dsm.api.NaverApi
import com.dsm.api.ThikiraApi
import com.dsm.api.response.AddressResponse
import com.example.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteAddressDataSource {

    suspend fun searchAddress(query: String): AddressResponse

    suspend fun changeAddress(address: String, roadAddress: String)
}

class RemoteAddressDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val naverApi: NaverApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAddressDataSource {

    override suspend fun searchAddress(query: String) = withContext(ioDispatcher) {
        naverApi.searchAddress(query)
    }

    override suspend fun changeAddress(address: String, roadAddress: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.changeAddress(address, roadAddress)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}