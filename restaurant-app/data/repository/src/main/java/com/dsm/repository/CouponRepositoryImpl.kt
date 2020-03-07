package com.dsm.repository

import com.dsm.api.dataSource.RemoteCouponDataSource
import com.dsm.db.dataSource.LocalCouponDataSource
import com.dsm.db.entity.CouponEntity
import com.dsm.model.repository.CouponRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CouponRepositoryImpl(
    private val localCouponDataSource: LocalCouponDataSource,
    private val remoteCouponDataSource: RemoteCouponDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CouponRepository {

    override suspend fun issueCoupon(expireDate: Date, price: Int) = withContext(ioDispatcher) {
        remoteCouponDataSource.issueCoupon(expireDate, price)

        localCouponDataSource.insertCoupon(CouponEntity(0, expireDate, price))
    }

}