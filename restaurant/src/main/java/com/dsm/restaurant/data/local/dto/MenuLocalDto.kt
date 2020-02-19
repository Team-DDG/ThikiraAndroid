package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsm.restaurant.domain.model.GroupModel
import com.dsm.restaurant.domain.model.MenuModel
import com.dsm.restaurant.domain.model.OptionModel

@Entity(tableName = "Menu")
data class MenuLocalDto(

    @PrimaryKey
    val menuId: Int,

    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupLocalDto>
) {
    fun toModel() = MenuModel(
        menuId = menuId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map(GroupLocalDto::toModel)
    )
}

data class GroupLocalDto(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionLocalDto>
) {
    fun toModel() = GroupModel(
        groupId = groupId,
        name = name,
        maxCount = maxCount,
        option = option.map(OptionLocalDto::toModel)
    )
}

data class OptionLocalDto(

    val optionId: Int,

    val name: String,

    val price: Int
) {
    fun toModel() = OptionModel(
        optionId = optionId,
        price = price,
        name = name
    )
}