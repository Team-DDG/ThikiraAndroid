package com.example.firebase.di

import com.example.firebase.FirebaseAuthSource
import com.example.firebase.FirebaseAuthSourceImpl
import org.koin.dsl.module

val firebaseModule = module {
    factory<FirebaseAuthSource> { FirebaseAuthSourceImpl() }
}