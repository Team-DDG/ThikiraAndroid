package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.remote.dto.MenuCategoryDto

interface MenuCategoryDataSource {

    suspend fun getRemoteMenuCategoryList(): List<MenuCategoryDto>

    suspend fun getLocalMenuCategoryList(): List<MenuCategoryLocalDto>?

    suspend fun insertLocalMenuCategoryList(menuCategoryList: List<MenuCategoryLocalDto>)

    suspend fun deleteAllLocalMenuCategory()

    suspend fun getLocalMenuCategoryIdByName(name: String): Int

    suspend fun deleteRemoteMenuCategoryList(menuCategoryList: List<Int>)

    suspend fun deleteLocalMenuCategory(menuCategory: Int)

    suspend fun updateRemoteMenuCategory(menuCategoryId: Int, name: String)

    suspend fun updateLocalMenuCategory(menuCategoryId: Int, name: String)
}