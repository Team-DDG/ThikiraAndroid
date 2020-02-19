package com.dsm.restaurant.di.menu

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
import com.dsm.restaurant.data.dataSource.MenuCategoryDataSourceImpl
import com.dsm.restaurant.data.dataSource.MenuDataSource
import com.dsm.restaurant.data.dataSource.MenuDataSourceImpl
import com.dsm.restaurant.data.repository.MenuCategoryRepositoryImpl
import com.dsm.restaurant.data.repository.MenuRepositoryImpl
import com.dsm.restaurant.domain.interactor.*
import com.dsm.restaurant.domain.repository.MenuCategoryRepository
import com.dsm.restaurant.domain.repository.MenuRepository
import com.dsm.restaurant.presentation.ui.menu.category.MenuCategoryListViewModel
import com.dsm.restaurant.presentation.ui.menu.list.MenuListViewModel
import com.dsm.restaurant.presentation.ui.menu.registration.MenuRegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {

    /**
     * MenuCategory
     */
    factory<MenuCategoryDataSource> { MenuCategoryDataSourceImpl(get(), get(), get()) }

    factory<MenuCategoryRepository> { MenuCategoryRepositoryImpl(get()) }

    factory { GetMenuCategoryListUseCase(get()) }

    factory { DeleteMenuCategoryListUseCase(get()) }

    factory { UpdateMenuCategoryUseCase(get()) }

    /**
     * Menu
     */
    factory<MenuDataSource> { MenuDataSourceImpl(get(), get(), get()) }

    factory<MenuRepository> { MenuRepositoryImpl(get(), get()) }

    factory { GetMenuListUseCase(get()) }

    factory { UploadMenuUseCase(get()) }

    /**
     * ViewModel
     */
    viewModel { MenuListViewModel(get(), get()) }

    viewModel { MenuCategoryListViewModel(get(), get(), get()) }

    viewModel { MenuRegistrationViewModel(get(), get()) }
}