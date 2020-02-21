package com.dsm.restaurant.domain.entity

import com.dsm.restaurant.presentation.model.MenuCategoryModel

data class MenuCategoryEntity(

    val menuCategoryId: Int,

    val name: String
) {
    fun toModel() = MenuCategoryModel(
        menuCategoryId = menuCategoryId,
        name = name
    )
}