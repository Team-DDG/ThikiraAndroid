package com.example.model.repository

interface AccountRepository {
    suspend fun register(body: Any)

    suspend fun unregister()

    suspend fun changePassWord(password: String)
}