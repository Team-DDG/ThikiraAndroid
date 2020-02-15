package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.remote.dto.MenuDto

interface MenuDataSource {

    suspend fun getRemoteMenuList(menuCategoryId: Int): List<MenuDto>

    suspend fun getLocalMenuList(menuCategoryId: Int): List<MenuLocalDto>?

    suspend fun insertLocalMenuList(menuList: List<MenuLocalDto>)

    suspend fun deleteAllLocalMenu(menuCategoryId: Int)

    suspend fun uploadRemoteMenu(body: Any)

    suspend fun insertLocalMenu(menu: MenuLocalDto)
}