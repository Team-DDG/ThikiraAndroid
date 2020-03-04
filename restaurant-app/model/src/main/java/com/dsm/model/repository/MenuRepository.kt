package com.dsm.model.repository

import androidx.lifecycle.LiveData
import com.dsm.model.Menu
import com.dsm.model.MenuRegistration

interface MenuRepository {

    fun observeMenusByMenuCategoryId(menuCategoryId: Int): LiveData<List<Menu>>

    suspend fun refreshMenus(menuCategoryId: Int)

    suspend fun uploadMenu(menuRegistration: MenuRegistration)
}