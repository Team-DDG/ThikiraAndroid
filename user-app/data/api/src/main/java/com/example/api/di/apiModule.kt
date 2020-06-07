package com.example.api.di

import com.example.api.ThikiraApi
import com.example.api.TokenApi
import com.example.api.datasource.*
import com.example.api.error.ErrorHandlerImpl
import com.example.api.interceptor.TokenInterceptor
import com.example.error.ErrorHandler
import com.example.pref.PrefStorage
import com.example.pref.PrefStorageImpl
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
            .baseUrl("https://thikira.herokuapp.com/api/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TokenApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://thikira.herokuapp.com/api/user/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor(get(), get()))
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ThikiraApi::class.java)
    }

    single<ErrorHandler> { ErrorHandlerImpl() }

    single<PrefStorage> { PrefStorageImpl(get()) }

    /**
     * data sources
     */
    factory<RemoteAuthDataSource> { RemoteAuthDataSourceImpl(get(), get()) }

    factory<RemoteAccountDataSource> { RemoteAccountDataSourceImpl(get(), get()) }

    factory<RemoteUserDataSource> { RemoteUserDataSourceImpl(get(), get()) }

    factory<RemoteRestaurantDataSource> { RemoteRestaurantDataSourceImpl(get(), get()) }
}