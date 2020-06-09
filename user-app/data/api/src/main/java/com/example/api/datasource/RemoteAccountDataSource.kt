package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.error.ErrorHandler
import com.example.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface RemoteAccountDataSource {
    suspend fun register(body: User)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)
}

class RemoteAccountDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAccountDataSource {
    override suspend fun register(body: User) = withContext(ioDispatcher) {
        try {
            thikiraApi.register(body)
        } catch (e: Exception) {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun unregister() = withContext(ioDispatcher) {
        try {
            thikiraApi.leaveThikira()
        } catch (e: Exception) {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun changePassword(newPassword: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.changePassword(newPassword)
        } catch (e: Exception) {
            throw  errorHandler.getNetworkException(e)
        }
    }
}