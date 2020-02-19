package com.dsm.restaurant.presentation.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.GetRestaurantInfoUseCase
import com.dsm.restaurant.domain.model.RestaurantModel
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class RestaurantViewModel(
    private val getRestaurantInfoUseCase: GetRestaurantInfoUseCase
) : ViewModel() {

    private val _restaurantInfo = MutableLiveData<RestaurantModel>()
    val restaurantInfo: LiveData<RestaurantModel> = _restaurantInfo

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        loadRestaurantInfo(forceUpdate = true)
    }

    fun loadRestaurantInfo(forceUpdate: Boolean) = viewModelScope.launch {
        try {
            _restaurantInfo.value = getRestaurantInfoUseCase(forceUpdate)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}