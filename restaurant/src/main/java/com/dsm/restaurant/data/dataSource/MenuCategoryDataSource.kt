package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.remote.dto.MenuCategoryDto

interface MenuCategoryDataSource {

    suspend fun getRemoteMenuCategoryList(): List<MenuCategoryDto>

    suspend fun getLocalMenuCategoryList(): List<MenuCategoryLocalDto>?

    suspend fun insertLocalMenuCategoryList(menuCategoryList: List<MenuCategoryLocalDto>)

    suspend fun deleteAllLocalMenuCategory()

    suspend fun getMenuCategoryIdFromName(name: String): Int

    suspend fun deleteRemoteMenuCategoryList(menuCategoryList: List<Int>)

    suspend fun deleteLocalMenuCategory(menuCategory: Int)

    suspend fun updateRemoteMenuCategory(name: String, menuCategoryId: Int)

    suspend fun updateLocalMenuCategory(name: String, menuCategoryId: Int)
}