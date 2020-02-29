package com.dsm.repository

import com.dsm.api.dataSource.RemoteAccountDataSource
import com.dsm.model.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepositoryImpl(
    private val remoteAccountDataSource: RemoteAccountDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AccountRepository {
    override suspend fun register(body: Any) = withContext(ioDispatcher) {
        remoteAccountDataSource.register(body)
    }

    override suspend fun unregister() = withContext(ioDispatcher) {
        remoteAccountDataSource.unregister()
    }

    override suspend fun changePassword(newPassword: String) = withContext(ioDispatcher) {
        remoteAccountDataSource.changePassword(newPassword)
    }

}