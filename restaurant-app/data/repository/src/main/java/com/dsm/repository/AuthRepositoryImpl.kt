package com.dsm.repository

import com.dsm.api.dataSource.RemoteAuthDataSource
import com.dsm.model.repository.AuthRepository
import com.dsm.pref.PrefStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val prefStorage: PrefStorage,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRepository {

    override suspend fun authToken() = withContext(ioDispatcher) {
        remoteAuthDataSource.authToken()
    }

    override suspend fun confirmEmailDuplication(email: String) = withContext(ioDispatcher) {
        remoteAuthDataSource.confirmEmailDuplication(email)
    }

    override suspend fun login(body: Any) = withContext(ioDispatcher) {
        val response = remoteAuthDataSource.login(body)
        prefStorage.setAccessToken(response.accessToken)
        prefStorage.setRefreshToken(response.refreshToken)
    }

    override suspend fun confirmPassword(password: String) = withContext(ioDispatcher) {
        remoteAuthDataSource.confirmPassword(password)
    }
}