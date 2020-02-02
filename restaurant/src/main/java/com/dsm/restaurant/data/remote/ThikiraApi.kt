package com.dsm.restaurant.data.remote

import com.dsm.restaurant.data.remote.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ThikiraApi {

    @GET("check_email")
    suspend fun checkEmail(@Query("email") email: String)

    @POST("sign_up")
    suspend fun register(@Body body: Any)

    @GET("auth")
    suspend fun authToken()

    @POST("auth/login")
    suspend fun login(@Body body: Any): TokenDto

}
