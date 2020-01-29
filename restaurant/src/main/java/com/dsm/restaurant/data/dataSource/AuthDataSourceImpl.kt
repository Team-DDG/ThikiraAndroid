package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.remote.ThikiraApi
import com.dsm.restaurant.data.remote.dto.TokenDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthDataSource {

    override suspend fun login(body: Any): TokenDto = withContext(ioDispatcher) {
        try {
            thikiraApi.login(body)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}