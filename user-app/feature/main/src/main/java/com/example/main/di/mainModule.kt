package com.example.main.di

import com.example.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule  = module {
    viewModel { MainViewModel(get(), get()) }
}