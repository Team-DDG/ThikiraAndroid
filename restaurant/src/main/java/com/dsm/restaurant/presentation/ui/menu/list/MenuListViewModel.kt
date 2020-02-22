package com.dsm.restaurant.presentation.ui.menu.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.baseapp.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.entity.MenuEntity
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.GetMenuListUseCase
import com.dsm.restaurant.presentation.model.MenuModel
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MenuListViewModel(
    private val getMenuCategoryListUseCase: GetMenuCategoryListUseCase,
    private val getMenuListUseCase: GetMenuListUseCase
) : ViewModel() {

    private val _menuCategoryList = MutableLiveData<List<String>>()
    val menuCategoryList: LiveData<List<String>> = _menuCategoryList

    private val _menuList = MutableLiveData<List<MenuModel>>()
    val menuList: LiveData<List<MenuModel>> = _menuList

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        getMenuCategory(forceUpdate = true)
    }

    fun getMenuCategory(forceUpdate: Boolean) = viewModelScope.launch {
        try {
            _menuCategoryList.value = getMenuCategoryListUseCase(forceUpdate).map { it.name }
        } catch (e: Exception) {
            _menuCategoryList.value = listOf("")
        }
    }

    fun getMenuList(categoryName: String, forceUpdate: Boolean) = viewModelScope.launch {
        try {
            _menuList.value = getMenuListUseCase(categoryName, forceUpdate).map(MenuEntity::toModel)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

}