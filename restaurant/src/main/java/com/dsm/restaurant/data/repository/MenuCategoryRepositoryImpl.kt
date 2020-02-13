package com.dsm.restaurant.data.repository

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.remote.dto.MenuCategoryDto
import com.dsm.restaurant.domain.repository.MenuCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuCategoryRepositoryImpl(
    private val menuCategoryDataSource: MenuCategoryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuCategoryRepository {

    override suspend fun getMenuCategoryList(forceUpdate: Boolean) = withContext(ioDispatcher) {
        if (!forceUpdate) {
            menuCategoryDataSource.getLocalMenuCategoryList()?.let {
                if (it.isNotEmpty()) return@withContext it.map(MenuCategoryLocalDto::toModel)
            }
        }

        menuCategoryDataSource.getRemoteMenuCategoryList().let {
            menuCategoryDataSource.deleteAllLocalMenuCategory()
            menuCategoryDataSource.insertLocalMenuCategoryList(it.map(MenuCategoryDto::toLocalDto))
            return@withContext it.map(MenuCategoryDto::toModel)
        }
    }

    override suspend fun deleteMenuCategoryList(menuCategoryList: List<Int>) = withContext(ioDispatcher) {
        try {
            menuCategoryDataSource.deleteRemoteMenuCategoryList(menuCategoryList)
        } catch (e: Exception) {
            throw e // Don't delete local menu categories when remote failed!!
        }

        menuCategoryList.forEach {
            menuCategoryDataSource.deleteLocalMenuCategory(it)
        }
    }

    override suspend fun updateMenuCategory(menuCategoryId: Int, name: String) = withContext(ioDispatcher) {
        try {
            menuCategoryDataSource.updateRemoteMenuCategory(name, menuCategoryId)
        } catch (e: Exception) {
            throw e // Don't update local menu category when remote failed!!
        }

        menuCategoryDataSource.updateLocalMenuCategory(name, menuCategoryId)
    }
}