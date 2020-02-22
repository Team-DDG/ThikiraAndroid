package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.databinding.ItemMenuCategoryBinding
import com.dsm.restaurant.databinding.ItemMenuCategoryDeleteBinding
import com.dsm.restaurant.databinding.ItemMenuCategoryUpdateBinding
import com.dsm.restaurant.presentation.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.menu.category.MenuCategoryListViewModel

class MenuCategoryListAdapter(
    private val viewModel: MenuCategoryListViewModel
) : ListAdapter<MenuCategoryModel, RecyclerView.ViewHolder>(MenuCategoryModel.DIFF_CALLBACK) {

    companion object {
        const val NORMAL_TYPE = 0
        const val DELETE_TYPE = 1
        const val UPDATE_TYPE = 2
    }

    var listViewType = NORMAL_TYPE
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MenuCategoryHolder(val binding: ItemMenuCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.menuCategoryModel = getItem(adapterPosition)
            binding.viewModel = viewModel
        }
    }

    inner class DeleteMenuCategoryHolder(val binding: ItemMenuCategoryDeleteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.menuCategoryModel = getItem(adapterPosition)
            binding.viewModel = viewModel
            binding.position = adapterPosition
        }
    }

    inner class UpdateMenuCategoryHolder(val binding: ItemMenuCategoryUpdateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val menuCategoryModel = getItem(adapterPosition)
            binding.viewModel = viewModel
            binding.menuCategoryModel = menuCategoryModel

            binding.ivMenuCategoryUpdateItemUpdate.setOnClickListener {
                binding.vsMenuCategoryUpdateItem.showNext()
            }

            binding.tvMenuCategoryUpdateItemUpdate.setOnClickListener {
                val input = binding.etMenuCategoryUpdateItemName.text.toString()
                if (input.isNotBlank()) {
                    viewModel.onClickUpdate(input, menuCategoryModel.menuCategoryId)
                    binding.vsMenuCategoryUpdateItem.showPrevious()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            NORMAL_TYPE -> MenuCategoryHolder(ItemMenuCategoryBinding.inflate(inflater, parent, false))
            DELETE_TYPE -> DeleteMenuCategoryHolder(ItemMenuCategoryDeleteBinding.inflate(inflater, parent, false))
            UPDATE_TYPE -> UpdateMenuCategoryHolder(ItemMenuCategoryUpdateBinding.inflate(inflater, parent, false))
            else -> MenuCategoryHolder(ItemMenuCategoryBinding.inflate(inflater, parent, false))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is MenuCategoryHolder -> holder.bind()
            is DeleteMenuCategoryHolder -> holder.bind()
            is UpdateMenuCategoryHolder -> holder.bind()
            else -> Unit
        }

    override fun getItemViewType(position: Int): Int = listViewType
}