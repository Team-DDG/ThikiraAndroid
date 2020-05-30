package com.example.api.di

import com.example.api.ThikiraApi
import com.example.api.TokenApi
import com.example.api.interceptor.TokenInterceptor
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

    single<PrefStorage> { PrefStorageImpl(get()) }

    //TODO: add factory for dependency injection to data source impl
}