package com.dsm.restaurant.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.restaurant.data.local.dto.MenuLocalDto

@Dao
interface MenuDao {

    @Query("select * from MenuLocalDto where menuCategoryId = :menuCategoryId")
    suspend fun getMenuList(menuCategoryId: Int): List<MenuLocalDto>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuList(menuList: List<MenuLocalDto>)

    @Query("delete from MenuLocalDto where menuCategoryId = :menuCategoryId")
    suspend fun deleteAllMenuByMenuCategoryId(menuCategoryId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menu: MenuLocalDto)
}