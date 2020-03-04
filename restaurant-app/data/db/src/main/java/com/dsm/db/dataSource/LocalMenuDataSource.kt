package com.dsm.db.dataSource

import androidx.lifecycle.LiveData
import com.dsm.db.dao.MenuDao
import com.dsm.db.entity.MenuEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalMenuDataSource {

    fun observeMenusById(menuCategoryId: Int): LiveData<List<MenuEntity>>

    suspend fun getMenusById(menuCategoryId: Int): List<MenuEntity>

    suspend fun insertMenus(menus: List<MenuEntity>)

    suspend fun deleteMenusByMenuCategoryId(menuCategoryId: Int)
}

class LocalMenuDataSourceImpl(
    private val menuDao: MenuDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalMenuDataSource {

    override fun observeMenusById(menuCategoryId: Int) =
        menuDao.observeMenusById(menuCategoryId)

    override suspend fun getMenusById(menuCategoryId: Int) = withContext(ioDispatcher) {
        menuDao.menusById(menuCategoryId)
    }

    override suspend fun insertMenus(menus: List<MenuEntity>) = withContext(ioDispatcher) {
        menuDao.insert(menus)
    }

    override suspend fun deleteMenusByMenuCategoryId(menuCategoryId: Int) = withContext(ioDispatcher) {
        menuDao.deleteByMenuCategoryId(menuCategoryId)
    }
}