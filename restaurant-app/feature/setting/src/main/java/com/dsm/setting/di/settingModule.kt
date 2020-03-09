package com.dsm.setting.di

import com.dsm.setting.viewModel.PasswordChangeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {

    viewModel { PasswordChangeViewModel(get(), get()) }
}