package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.ItemMenuBinding
import com.dsm.restaurant.domain.model.MenuModel

class MenuListAdapter : ListAdapter<MenuModel, MenuListAdapter.MenuHolder>(MenuModel.DIFF_CALLBACK) {

    inner class MenuHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)
            binding.menuModel = item

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_menuListFragment_to_menuDetailFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder =
        MenuHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MenuHolder, position: Int) = holder.bind()
}