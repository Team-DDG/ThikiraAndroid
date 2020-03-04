package com.dsm.restaurant.di

import androidx.room.Room
import com.dsm.restaurant.data.local.AppDatabase
import com.dsm.restaurant.data.local.PrefStorage
import com.dsm.restaurant.data.local.PrefStorageImpl
import org.koin.dsl.module

val localModule = module {

    factory<PrefStorage> { PrefStorageImpl(get()) }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "thikira.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<AppDatabase>().restaurantDao() }

    factory { get<AppDatabase>().menuCategoryDao() }

    factory { get<AppDatabase>().menuDao() }
}