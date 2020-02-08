package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.trashModel.OrderedModel
import kotlinx.android.synthetic.main.item_ordered.view.*
import kotlinx.android.synthetic.main.item_ordered_option.view.*

class OrderedListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ORDERED = 0
        private const val TYPE_OPTION = 1
    }

    var orderedItems = listOf<OrderedModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class OrderedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = orderedItems[adapterPosition] as OrderedModel.Ordered
            itemView.tv_ordered_item_name.text = item.name
            itemView.tv_ordered_item_amount.text = item.amount
            itemView.tv_ordered_item_price.text = item.price
        }
    }

    inner class OptionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = orderedItems[adapterPosition] as OrderedModel.Option
            itemView.tv_ordered_option_item_option_name.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ORDERED -> OrderedHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ordered, parent, false))
            else -> OptionHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ordered_option, parent, false))
        }

    override fun getItemCount(): Int = orderedItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is OrderedHolder -> holder.bind()
            is OptionHolder -> holder.bind()
            else -> Unit
        }

    override fun getItemViewType(position: Int): Int =
        when (orderedItems[position]) {
            is OrderedModel.Ordered -> TYPE_ORDERED
            else -> TYPE_OPTION
        }
}