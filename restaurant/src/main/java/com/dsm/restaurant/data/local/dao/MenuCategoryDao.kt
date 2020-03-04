package com.dsm.restaurant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.restaurant.data.local.dto.MenuCategoryLocalDto

@Dao
interface MenuCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuCategoryList(menuCategoryList: List<MenuCategoryLocalDto>)

    @Query("select * from MenuCategory")
    suspend fun getMenuCategoryList(): List<MenuCategoryLocalDto>?

    @Query("select menuCategoryId from MenuCategory where name = :name")
    suspend fun getMenuCategoryIdByName(name: String): Int

    @Query("delete from MenuCategory")
    suspend fun deleteAllMenuCategory()

    @Query("delete from MenuCategory where menuCategoryId = :menuCategoryId")
    suspend fun deleteMenuCategory(menuCategoryId: Int)

    @Query("update MenuCategory set name = :name where menuCategoryId = :menuCategoryId")
    suspend fun updateMenuCategory(name: String, menuCategoryId: Int)
}