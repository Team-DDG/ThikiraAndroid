package com.dsm.db.di

import androidx.room.Room
import com.dsm.db.AppDatabase
import com.dsm.db.dataSource.*
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

    factory { get<AppDatabase>().couponDao() }

    /**
     * data sources
     */
    factory<LocalCouponDataSource> { LocalCouponDataSourceImpl(get()) }

    factory<LocalMenuCategoryDataSource> { LocalMenuCategoryDataSourceImpl(get()) }

    factory<LocalMenuDataSource> { LocalMenuDataSourceImpl(get()) }
}