package com.dsm.restaurant.presentation.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.domain.model.AddressModel

@BindingAdapter("addressList")
fun RecyclerView.bindAddressList(addressListLiveData: LiveData<List<AddressModel>>) {
    addressListLiveData.value?.let {
        (adapter as AddressListAdapter).locationItems = it
    }
}