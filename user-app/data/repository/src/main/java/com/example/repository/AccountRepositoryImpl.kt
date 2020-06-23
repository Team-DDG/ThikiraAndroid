package com.example.repository

import com.example.api.datasource.RemoteAccountDataSource
import com.example.model.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepositoryImpl(
    private val accountDataSource: RemoteAccountDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): AccountRepository {
    override suspend fun register(body: Any) = withContext(ioDispatcher) {
        accountDataSource.register(body)
    }

    override suspend fun unregister() = withContext(ioDispatcher) {
        accountDataSource.unregister()
    }

    override suspend fun changePassWord(password: String) = withContext(ioDispatcher) {
        accountDataSource.changePassword(password)
    }
}