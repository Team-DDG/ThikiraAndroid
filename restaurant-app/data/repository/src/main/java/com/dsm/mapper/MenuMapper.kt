package com.dsm.mapper

import com.dsm.api.response.MenuResponse
import com.dsm.db.entity.GroupItem
import com.dsm.db.entity.MenuEntity
import com.dsm.db.entity.OptionItem
import com.dsm.model.Group
import com.dsm.model.Menu
import com.dsm.model.Option

internal fun MenuEntity.toMenu() =
    Menu(
        menuId = menuId,
        name = name,
        description = description,
        price = price,
        image = image,
        group = group.map { groupItem ->
            Group(
                groupId = groupItem.groupId,
                name = groupItem.name,
                maxCount = groupItem.maxCount,
                option = groupItem.option.map { optionItem ->
                    Option(
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
            GroupItem(
                groupId = groupItem.groupId,
                name = groupItem.name,
                maxCount = groupItem.maxCount,
                option = groupItem.option.map { optionItem ->
                    OptionItem(
                        optionId = optionItem.optionId,
                        name = optionItem.name,
                        price = optionItem.price
                    )
                }
            )
        }
    )