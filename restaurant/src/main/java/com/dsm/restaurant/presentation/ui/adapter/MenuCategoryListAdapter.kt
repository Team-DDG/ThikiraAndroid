package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.databinding.ItemMenuCategoryBinding
import com.dsm.restaurant.databinding.ItemMenuCategoryDeleteBinding
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.main.menu.category.MenuCategoryListViewModel

class MenuCategoryListAdapter(
    private val viewModel: MenuCategoryListViewModel
) : ListAdapter<MenuCategoryModel, RecyclerView.ViewHolder>(MenuCategoryModel.DIFF_CALLBACK) {

    companion object {
        const val NORMAL_TYPE = 0
        const val DELETE_TYPE = 1
    }

    var viewType = NORMAL_TYPE
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MenuCategoryHolder(val binding: ItemMenuCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.menuCategoryModel = getItem(adapterPosition)
        }
    }

    inner class DeleteMenuCategoryHolder(val binding: ItemMenuCategoryDeleteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.viewModel = viewModel
            binding.menuCategoryModel = getItem(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            NORMAL_TYPE -> MenuCategoryHolder(ItemMenuCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> DeleteMenuCategoryHolder(ItemMenuCategoryDeleteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is MenuCategoryHolder -> holder.bind()
            is DeleteMenuCategoryHolder -> holder.bind()
            else -> Unit
        }

    override fun getItemViewType(position: Int): Int = viewType
}