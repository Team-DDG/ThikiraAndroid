package com.dsm.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_order.view.*

class OrderListAdapter : ListAdapter<OrderModel, OrderListAdapter.OrderHolder>(OrderModel.DIFF_CALLBACK) {

    inner class OrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = getItem(adapterPosition)
            itemView.tv_order_addr.text = item.address
            itemView.tv_order_method.text = item.method
            itemView.tv_order_nick.text = item.name
            itemView.tv_order_price.text = item.price
            itemView.tv_order_time.text = item.orderTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder =
        OrderHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))

    override fun onBindViewHolder(holder: OrderHolder, position: Int) = holder.bind()
}

