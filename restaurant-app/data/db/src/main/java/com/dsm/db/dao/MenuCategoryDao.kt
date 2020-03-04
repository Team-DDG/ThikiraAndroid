package com.dsm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.db.entity.MenuCategoryEntity

@Dao
interface MenuCategoryDao {

    @Query("select * from MenuCategory")
    fun observeMenuCategories(): LiveData<List<MenuCategoryEntity>>

    @Query("select * from MenuCategory")
    suspend fun menuCategories(): List<MenuCategoryEntity>

    @Query("delete from MenuCategory")
    suspend fun delete()

    @Query("delete from MenuCategory where menuCategoryId = :menuCategoryId")
    suspend fun deleteById(menuCategoryId: Int)

    @Query("update MenuCategory set name = :name where menuCategoryId = :menuCategoryId")
    suspend fun updateName(menuCategoryId: Int, name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menuCategory: MenuCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(menuCategories: List<MenuCategoryEntity>)
}