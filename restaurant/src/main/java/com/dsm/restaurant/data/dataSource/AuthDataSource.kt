package com.dsm.restaurant.data.dataSource

import com.dsm.restaurant.data.remote.dto.TokenDto

interface AuthDataSource {
    suspend fun login(body: Any): TokenDto
}