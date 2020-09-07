package com.example.restaurant.ext

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Menu
import com.example.restaurant.adapter.RestaurantMenuAdapter
import com.google.android.material.tabs.TabLayout

@BindingAdapter("menu_category_list")
fun RecyclerView.bindMenuCategory(data: LiveData<List<Menu>>) {
    data.value?.let { menus ->
        (adapter as RestaurantMenuAdapter).menus = menus
    }
}

fun TabLayout.onTabSelected(onTabSelected: (tab: TabLayout.Tab?) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabSelected(tab: TabLayout.Tab?) {
            onTabSelected(tab)
        }
    })
}