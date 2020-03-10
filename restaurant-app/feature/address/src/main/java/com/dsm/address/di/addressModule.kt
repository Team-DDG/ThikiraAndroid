package com.dsm.address.di

import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.address.viewModel.AddressSelectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressModule = module {

    viewModel { AddressSearchViewModel(get()) }

    viewModel { AddressSelectViewModel() }
}