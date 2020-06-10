package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.api.response.CouponResponse
import com.example.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteCouponDataSource {

    suspend fun issueCoupon(body: Any)

    suspend fun getCoupons(): List<CouponResponse>
}

class RemoteCouponDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteCouponDataSource {

    override suspend fun issueCoupon(body: Any) = withContext(ioDispatcher) {
        try {
            thikiraApi.issueCoupon(body)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun getCoupons(): List<CouponResponse> = withContext(ioDispatcher) {
        try {
            thikiraApi.getCoupons()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}
