package com.dsm.restaurant.presentation.ui.menu.category

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.entity.MenuCategoryEntity
import com.dsm.restaurant.domain.interactor.DeleteMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.UpdateMenuCategoryUseCase
import com.dsm.restaurant.presentation.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.adapter.MenuCategoryListAdapter.Companion.DELETE_TYPE
import com.dsm.restaurant.presentation.ui.adapter.MenuCategoryListAdapter.Companion.NORMAL_TYPE
import com.dsm.restaurant.presentation.ui.adapter.MenuCategoryListAdapter.Companion.UPDATE_TYPE
import com.dsm.restaurant.presentation.util.BusProvider
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MenuCategoryListViewModel(
    private val getMenuCategoryListUseCase: GetMenuCategoryListUseCase,
    private val deleteMenuCategoryListUseCase: DeleteMenuCategoryListUseCase,
    private val updateMenuCategoryUseCase: UpdateMenuCategoryUseCase
) : ViewModel() {

    private val _menuCategoryList = MutableLiveData<List<MenuCategoryModel>>()
    val menuCategoryList: LiveData<List<MenuCategoryModel>> = _menuCategoryList

    private val selectedMenuCategoryList = MutableLiveData<ArrayList<Int>>(arrayListOf())

    private val _changeViewTypeEvent = SingleLiveEvent<Int>()
    val changeViewTypeEvent: LiveData<Int> = _changeViewTypeEvent

    private val _isDeleting = MutableLiveData<Boolean>(false)
    val isDeleting: LiveData<Boolean> = _isDeleting

    private val _isUpdating = MutableLiveData<Boolean>(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    val isSelecting: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_isDeleting) { value = _isDeleting.value!! || _isUpdating.value!! }
        addSource(_isUpdating) { value = _isDeleting.value!! || _isUpdating.value!! }
    }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popBackStackEvent = SingleLiveEvent<Unit>()
    val popBackStackEvent: LiveData<Unit> = _popBackStackEvent

    init {
        getMenuCategory(forceUpdate = true)
    }

    fun getMenuCategory(forceUpdate: Boolean) = viewModelScope.launch {
        try {
            _menuCategoryList.value = getMenuCategoryListUseCase(forceUpdate).map(MenuCategoryEntity::toModel)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickMenuCategoryItem(menuCategoryModel: MenuCategoryModel) {
        BusProvider.getInstance().post(menuCategoryModel)
        _popBackStackEvent.call()
    }

    fun isSelectedItem(menuCategoryId: Int): Boolean =
        selectedMenuCategoryList.value?.contains(menuCategoryId) ?: false

    fun onClickDeleteCheckbox(menuCategoryId: Int) {
        if (selectedMenuCategoryList.value!!.contains(menuCategoryId))
            selectedMenuCategoryList.value!!.remove(menuCategoryId)
        else
            selectedMenuCategoryList.value!!.add(menuCategoryId)
    }

    fun onClickDelete() = viewModelScope.launch {
        if (selectedMenuCategoryList.value!!.isEmpty()) return@launch

        try {
            deleteMenuCategoryListUseCase(selectedMenuCategoryList.value!!)
            _changeViewTypeEvent.value = NORMAL_TYPE
            _isDeleting.value = false

            getMenuCategory(forceUpdate = false)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickUpdate(updateText: String, menuCategoryId: Int) = viewModelScope.launch {
        try {
            updateMenuCategoryUseCase(menuCategoryId, updateText)

            getMenuCategory(forceUpdate = false)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                is ConflictException -> R.string.fail_category_menu_name_conflict
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickStartDelete() {
        _isDeleting.value = true
        _changeViewTypeEvent.value = DELETE_TYPE
    }

    fun onClickStartUpdate() {
        _isUpdating.value = true
        _changeViewTypeEvent.value = UPDATE_TYPE
    }

    fun onClickCancel() {
        _isDeleting.value = false
        _isUpdating.value = false
        _changeViewTypeEvent.value = NORMAL_TYPE
    }
}