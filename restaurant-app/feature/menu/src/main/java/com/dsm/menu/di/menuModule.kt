package com.dsm.menu.di

import com.dsm.menu.viewModel.MenuCategorySelectViewModel
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import com.dsm.menu.viewModel.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {

    viewModel { MenuCategorySelectViewModel() }

    viewModel { MenuCategoryViewModel(get()) }

    viewModel { MenuRegistrationViewModel(get(), get()) }

    viewModel { MenuViewModel(get(), get()) }
}