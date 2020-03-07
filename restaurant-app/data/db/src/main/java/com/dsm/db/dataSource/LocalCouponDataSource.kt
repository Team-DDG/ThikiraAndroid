package com.dsm.db.dataSource

import androidx.lifecycle.LiveData
import com.dsm.db.dao.CouponDao
import com.dsm.db.entity.CouponEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalCouponDataSource {

    fun observeCoupon(): LiveData<List<CouponEntity>>

    suspend fun insertCoupons(coupons: List<CouponEntity>)

    suspend fun deleteAllCoupons()

    suspend fun insertCoupon(coupon: CouponEntity)
}

class LocalCouponDataSourceImpl(
    private val couponDao: CouponDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalCouponDataSource {

    override fun observeCoupon(): LiveData<List<CouponEntity>> =
        couponDao.observeCoupon()

    override suspend fun insertCoupons(coupons: List<CouponEntity>) = withContext(ioDispatcher) {
        couponDao.insert(coupons)
    }

    override suspend fun deleteAllCoupons() = withContext(ioDispatcher) {
        couponDao.deleteAll()
    }

    override suspend fun insertCoupon(coupon: CouponEntity) = withContext(ioDispatcher) {
        couponDao.insert(coupon)
    }
}