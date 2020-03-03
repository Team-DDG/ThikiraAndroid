package com.dsm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dsm.api.dataSource.RemoteMenuDataSource
import com.dsm.db.dataSource.LocalMenuDataSource
import com.dsm.db.entity.MenuEntity
import com.dsm.mapper.toMenu
import com.dsm.mapper.toMenuEntity
import com.dsm.mapper.toRequest
import com.dsm.model.Menu
import com.dsm.model.MenuRegistration
import com.dsm.model.repository.MenuRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepositoryImpl(
    private val localDataSource: LocalMenuDataSource,
    private val remoteDataSource: RemoteMenuDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuRepository {

    override fun observeMenusByMenuCategoryId(menuCategoryId: Int): LiveData<List<Menu>> =
        localDataSource.observeMenusById(menuCategoryId).map { it.map(MenuEntity::toMenu) }

    override suspend fun refreshMenus(menuCategoryId: Int) = withContext(ioDispatcher) {
        remoteDataSource.getMenusByMenuCategory(menuCategoryId).let {
            localDataSource.deleteMenusByMenuCategoryId(menuCategoryId)
            localDataSource.insertMenus(it.map { it.toMenuEntity(menuCategoryId) })
        }
    }

    override suspend fun uploadMenu(menuRegistration: MenuRegistration) = withContext(ioDispatcher) {
        remoteDataSource.uploadMenu(menuRegistration.toRequest())
    }
}