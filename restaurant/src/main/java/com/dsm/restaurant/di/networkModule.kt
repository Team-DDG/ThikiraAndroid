package com.dsm.restaurant.di

import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.error.ErrorHandlerImpl
import com.dsm.restaurant.data.remote.ThikiraApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.86:1234/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(ThikiraApi::class.java)
    }

    single<ErrorHandler> { ErrorHandlerImpl() }
}