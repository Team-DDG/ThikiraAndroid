package com.dsm.restaurant.presentation.ui.main.menu.menuList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MenuListViewModel(
    private val getMenuCategoryListUseCase: GetMenuCategoryListUseCase
) : ViewModel() {

    private val _menuCategoryList = MutableLiveData<List<String>>()
    val menuCategoryList: LiveData<List<String>> = _menuCategoryList

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        getMenuCategory(forceUpdate = true)
    }

    fun getMenuCategory(forceUpdate: Boolean) = viewModelScope.launch {
        try {
            _menuCategoryList.value = getMenuCategoryListUseCase(forceUpdate)
        } catch (e: Exception) {
            _menuCategoryList.value = listOf("")
        }
    }
}