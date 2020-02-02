package com.dsm.restaurant.domain.repository

interface AuthRepository {

    suspend fun authToken()

    suspend fun login(body: Any)
}