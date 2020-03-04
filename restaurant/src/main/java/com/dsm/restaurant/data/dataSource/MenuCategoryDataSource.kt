package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.local.dao.MenuCategoryDao
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.remote.ThikiraApi
import com.dsm.restaurant.data.remote.dto.MenuCategoryDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MenuCategoryDataSource {

    suspend fun getRemoteMenuCategoryList(): List<MenuCategoryDto>

    suspend fun getLocalMenuCategoryList(): List<MenuCategoryLocalDto>?

    suspend fun insertLocalMenuCategoryList(menuCategoryList: List<MenuCategoryLocalDto>)

    suspend fun deleteAllLocalMenuCategory()

    suspend fun getLocalMenuCategoryIdByName(name: String): Int

    suspend fun deleteRemoteMenuCategoryList(menuCategoryList: List<Int>)

    suspend fun deleteLocalMenuCategory(menuCategory: Int)

    suspend fun updateRemoteMenuCategory(menuCategoryId: Int, name: String)

    suspend fun updateLocalMenuCategory(menuCategoryId: Int, name: String)
}

class MenuCategoryDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val menuCategoryDao: MenuCategoryDao,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuCategoryDataSource {

    override suspend fun getRemoteMenuCategoryList(): List<MenuCategoryDto> = withContext(ioDispatcher) {
        try {
            thikiraApi.getMenuCategoryList()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun getLocalMenuCategoryList(): List<MenuCategoryLocalDto>? = withContext(ioDispatcher) {
        menuCategoryDao.getMenuCategoryList()
    }

    override suspend fun insertLocalMenuCategoryList(menuCategoryList: List<MenuCategoryLocalDto>) = withContext(ioDispatcher) {
        menuCategoryDao.insertMenuCategoryList(menuCategoryList)
    }

    override suspend fun deleteAllLocalMenuCategory() = withContext(ioDispatcher) {
        menuCategoryDao.deleteAllMenuCategory()
    }

    override suspend fun getLocalMenuCategoryIdByName(name: String): Int = withContext(ioDispatcher) {
        menuCategoryDao.getMenuCategoryIdByName(name)
    }

    override suspend fun deleteRemoteMenuCategoryList(menuCategoryList: List<Int>) = withContext(ioDispatcher) {
        try {
            thikiraApi.deleteMenuCategoryList(menuCategoryList)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun deleteLocalMenuCategory(menuCategory: Int) = withContext(ioDispatcher) {
        menuCategoryDao.deleteMenuCategory(menuCategory)
    }

    override suspend fun updateRemoteMenuCategory(menuCategoryId: Int, name: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.updateMenuCategory(menuCategoryId, name)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun updateLocalMenuCategory(menuCategoryId: Int, name: String) = withContext(ioDispatcher) {
        menuCategoryDao.updateMenuCategory(name, menuCategoryId)
    }
}