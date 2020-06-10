package com.dsm.restaurant.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.model.Restaurant
import com.dsm.model.repository.RestaurantRepository
import com.example.error.exception.ForbiddenException
import kotlinx.coroutines.launch

class RestaurantViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    val restaurant: LiveData<Restaurant> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            refreshRestaurant()
        }
        restaurantRepository.observeRestaurantInfo().distinctUntilChanged()
    }

    val restaurantTime: LiveData<String> = restaurant.map { "${it.openTime}~${it.closeTime}" }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        loadRestaurant(true)
    }

    fun loadRestaurant(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun refreshRestaurant() = viewModelScope.launch {
        try {
            restaurantRepository.refreshRestaurantInfo()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}