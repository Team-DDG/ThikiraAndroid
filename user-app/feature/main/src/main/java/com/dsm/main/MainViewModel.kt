package com.dsm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val eventList: MutableLiveData<List<Event>> = MutableLiveData(
        listOf(
            Event(
                "https://img9.yna.co.kr/etc/inner/KR/2019/10/11/AKR20191011139500030_01_i_P2.jpg",
                "https://img9.yna.co.kr/etc/inner/KR/2019/10/11/AKR20191011139500030_01_i_P2.jpg"
            ),
            Event(
                "https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.bloter.net%2Farchives%2F261967&psig=AOvVaw0hWE1dxwVcatNCgeG2Hmxq&ust=1590907644081000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIDr1fr-2ukCFQAAAAAdAAAAABAR",
                "https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.bloter.net%2Farchives%2F261967&psig=AOvVaw0hWE1dxwVcatNCgeG2Hmxq&ust=1590907644081000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIDr1fr-2ukCFQAAAAAdAAAAABAR"
            )
        )
    )


}