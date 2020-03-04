package com.dsm.restaurant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.restaurant.data.local.dto.RestaurantLocalDto

@Dao
interface RestaurantDao {

    @Query("select * from Restaurant")
    suspend fun getRestaurantInfo(): RestaurantLocalDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurantInfo(restaurantLocalDto: RestaurantLocalDto)

    @Query("delete from Restaurant")
    suspend fun deleteRestaurantInfo()
}