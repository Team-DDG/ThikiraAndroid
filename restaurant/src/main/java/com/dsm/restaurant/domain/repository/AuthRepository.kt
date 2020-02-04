package com.dsm.restaurant.domain.repository

interface AuthRepository {

    suspend fun authToken()

    suspend fun authPassword(password: String)

    suspend fun login(body: Any)
}