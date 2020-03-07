package com.dsm.db.dataSource

import com.dsm.db.dao.CouponDao
import com.dsm.db.entity.CouponEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalCouponDataSource {

    suspend fun insertCoupon(coupon: CouponEntity)
}

class LocalCouponDataSourceImpl(
    private val couponDao: CouponDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalCouponDataSource {

    override suspend fun insertCoupon(coupon: CouponEntity) = withContext(ioDispatcher) {
        couponDao.insert(coupon)
    }
}