package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsm.restaurant.domain.model.GroupModel
import com.dsm.restaurant.domain.model.MenuModel
import com.dsm.restaurant.domain.model.OptionModel

@Entity
data class MenuLocalDto(

    @PrimaryKey
    val menuId: Int,

    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupLocalItem>
) {
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

data class GroupLocalItem(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionLocalItem>
)

data class OptionLocalItem(

    val optionId: Int,

    val name: String,

    val price: Int
)