package com.dsm.menu.viewModel

import android.util.SparseBooleanArray
import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.error.exception.ForbiddenException
import com.dsm.model.Menu
import com.dsm.model.MenuCategory
import com.dsm.model.repository.MenuCategoryRepository
import com.dsm.model.repository.MenuRepository
import kotlinx.coroutines.launch

class MenuViewModel(
    private val menuCategoryRepository: MenuCategoryRepository,
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _menuCategoryForceUpdate = MutableLiveData<Boolean>(false)
    val menuCategories: LiveData<List<MenuCategory>> = _menuCategoryForceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            refreshMenuCategories()
        }
        menuCategoryRepository.observeMenuCategories().distinctUntilChanged()
    }

    private val forceUpdatedMenuCategory = SparseBooleanArray()
    private val _menuForceUpdate = MutableLiveData<Pair<Int, Boolean>>()
    val menus: LiveData<List<Menu>> = _menuForceUpdate.switchMap {
        if (it.second) {
            refreshMenus(it.first)
        }
        menuRepository.observeMenusByMenuCategoryId(it.first).distinctUntilChanged()
    }

    init {
        loadMenuCategories(true)
    }

    fun loadMenus(menuCategoryName: String) {
        val menuCategoryId = getMenuCategoryIdByName(menuCategoryName)
        forceUpdatedMenuCategory.get(menuCategoryId).let {
            _menuForceUpdate.value = menuCategoryId to !it
            forceUpdatedMenuCategory.put(menuCategoryId, true)
        }
    }

    private fun getMenuCategoryIdByName(menuCategoryName: String): Int =
        menuCategories.value?.first { it.name == menuCategoryName }?.menuCategoryId ?: -1

    private fun loadMenuCategories(forceUpdate: Boolean) {
        _menuCategoryForceUpdate.value = forceUpdate
    }

    private fun refreshMenuCategories() = viewModelScope.launch {
        try {
            menuCategoryRepository.refreshMenuCategories()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun refreshMenus(menuCategoryId: Int) = viewModelScope.launch {
        try {
            menuRepository.refreshMenus(menuCategoryId)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}