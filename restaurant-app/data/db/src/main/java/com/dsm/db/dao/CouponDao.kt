package com.dsm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.db.entity.CouponEntity

@Dao
interface CouponDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coupon: CouponEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coupons: List<CouponEntity>)

    @Query("delete from coupon")
    suspend fun deleteAll()

    @Query("select * from coupon")
    fun observeCoupon(): LiveData<List<CouponEntity>>
}