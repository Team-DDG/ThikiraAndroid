package com.example.model.repository

import com.example.model.Menu
import com.example.model.MenuCategory

interface MenuRepository {
    suspend fun getMenuList(categoryId: String): List<Menu>

    suspend fun getMenuCategory(restaurantId: String): List<MenuCategory>
}