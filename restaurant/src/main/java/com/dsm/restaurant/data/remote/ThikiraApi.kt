package com.dsm.restaurant.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ThikiraApi {

    @GET("check_email")
    suspend fun checkEmail(@Query("email") email: String)

    @POST("sign_up")
    suspend fun register(@Body body: Any)
}