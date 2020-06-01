package com.dsm.main

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("listdata")
fun ViewPager2.bindRecyclerView(data: List<Event>) {
    val adapter = adapter as MainEventAdapter
    adapter.setList(data)
}