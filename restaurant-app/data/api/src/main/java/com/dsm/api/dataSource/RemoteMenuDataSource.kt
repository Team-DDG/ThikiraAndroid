package com.dsm.api.dataSource

import com.dsm.api.ThikiraApi
import com.dsm.api.request.MenuRegistrationRequest
import com.dsm.api.response.MenuResponse
import com.example.error.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteMenuDataSource {

    suspend fun getMenusByMenuCategory(menuCategoryId: Int): List<MenuResponse>

    suspend fun uploadMenu(menuRegistrationRequest: MenuRegistrationRequest)

    suspend fun deleteMenu(menuId: Int)
}

class RemoteMenuDataSourceImpl(
    private val thikiraApi: ThikiraApi,
    private val errorHandler: ErrorHandler,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteMenuDataSource {

    override suspend fun getMenusByMenuCategory(menuCategoryId: Int) = withContext(ioDispatcher) {
        try {
            thikiraApi.getMenus(menuCategoryId)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }


    override suspend fun uploadMenu(menuRegistrationRequest: MenuRegistrationRequest) = withContext(ioDispatcher) {
        try {
            thikiraApi.uploadMenu(menuRegistrationRequest)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }

    override suspend fun deleteMenu(menuId: Int) = withContext(ioDispatcher) {
        try {
            thikiraApi.deleteMenu(menuId)
        } catch (e: Exception) {
            throw errorHandler.getNetworkException(e)
        }
    }
}