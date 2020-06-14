package com.dsm.main.di

import com.dsm.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule  = module {
    viewModel { MainViewModel(get(), get()) }
}