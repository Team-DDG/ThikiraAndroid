package com.dsm.restaurant.domain.repository

interface AccountRepository {

    suspend fun register(body: Any)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)
}