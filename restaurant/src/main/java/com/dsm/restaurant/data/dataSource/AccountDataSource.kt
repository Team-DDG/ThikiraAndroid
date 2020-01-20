package com.dsm.restaurant.data.dataSource

interface AccountDataSource {

    suspend fun checkEmail(email: String)
}