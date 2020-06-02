package com.dsm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.Event
import com.example.model.Restaurant

class MainViewModel : ViewModel() {
    val eventList: MutableLiveData<List<Event>> = MutableLiveData(listOf())
    val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData(listOf())


}