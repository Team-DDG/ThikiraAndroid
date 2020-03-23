package com.dsm.order.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.model.Order
import com.dsm.model.OrderDetailItem
import com.dsm.order.item.OrderItem
import com.dsm.order.item.OrderedGroupItem
import com.dsm.order.item.OrderedItem
import com.dsm.order.item.OrderedOptionItem
import com.xwray.groupie.GroupAdapter

@BindingAdapter("orders")
fun RecyclerView.bindOrders(orderLiveData: LiveData<List<Order>>) {
    orderLiveData.value?.let {
        (this.adapter as GroupAdapter).update(it.map { OrderItem(it) })
    }
}

@BindingAdapter("ordered")
fun RecyclerView.bindOrdered(orderDetails: List<OrderDetailItem>) {
    val adapter = this.adapter as GroupAdapter
    orderDetails.forEach {
        adapter.add(OrderedItem(it))
        it.group.forEach {
            adapter.add(OrderedGroupItem(it))
            it.option.forEach {
                adapter.add(OrderedOptionItem(it))
            }
        }
    }
}