package com.dsm.base.di

import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.api.dataSource.RemoteAccountDataSource
import com.dsm.api.dataSource.RemoteAccountDataSourceImpl
import com.dsm.model.repository.AccountRepository
import com.dsm.repository.AccountRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    factory<RemoteAccountDataSource> { RemoteAccountDataSourceImpl(get(), get()) }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    viewModel { RegisterViewModel(get(), get(), get()) }
}