package com.dsm.restaurant.di

import com.dsm.restaurant.ThikiraRestaurantApplication
import com.dsm.restaurant.data.error.ErrorHandler
import com.dsm.restaurant.data.error.ErrorHandlerImpl
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.data.firebase.FirebaseSourceImpl
import com.dsm.restaurant.data.remote.NaverApi
import com.dsm.restaurant.data.remote.NaverInterceptor
import com.dsm.restaurant.data.remote.ThikiraApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl((androidContext() as ThikiraRestaurantApplication).getApiUrl())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ThikiraApi::class.java)
    }

    factory {
        Retrofit.Builder()
            .baseUrl((androidContext() as ThikiraRestaurantApplication).getNaverApiUrl())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(NaverInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NaverApi::class.java)
    }

    single<ErrorHandler> { ErrorHandlerImpl() }

    factory<FirebaseSource> { FirebaseSourceImpl() }
}