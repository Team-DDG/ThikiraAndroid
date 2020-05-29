package com.dsm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.db.entity.MenuEntity

@Dao
interface MenuDao {

    @Query("select * from Menu where menuCategoryId = :menuCategoryId")
    fun observeMenusById(menuCategoryId: Int): LiveData<List<MenuEntity>>

    @Query("select * from Menu where menuCategoryId = :menuCategoryId")
    suspend fun menusById(menuCategoryId: Int): List<MenuEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menus: List<MenuEntity>)

    @Query("delete from Menu where menuCategoryId = :menuCategoryId")
    suspend fun deleteByMenuCategoryId(menuCategoryId: Int)

    @Query("delete from Menu where menuId = :menuId")
    suspend fun deleteMenuByMenuId(menuId: Int)
}