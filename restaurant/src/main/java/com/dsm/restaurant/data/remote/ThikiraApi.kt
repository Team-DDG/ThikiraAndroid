package com.dsm.restaurant.data.remote

import com.dsm.restaurant.data.remote.dto.TokenDto
import retrofit2.http.*

interface ThikiraApi {

    @GET("check_email")
    suspend fun checkEmail(@Query("email") email: String)

    @POST("sign_up")
    suspend fun register(@Body body: Any)

    @DELETE("leave")
    suspend fun unregister()

    @GET("auth")
    suspend fun authToken()

    @GET("auth/password")
    suspend fun authPassword(@Query("password") password: String)

    @POST("auth/login")
    suspend fun login(@Body body: Any): TokenDto


}
