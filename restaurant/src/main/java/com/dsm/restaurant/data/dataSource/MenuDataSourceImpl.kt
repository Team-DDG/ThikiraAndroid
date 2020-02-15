package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.local.dao.MenuDao
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.remote.ThikiraApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val menuDao: MenuDao,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuDataSource {
    override suspend fun getRemoteMenuList(menuCategoryId: Int) = withContext(ioDispatcher) {
        try {
            thikiraApi.getMenuList(menuCategoryId)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun getLocalMenuList(menuCategoryId: Int) = withContext(ioDispatcher) {
        menuDao.getMenuList(menuCategoryId)
    }

    override suspend fun insertLocalMenuList(menuList: List<MenuLocalDto>) = withContext(ioDispatcher) {
        menuDao.insertMenuList(menuList)
    }

    override suspend fun deleteAllLocalMenu(menuCategoryId: Int) = withContext(ioDispatcher) {
        menuDao.deleteAllMenuByMenuCategoryId(menuCategoryId)
    }

    override suspend fun uploadRemoteMenu(body: Any) = withContext(ioDispatcher) {
        try {
            thikiraApi.uploadMenu(body)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun insertLocalMenu(menu: MenuLocalDto) = withContext(ioDispatcher) {
        menuDao.insertMenu(menu)
    }
}