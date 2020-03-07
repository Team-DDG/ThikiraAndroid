package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

interface RemoteCouponDataSource {

    suspend fun issueCoupon(expireDate: Date, price: Int)
}

class RemoteCouponDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteCouponDataSource {

    override suspend fun issueCoupon(expireDate: Date, price: Int) = withContext(ioDispatcher) {
        try {
            thikiraApi.issueCoupon(expireDate, price)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}
