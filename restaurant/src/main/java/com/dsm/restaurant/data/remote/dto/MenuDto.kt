package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.data.local.dto.GroupLocalDto
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.local.dto.OptionLocalDto
import com.dsm.restaurant.domain.entity.GroupEntity
import com.dsm.restaurant.domain.entity.MenuEntity
import com.dsm.restaurant.domain.entity.OptionEntity
import com.google.gson.annotations.SerializedName

data class MenuDto(

    @SerializedName("menu_id")
    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupDto>
) {
    fun toLocalDto(menuCategoryId: Int) = MenuLocalDto(
        menuId = menuId,
        menuCategoryId = menuCategoryId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map(GroupDto::toLocalDto)
    )

    fun toEntity() = MenuEntity(
        menuId = menuId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map(GroupDto::toEntity)
    )
}

data class GroupDto(

    @SerializedName("group_id")
    val groupId: Int,

    val name: String,

    @SerializedName("max_count")
    val maxCount: Int,

    val option: List<OptionDto>
) {
    fun toLocalDto() = GroupLocalDto(
        groupId = groupId,
        name = name,
        maxCount = maxCount,
        option = option.map(OptionDto::toLocalDto)
    )

    fun toEntity() = GroupEntity(
        groupId = groupId,
        name = name,
        maxCount = maxCount,
        option = option.map(OptionDto::toEntity)
    )
}

data class OptionDto(

    @SerializedName("option_id")
    val optionId: Int,

    val name: String,

    val price: Int
) {
    fun toLocalDto() = OptionLocalDto(
        optionId = optionId,
        name = name,
        price = price
    )

    fun toEntity() = OptionEntity(
        optionId = optionId,
        name = name,
        price = price
    )
}