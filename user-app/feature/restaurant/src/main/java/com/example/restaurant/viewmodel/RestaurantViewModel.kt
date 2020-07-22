package com.example.restaurant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.androidcomponent.SingleLiveEvent
import com.example.model.Restaurant
import com.example.model.repository.MenuRepository
import com.example.restaurant.R

class RestaurantViewModel(
    private val menuRepository: MenuRepository
): ViewModel() {
    private val _star: MutableLiveData<Int> = MutableLiveData()
    val star: LiveData<Int> = _star

    private val _operatingTime: MutableLiveData<String> = MutableLiveData()
    val operatingTime: LiveData<String> = _operatingTime

    private val _phone: MutableLiveData<String> = MutableLiveData()
    val phone: LiveData<String> = _phone

    private val _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String> = _email

    private val _address: MutableLiveData<String> = MutableLiveData()
    val address: LiveData<String> = _address

    private val _area: MutableLiveData<String> = MutableLiveData()
    val area: LiveData<String> = _area

    private val _category: MutableLiveData<String> = MutableLiveData()
    val category: LiveData<String> = _category

    private val _minPrice: MutableLiveData<String> = MutableLiveData()
    val minPrice: LiveData<String> = _minPrice

    private val _dayOff: MutableLiveData<String> = MutableLiveData()
    val dayOff: LiveData<String> = _dayOff

    private val _description: MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String> = _description

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    fun setRestaurantInfo(info: Restaurant) {
        _star.value = info.star
        _operatingTime.value = info.openTime + " ~ " + info.closeTime
        _phone.value = info.phone
        _email.value = info.email
        _address.value = info.address
        _area.value = info.area
        _category.value = "카테고리: " + info.category
        _minPrice.value = "최소 주문 금액: " + info.minPrice
        _dayOff.value = "휴무: " + info.dayOff
        _description.value = info.description
    }

    fun failedToGetRestaurantInfo() {
        _toastEvent.value = R.string.failed_get_restaurant
    }
}