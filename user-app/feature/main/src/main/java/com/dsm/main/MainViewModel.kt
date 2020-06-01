package com.dsm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val eventList: MutableLiveData<List<Event>> = MutableLiveData(listOf())


}