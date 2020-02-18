package com.dsm.restaurant.di.address

import com.dsm.restaurant.data.dataSource.AddressDataSource
import com.dsm.restaurant.data.dataSource.AddressDataSourceImpl
import com.dsm.restaurant.data.repository.AddressRepositoryImpl
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.repository.AddressRepository
import com.dsm.restaurant.presentation.ui.address.AddressSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressModule = module {
    factory<AddressDataSource> { AddressDataSourceImpl(get(), get()) }

    factory<AddressRepository> { AddressRepositoryImpl(get()) }

    factory { SearchAddressUseCase(get()) }

    viewModel { AddressSearchViewModel(get()) }
}