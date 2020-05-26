package com.dsm.api.di

import com.dsm.api.NaverApi
import com.dsm.api.ThikiraApi
import com.dsm.api.TokenApi
import com.dsm.api.dataSource.*
import com.dsm.api.error.ErrorHandlerImpl
import com.dsm.api.interceptor.NaverInterceptor
import com.dsm.api.interceptor.TokenInterceptor
import com.dsm.error.ErrorHandler
import com.dsm.pref.PrefStorage
import com.dsm.pref.PrefStorageImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://thikira.herokuapp.com/api/restaurant/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TokenApi::class.java)
    }

    factory {
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(NaverInterceptor())
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build()
            ).addConverterFactory(GsonConverterFactory.create())
            .build().create(NaverApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://thikira.herokuapp.com/api/restaurant/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor(get(), get()))
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build()
            ).addConverterFactory(GsonConverterFactory.create())
            .build().create(ThikiraApi::class.java)
    }

    single<ErrorHandler> { ErrorHandlerImpl() }

    single<PrefStorage> { PrefStorageImpl(get()) }

    /**
     * data sources
     */
    factory<RemoteAccountDataSource> { RemoteAccountDataSourceImpl(get(), get()) }

    factory<RemoteAddressDataSource> { RemoteAddressDataSourceImpl(get(), get(), get()) }

    factory<RemoteAuthDataSource> { RemoteAuthDataSourceImpl(get(), get()) }

    factory<RemoteCouponDataSource> { RemoteCouponDataSourceImpl(get(), get()) }

    factory<RemoteMenuCategoryDataSource> { RemoteMenuCategoryDataSourceImpl(get(), get()) }

    factory<RemoteMenuDataSource> { RemoteMenuDataSourceImpl(get(), get()) }

    factory<RemoteRestaurantDataSource> { RemoteRestaurantDataSourceImpl(get(), get()) }

    factory<RemoteOrderDataSource> { RemoteOrderDataSourceImpl(get(), get()) }
}