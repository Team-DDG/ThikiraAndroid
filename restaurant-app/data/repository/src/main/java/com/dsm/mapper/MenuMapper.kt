package com.dsm.mapper

import com.dsm.api.response.MenuResponse
import com.dsm.db.entity.MenuEntity
import com.dsm.model.Menu
import com.dsm.model.MenuGroupItem
import com.dsm.model.MenuOptionItem

internal fun MenuEntity.toMenu() =
    Menu(
        menuId = menuId,
        name = name,
        description = description,
        price = price,
        image = image,
        group = group.map { groupItem ->
            MenuGroupItem(
                groupId = groupItem.groupId,
                name = groupItem.name,
                maxCount = groupItem.maxCount,
                option = groupItem.option.map { optionItem ->
                    MenuOptionItem(
                        optionId = optionItem.optionId,
                        name = optionItem.name,
                        price = optionItem.price
                    )
                }
            )
        }
    )

internal fun MenuResponse.toMenuEntity(menuCategoryId: Int): MenuEntity =
    MenuEntity(
        menuId = menuId,
        menuCategoryId = menuCategoryId,
        name = name,
        description = description,
        price = price,
        image = image,
        group = group.map { groupItem ->
            com.dsm.db.entity.MenuGroupItem(
                groupId = groupItem.groupId,
                name = groupItem.name,
                maxCount = groupItem.maxCount,
                option = groupItem.option.map { optionItem ->
                    com.dsm.db.entity.MenuOptionItem(
                        optionId = optionItem.optionId,
                        name = optionItem.name,
                        price = optionItem.price
                    )
                }
            )
        }
    )