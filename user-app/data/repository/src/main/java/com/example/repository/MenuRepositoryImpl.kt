package com.example.repository

import com.example.api.datasource.RemoteMenuDataSource
import com.example.model.Menu
import com.example.model.MenuCategory
import com.example.model.repository.MenuRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepositoryImpl (
    private val remoteMenuDataSource: RemoteMenuDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): MenuRepository {
    override suspend fun getMenuList(categoryId: Int): List<Menu> = withContext(ioDispatcher) {
        remoteMenuDataSource.getMenuList(categoryId)
    }

    override suspend fun getMenuCategory(restaurantId: Int): List<MenuCategory> = withContext(ioDispatcher) {
        remoteMenuDataSource.getMenuCategory(restaurantId)
    }
}