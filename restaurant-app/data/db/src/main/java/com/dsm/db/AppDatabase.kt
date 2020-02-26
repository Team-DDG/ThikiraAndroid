package com.dsm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dsm.db.dao.MenuCategoryDao
import com.dsm.db.dao.MenuDao
import com.dsm.db.dao.RestaurantDao
import com.dsm.db.entity.GroupItem
import com.dsm.db.entity.MenuCategoryEntity
import com.dsm.db.entity.MenuEntity
import com.dsm.db.entity.RestaurantEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [
        RestaurantEntity::class,
        MenuCategoryEntity::class,
        MenuEntity::class
    ],
    version = 4
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun menuCategoryDao(): MenuCategoryDao

    abstract fun menuDao(): MenuDao
}

class DatabaseConverters {
    @TypeConverter
    fun fromStringToGroupList(from: String): List<GroupItem> =
        Gson().fromJson(from, object : TypeToken<List<GroupItem>>() {}.type)

    @TypeConverter
    fun fromGroupListToString(from: List<GroupItem>): String =
        Gson().toJson(from)
}
