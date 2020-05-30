package com.example.api

import com.example.api.response.TokenResponse
import com.example.model.User
import retrofit2.http.*

interface ThikiraApi {
    @POST("create")
    suspend fun register(@Body body: User)

    @DELETE("leave")
    suspend fun leaveThikira(@Header("Authorization") token: String)

    @PATCH("password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body password: String
    )

    /**
     * auth
     */
    @GET("auth")
    suspend fun confirmAccessToken(@Header("Authorization") token: String)

    @GET("auth/email")
    suspend fun confirmEmail(@Query("email") email: String)

    @POST("auth/password")
    suspend fun confirmPassword(
        @Header("Authorization") token: String,
        @Body password: String
    )

    @POST("auth/sing_in")
    suspend fun login(@Body body: User): TokenResponse
}