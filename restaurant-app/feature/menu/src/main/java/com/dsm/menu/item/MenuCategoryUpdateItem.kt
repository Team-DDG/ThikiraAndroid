package com.dsm.menu.item

import com.dsm.menu.R
import com.dsm.menu.databinding.ItemMenuCategoryUpdateBinding
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.dsm.model.MenuCategory
import com.xwray.groupie.databinding.BindableItem

class MenuCategoryUpdateItem(
    private val menuCategory: MenuCategory,
    private val viewModel: MenuCategoryViewModel
) : BindableItem<ItemMenuCategoryUpdateBinding>() {

    override fun getLayout(): Int = R.layout.item_menu_category_update

    override fun bind(viewBinding: ItemMenuCategoryUpdateBinding, position: Int) {
        viewBinding.menuCategory = menuCategory

        viewBinding.ivUpdate.setOnClickListener {
            viewBinding.vsMenuCategory.showNext()
        }

        viewBinding.tvUpdate.setOnClickListener {
            val input = viewBinding.etName.text.toString()
            if (input.isNotBlank()) {
                viewModel.onClickUpdate(menuCategory.menuCategoryId, input)
                viewBinding.vsMenuCategory.showPrevious()
            }
        }
    }

}