package com.example.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TokenApi {
    @GET("auth/refresh")
    fun refreshToken(@Header("X--Refresh-Token") token: String): Call<Map<String, String>>
}