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

    override suspend fun checkEmail(email: String) = withContext(ioDispatcher) {
        accountDataSource.checkEmail(email)
    }

}