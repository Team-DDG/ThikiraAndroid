package com.dsm.restaurant.presentation.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.presentation.model.AddressModel
import com.dsm.restaurant.presentation.model.MenuCategoryModel
import com.dsm.restaurant.presentation.model.MenuModel
import com.dsm.restaurant.presentation.model.MenuRegistrationOptionModel

@BindingAdapter("addressList")
fun RecyclerView.bindAddressList(addressListLiveData: LiveData<List<AddressModel>>) {
    addressListLiveData.value?.let {
        (adapter as AddressListAdapter).addressItems = it
    }
}

@BindingAdapter("menuList")
fun RecyclerView.bindMenuList(menuListLiveData: LiveData<List<MenuModel>>) {
    menuListLiveData.value?.let {
        (adapter as MenuListAdapter).menuItems = it
    }
}

@BindingAdapter("menuCategoryList")
fun RecyclerView.bindMenuCategoryList(menuCategoryListLiveData: LiveData<List<MenuCategoryModel>>) {
    menuCategoryListLiveData.value?.let {
        (adapter as MenuCategoryListAdapter).submitList(it)
    }
}

@BindingAdapter("menuRegistrationOptionList")
fun RecyclerView.bindMenuRegistrationOptionList(menuRegistrationOptionListLiveData: LiveData<ArrayList<MenuRegistrationOptionModel>>) {
    menuRegistrationOptionListLiveData.value?.let {
        (adapter as MenuOptionRegistrationListAdapter).listItems = it
    }
}
