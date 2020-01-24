package com.dsm.restaurant.di.account

import com.dsm.restaurant.data.dataSource.AccountDataSource
import com.dsm.restaurant.data.dataSource.AccountDataSourceImpl
import com.dsm.restaurant.data.repository.AccountRepositoryImpl
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.presentation.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    factory<AccountDataSource> { AccountDataSourceImpl(get(), get(), get()) }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    // register
    factory { CheckEmailUseCase(get()) }

    factory { SearchAddressUseCase(get()) }

    viewModel { RegisterViewModel(get(), get()) }
}