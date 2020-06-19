package com.example.api

import com.example.api.response.TokenResponse
import com.example.api.response.UserResponse
import com.example.model.Event
import com.example.model.Restaurant
import retrofit2.http.*

interface ThikiraApi {
    @GET("/")
    suspend fun getUserInfo(): UserResponse

    @POST("create")
    suspend fun register(@Body body: Any)

    @DELETE("leave")
    suspend fun leaveThikira()

    @FormUrlEncoded
    @PATCH("password")
    suspend fun changePassword(@Field("password") password: String)

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

    @FormUrlEncoded
    @POST("auth/password")
    suspend fun confirmPassword(@Field("password") password: String)

    @POST("auth/sign_in")
    suspend fun login(@Body body: Any): TokenResponse

    /**
     * restaurant
     */

    @GET("restaurant")
    suspend fun getRestaurantList(
        @Query("sort_option") sortOption: String,
        @Query("category") category: String
    ): List<Restaurant>

    /**
     * event
     */

    @GET("event")
    suspend fun getEventList(): List<Event>
}