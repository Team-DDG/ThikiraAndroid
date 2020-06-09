package com.example.account.di

import com.example.account.viewmodel.LoginViewModel
import com.example.account.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel() }

}