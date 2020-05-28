package com.example.api.di

import com.example.api.ThikiraApi
import com.example.api.TokenApi
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
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ThikiraApi::class.java)
    }
}