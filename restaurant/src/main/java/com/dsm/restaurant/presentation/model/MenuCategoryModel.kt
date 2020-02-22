package com.dsm.restaurant.presentation.model

import androidx.recyclerview.widget.DiffUtil

data class MenuCategoryModel(

    val menuCategoryId: Int,

    val name: String,

    var isChecked: Boolean = false
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MenuCategoryModel>() {
            override fun areItemsTheSame(oldItem: MenuCategoryModel, newItem: MenuCategoryModel): Boolean =
                oldItem.menuCategoryId == newItem.menuCategoryId

            override fun areContentsTheSame(oldItem: MenuCategoryModel, newItem: MenuCategoryModel): Boolean =
                oldItem.name == newItem.name && oldItem.isChecked == newItem.isChecked

        }
    }
}