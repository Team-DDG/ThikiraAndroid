package com.dsm.restaurant.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.databinding.ItemAddressBinding
import com.dsm.restaurant.domain.model.AddressModel
import com.dsm.restaurant.presentation.ui.address.AddressSearchViewModel

class AddressListAdapter(
    private val viewModel: AddressSearchViewModel
) : RecyclerView.Adapter<AddressListAdapter.AddressHolder>() {

    var addressItems = listOf<AddressModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class AddressHolder(private val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = addressItems[adapterPosition]
            binding.addressModel = item
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder =
        AddressHolder(ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AddressHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = addressItems.size
}