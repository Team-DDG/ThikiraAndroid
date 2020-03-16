package com.dsm.menu.item

import com.dsm.menu.R
import com.dsm.menu.databinding.ItemMenuDetailOptionBinding
import com.dsm.model.MenuOptionItem
import com.xwray.groupie.databinding.BindableItem

class MenuDetailOptionItem(
    private val menuOptionItem: MenuOptionItem
) : BindableItem<ItemMenuDetailOptionBinding>() {

    override fun getLayout(): Int = R.layout.item_menu_detail_option

    override fun bind(viewBinding: ItemMenuDetailOptionBinding, position: Int) {
        viewBinding.menuOption = menuOptionItem
    }
}