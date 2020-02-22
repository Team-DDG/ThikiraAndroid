package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.local.dao.MenuDao
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.remote.ThikiraApi
import com.dsm.restaurant.data.remote.dto.MenuDto
import com.dsm.restaurant.data.remote.dto.MenuRegistrationDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MenuDataSource {

    suspend fun getRemoteMenuList(menuCategoryId: Int): List<MenuDto>

    suspend fun getLocalMenuList(menuCategoryId: Int): List<MenuLocalDto>?

    suspend fun insertLocalMenuList(menuList: List<MenuLocalDto>)

    suspend fun deleteAllLocalMenu(menuCategoryId: Int)

    suspend fun uploadRemoteMenu(menuRegistrationDto: MenuRegistrationDto)
}

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

    override suspend fun uploadRemoteMenu(menuRegistrationDto: MenuRegistrationDto) = withContext(ioDispatcher) {
        try {
            thikiraApi.uploadMenu(menuRegistrationDto)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}