package com.dsm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.Event
import com.example.model.Restaurant

class MainViewModel : ViewModel() {
    val eventList: MutableLiveData<List<Event>> = MutableLiveData(listOf())
    val restaurantList: MutableLiveData<List<Restaurant>> = MutableLiveData(listOf(
////        Dummy Data
//        Restaurant(
//            name = "도훈이네 김치찌개",
//            minPrice = 12000
//        ),
//        Restaurant(
//            name = "도훈이네 부대찌개",
//            minPrice = 15000
//        ),
//        Restaurant(
//            name = "도훈이네 된장찌개",
//            minPrice = 8000
//        )
    ))


}