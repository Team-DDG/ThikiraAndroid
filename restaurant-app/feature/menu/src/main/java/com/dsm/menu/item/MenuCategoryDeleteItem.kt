package com.dsm.menu.item

import com.dsm.menu.R
import com.dsm.menu.databinding.ItemMenuCategoryDeleteBinding
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.dsm.model.MenuCategory
import com.xwray.groupie.databinding.BindableItem

class MenuCategoryDeleteItem(
    private val menuCategory: MenuCategory,
    private val viewModel: MenuCategoryViewModel
) : BindableItem<ItemMenuCategoryDeleteBinding>() {

    override fun getLayout(): Int = R.layout.item_menu_category_delete

    override fun bind(viewBinding: ItemMenuCategoryDeleteBinding, position: Int) {
        viewBinding.menuCategory = menuCategory
        viewBinding.cbDelete.setOnClickListener {
            viewModel.onClickDeleteCheckBox(menuCategory.menuCategoryId, viewBinding.cbDelete.isChecked)
        }
    }

}