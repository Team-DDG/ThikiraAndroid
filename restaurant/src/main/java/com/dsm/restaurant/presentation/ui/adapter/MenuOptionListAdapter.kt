package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.trashModel.OptionModel
import kotlinx.android.synthetic.main.item_menu_option.view.*

class MenuOptionListAdapter : RecyclerView.Adapter<MenuOptionListAdapter.OptionHolder>() {

    var optionItems = listOf<OptionModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class OptionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = optionItems[adapterPosition]
            itemView.tv_menu_option_item_option.text = item.option
            itemView.tv_menu_option_item_price.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionHolder =
        OptionHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu_option, parent, false))

    override fun getItemCount(): Int = optionItems.size

    override fun onBindViewHolder(holder: OptionHolder, position: Int) = holder.bind()
}