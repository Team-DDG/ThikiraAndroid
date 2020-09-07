package com.example.base

import android.app.Application
import com.example.main.di.mainModule
import com.example.account.di.accountModule
import com.example.api.di.apiModule
import com.example.di.repositoryModule
import com.example.firebase.di.firebaseModule
import com.example.restaurant.di.restaurantModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    apiModule,
                    repositoryModule,
                    firebaseModule,

                    accountModule,
                    mainModule,
                    restaurantModule
                )
            )
        }
    }
}