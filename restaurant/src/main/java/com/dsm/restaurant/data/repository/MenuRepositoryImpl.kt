package com.dsm.restaurant.data.repository

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
import com.dsm.restaurant.data.dataSource.MenuDataSource
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.remote.dto.MenuDto
import com.dsm.restaurant.domain.repository.MenuRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepositoryImpl(
    private val menuDataSource: MenuDataSource,
    private val menuCategoryDataSource: MenuCategoryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuRepository {

    override suspend fun getMenuList(categoryName: String, forceUpdate: Boolean) = withContext(ioDispatcher) {
        val menuCategoryId = menuCategoryDataSource.getMenuCategoryIdFromName(categoryName)
        if (!forceUpdate) {
            menuDataSource.getLocalMenuList(menuCategoryId)?.let {
                if (it.isNotEmpty()) return@withContext it.map(MenuLocalDto::toModel)
            }
        }

        menuDataSource.getRemoteMenuList(menuCategoryId).let {
            menuDataSource.deleteAllLocalMenu(menuCategoryId)
            menuDataSource.insertLocalMenuList(it.map { it.toLocalDto(menuCategoryId) })
            return@withContext it.map(MenuDto::toModel)
        }
    }

    override suspend fun uploadMenu(body: Any) = withContext(ioDispatcher) {
        try {
            val menuId = menuDataSource.uploadRemoteMenu(body)

            (body as MutableMap<String, Any>)["menuId"] = menuId

            menuDataSource.insertLocalMenu(Gson().run {
                fromJson(toJsonTree(body), MenuLocalDto::class.java)
            })
        } catch (e: Exception) {
            throw e
        }
    }
}