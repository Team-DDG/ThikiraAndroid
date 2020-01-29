package com.dsm.restaurant.data.local

interface PrefStorage {

    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String

    fun deleteAccessToken()

    fun setRefreshToken(refreshToken: String)

    fun getRefreshToken(): String

    fun deleteRefreshToken()
}