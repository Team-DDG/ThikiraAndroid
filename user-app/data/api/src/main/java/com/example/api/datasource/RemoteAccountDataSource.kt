package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.api.response.TokenResponse
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

    suspend fun confirmEmail(email: String)

    suspend fun confirmPassword(password: String)

    suspend fun login(body: User): TokenResponse
    //TODO: discuss about login api's file
}

class RemoteAccountDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteAccountDataSource {
    override suspend fun register(body: User) = withContext(ioDispatcher) {
        try
        {
            thikiraApi.register(body)
        } catch (e: Exception)
        {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun unregister() = withContext(ioDispatcher) {
        try
        {
//            thikiraApi.leaveThikira()
        } catch (e: Exception)
        {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun changePassword(newPassword: String) = withContext(ioDispatcher) {
        try
        {
//            thikiraApi.changePassword(Unit(), newPassword)
        } catch (e: Exception)
        {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun confirmEmail(email: String) = withContext(ioDispatcher) {
        try
        {
            thikiraApi.confirmEmail(email)
        } catch (e: Exception)
        {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun confirmPassword(password: String) = withContext(ioDispatcher) {
        try
        {
//            thikiraApi.changePassword(Unit(), password)
        } catch (e: Exception)
        {
            throw  errorHandler.getNetworkException(e)
        }
    }

    override suspend fun login(body: User) = withContext(ioDispatcher) {
        try
        {
            thikiraApi.login(body)
        } catch (e: Exception)
        {
            throw  errorHandler.getNetworkException(e)
        }
    }
}