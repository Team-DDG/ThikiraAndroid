package com.example.api.datasource

import com.example.api.ThikiraApi
import com.example.error.ErrorHandler
import com.example.model.Menu
import com.example.model.MenuCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteMenuDataSource {
    suspend fun getMenuList(menuCategoryId: Int): List<Menu>

    suspend fun getMenuCategory(restaurantId: Int): List<MenuCategory>
}

class RemoteMenuDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteMenuDataSource {
    override suspend fun getMenuList(menuCategoryId: Int): List<Menu> = withContext(ioDispatcher) {
        try {
            thikiraApi.getMenuList(menuCategoryId)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun getMenuCategory(restaurantId: Int): List<MenuCategory> = withContext(ioDispatcher) {
        try {
            thikiraApi.getMenuCategory(restaurantId)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}