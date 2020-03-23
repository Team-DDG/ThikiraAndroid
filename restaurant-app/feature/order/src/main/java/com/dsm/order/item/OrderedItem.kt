package com.dsm.order.item

import com.dsm.model.OrderDetailItem
import com.dsm.order.R
import com.dsm.order.databinding.ItemOrderedBinding
import com.xwray.groupie.databinding.BindableItem

class OrderedItem(
    private val orderGroupItem: OrderDetailItem
) : BindableItem<ItemOrderedBinding>() {

    override fun getLayout(): Int = R.layout.item_ordered

    override fun bind(viewBinding: ItemOrderedBinding, position: Int) {
        viewBinding.orderDetail = orderGroupItem
    }

}