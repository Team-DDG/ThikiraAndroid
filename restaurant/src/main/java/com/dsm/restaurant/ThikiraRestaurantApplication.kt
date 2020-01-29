package com.dsm.restaurant

import android.app.Application
import com.dsm.restaurant.di.account.accountModule
import com.dsm.restaurant.di.auth.authModule
import com.dsm.restaurant.di.localModule
import com.dsm.restaurant.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ThikiraRestaurantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ThikiraRestaurantApplication)
            modules(
                listOf(
                    networkModule,
                    localModule,

                    accountModule,
                    authModule
                )
            )
        }
    }
}