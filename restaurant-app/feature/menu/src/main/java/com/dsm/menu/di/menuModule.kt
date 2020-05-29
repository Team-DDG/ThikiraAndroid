package com.dsm.menu.di

import com.dsm.menu.viewModel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {

    viewModel { MenuCategorySelectViewModel() }

    viewModel { MenuCategoryViewModel(get()) }

    viewModel { MenuRegistrationViewModel(get(), get()) }

    viewModel { MenuViewModel(get(), get()) }

    viewModel { AddMenuCategoryViewModel(get()) }

    viewModel { MenuDetailViewModel(get()) }
}