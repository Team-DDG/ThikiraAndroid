package com.dsm.restaurant.domain.repository

interface AuthRepository {

    suspend fun authToken()

    suspend fun confirmPassword(password: String)

    suspend fun login(body: Any)
}