package com.dsm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dsm.api.dataSource.RemoteCouponDataSource
import com.dsm.api.response.CouponResponse
import com.dsm.db.dataSource.LocalCouponDataSource
import com.dsm.db.entity.CouponEntity
import com.dsm.mapper.toCoupon
import com.dsm.mapper.toCouponEntity
import com.dsm.model.Coupon
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

    override fun observeCoupon(): LiveData<List<Coupon>> =
        localCouponDataSource.observeCoupon().map { it.map(CouponEntity::toCoupon) }

    override suspend fun issueCoupon(expireDate: String, price: Int) = withContext(ioDispatcher) {
        remoteCouponDataSource.issueCoupon(
            hashMapOf(
                "discount_amount" to price,
                "expired_day" to expireDate
            )
        )

        localCouponDataSource.insertCoupon(CouponEntity(0, Date(), price, false))
    }

    override suspend fun refreshCoupons() = withContext(ioDispatcher) {
        remoteCouponDataSource.getCoupons().let {
            localCouponDataSource.deleteAllCoupons()

            localCouponDataSource.insertCoupons(it.map(CouponResponse::toCouponEntity))
        }
    }

}