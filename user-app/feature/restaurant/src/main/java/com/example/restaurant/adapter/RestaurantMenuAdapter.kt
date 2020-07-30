package com.example.restaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.MenuCategory
import com.example.restaurant.databinding.ItemMenuBinding
import com.example.restaurant.viewmodel.RestaurantViewModel

class RestaurantMenuAdapter(
    private val viewModel: RestaurantViewModel
): RecyclerView.Adapter<MenuViewHolder>() {
    private val menuCategoryList: List<MenuCategory>? = viewModel.menuCategoryList.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind()
    }

}

class MenuViewHolder(private val binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind() {

    }
}
