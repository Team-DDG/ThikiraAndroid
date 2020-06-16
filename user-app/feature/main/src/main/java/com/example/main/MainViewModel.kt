package com.example.main

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
    val eventList: MutableLiveData<List<Event>> = MutableLiveData(listOf())
    val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData(listOf())
    private val restaurantMap: HashMap<String, List<Restaurant>> = hashMapOf()

    val progressBarVisibility = MutableLiveData(false)

    init {
        setRestaurantHashMap()
        getRestaurantList("한식")
        getEventList()
    }

    private fun setRestaurantHashMap() {
        restaurantMap.putAll(
            hashMapOf(
                Pair("한식", listOf()),
                Pair("중식", listOf()),
                Pair("일식", listOf()),
                Pair("분식", listOf()),
                Pair("치킨", listOf()),
                Pair("피자", listOf()),
                Pair("야식", listOf()),
                Pair("도시락", listOf()),
                Pair("디저트", listOf()),
                Pair("족발 보쌈", listOf()),
                Pair("패스트푸드", listOf())
            )
        )
    }

    private fun getEventList() = viewModelScope.launch {
        eventList.value = eventRepository.getEventList()
    }

    fun getRestaurantList(category: String) = viewModelScope.launch {
        progressBarVisibility.value = true
        if (restaurantMap[category]!!.isNotEmpty()) {
            restaurantList.value = restaurantMap[category]
        } else {
            restaurantList.value = restaurantRepository.getRestaurantList("NEARNESS", category)
        }
        progressBarVisibility.value = false
    }

}