package com.dsm.account.di

import com.dsm.account.viewModel.LoginViewModel
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.account.viewModel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { RegisterViewModel(get(), get(), get()) }

    viewModel { SplashViewModel(get()) }
}