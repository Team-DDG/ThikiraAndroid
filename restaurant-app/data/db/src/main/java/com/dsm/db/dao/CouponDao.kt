package com.dsm.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.dsm.db.entity.CouponEntity

@Dao
interface CouponDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coupon: CouponEntity)
}