package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.remote.dto.MenuCategoryDto

interface MenuCategoryDataSource {

    suspend fun getRemoteMenuCategoryList(): List<MenuCategoryDto>

    suspend fun getLocalMenuCategoryList(): List<MenuCategoryLocalDto>?

    suspend fun insertLocalMenuCategoryList(menuCategoryList: List<MenuCategoryLocalDto>)

    suspend fun deleteAllLocalMenuCategory()

    suspend fun getMenuCategoryIdFromName(name: String): Int
}