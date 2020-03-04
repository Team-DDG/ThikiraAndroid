package com.dsm.address.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.address.item.AddressItem
import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.model.Address
import com.xwray.groupie.GroupAdapter

@BindingAdapter("addresses", "viewModel")
fun RecyclerView.bindAddresses(
    addressesLiveData: LiveData<List<Address>>,
    viewModel: AddressSearchViewModel
) {
    adapter?.let {
        val addresses = addressesLiveData.value ?: emptyList()
        (adapter as GroupAdapter).update(addresses.map { AddressItem(it, viewModel) })
    }
}