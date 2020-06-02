package com.dsm.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Event
import com.dsm.main.databinding.ItemEventBinding

class MainEventAdapter : RecyclerView.Adapter<MainEventAdapter.MainEventViewHolder>() {
    private var items: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainEventViewHolder {
        return MainEventViewHolder(ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainEventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(data: List<Event>) {
        items = data
        notifyDataSetChanged()
    }

    inner class MainEventViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {
            Glide.with(binding.root.context).load(item.bannerImageURL).into(binding.imageEvent)
        }
    }
}