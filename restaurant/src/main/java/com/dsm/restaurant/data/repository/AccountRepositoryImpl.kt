package com.dsm.restaurant.data.repository

import com.dsm.restaurant.data.dataSource.AccountDataSource
import com.dsm.restaurant.domain.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepositoryImpl(
    private val accountDataSource: AccountDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AccountRepository {

    override suspend fun register(body: Any) = withContext(ioDispatcher) {
        accountDataSource.register(body)
    }

    override suspend fun unregister() = withContext(ioDispatcher) {
        accountDataSource.unregister()
    }

    override suspend fun changePassword(newPassword: String) = withContext(ioDispatcher) {
        accountDataSource.changePassword(newPassword)
    }
}