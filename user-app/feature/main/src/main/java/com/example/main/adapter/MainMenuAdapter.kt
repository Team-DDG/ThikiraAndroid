package com.example.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsm.androidcomponent.ext.numberAutoComma
import com.example.main.adapter.MainMenuAdapter.MainMenuViewHolder
import com.dsm.main.databinding.ItemRestaurantBinding
import com.example.model.Restaurant
import java.text.NumberFormat
import java.util.*

class MainMenuAdapter : RecyclerView.Adapter<MainMenuViewHolder>() {
    private var items: List<Restaurant> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        return MainMenuViewHolder(ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(data: List<Restaurant>) {
        items = data
        notifyDataSetChanged()
    }

    inner class MainMenuViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Restaurant) {
            Glide.with(itemView.context).load(item.image).into(binding.rvMainImage)
            binding.rvMainRestaurantName.text = item.name
            binding.rvMainScore.text = "${item.star}"
            binding.rvMainMinPrice.text = "${item.minPrice.numberAutoComma()}${binding.rvMainMinPrice.text}"
        }
    }
}