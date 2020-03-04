package com.dsm.firebase.di

import com.dsm.firebase.FirebaseStorageSource
import com.dsm.firebase.FirebaseStorageSourceImpl
import org.koin.dsl.module

val firebaseModule = module {

    factory<FirebaseStorageSource> { FirebaseStorageSourceImpl() }
}