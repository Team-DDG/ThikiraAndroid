package com.dsm.api

import com.dsm.api.response.*
import retrofit2.http.*
import java.util.*

interface ThikiraApi {

    @GET("/api/restaurant")
    suspend fun getRestaurantInfo(): RestaurantResponse

    @POST("create")
    suspend fun register(@Body body: Any)

    @DELETE("leave")
    suspend fun unregister()

    @GET("auth")
    suspend fun authToken()

    @FormUrlEncoded
    @PATCH("password")
    suspend fun changePassword(@Field("password") newPassword: String)

    @FormUrlEncoded
    @PATCH("address")
    suspend fun changeAddress(
        @Field("address") address: String,
        @Field("road_address") roadAddress: String
    )

    /**
     * auth
     */
    @FormUrlEncoded
    @POST("auth/password")
    suspend fun confirmPassword(@Field("password") password: String)

    @GET("auth/email")
    suspend fun confirmEmailDuplication(@Query("email") email: String)

    @POST("auth/sign_in")
    suspend fun login(@Body body: Any): TokenResponse

    /**
     * menu
     */
    @GET("menu")
    suspend fun getMenus(@Query("mc_id") menuCategoryId: Int): List<MenuResponse>

    @GET("menu/category")
    suspend fun getMenuCategories(): List<MenuCategoryResponse>

    @POST("menu")
    suspend fun uploadMenu(@Body body: Any)

    @DELETE("menu/{m_id}")
    suspend fun deleteMenu(@Path("m_id") menuId: Int)

    /**
     * menu category
     */
    @DELETE("menu/category/{mc_ids}")
    suspend fun deleteMenuCategory(@Path("mc_ids") menuCategoryIds: Int)

    @FormUrlEncoded
    @PATCH("menu/category")
    suspend fun updateMenuCategoryName(
        @Field("mc_id") menuCategoryId: Int,
        @Field("name") name: String
    )

    @FormUrlEncoded
    @POST("menu/category")
    suspend fun addMenuCategory(@Field("name") name: String): Map<String, Int>

    /**
     * coupon
     */
    @POST("coupon")
    suspend fun issueCoupon(@Body body: Any)

    @GET("coupon")
    suspend fun getCoupons(): List<CouponResponse>

    /**
     * order
     */
    @GET("order")
    suspend fun getOrders(@Query("date") date: Date): List<OrderResponse>
}
