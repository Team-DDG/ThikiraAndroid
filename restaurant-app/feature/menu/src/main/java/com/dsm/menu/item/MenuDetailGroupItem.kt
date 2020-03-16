package com.dsm.menu.item

import com.dsm.menu.R
import com.dsm.menu.databinding.ItemMenuDetailGroupBinding
import com.dsm.model.MenuGroupItem
import com.xwray.groupie.databinding.BindableItem

class MenuDetailGroupItem(
    private val menuGroupItem: MenuGroupItem
) : BindableItem<ItemMenuDetailGroupBinding>() {

    override fun getLayout(): Int = R.layout.item_menu_detail_group

    override fun bind(viewBinding: ItemMenuDetailGroupBinding, position: Int) {
        viewBinding.menuGroup = menuGroupItem
    }
}
