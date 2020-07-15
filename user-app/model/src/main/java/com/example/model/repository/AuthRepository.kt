package com.example.model.repository

interface AuthRepository {
    suspend fun confirmAccessToken()

    suspend fun confirmEmail(email: String)

    suspend fun confirmPassword(password: String)

    suspend fun login(body: Any)

    fun checkLogin(): Boolean

    fun logout()
}