package com.dsm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Event
import com.example.model.Restaurant
import com.example.model.repository.RestaurantRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    val eventList: MutableLiveData<List<Event>> = MutableLiveData(listOf())
    val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData(listOf())
    private val restaurantMap: HashMap<String, List<Restaurant>?> = hashMapOf(
        Pair("한식", null),
        Pair("중식", null),
        Pair("일식", null),
        Pair("분식", null),
        Pair("치킨", null),
        Pair("피자", null),
        Pair("야식", null),
        Pair("도시락", null),
        Pair("족발 보쌈", null),
        Pair("패스트푸드", null)
    )

    val progressBarVisibility = MutableLiveData(false)

    init {
        getRestaurantList("한식")
    }

    private fun getEventList() {

    }

    fun getRestaurantList(category: String) = viewModelScope.launch {
        progressBarVisibility.value = true
        if (restaurantMap[category]!!.isNullOrEmpty()) {
            restaurantList.value = restaurantMap[category]
        } else {
            restaurantList.value = restaurantRepository.getRestaurantList("NEARNESS", category)
        }
        progressBarVisibility.value = false
    }

}