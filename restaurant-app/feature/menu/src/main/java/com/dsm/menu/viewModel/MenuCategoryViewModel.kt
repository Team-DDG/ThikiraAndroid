package com.dsm.menu.viewModel

import android.util.SparseBooleanArray
import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.menu.binding.MenuCategoryViewType
import com.dsm.model.MenuCategory
import com.dsm.model.repository.MenuCategoryRepository
import com.example.error.exception.ForbiddenException
import kotlinx.coroutines.launch

class MenuCategoryViewModel(
    private val menuCategoryRepository: MenuCategoryRepository
) : ViewModel() {

    private val _forceUpdate = MutableLiveData<Boolean>()

    val menuCategories: LiveData<List<MenuCategory>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            refreshMenuCategories()
        }
        menuCategoryRepository.observeMenuCategories().distinctUntilChanged()
    }

    private val selectedMenu = SparseBooleanArray()

    private val _viewType = MutableLiveData<MenuCategoryViewType>(MenuCategoryViewType.NORMAL)
    val viewType: LiveData<MenuCategoryViewType> = _viewType

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    fun setViewType(type: MenuCategoryViewType) {
        _viewType.value = type
    }

    init {
        loadMenuCategories(true)
    }

    fun loadMenuCategories(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun refreshMenuCategories() = viewModelScope.launch {
        try {
            menuCategoryRepository.refreshMenuCategories()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickDeleteCheckBox(menuCategoryId: Int, isChecked: Boolean) {
        selectedMenu.put(menuCategoryId, isChecked)
    }

    fun onClickDelete() = viewModelScope.launch {
        try {
            menuCategoryRepository.deleteMenuCategories(
                menuCategories.value!!
                    .filter { selectedMenu[it.menuCategoryId] }
                    .map { it.menuCategoryId }
            )

            _viewType.value = MenuCategoryViewType.NORMAL
            selectedMenu.clear()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickUpdate(menuCategoryId: Int, name: String) = viewModelScope.launch {
        try {
            menuCategoryRepository.updateMenuCategoryName(menuCategoryId, name)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}