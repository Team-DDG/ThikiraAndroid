package com.example.api.datasource

import com.example.model.User

interface RemoteAccountDataSource {
    suspend fun register(body: User)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)

    suspend fun confirmEmail(email: String)

    suspend fun confirmPassword(password: String)

    suspend fun login(body: User)
}