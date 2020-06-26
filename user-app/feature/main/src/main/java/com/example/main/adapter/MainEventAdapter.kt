package com.example.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Event
import com.dsm.main.databinding.ItemEventBinding
import com.example.main.ui.EventActivity

class MainEventAdapter : RecyclerView.Adapter<MainEventAdapter.MainEventViewHolder>() {
    private var items: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainEventViewHolder {
        return MainEventViewHolder(ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = Integer.MAX_VALUE

    override fun onBindViewHolder(holder: MainEventViewHolder, position: Int) {
        if (items.isNotEmpty()) holder.bind(items[position % items.size])
    }

    fun setList(data: List<Event>) {
        items = data
        notifyDataSetChanged()
    }

    inner class MainEventViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Event) {
            Glide.with(itemView.context).load(item.bannerImage).into(binding.imageEvent)
            binding.imageEvent.setOnClickListener {
                val intent = Intent(binding.root.context, EventActivity::class.java)
                intent.putExtra("index", item.mainImage)
                binding.root.context.startActivity(intent)
            }
        }
    }
}