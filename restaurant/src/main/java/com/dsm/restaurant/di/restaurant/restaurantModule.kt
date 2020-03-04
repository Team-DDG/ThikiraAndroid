package com.dsm.restaurant.di.restaurant

import com.dsm.restaurant.data.dataSource.RestaurantDataSource
import com.dsm.restaurant.data.dataSource.RestaurantDataSourceImpl
import com.dsm.restaurant.data.repository.RestaurantRepositoryImpl
import com.dsm.restaurant.domain.interactor.GetRestaurantInfoUseCase
import com.dsm.restaurant.domain.repository.RestaurantRepository
import com.dsm.restaurant.presentation.ui.restaurant.RestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantModule = module {
    factory<RestaurantDataSource> { RestaurantDataSourceImpl(get(), get(), get()) }

    factory<RestaurantRepository> { RestaurantRepositoryImpl(get()) }

    factory { GetRestaurantInfoUseCase(get()) }

    viewModel { RestaurantViewModel(get()) }
}