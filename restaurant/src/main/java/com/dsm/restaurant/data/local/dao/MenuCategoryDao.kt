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

    @Query("select * from MenuCategoryLocalDto")
    suspend fun getMenuCategoryList(): List<MenuCategoryLocalDto>?

    @Query("select menuCategoryId from MenuCategoryLocalDto where name = :name")
    suspend fun getMenuCategoryIdFromName(name: String): Int

    @Query("delete from MenuCategoryLocalDto")
    suspend fun deleteAllMenuCategory()
}