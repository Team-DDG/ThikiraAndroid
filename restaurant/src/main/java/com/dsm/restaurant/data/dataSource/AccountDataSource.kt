package com.dsm.restaurant.data.dataSource

interface AccountDataSource {

    suspend fun register(body: Any)

    suspend fun unregister()

    suspend fun changePassword(newPassword: String)
}