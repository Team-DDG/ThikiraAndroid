package com.dsm.restaurant.data.remote

import com.dsm.restaurant.data.remote.dto.MenuCategoryDto
import com.dsm.restaurant.data.remote.dto.MenuDto
import com.dsm.restaurant.data.remote.dto.RestaurantDto
import com.dsm.restaurant.data.remote.dto.TokenDto
import retrofit2.http.*

interface ThikiraApi {

    @GET("/")
    suspend fun getRestaurantInfo(): RestaurantDto

    @GET("check_email")
    suspend fun checkEmail(@Query("email") email: String)

    @POST("sign_up")
    suspend fun register(@Body body: Any)

    @DELETE("leave")
    suspend fun unregister()

    @GET("auth")
    suspend fun authToken()

    @FormUrlEncoded
    @PATCH("password")
    suspend fun changePassword(@Field("password") newPassword: String)

    @GET("auth/password")
    suspend fun authPassword(@Query("password") password: String)

    @POST("auth/login")
    suspend fun login(@Body body: Any): TokenDto

    @GET("menu")
    suspend fun getMenuList(@Query("menu_category_id") menuCategoryId: Int): List<MenuDto>

    @GET("menu/category")
    suspend fun getMenuCategoryList(): List<MenuCategoryDto>

    @DELETE("menu/category/{mc_ids}")
    suspend fun deleteMenuCategoryList(@Path("mc_ids") menuCategoryIds: List<Int>)
}
