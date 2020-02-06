package com.dsm.restaurant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.restaurant.data.local.dao.RestaurantDao
import com.dsm.restaurant.data.local.dto.RestaurantLocalDto

@Database(
    entities = [
        RestaurantLocalDto::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
}