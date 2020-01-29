package com.dsm.restaurant.di

import com.dsm.restaurant.data.local.PrefStorage
import com.dsm.restaurant.data.local.PrefStorageImpl
import org.koin.dsl.module

val localModule = module {

    factory<PrefStorage> { PrefStorageImpl(get()) }
}