package com.dsm.order.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.model.Order
import com.dsm.order.item.OrderItem
import com.xwray.groupie.GroupAdapter

@BindingAdapter("orders")
fun RecyclerView.bindOrders(orderLiveData: LiveData<List<Order>>) {
    orderLiveData.value?.let {
        (this.adapter as GroupAdapter).update(it.map { OrderItem(it) })
    }
}