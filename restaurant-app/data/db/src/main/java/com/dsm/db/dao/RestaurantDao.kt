package com.dsm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.db.entity.AddressEntity
import com.dsm.db.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("select address, roadAddress from Restaurant")
    suspend fun address(): AddressEntity?

    @Query("update restaurant set address = :address, roadAddress = :roadAddress")
    suspend fun updateAddress(address: String, roadAddress: String)

    @Query("select * from restaurant")
    fun observeRestaurant(): LiveData<RestaurantEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: RestaurantEntity)

    @Query("delete from restaurant")
    suspend fun delete()
}