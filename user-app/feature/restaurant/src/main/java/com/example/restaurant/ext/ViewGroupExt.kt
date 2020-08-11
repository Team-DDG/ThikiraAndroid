package com.example.restaurant.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Menu
import com.example.restaurant.adapter.RestaurantMenuAdapter

@BindingAdapter("menu_category_list")
fun RecyclerView.bindMenuCategory(data: List<Menu>) {
    val adapter = adapter as RestaurantMenuAdapter
    adapter.setList(data)
}