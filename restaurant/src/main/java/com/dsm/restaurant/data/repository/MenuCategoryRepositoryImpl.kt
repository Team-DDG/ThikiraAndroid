package com.dsm.restaurant.data.repository

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
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
                return@withContext it.map { it.name }
            }
        }

        menuCategoryDataSource.getRemoteMenuCategoryList().let {
            menuCategoryDataSource.insertLocalMenuCategoryList(it.map { it.toLocalDto() })
            return@withContext menuCategoryDataSource.getRemoteMenuCategoryList().map { it.name }
        }
    }
}