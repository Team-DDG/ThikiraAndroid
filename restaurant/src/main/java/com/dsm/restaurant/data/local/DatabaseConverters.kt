package com.dsm.restaurant.data.local

import androidx.room.TypeConverter
import com.dsm.restaurant.data.local.dto.GroupLocalDto
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverters {

    @TypeConverter
    fun fromStringToMenuCategoryList(from: String): List<MenuCategoryLocalDto> =
        Gson().fromJson(from, object : TypeToken<List<MenuCategoryLocalDto>>() {}.type)

    @TypeConverter
    fun fromMenuCategoryListToString(from: List<MenuCategoryLocalDto>): String =
        Gson().toJson(from)

    @TypeConverter
    fun fromStringToGroupList(from: String): List<GroupLocalDto> =
        Gson().fromJson(from, object : TypeToken<List<GroupLocalDto>>() {}.type)

    @TypeConverter
    fun fromGroupListToString(from: List<GroupLocalDto>): String =
        Gson().toJson(from)
}

