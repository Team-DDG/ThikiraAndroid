package com.dsm.order.item

import com.dsm.model.OrderOptionItem
import com.dsm.order.R
import com.dsm.order.databinding.ItemOrderedOptionBinding
import com.xwray.groupie.databinding.BindableItem

class OrderedOptionItem(
    private val orderOptionItem: OrderOptionItem
) : BindableItem<ItemOrderedOptionBinding>() {

    override fun getLayout(): Int = R.layout.item_ordered_option

    override fun bind(viewBinding: ItemOrderedOptionBinding, position: Int) {
        viewBinding.orderOption = orderOptionItem
    }

}