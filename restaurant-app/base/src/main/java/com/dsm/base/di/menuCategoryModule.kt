package com.dsm.base.di

import com.dsm.api.dataSource.RemoteMenuCategoryDataSource
import com.dsm.api.dataSource.RemoteMenuCategoryDataSourceImpl
import com.dsm.db.dataSource.LocalMenuCategoryDataSource
import com.dsm.db.dataSource.LocalMenuCategoryDataSourceImpl
import com.dsm.menu.viewModel.MenuCategorySelectViewModel
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.dsm.model.repository.MenuCategoryRepository
import com.dsm.repository.MenuCategoryRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuCategoryModule = module {

    factory<LocalMenuCategoryDataSource> { LocalMenuCategoryDataSourceImpl(get()) }

    factory<RemoteMenuCategoryDataSource> { RemoteMenuCategoryDataSourceImpl(get(), get()) }

    factory<MenuCategoryRepository> { MenuCategoryRepositoryImpl(get(), get()) }

    viewModel { MenuCategoryViewModel(get()) }

    viewModel { MenuCategorySelectViewModel() }
}