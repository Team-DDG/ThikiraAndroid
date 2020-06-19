package com.example.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Event
import com.example.model.Restaurant
import com.example.model.repository.EventRepository
import com.example.model.repository.RestaurantRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val restaurantRepository: RestaurantRepository,
    private val eventRepository: EventRepository
) : ViewModel() {
    private val _events: MutableLiveData<List<Event>> = MutableLiveData(listOf())
    val events: LiveData<List<Event>> = _events
    private val _restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData(listOf())
    val restaurants: LiveData<List<Restaurant>> = _restaurants
    private val restaurantMap: HashMap<String, List<Restaurant>> = hashMapOf()

    private val isRestaurantLoading = MutableLiveData(false)

    init {
        setRestaurantHashMap()
        getRestaurantList("한식")
        getEventList()
    }

    private fun setRestaurantHashMap() {
        restaurantMap.run {
            putAll(
                hashMapOf(
                    "한식" to listOf(),
                    "중식" to listOf(),
                    "일식" to listOf(),
                    "분식" to listOf(),
                    "치킨" to listOf(),
                    "피자" to listOf(),
                    "야식" to listOf(),
                    "도시락" to listOf(),
                    "디저트" to listOf(),
                    "족발 보쌈" to listOf(),
                    "패스트푸드" to listOf()
                )
            )
        }
    }

    private fun getEventList() = viewModelScope.launch {
        _events.value = eventRepository.getEventList()
    }

    fun getRestaurantList(category: String) = viewModelScope.launch {
        isRestaurantLoading.value = true
        if (restaurantMap[category]!!.isNotEmpty()) {
            _restaurants.value = restaurantMap[category]
        } else {
            _restaurants.value = restaurantRepository.getRestaurantList("NEARNESS", category)
        }
        isRestaurantLoading.value = false
    }

}