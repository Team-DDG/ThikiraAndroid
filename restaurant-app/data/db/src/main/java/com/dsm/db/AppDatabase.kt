package com.dsm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dsm.db.dao.CouponDao
import com.dsm.db.dao.MenuCategoryDao
import com.dsm.db.dao.MenuDao
import com.dsm.db.dao.RestaurantDao
import com.dsm.db.entity.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Database(
    entities = [
        RestaurantEntity::class,
        MenuCategoryEntity::class,
        MenuEntity::class,
        CouponEntity::class
    ],
    version = 11,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun menuCategoryDao(): MenuCategoryDao

    abstract fun menuDao(): MenuDao

    abstract fun couponDao(): CouponDao
}

class DatabaseConverters {
    @TypeConverter
    fun fromStringToMenuGroups(from: String): List<MenuGroupItem> =
        Gson().fromJson(from, object : TypeToken<List<MenuGroupItem>>() {}.type)

    @TypeConverter
    fun fromMenuGroupsToString(from: List<MenuGroupItem>): String =
        Gson().toJson(from)

    @TypeConverter
    fun fromLongToDate(from: Long) = Date(from)

    @TypeConverter
    fun fromDateToLong(from: Date) = from.time
}
