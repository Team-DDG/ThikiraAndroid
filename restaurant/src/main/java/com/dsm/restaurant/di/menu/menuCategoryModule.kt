package com.dsm.restaurant.di.menu

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
import com.dsm.restaurant.data.dataSource.MenuCategoryDataSourceImpl
import com.dsm.restaurant.data.repository.MenuCategoryRepositoryImpl
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.repository.MenuCategoryRepository
import com.dsm.restaurant.presentation.ui.main.menu.menuList.MenuListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {

    factory<MenuCategoryDataSource> { MenuCategoryDataSourceImpl(get(), get(), get()) }

    factory<MenuCategoryRepository> { MenuCategoryRepositoryImpl(get()) }

    factory { GetMenuCategoryListUseCase(get()) }

    viewModel { MenuListViewModel(get()) }
}