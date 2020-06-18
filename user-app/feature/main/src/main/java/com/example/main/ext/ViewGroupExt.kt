package com.example.main.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.model.Event
import com.example.main.adapter.MainEventAdapter
import com.example.main.adapter.MainMenuAdapter
import com.example.model.Restaurant

@BindingAdapter("restaurant_list")
fun RecyclerView.bindRecyclerView(data: List<Restaurant>) {
    val adapter = adapter as MainMenuAdapter
    adapter.setList(data)
}

@BindingAdapter("event_list")
fun ViewPager2.bindRecyclerView(data: List<Event>) {
    val adapter = adapter as MainEventAdapter
    adapter.setList(data)
}