package com.dsm.base.di

import com.dsm.account.viewModel.LoginViewModel
import com.dsm.account.viewModel.SplashViewModel
import com.dsm.api.dataSource.RemoteAuthDataSource
import com.dsm.api.dataSource.RemoteAuthDataSourceImpl
import com.dsm.model.repository.AuthRepository
import com.dsm.repository.AuthRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    factory<RemoteAuthDataSource> { RemoteAuthDataSourceImpl(get(), get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { LoginViewModel(get()) }
}