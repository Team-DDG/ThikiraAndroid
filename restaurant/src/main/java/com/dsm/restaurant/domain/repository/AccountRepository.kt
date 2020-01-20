package com.dsm.restaurant.domain.repository

interface AccountRepository {

    suspend fun checkEmail(email: String)
}