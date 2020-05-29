package com.dsm.menu.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.menu.R
import com.dsm.model.repository.MenuRepository
import kotlinx.coroutines.launch

class MenuDetailViewModel(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _popEvent = SingleLiveEvent<Unit>()
    val popEvent: LiveData<Unit> = _popEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    fun onClickDeleteMenu(menuId: Int) = viewModelScope.launch {
        try {
            menuRepository.deleteMenu(menuId)

            _popEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_exception_internal
        }
    }
}