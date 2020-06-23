package com.example.di

import com.example.model.repository.AccountRepository
import com.example.model.repository.AuthRepository
import com.example.model.repository.EventRepository
import com.example.model.repository.RestaurantRepository
import com.example.repository.AccountRepositoryImpl
import com.example.repository.AuthRepositoryImpl
import com.example.repository.EventRepositoryImpl
import com.example.repository.RestaurantRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
    factory<EventRepository> { EventRepositoryImpl(get()) }
    factory<AccountRepository> { AccountRepositoryImpl(get()) }
}