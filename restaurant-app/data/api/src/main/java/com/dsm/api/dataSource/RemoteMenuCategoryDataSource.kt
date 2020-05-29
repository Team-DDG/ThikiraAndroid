package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.api.response.MenuCategoryResponse
import com.dsm.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteMenuCategoryDataSource {

    suspend fun getMenuCategories(): List<MenuCategoryResponse>

    suspend fun deleteMenuCategories(menuCategories: List<Int>)

    suspend fun updateMenuCategoryName(menuCategoryId: Int, menuCategoryName: String)

    suspend fun addMenuCategory(menuCategory: String): Int
}

class RemoteMenuCategoryDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteMenuCategoryDataSource {

    override suspend fun getMenuCategories(): List<MenuCategoryResponse> = withContext(ioDispatcher) {
        try {
            thikiraApi.getMenuCategories()
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun deleteMenuCategories(menuCategories: List<Int>) = withContext(ioDispatcher) {
        try {
            thikiraApi.deleteMenuCategories(menuCategories)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun updateMenuCategoryName(menuCategoryId: Int, menuCategoryName: String) = withContext(ioDispatcher) {
        try {
            thikiraApi.updateMenuCategoryName(menuCategoryId, menuCategoryName)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun addMenuCategory(menuCategory: String): Int = withContext(ioDispatcher) {
        try {
            thikiraApi.addMenuCategory(menuCategory)["mc_id"] ?: 0
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}