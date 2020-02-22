package com.dsm.restaurant.domain.entity

import com.dsm.restaurant.data.remote.dto.MenuRegistrationDto
import com.dsm.restaurant.data.remote.dto.MenuRegistrationGroupDto
import com.dsm.restaurant.data.remote.dto.MenuRegistrationOptionDto

data class MenuRegistrationEntity(
    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<MenuRegistrationGroupEntity>
) {
    fun toDto() = MenuRegistrationDto(
        menuCategoryId = menuCategoryId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map(MenuRegistrationGroupEntity::toDto)
    )
}

data class MenuRegistrationGroupEntity(
    val name: String,

    val maxCount: Int,

    val option: List<MenuRegistrationOptionEntity>
) {
    fun toDto() = MenuRegistrationGroupDto(
        name = name,
        maxCount = maxCount,
        option = option.map(MenuRegistrationOptionEntity::toDto)
    )
}

data class MenuRegistrationOptionEntity(

    val name: String,

    val price: Int
) {
    fun toDto() = MenuRegistrationOptionDto(
        name = name,
        price = price
    )
}