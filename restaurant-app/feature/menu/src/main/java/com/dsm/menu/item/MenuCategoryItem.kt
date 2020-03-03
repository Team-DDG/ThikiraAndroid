package com.dsm.menu.item

import com.dsm.menu.R
import com.dsm.menu.databinding.ItemMenuCategoryBinding
import com.dsm.menu.viewModel.MenuCategorySelectViewModel
import com.dsm.model.MenuCategory
import com.xwray.groupie.databinding.BindableItem

class MenuCategoryItem(
    private val menuCategory: MenuCategory,
    private val menuCategorySelectViewModel: MenuCategorySelectViewModel
) : BindableItem<ItemMenuCategoryBinding>() {

    override fun getLayout(): Int = R.layout.item_menu_category

    override fun bind(viewBinding: ItemMenuCategoryBinding, position: Int) {
        viewBinding.menuCategory = menuCategory
        viewBinding.menuCategorySelectViewModel = menuCategorySelectViewModel
    }
}