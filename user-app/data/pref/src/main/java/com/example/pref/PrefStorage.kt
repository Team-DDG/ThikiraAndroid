package com.example.pref

interface PrefStorage {
    fun setAccessToken(accessToken: String)

    fun getAccessToken(): String

    fun deleteAccessToken()

    fun setRefreshToken(refreshToken: String)

    fun getRefreshToken(): String

    fun deleteRefreshToken()
}