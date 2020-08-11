package com.example.model.repository

import com.example.model.Menu
import com.example.model.MenuCategory

interface MenuRepository {
    suspend fun getMenuList(categoryId: Int): List<Menu>

    suspend fun getMenuCategory(restaurantId: Int): List<MenuCategory>
}