package com.dsm.setting.di

import com.dsm.setting.viewModel.AddressChangeViewModel
import com.dsm.setting.viewModel.PasswordChangeViewModel
import com.dsm.setting.viewModel.UnregisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {

    viewModel { PasswordChangeViewModel(get(), get()) }

    viewModel { UnregisterViewModel(get(), get()) }

    viewModel { AddressChangeViewModel(get(), get()) }
}