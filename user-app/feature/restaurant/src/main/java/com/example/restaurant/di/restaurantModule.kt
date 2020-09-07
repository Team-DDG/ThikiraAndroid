package com.example.restaurant.di

import com.example.restaurant.viewmodel.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantModule = module {
    viewModel { RestaurantViewModel(get()) }
}