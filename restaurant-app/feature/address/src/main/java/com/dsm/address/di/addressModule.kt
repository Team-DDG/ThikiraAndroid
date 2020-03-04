package com.dsm.address.di

import com.dsm.address.viewModel.AddressSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressModule = module {

    viewModel { AddressSearchViewModel(get()) }
}