package com.dsm.mapper

import com.dsm.api.response.MenuCategoryResponse
import com.dsm.db.entity.MenuCategoryEntity
import com.dsm.model.MenuCategory

internal fun MenuCategoryEntity.toMenuCategory() =
    MenuCategory(
        menuCategoryId = menuCategoryId,
        name = name
    )

internal fun MenuCategoryResponse.toMenuCategory() =
    MenuCategory(
        menuCategoryId = menuCategoryId,
        name = name
    )

internal fun MenuCategoryResponse.toMenuEntity() =
    MenuCategoryEntity(
        menuCategoryId = menuCategoryId,
        name = name
    )