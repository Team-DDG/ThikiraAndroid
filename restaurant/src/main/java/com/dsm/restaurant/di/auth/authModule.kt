package com.dsm.restaurant.di.auth

import com.dsm.restaurant.data.dataSource.AuthDataSource
import com.dsm.restaurant.data.dataSource.AuthDataSourceImpl
import com.dsm.restaurant.data.repository.AuthRepositoryImpl
import com.dsm.restaurant.domain.interactor.AuthTokenUseCase
import com.dsm.restaurant.domain.interactor.ConfirmEmailDuplicationUseCase
import com.dsm.restaurant.domain.interactor.LoginUseCase
import com.dsm.restaurant.domain.repository.AuthRepository
import com.dsm.restaurant.presentation.ui.login.LoginViewModel
import com.dsm.restaurant.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    factory<AuthDataSource> { AuthDataSourceImpl(get(), get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory { ConfirmEmailDuplicationUseCase(get()) }

    // login
    factory { LoginUseCase(get()) }

    viewModel { LoginViewModel(get()) }

    // splash
    factory { AuthTokenUseCase(get()) }

    viewModel { SplashViewModel(get()) }
}