package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsm.restaurant.domain.model.MenuCategoryModel

@Entity
data class MenuCategoryLocalDto(

    @PrimaryKey
    val menuCategoryId: Int,

    val name: String
) {
    fun toModel() = MenuCategoryModel(
        menuCategoryId = menuCategoryId,
        name = name
    )
}