package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsm.restaurant.domain.entity.GroupEntity
import com.dsm.restaurant.domain.entity.MenuEntity
import com.dsm.restaurant.domain.entity.OptionEntity

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
    fun toEntity() = MenuEntity(
        menuId = menuId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map(GroupLocalDto::toEntity)
    )
}

data class GroupLocalDto(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionLocalDto>
) {
    fun toEntity() = GroupEntity(
        groupId = groupId,
        name = name,
        maxCount = maxCount,
        option = option.map(OptionLocalDto::toEntity)
    )
}

data class OptionLocalDto(

    val optionId: Int,

    val name: String,

    val price: Int
) {
    fun toEntity() = OptionEntity(
        optionId = optionId,
        price = price,
        name = name
    )
}