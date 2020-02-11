package com.dsm.restaurant.domain.model

import androidx.recyclerview.widget.DiffUtil

data class MenuModel(

    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupModel>
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MenuModel>() {
            override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
                oldItem.menuId == newItem.menuId

            override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
                oldItem.name == newItem.name && oldItem.price == newItem.price
                        && oldItem.description == newItem.description && oldItem.image == newItem.image

        }
    }
}

data class GroupModel(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionModel>
)

data class OptionModel(

    val optionId: Int,

    val name: String,

    val price: Int
)