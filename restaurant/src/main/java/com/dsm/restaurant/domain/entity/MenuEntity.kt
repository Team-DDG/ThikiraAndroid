package com.dsm.restaurant.domain.entity

import com.dsm.restaurant.presentation.model.GroupModel
import com.dsm.restaurant.presentation.model.MenuModel
import com.dsm.restaurant.presentation.model.OptionModel

data class MenuEntity(

    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupEntity>
) {
    fun toModel() = MenuModel(
        menuId = menuId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map(GroupEntity::toModel)
    )
}

data class GroupEntity(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionEntity>
) {
    fun toModel() = GroupModel(
        groupId = groupId,
        name = name,
        maxCount = maxCount,
        option = option.map(OptionEntity::toModel)
    )
}

data class OptionEntity(

    val optionId: Int,

    val name: String,

    val price: Int
) {
    fun toModel() = OptionModel(
        optionId = optionId,
        name = name,
        price = price
    )
}