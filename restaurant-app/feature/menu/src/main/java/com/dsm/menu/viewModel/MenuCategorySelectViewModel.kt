package com.dsm.menu.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.model.MenuCategory

class MenuCategorySelectViewModel : ViewModel() {

    private val _selectedMenuCategory = MutableLiveData<MenuCategory>()
    val selectedMenuCategory: LiveData<MenuCategory> = _selectedMenuCategory

    private val _popToRegistrationEvent = SingleLiveEvent<Unit>()
    val popToRegistrationEvent: LiveData<Unit> = _popToRegistrationEvent

    fun onClickMenuCategory(menuCategory: MenuCategory) {
        _selectedMenuCategory.value = menuCategory
        _popToRegistrationEvent.call()
    }
}