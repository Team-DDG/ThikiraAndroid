package com.dsm.order.item

import com.dsm.model.OrderGroupItem
import com.dsm.order.R
import com.dsm.order.databinding.ItemOrderedGroupBinding
import com.xwray.groupie.databinding.BindableItem

class OrderedGroupItem(
    private val orderGroupItem: OrderGroupItem
) : BindableItem<ItemOrderedGroupBinding>() {
    override fun getLayout(): Int = R.layout.item_ordered_group

    override fun bind(viewBinding: ItemOrderedGroupBinding, position: Int) {
        viewBinding.orderGroup = orderGroupItem
    }

}