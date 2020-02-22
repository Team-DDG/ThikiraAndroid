package com.dsm.restaurant.data.remote

import com.dsm.restaurant.data.remote.dto.MenuCategoryDto
import com.dsm.restaurant.data.remote.dto.MenuDto
import com.dsm.restaurant.data.remote.dto.RestaurantDto
import com.dsm.restaurant.data.remote.dto.TokenDto
import retrofit2.http.*

interface ThikiraApi {

    @GET("/")
    suspend fun getRestaurantInfo(): RestaurantDto

    @POST("create")
    suspend fun register(@Body body: Any)

    @DELETE("leave")
    suspend fun unregister()

    @GET("auth")
    suspend fun authToken()

    @FormUrlEncoded
    @PATCH("password")
    suspend fun changePassword(@Field("password") newPassword: String)

    /**
     * auth
     */
    @GET("auth/password")
    suspend fun confirmPassword(@Query("password") password: String)

    @GET("auth/email")
    suspend fun confirmEmailDuplication(@Query("email") email: String)

    @POST("auth/sign_in")
    suspend fun login(@Body body: Any): TokenDto

    /**
     * menu
     */
    @GET("menu")
    suspend fun getMenuList(@Query("mc_id") menuCategoryId: Int): List<MenuDto>

    @GET("menu/category")
    suspend fun getMenuCategoryList(): List<MenuCategoryDto>

    @POST("menu")
    suspend fun uploadMenu(@Body body: Any)

    /**
     * menu category
     */
    @DELETE("menu/category/{mc_ids}")
    suspend fun deleteMenuCategoryList(@Path("mc_ids") menuCategoryIds: List<Int>)

    @FormUrlEncoded
    @PATCH("menu/category")
    suspend fun updateMenuCategory(
        @Field("mc_id") menuCategoryId: Int,
        @Field("name") name: String
    )
}
