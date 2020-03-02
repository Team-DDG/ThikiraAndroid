package com.dsm.model.repository

import androidx.lifecycle.LiveData
import com.dsm.model.MenuCategory

interface MenuCategoryRepository {

    suspend fun observeMenuCategories(): LiveData<List<MenuCategory>>

    suspend fun getMenuCategories(forceUpdate: Boolean): List<MenuCategory>

    suspend fun deleteMenuCategories(menuCategoriesId: List<Int>)

    suspend fun updateMenuCategoryName(menuCategoryId: Int, name: String)
}