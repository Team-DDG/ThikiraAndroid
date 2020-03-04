package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.remote.NaverApi
import com.dsm.restaurant.data.remote.dto.AddressDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AddressDataSource {

    suspend fun searchAddress(query: String): AddressDto
}

class AddressDataSourceImpl(
    private val naverApi: NaverApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AddressDataSource {

    override suspend fun searchAddress(query: String): AddressDto = withContext(ioDispatcher) {
        try {
            naverApi.searchAddress(query)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}