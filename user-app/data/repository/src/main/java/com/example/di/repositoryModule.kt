package com.example.di

import com.example.model.repository.*
import com.example.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
    factory<EventRepository> { EventRepositoryImpl(get()) }
    factory<AccountRepository> { AccountRepositoryImpl(get()) }
    factory<MenuRepository> { MenuRepositoryImpl(get()) }
}