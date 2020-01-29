package com.dsm.restaurant.domain.repository

interface AuthRepository {

    suspend fun login(body: Any)
}