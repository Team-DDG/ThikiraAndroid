package com.dsm.model.repository

interface AuthRepository {

    suspend fun authToken()

    suspend fun confirmEmailDuplication(email: String)

    suspend fun login(body: Any)

    suspend fun confirmPassword(password: String)
}