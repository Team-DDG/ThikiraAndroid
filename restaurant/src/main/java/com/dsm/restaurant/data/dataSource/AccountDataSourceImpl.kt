package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.remote.NaverApi
import com.dsm.restaurant.data.remote.ThikiraApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val naverApi: NaverApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AccountDataSource {

    override suspend fun searchAddress(query: String) = withContext(ioDispatcher) {
        try {
            naverApi.searchAddress(query)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun checkEmail(email: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.checkEmail(email)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun register(body: Any) = withContext(ioDispatcher) {
        try {
            thikiraApi.register(body)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}