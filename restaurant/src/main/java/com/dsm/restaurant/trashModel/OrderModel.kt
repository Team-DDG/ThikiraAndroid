package com.dsm.restaurant.trashModel

import androidx.recyclerview.widget.DiffUtil

data class OrderModel(

    val orderId: Int,

    val name: String,

    val address: String,

    val orderTime: String,

    val price: String,

    val method: String,

    val status: String
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OrderModel>() {
            override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean =
                oldItem.orderId == newItem.orderId

            override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean =
                oldItem == newItem
        }
    }

    override fun equals(other: Any?): Boolean =
        if (other is OrderModel)
            (name == other.name &&
                    address == other.address &&
                    orderTime == other.orderTime &&
                    price == other.price &&
                    method == other.method &&
                    status == other.status)
        else false


    override fun hashCode(): Int {
        var result = orderId
        result = 31 * result + name.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + orderTime.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + method.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}