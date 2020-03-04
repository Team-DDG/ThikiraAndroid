package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsm.restaurant.domain.entity.MenuCategoryEntity

@Entity(tableName = "MenuCategory")
data class MenuCategoryLocalDto(

    @PrimaryKey
    val menuCategoryId: Int,

    val name: String
) {
    fun toEntity() = MenuCategoryEntity(
        menuCategoryId = menuCategoryId,
        name = name
    )
}