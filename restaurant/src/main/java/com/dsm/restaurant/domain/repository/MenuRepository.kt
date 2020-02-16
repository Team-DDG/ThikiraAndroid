package com.dsm.restaurant.domain.repository

import com.dsm.restaurant.domain.model.MenuModel

interface MenuRepository {

    suspend fun getMenuList(categoryName: String, forceUpdate: Boolean): List<MenuModel>

    suspend fun uploadMenu(body: Any)
}