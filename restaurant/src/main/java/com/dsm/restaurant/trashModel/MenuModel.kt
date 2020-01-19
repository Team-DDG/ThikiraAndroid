package com.dsm.restaurant.trashModel

import androidx.recyclerview.widget.DiffUtil

data class MenuModel(

    val menuId: Int,

    val image: String,

    val name: String,

    val price: String
) {

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<MenuModel>() {
            override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
                oldItem.menuId == newItem.menuId

            override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean =
                oldItem == newItem

        }
    }

    override fun equals(other: Any?): Boolean =
        if (other is MenuModel) (image == other.image &&
                name == other.name &&
                price == other.price)
        else false

    override fun hashCode(): Int {
        var result = image.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}