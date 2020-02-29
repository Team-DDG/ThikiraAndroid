package com.dsm.base

import android.app.Application
import com.dsm.api.di.apiModule
import com.dsm.base.di.accountModule
import com.dsm.base.di.addressModule
import com.dsm.base.di.authModule
import com.dsm.firebase.di.firebaseModule
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
                    firebaseModule,

                    authModule,
                    addressModule,
                    accountModule
                )
            )
        }
    }
}