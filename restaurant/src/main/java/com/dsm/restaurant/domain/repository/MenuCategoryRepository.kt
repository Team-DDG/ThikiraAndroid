package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.model.MenuCategoryModel

interface MenuCategoryRepository {

    suspend fun getMenuCategoryList(forceUpdate: Boolean): List<MenuCategoryModel>

    suspend fun deleteMenuCategoryList(menuCategoryList: List<Int>)
}