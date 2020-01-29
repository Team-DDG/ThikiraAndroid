package com.dsm.restaurant.data.remote

import com.dsm.restaurant.data.remote.dto.AddressDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {

    @GET("v1/search/local.json")
    suspend fun searchAddress(@Query("query") query: String): AddressDto
}