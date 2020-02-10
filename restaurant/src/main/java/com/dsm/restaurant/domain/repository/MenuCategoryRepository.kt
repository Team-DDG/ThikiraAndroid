package com.dsm.restaurant.domain.repository

interface MenuCategoryRepository {

    suspend fun getMenuCategoryList(forceUpdate: Boolean): List<String>
}