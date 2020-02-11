package com.dsm.restaurant.presentation.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.domain.model.AddressModel
import com.dsm.restaurant.domain.model.MenuModel

@BindingAdapter("addressList")
fun RecyclerView.bindAddressList(addressListLiveData: LiveData<List<AddressModel>>) {
    addressListLiveData.value?.let {
        (adapter as AddressListAdapter).submitList(it)
    }
}

@BindingAdapter("menuList")
fun RecyclerView.bindMenuList(menuListLiveData: LiveData<List<MenuModel>>) {
    menuListLiveData.value?.let {
        (adapter as MenuListAdapter).submitList(it)
    }
}