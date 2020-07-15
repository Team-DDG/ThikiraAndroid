package com.example.repository

import com.example.api.datasource.RemoteAuthDataSource
import com.example.model.repository.AuthRepository
import com.example.pref.PrefStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val prefStorage: PrefStorage,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AuthRepository {
    override suspend fun confirmAccessToken() = withContext(ioDispatcher) {
        remoteAuthDataSource.confirmAccessToken()
    }

    override suspend fun confirmEmail(email: String) = withContext(ioDispatcher) {
        remoteAuthDataSource.confirmEmail(email)
    }

    override suspend fun confirmPassword(password: String) = withContext(ioDispatcher) {
        remoteAuthDataSource.confirmPassword(password)
    }

    override suspend fun login(body: Any) {
        val response = remoteAuthDataSource.login(body)
        prefStorage.setAccessToken(response.accessToken)
        prefStorage.setRefreshToken(response.refreshToken)
    }

    override fun checkLogin(): Boolean {
        return prefStorage.getAccessToken() != ""
    }

    override fun logout() {
        prefStorage.deleteAccessToken()
        prefStorage.deleteRefreshToken()
    }
}