package com.dsm.restaurant.di

import com.dsm.restaurant.viewModel.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantModule = module {

    viewModel { RestaurantViewModel(get()) }
}