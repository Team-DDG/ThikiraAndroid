package com.example.base

import android.app.Application
import com.example.account.di.accountModule
import com.example.api.di.apiModule
import com.example.di.repositoryModule
import com.example.firebase.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

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

                    accountModule
                )
            )
        }
    }
}