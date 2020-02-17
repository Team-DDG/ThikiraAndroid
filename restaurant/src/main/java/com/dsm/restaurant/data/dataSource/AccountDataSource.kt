package com.dsm.restaurant.data.dataSource

interface AccountDataSource {

    suspend fun confirmEmailDuplication(email: String)

    suspend fun register(body: Any)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)
}