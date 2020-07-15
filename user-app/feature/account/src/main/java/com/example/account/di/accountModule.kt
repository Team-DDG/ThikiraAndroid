package com.example.account.di

import com.example.account.viewmodel.LoginViewModel
import com.example.account.viewmodel.SignupViewModel
import com.example.account.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { SignupViewModel(get(), get(), get()) }

    viewModel { SplashViewModel(get()) }
}