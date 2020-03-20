package com.dsm.menu.item

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.dsm.menu.R
import com.dsm.menu.databinding.ItemMenuBinding
import com.dsm.model.Menu
import com.xwray.groupie.databinding.BindableItem

class MenuItem(
    private val menu: Menu
) : BindableItem<ItemMenuBinding>() {

    override fun getLayout(): Int = R.layout.item_menu

    override fun bind(viewBinding: ItemMenuBinding, position: Int) {
        viewBinding.menu = menu
        viewBinding.root.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_menuFragment_to_menuDetailFragment,
                bundleOf("menu" to menu)
            )
        }
    }
}