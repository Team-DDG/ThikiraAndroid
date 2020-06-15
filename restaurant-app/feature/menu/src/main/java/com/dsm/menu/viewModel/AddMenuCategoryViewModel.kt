package com.dsm.menu.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.menu.R
import com.dsm.model.repository.MenuCategoryRepository
import com.example.error.exception.ConflictException
import kotlinx.coroutines.launch

class AddMenuCategoryViewModel(
    private val menuCategoryRepository: MenuCategoryRepository
) : ViewModel() {

    val categoryName = MutableLiveData<String>()

    val isAddCategoryEnable: LiveData<Boolean> = categoryName.map { !it.isNullOrBlank() }

    private val _popEvent = SingleLiveEvent<Unit>()
    val popEvent: LiveData<Unit> = _popEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    fun onClickAdd() = viewModelScope.launch {
        try {
            menuCategoryRepository.addMenuCategory(categoryName.value!!)

            _popEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_menu_category_conflict_name
                else -> R.string.fail_exception_internal
            }
        }
    }
}