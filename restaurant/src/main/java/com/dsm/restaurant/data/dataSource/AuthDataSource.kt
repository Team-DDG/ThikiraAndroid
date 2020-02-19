package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.remote.dto.TokenDto

interface AuthDataSource {

    suspend fun authToken()

    suspend fun confirmEmailDuplication(email: String)

    suspend fun login(body: Any): TokenDto

    suspend fun confirmPassword(password: String)
}