package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.trashModel.MenuModel
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuListAdapter : ListAdapter<MenuModel, MenuListAdapter.MenuHolder>(MenuModel.DIFF_CALLBACK) {

    inner class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = getItem(adapterPosition)
            itemView.tv_menu_name.text = item.name
            itemView.tv_menu_price.text = item.price

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_menuFragment_to_menuDetailFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder =
        MenuHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false))

    override fun onBindViewHolder(holder: MenuHolder, position: Int) = holder.bind()
}