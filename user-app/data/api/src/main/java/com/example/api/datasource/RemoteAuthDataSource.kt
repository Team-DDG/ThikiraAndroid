package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.api.response.TokenResponse
import com.example.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteAuthDataSource {
    suspend fun confirmAccessToken()

    suspend fun confirmEmail(email: String)

    suspend fun confirmPassword(password: String)

    suspend fun refreshToken(): TokenResponse

    suspend fun login(body: Any): TokenResponse
}

class RemoteAuthDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): RemoteAuthDataSource {
    override suspend fun confirmAccessToken() = withContext(ioDispatcher) {
        try {
            thikiraApi.confirmAccessToken()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun confirmEmail(email: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.confirmEmail(email)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun confirmPassword(password: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.confirmPassword(password)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun refreshToken(): TokenResponse = withContext(ioDispatcher) {
        try {
            thikiraApi.refreshToken()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun login(body: Any): TokenResponse = withContext(ioDispatcher) {
        try {
            thikiraApi.login(body)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

}