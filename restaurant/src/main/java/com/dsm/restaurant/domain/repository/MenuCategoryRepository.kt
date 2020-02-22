package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.entity.MenuCategoryEntity

interface MenuCategoryRepository {

    suspend fun getMenuCategoryList(forceUpdate: Boolean): List<MenuCategoryEntity>

    suspend fun deleteMenuCategoryList(menuCategoryList: List<Int>)

    suspend fun updateMenuCategory(menuCategoryId: Int, name: String)
}