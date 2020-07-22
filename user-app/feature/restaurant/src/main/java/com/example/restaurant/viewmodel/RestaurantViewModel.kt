package com.example.restaurant.viewmodel

import androidx.lifecycle.ViewModel
import com.example.model.repository.MenuRepository

class RestaurantViewModel(
    private val menuRepository: MenuRepository
): ViewModel() {

}