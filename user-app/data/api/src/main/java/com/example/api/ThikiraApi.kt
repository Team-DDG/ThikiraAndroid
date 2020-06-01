package com.example.api

import com.example.api.response.TokenResponse
import com.example.api.response.UserResponse
import com.example.model.User
import retrofit2.http.*

interface ThikiraApi {
    @GET("/")
    suspend fun getUserInfo(): UserResponse

    @POST("create")
    suspend fun register(@Body body: Any)

    @DELETE("leave")
    suspend fun leaveThikira()

    @PATCH("password")
    suspend fun changePassword(@Body password: String)

    @FormUrlEncoded
    @PATCH("address")
    suspend fun changeAddress(
        @Field("add_parcel") address: String,
        @Field("add_street") roadAddress: String
    )

    /**
     * auth
     */
    @GET("auth")
    suspend fun confirmAccessToken()

    @GET("auth/email")
    suspend fun confirmEmail(@Query("email") email: String)

    @POST("auth/password")
    suspend fun confirmPassword(@Body password: String)

    @GET("auth/refresh")
    suspend fun refreshToken(): TokenResponse

    @POST("auth/sing_in")
    suspend fun login(@Body body: Any): TokenResponse
}