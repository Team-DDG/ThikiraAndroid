package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.entity.MenuEntity

interface MenuRepository {

    suspend fun getMenuList(categoryName: String, forceUpdate: Boolean): List<MenuEntity>

    suspend fun uploadMenu(body: Any)
}