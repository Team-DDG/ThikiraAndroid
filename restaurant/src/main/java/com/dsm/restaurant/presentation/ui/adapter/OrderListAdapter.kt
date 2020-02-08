package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.trashModel.OrderModel
import kotlinx.android.synthetic.main.item_order.view.*

class OrderListAdapter : ListAdapter<OrderModel, OrderListAdapter.OrderHolder>(OrderModel.DIFF_CALLBACK) {

    inner class OrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = getItem(adapterPosition)
            itemView.tv_order_item_address.text = item.address
            itemView.tv_order_item_method.text = item.method
            itemView.tv_order_item_nick.text = item.name
            itemView.tv_order_item_price.text = item.price
            itemView.tv_order_item_time.text = item.orderTime
            itemView.tv_order_item_status.text = item.status

            itemView.setOnClickListener {
                itemView.findNavController().navigate(R.id.action_orderFragment_to_orderDetailDialog)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder =
        OrderHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))

    override fun onBindViewHolder(holder: OrderHolder, position: Int) = holder.bind()
}

