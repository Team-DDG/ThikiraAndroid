package com.dsm.restaurant.presentation.model

import com.dsm.restaurant.domain.entity.MenuRegistrationEntity
import com.dsm.restaurant.domain.entity.MenuRegistrationGroupEntity
import com.dsm.restaurant.domain.entity.MenuRegistrationOptionEntity

sealed class MenuRegistrationOptionModel {

    data class Group(val groupName: String, val maxCount: Int) : MenuRegistrationOptionModel()

    data class Option(val optionName: String, val price: Int, val parentGroup: String) : MenuRegistrationOptionModel()

    object AddGroup : MenuRegistrationOptionModel()
}

data class MenuRegistrationModel(
    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<MenuRegistrationOptionModel>
) {
    fun toEntity() = MenuRegistrationEntity(
        menuCategoryId = menuCategoryId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = mapOptionListToEntity(group)
    )

    private fun mapOptionListToEntity(group: List<MenuRegistrationOptionModel>): List<MenuRegistrationGroupEntity> {
        val list = arrayListOf<MenuRegistrationGroupEntity>()
        group.forEach {
            if (it is MenuRegistrationOptionModel.Group) {
                list.add(MenuRegistrationGroupEntity(name = it.groupName, maxCount = it.maxCount, option = arrayListOf()))
            } else if (it is MenuRegistrationOptionModel.Option) {
                (list[list.size - 1].option as ArrayList).add(MenuRegistrationOptionEntity(name = it.optionName, price = it.price))
            }
        }
        return list
    }
}

