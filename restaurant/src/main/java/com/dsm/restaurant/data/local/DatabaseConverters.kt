package com.dsm.restaurant.data.local

import androidx.room.TypeConverter
import com.dsm.restaurant.data.local.dto.GroupLocalItem
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverters {

    @TypeConverter
    fun fromStringToMenuCategoryList(from: String): List<MenuCategoryLocalDto> {
        val listType = object : TypeToken<List<MenuCategoryLocalDto>>() {}.type
        return Gson().fromJson(from, listType)
    }

    @TypeConverter
    fun fromMenuCateogryListToString(from: List<MenuCategoryLocalDto>): String {
        return Gson().toJson(from)
    }

    @TypeConverter
    fun fromStringToGroupList(from: String): List<GroupLocalItem> {
        val listType = object : TypeToken<List<GroupLocalItem>>() {}.type
        return Gson().fromJson(from, listType)
    }

    @TypeConverter
    fun fromGroupListToString(from: List<GroupLocalItem>): String {
        return Gson().toJson(from)
    }
}

