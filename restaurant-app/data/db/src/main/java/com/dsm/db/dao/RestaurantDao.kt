package com.dsm.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.dsm.db.entity.AddressEntity

@Dao
interface RestaurantDao {

    @Query("select address, roadAddress from Restaurant")
    suspend fun address(): AddressEntity?

    @Query("update restaurant set address = :address, roadAddress = :roadAddress")
    suspend fun updateAddress(address: String, roadAddress: String)
}