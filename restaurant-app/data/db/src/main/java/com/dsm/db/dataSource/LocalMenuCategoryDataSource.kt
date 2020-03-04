package com.dsm.db.dataSource

import androidx.lifecycle.LiveData
import com.dsm.db.dao.MenuCategoryDao
import com.dsm.db.entity.MenuCategoryEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalMenuCategoryDataSource {

    fun observeMenuCategories(): LiveData<List<MenuCategoryEntity>>

    suspend fun getMenuCategories(): List<MenuCategoryEntity>

    suspend fun deleteMenuCategories()

    suspend fun deleteMenuCategoryById(menuCategoryId: Int)

    suspend fun updateMenuCategoryName(menuCategoryId: Int, menuCategoryName: String)

    suspend fun insertMenuCategory(menuCategory: MenuCategoryEntity)

    suspend fun insertMenuCategories(menuCategories: List<MenuCategoryEntity>)
}

class LocalMenuCategoryDataSourceImpl(
    private val menuCategoryDao: MenuCategoryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalMenuCategoryDataSource {

    override fun observeMenuCategories() =
        menuCategoryDao.observeMenuCategories()

    override suspend fun getMenuCategories() = withContext(ioDispatcher) {
        menuCategoryDao.menuCategories()
    }

    override suspend fun deleteMenuCategories() = withContext(ioDispatcher) {
        menuCategoryDao.delete()
    }

    override suspend fun deleteMenuCategoryById(menuCategoryId: Int) = withContext(ioDispatcher) {
        menuCategoryDao.deleteById(menuCategoryId)
    }

    override suspend fun updateMenuCategoryName(menuCategoryId: Int, menuCategoryName: String) = withContext(ioDispatcher) {
        menuCategoryDao.updateName(menuCategoryId, menuCategoryName)
    }

    override suspend fun insertMenuCategory(menuCategory: MenuCategoryEntity) = withContext(ioDispatcher) {
        menuCategoryDao.insert(menuCategory)
    }

    override suspend fun insertMenuCategories(menuCategories: List<MenuCategoryEntity>) = withContext(ioDispatcher) {
        menuCategoryDao.insert(menuCategories)
    }

}