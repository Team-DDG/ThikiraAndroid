package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteAccountDataSource {

    suspend fun register(body: Any)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)
}

class RemoteAccountDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: com.dsm.error.ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAccountDataSource {
    override suspend fun register(body: Any) = withContext(ioDispatcher) {
        try {
            thikiraApi.register(body)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun unregister() = withContext(ioDispatcher) {
        try {
            thikiraApi.unregister()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun changePassword(newPassword: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.changePassword(newPassword)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

}