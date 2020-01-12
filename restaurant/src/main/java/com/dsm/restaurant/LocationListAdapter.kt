package com.dsm.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_location.view.*

class LocationListAdapter : RecyclerView.Adapter<LocationListAdapter.LocationHolder>() {

    inner class LocationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item = locationItems[adapterPosition]
            itemView.tv_location_parcel.text = item.parcel
            itemView.tv_location_road.text = item.road
        }
    }

    var locationItems = listOf<LocationModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder =
        LocationHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false))

    override fun getItemCount(): Int = locationItems.size

    override fun onBindViewHolder(holder: LocationHolder, position: Int) = holder.bind()
}