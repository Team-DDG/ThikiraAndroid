package com.dsm.restaurant.domain.repository

interface AccountRepository {

    suspend fun confirmEmailDuplication(email: String)

    suspend fun register(body: Any)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)
}