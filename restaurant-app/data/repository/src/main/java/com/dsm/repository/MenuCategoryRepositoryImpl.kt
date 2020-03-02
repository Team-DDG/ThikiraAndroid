package com.dsm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dsm.api.dataSource.RemoteMenuCategoryDataSource
import com.dsm.api.response.MenuCategoryResponse
import com.dsm.db.dataSource.LocalMenuCategoryDataSource
import com.dsm.db.entity.MenuCategoryEntity
import com.dsm.mapper.toEntity
import com.dsm.mapper.toMenuCategory
import com.dsm.model.MenuCategory
import com.dsm.model.repository.MenuCategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuCategoryRepositoryImpl(
    private val remoteDataSource: RemoteMenuCategoryDataSource,
    private val localDataSource: LocalMenuCategoryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuCategoryRepository {

    override suspend fun observeMenuCategories(): LiveData<List<MenuCategory>> = withContext(ioDispatcher) {
        localDataSource.observeMenuCategories().map { it.map(MenuCategoryEntity::toMenuCategory) }
    }

    override suspend fun getMenuCategories(forceUpdate: Boolean): List<MenuCategory> = withContext(ioDispatcher) {
        if (forceUpdate) {
            localDataSource.deleteMenuCategories()
            remoteDataSource.getMenuCategories().let {
                localDataSource.insertMenuCategories(it.map(MenuCategoryResponse::toEntity))
                return@withContext it.map(MenuCategoryResponse::toMenuCategory)
            }
        }

        localDataSource.getMenuCategories().map(MenuCategoryEntity::toMenuCategory)
    }

    override suspend fun deleteMenuCategories(menuCategoriesId: List<Int>) = withContext(ioDispatcher) {
        remoteDataSource.deleteMenuCategories(menuCategoriesId)
        menuCategoriesId.forEach {
            localDataSource.deleteMenuCategoryById(it)
        }
    }

    override suspend fun updateMenuCategoryName(menuCategoryId: Int, name: String) = withContext(ioDispatcher) {
        remoteDataSource.updateMenuCategoryName(menuCategoryId, name)
        localDataSource.updateMenuCategoryName(menuCategoryId, name)
    }
}