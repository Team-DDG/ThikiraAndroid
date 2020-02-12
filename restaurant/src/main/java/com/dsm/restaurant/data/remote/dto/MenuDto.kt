package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.data.local.dto.GroupLocalItem
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.local.dto.OptionLocalItem
import com.dsm.restaurant.domain.model.GroupModel
import com.dsm.restaurant.domain.model.MenuModel
import com.dsm.restaurant.domain.model.OptionModel
import com.google.gson.annotations.SerializedName

data class MenuDto(

    @SerializedName("menu_id")
    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupItem>
) {
    fun toLocalDto(menuCategoryId: Int) = MenuLocalDto(
        menuId = menuId,
        menuCategoryId = menuCategoryId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map { group ->
            GroupLocalItem(
                groupId = group.groupId,
                name = group.name,
                maxCount = group.maxCount,
                option = group.option.map { option ->
                    OptionLocalItem(
                        optionId = option.optionId,
                        name = option.name,
                        price = option.price
                    )
                }
            )
        }
    )

    fun toModel() = MenuModel(
        menuId = menuId,
        name = name,
        price = price,
        description = description,
        image = image,
        group = group.map { group ->
            GroupModel(
                groupId = group.groupId,
                name = group.name,
                maxCount = group.maxCount,
                option = group.option.map { option ->
                    OptionModel(
                        optionId = option.optionId,
                        name = option.name,
                        price = option.price
                    )
                }
            )
        }
    )
}

data class GroupItem(

    @SerializedName("group_id")
    val groupId: Int,

    val name: String,

    @SerializedName("max_count")
    val maxCount: Int,

    val option: List<OptionItem>
)

data class OptionItem(

    @SerializedName("option_id")
    val optionId: Int,

    val name: String,

    val price: Int
)