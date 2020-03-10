package com.dsm.di

import com.dsm.model.repository.*
import com.dsm.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    factory<AddressRepository> { AddressRepositoryImpl(get(), get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory<MenuCategoryRepository> { MenuCategoryRepositoryImpl(get(), get()) }

    factory<MenuRepository> { MenuRepositoryImpl(get(), get()) }

    factory<CouponRepository> { CouponRepositoryImpl(get(), get()) }

    factory<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
}