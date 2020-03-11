package com.dsm.order.item

import com.dsm.model.Order
import com.dsm.order.R
import com.dsm.order.databinding.ItemOrderBinding
import com.xwray.groupie.databinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

class OrderItem(
    private val order: Order
) : BindableItem<ItemOrderBinding>() {

    override fun getLayout(): Int = R.layout.item_order

    override fun bind(viewBinding: ItemOrderBinding, position: Int) {
        val sdf = SimpleDateFormat("HH:mm", Locale.KOREA)
        viewBinding.order = order
        viewBinding.orderTime = sdf.format(order.orderTime)
    }

}