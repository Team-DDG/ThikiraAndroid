package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.api.response.UserResponse
import com.example.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface RemoteUserDataSource{
    suspend fun getUserInfo(): UserResponse
}

class RemoteUserDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): RemoteUserDataSource {
    override suspend fun getUserInfo(): UserResponse = withContext(ioDispatcher) {
        try {
            thikiraApi.getUserInfo()
        } catch (e: Exception){
            throw errorHandler.getNetworkException(e)
        }
    }
}