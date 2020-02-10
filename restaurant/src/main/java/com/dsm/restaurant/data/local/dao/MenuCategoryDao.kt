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
}