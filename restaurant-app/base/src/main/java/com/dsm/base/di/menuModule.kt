package com.dsm.base.di

import com.dsm.api.dataSource.RemoteMenuDataSource
import com.dsm.api.dataSource.RemoteMenuDataSourceImpl
import com.dsm.db.dataSource.LocalMenuDataSource
import com.dsm.db.dataSource.LocalMenuDataSourceImpl
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import com.dsm.menu.viewModel.MenuViewModel
import com.dsm.model.repository.MenuRepository
import com.dsm.repository.MenuRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val menuModule = module {

    factory<LocalMenuDataSource> { LocalMenuDataSourceImpl(get()) }

    factory<RemoteMenuDataSource> { RemoteMenuDataSourceImpl(get(), get()) }

    factory<MenuRepository> { MenuRepositoryImpl(get(), get()) }

    viewModel { MenuViewModel(get(), get()) }

    viewModel { MenuRegistrationViewModel(get(), get()) }
}