package com.dsm.restaurant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dsm.restaurant.data.local.dao.MenuCategoryDao
import com.dsm.restaurant.data.local.dao.RestaurantDao
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.local.dto.RestaurantLocalDto

@Database(
    entities = [
        RestaurantLocalDto::class,
        MenuCategoryLocalDto::class
    ],
    version = 3
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun menuCategoryDao(): MenuCategoryDao
}