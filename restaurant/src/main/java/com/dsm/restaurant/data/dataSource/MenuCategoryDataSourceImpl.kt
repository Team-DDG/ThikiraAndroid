package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.local.dao.MenuCategoryDao
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto
import com.dsm.restaurant.data.remote.ThikiraApi
import com.dsm.restaurant.data.remote.dto.MenuCategoryDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}