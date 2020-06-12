package com.example.account.di

import com.example.account.viewmodel.LoginViewModel
import com.example.account.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { RegisterViewModel(get()) }

}