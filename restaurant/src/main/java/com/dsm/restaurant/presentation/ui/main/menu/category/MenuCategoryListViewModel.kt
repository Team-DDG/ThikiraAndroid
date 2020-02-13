package com.dsm.restaurant.presentation.ui.main.menu.category

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.DeleteMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MenuCategoryListViewModel(
    private val getMenuCategoryListUseCase: GetMenuCategoryListUseCase,
    private val deleteMenuCategoryListUseCase: DeleteMenuCategoryListUseCase
) : ViewModel() {

    private val _menuCategoryList = MutableLiveData<List<MenuCategoryModel>>()
    val menuCategoryList: LiveData<List<MenuCategoryModel>> = _menuCategoryList

    private val selectedMenuCategoryList = MutableLiveData<ArrayList<Int>>().apply { value = arrayListOf() }

    private val _changeViewTypeNormalEvent = SingleLiveEvent<Unit>()
    val changeViewTypeNormalEvent: LiveData<Unit> = _changeViewTypeNormalEvent

    private val _changeViewTypeDeleteEvent = SingleLiveEvent<Unit>()
    val changeViewTypeDeleteEvent: LiveData<Unit> = _changeViewTypeDeleteEvent

    private val _isDeleting = MutableLiveData<Boolean>(false)
    private val _isUpdating = MutableLiveData<Boolean>(false)

    val isSelecting: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_isDeleting) { value = _isDeleting.value!! || _isUpdating.value!! }
        addSource(_isUpdating) { value = _isDeleting.value!! || _isUpdating.value!! }
    }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        getMenuCategory(forceUpdate = true)
    }

    fun getMenuCategory(forceUpdate: Boolean) = viewModelScope.launch {
        try {
            _menuCategoryList.value = getMenuCategoryListUseCase(forceUpdate)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun isSelectedItem(menuCategoryId: Int): Boolean =
        selectedMenuCategoryList.value?.contains(menuCategoryId) ?: false

    fun onDeleteCheck(menuCategoryId: Int) {
        if (selectedMenuCategoryList.value!!.contains(menuCategoryId))
            selectedMenuCategoryList.value!!.remove(menuCategoryId)
        else
            selectedMenuCategoryList.value!!.add(menuCategoryId)
    }

    fun onClickDelete() = viewModelScope.launch {
        if (selectedMenuCategoryList.value!!.isEmpty()) return@launch

        try {
            deleteMenuCategoryListUseCase(selectedMenuCategoryList.value!!)
            _changeViewTypeNormalEvent.call()
            _isDeleting.value = false

            getMenuCategory(forceUpdate = false)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onStartDeleting() {
        _isDeleting.value = true
        _changeViewTypeDeleteEvent.call()
    }

    fun onClickCancel() {
        _isDeleting.value = false
        _isUpdating.value = false
        _changeViewTypeNormalEvent.call()
    }
}