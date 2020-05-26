package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.api.response.TokenResponse
import com.dsm.error.exception.ForbiddenException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteAuthDataSource {

    suspend fun authToken()

    suspend fun confirmEmailDuplication(email: String)

    suspend fun login(body: Any): TokenResponse

    suspend fun confirmPassword(password: String)
}

class RemoteAuthDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: com.dsm.error.ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAuthDataSource {

    override suspend fun authToken() = withContext(ioDispatcher) {
        try {
            thikiraApi.authToken()
            throw ForbiddenException(Throwable("")) // TODO: authToken api가 고쳐지면 삭제
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun confirmEmailDuplication(email: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.confirmEmailDuplication(email)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun login(body: Any) = withContext(ioDispatcher) {
        try {
            thikiraApi.login(body)
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
}