package com.dsm.db.di

import androidx.room.Room
import com.dsm.db.AppDatabase
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "thikira.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<AppDatabase>().menuCategoryDao() }

    factory { get<AppDatabase>().restaurantDao() }

    factory { get<AppDatabase>().menuDao() }
}