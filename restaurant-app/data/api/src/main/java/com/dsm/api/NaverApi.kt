package com.dsm.api

import com.dsm.api.response.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {

    @GET("v1/search/local.json")
    suspend fun searchAddress(@Query("query") query: String): AddressResponse
}