package com.dsm.base

import android.app.Application
import com.dsm.account.di.accountModule
import com.dsm.address.di.addressModule
import com.dsm.api.di.apiModule
import com.dsm.coupon.di.couponModule
import com.dsm.db.di.dbModule
import com.dsm.di.repositoryModule
import com.dsm.firebase.di.firebaseModule
import com.dsm.menu.di.menuModule
import com.dsm.order.di.orderModule
import com.dsm.restaurant.di.restaurantModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    // data
                    apiModule,
                    firebaseModule,
                    dbModule,
                    repositoryModule,

                    // feature
                    accountModule,
                    addressModule,
                    menuModule,
                    couponModule,
                    restaurantModule,
                    orderModule
                )
            )
        }
    }
}